package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.Collection;

@MultipartConfig
public class EditProductDetailsController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String ERROR = "GetSpecificProductController";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            // Retrieve product details information
            int productDetailID = Integer.parseInt(request.getParameter("productDetailID"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date importDate = Date.valueOf(request.getParameter("importDate"));
            int detailStatus = Integer.parseInt(request.getParameter("detailStatus"));
            Collection<Part> fileParts =  request.getParts();
            StringBuilder imagePathBuilder = new StringBuilder();
            
            ProductDAO productDAO = new ProductDAO();
            ProductDetailsDTO existingProductDetail = productDAO.selectProductDetailByID(productDetailID);
            String existingImage = existingProductDetail.getImage();

            for (Part filePart : fileParts) {
                if (filePart.getName().equals("imageUpload") && filePart.getSize() > 0) {
                    String path = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(path);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String imagePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    filePart.write(path + File.separator + fileName);

                    if (imagePathBuilder.length() > 0) {
                        imagePathBuilder.append(";");
                    }
                    imagePathBuilder.append(imagePath);
                }
            }
            String imagePaths = imagePathBuilder.toString();
            if(imagePaths == null || imagePaths.isEmpty()){
                imagePaths = existingImage;
            }
            
            boolean checkProductDetails = productDAO.editProductDetails(productDetailID, stockQuantity, price, importDate, imagePaths, detailStatus);

            if (checkProductDetails) {
                request.setAttribute("SUCCESS_MESSAGE", "Product details updated successfully!");
                request.setAttribute("newProductID", existingProductDetail.getProductID());
                url = SUCCESS;
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
