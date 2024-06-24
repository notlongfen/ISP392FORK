package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "EditProductDetailsController", urlPatterns = {"/EditProductDetailsController"})

public class EditProductDetailsController extends HttpServlet {

    private static final String ERROR = "product.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            // Retrieve product details information
            int productID = Integer.parseInt(request.getParameter("productID"));
             String originalColor = request.getParameter("originalColor");
            String originalsize = request.getParameter("originalSize");
            String color = request.getParameter("color");
            String size = request.getParameter("size");
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date importDate = Date.valueOf(request.getParameter("importDate")); 
            String image = request.getParameter("image"); 
            int detailStatus = Integer.parseInt(request.getParameter("detailStatus"));

            ProductDAO productDAO = new ProductDAO();

            ProductDetailsDTO productDetails = new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate, image, detailStatus);
            boolean checkProductDetails = productDAO.editProductDetails(productDetails, originalColor, originalsize);

            if (checkProductDetails) {
                request.setAttribute("MESSAGE", "Product details updated successfully!");
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
