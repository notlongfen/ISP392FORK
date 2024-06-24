package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddProductDetailsController extends HttpServlet {

    private static final String ERROR = "GetProductsController";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String color = request.getParameter("color");
            String[] sizes = request.getParameterValues("sizes");
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date importDate = Date.valueOf(request.getParameter("importDate"));
            String image = request.getParameter("image");
            int status = 1; // Default status

            ProductDAO productDAO = new ProductDAO();
           boolean check = true;

            for (String size : sizes) {
                ProductDetailsDTO productDetails = new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate, image, status);
                if (!productDAO.addProductDetails(productDetails)) {
                    check = false;
                    break;
                }
            }

            if (check) {
                url = SUCCESS;
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
