package com.mycompany.isp392.controllers;

import com.mycompany.isp392.cart.CartDAO;
import com.mycompany.isp392.product.ManageProductDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.user.UserDTO;

import net.coobird.thumbnailator.Thumbnails;
import utils.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@MultipartConfig
public class EditProductDetailsController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String ERROR = "GetSpecificProductController";
    private static final String SUCCESS = "GetProductsController";
    private static final int IMAGE_WIDTH = 500; // Set desired image width
    private static final int IMAGE_HEIGHT = 500; // Set desired image height

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            // Retrieve product details information
            int productDetailID = Integer.parseInt(request.getParameter("productDetailID"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date importDate = Date.valueOf(request.getParameter("importDate"));
            int detailStatus = Integer.parseInt(request.getParameter("detailStatus"));

            int oldStockQuantity = Integer.parseInt(request.getParameter("oldStockQuantity"));
            int oldPrice = Integer.parseInt(request.getParameter("oldPrice"));
            Date oldImportDate = Date.valueOf(request.getParameter("oldImportDate"));
            int oldDetailStatus = Integer.parseInt(request.getParameter("oldDetailStatus"));

            Collection<Part> fileParts = request.getParts();
            StringBuilder imagePathBuilder = new StringBuilder();

            ProductDAO productDAO = new ProductDAO();
            CartDAO cartDAO = new CartDAO();
            ProductDetailsDTO existingProductDetail = productDAO.selectProductDetailByID(productDetailID);
            String existingImage = existingProductDetail.getImage();
            String oldImage = existingProductDetail.getImage();
            int existingPrice = existingProductDetail.getPrice();

            for (Part filePart : fileParts) {
                if (filePart.getName().equals("imageUpload") && filePart.getSize() > 0) {
                    String path = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(path);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String imagePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    File outputFile = new File(path + File.separator + fileName);

                    // Resize the image
                    Thumbnails.of(filePart.getInputStream())
                            .size(IMAGE_WIDTH, IMAGE_HEIGHT)
                            .toFile(outputFile);

                    if (imagePathBuilder.length() > 0) {
                        imagePathBuilder.append(";");
                    }
                    imagePathBuilder.append(imagePath);
                }
            }

            String imagePaths = imagePathBuilder.toString();
            if (imagePaths.isEmpty()) {
                imagePaths = existingImage;
            }

            List<Integer> listCart = cartDAO.getCartsByProduct(productDetailID);
            if (existingPrice != price && !listCart.isEmpty()) {
                boolean checkCartDetails = cartDAO.updateCartDetailsPrice(productDetailID, price);
                boolean checkAllCarts = true;
                for (int cartID : listCart) {
                    boolean checkCart = cartDAO.updateCart(cartID);
                    if (!checkCart) {
                        checkAllCarts = false;
                        break;
                    }
                }
                if (!checkCartDetails && !checkAllCarts) {
                    request.setAttribute("ERROR_MESSAGE", "Failed to update product details.");
                }
            }

            boolean checkProductDetails = productDAO.editProductDetails(productDetailID, stockQuantity, price, importDate, imagePaths, detailStatus);

            if (checkProductDetails) {
                List<String> FieldOld = new ArrayList<>();
                List<String> FieldNew = new ArrayList<>();

                if (oldStockQuantity != stockQuantity) {
                    FieldOld.add(String.valueOf(oldStockQuantity));
                    FieldNew.add(String.valueOf(stockQuantity));
                }
                if (oldPrice != price) {
                    FieldOld.add(String.valueOf(oldPrice));
                    FieldNew.add(String.valueOf(price));
                }
                if (!oldImportDate.equals(importDate)) {
                    FieldOld.add(String.valueOf(oldImportDate));
                    FieldNew.add(String.valueOf(importDate));
                }
                if (oldDetailStatus != detailStatus) {
                    FieldOld.add(String.valueOf(oldDetailStatus));
                    FieldNew.add(String.valueOf(detailStatus));
                }
                
                if (oldImage != imagePaths) {
                    FieldOld.add(String.valueOf(oldImage));
                    FieldNew.add(String.valueOf(imagePaths));
                }

                if (FieldOld.size() > 0 && FieldNew.size() > 0) {
                    String action = request.getParameter("edit");
                    UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                    int empID = user.getUserID();
                    boolean isProductDetails = true;
//                    ManageProductDTO manage = new ManageProductDTO(productDetailID, empID, FieldOld, FieldNew, action, isProductDetails);
                    ManageProductDTO manage = new ManageProductDTO(productDetailID, empID, FieldOld, FieldNew, action, isProductDetails);
                    DbUtils.addCheckLogToDB("OverseeProductDetail", "ProductDetailsID", manage);
                    request.setAttribute("SUCCESS_MESSAGE", "Product details updated successfully!");
                    request.setAttribute("newProductID", existingProductDetail.getProductID());
                    url = SUCCESS;
                }

            } else {
                request.setAttribute("ERROR_MESSAGE", "Failed to update product details.");
            }
        } catch (SQLException e) {
            log("Error at EditProductDetailsController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "Error updating product details: " + e.getMessage());
        } catch (NumberFormatException e) {
            log("Error at EditProductDetailsController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "Invalid number format: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "EditProductDetailsController";
    }
}
