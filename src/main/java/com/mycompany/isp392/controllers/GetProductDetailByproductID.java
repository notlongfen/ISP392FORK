package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetProductDetailByproductID extends HttpServlet {

    private static final String SUCCESS_PAGE = "US_ProductDetail.jsp";
    private static final String ERROR_PAGE = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;

        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            ProductDAO productDAO = new ProductDAO();
            ProductDTO product = productDAO.getProductByID(productID);

            if (product != null) {
                List<ProductDetailsDTO> productDetails = productDAO.getProductDetailsByProductID(productID);

                if (!productDetails.isEmpty()) {
                    request.setAttribute("productDetails", productDetails);
                    request.setAttribute("product", product);
                    url = SUCCESS_PAGE;
                } else {
                    request.setAttribute("error", "No product details found for this product.");
                }
            } else {
                request.setAttribute("error", "No product found with ID: " + productID);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Data conversion error. Please enter a valid product ID.");
            e.printStackTrace();
        } catch (SQLException e) {
            request.setAttribute("error", "Database query error.");
            e.printStackTrace();
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
        return "Servlet for retrieving product details by product ID";
    }

}
