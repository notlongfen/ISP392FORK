package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.product.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
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
    private static final String ERROR = "CreateProductDetail.jsp";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            int productID = Integer.parseInt(request.getParameter("parentProductID"));
            String color = request.getParameter("color");
            String[] sizes = request.getParameterValues("sizes");
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date importDate = Date.valueOf(request.getParameter("importDate"));
            Collection<Part> fileParts = request.getParts();

            ProductDAO productDAO = new ProductDAO();
            boolean check = true;
            StringBuilder imagePathBuilder = new StringBuilder();

            for (Part filePart : fileParts) {
                if (filePart.getName().equals("images") && filePart.getSize() > 0) {
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

            for (String size : sizes) {
                ProductDetailsDTO productDetails = new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate, imagePaths, 1);
                if (!productDAO.addProductDetails(productDetails)) {
                    check = false;
                    break;
                }
            }

            if (check) {
                request.setAttribute("newProductID", productID);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AddProductDetailsController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return "";
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
