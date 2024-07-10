package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.product.ProductError;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AddProductDetailsController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String ERROR = "GetProductsController";
    private static final String SUCCESS = "GetProductsController";
    private static final int IMAGE_WIDTH = 500; // Set desired image width
    private static final int IMAGE_HEIGHT = 500; // Set desired image height

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        boolean hasError = false;

        try {
            int productID = Integer.parseInt(request.getParameter("parentProductID"));
            String color = request.getParameter("color");
            String[] sizes = request.getParameterValues("sizes");
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date importDate = Date.valueOf(LocalDate.now()); // Set import date to today
            Collection<Part> fileParts = request.getParts();

            ProductDAO productDAO = new ProductDAO();
            boolean check = true;
            StringBuilder imagePathBuilder = new StringBuilder();

            // Validation
            if (color == null || color.isEmpty()) {
                productError.setColorError("Color is required.");
                hasError = true;
            }

            if (sizes == null || sizes.length == 0) {
                productError.setSizeError("Size is required.");
                hasError = true;
            }

            // Check for duplicates
            for (String size : sizes) {
                if (productDAO.checkDuplicateProductDetail(productID, color, size)) {
                    productError.setError("Duplicate product detail found for color: " + color + " and size: " + size);
                    hasError = true;
                    break;
                }
            }

            // Image upload logic
            for (Part filePart : fileParts) {
                if (filePart.getName().equals("images") && filePart.getSize() > 0) {
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

            if (hasError) {
                request.setAttribute("PRODUCT_ERROR", productError);
            } else {
                for (String size : sizes) {
                    ProductDetailsDTO productDetails = new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate, imagePaths, 1);
                    if (!productDAO.addProductDetails(productDetails)) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    request.setAttribute("newProductID", productID);
                    request.getSession().setAttribute("SUCCESS_MESSAGE", "Product added successfully!");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at AddProductDetailsController: " + e.toString());
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
        return "AddProductDetailsController";
    }
}
