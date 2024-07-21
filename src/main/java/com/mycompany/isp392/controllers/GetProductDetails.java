package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "GetProductDetails", urlPatterns = {"/GetProductDetails"})
public class GetProductDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int productId = Integer.parseInt(request.getParameter("productID"));
        ProductDAO productDAO = new ProductDAO();
        Map<String, Map<String, Map<String, Object>>> colorSizeMap = productDAO.getProductDetailsByProductID2(productId);
        ProductDTO product = productDAO.selectProduct(productId);
        CategoryDTO category = productDAO.getCategoryByProductID(productId); // Get the category details

        request.setAttribute("colorSizeMap", colorSizeMap);
        request.setAttribute("product", product);
        request.setAttribute("category", category); // Set the category as a request attribute

        request.getRequestDispatcher("US_ProductDetail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetProductDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetProductDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Product Details Controller";
    }
}

