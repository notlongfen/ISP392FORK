package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetProductsController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADD_PRODUCT_DETAILS_PAGE = "AddProductDetails.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> productList = productDAO.getAllProducts();
            request.setAttribute("PRODUCT_LIST", productList);
            url = ADD_PRODUCT_DETAILS_PAGE;
        } catch (SQLException e) {
            log("Error at GetProductsController: " + e.toString());
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
