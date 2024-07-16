package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteProductController", urlPatterns = {"/DeleteProductController"})
public class DeleteProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productID = Integer.parseInt(request.getParameter("id"));
            ProductDAO productDAO = new ProductDAO();
            boolean check = productDAO.deleteProduct(productID) && productDAO.deleteProductDetails(productID);
            if (check) {
                request.setAttribute("SUCCESS_MESSAGE", "Product deleted successfully!");
                url = SUCCESS;
            }
        } catch (SQLException e) {
            log("Error at DeleteProductController: " + e.toString());
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
