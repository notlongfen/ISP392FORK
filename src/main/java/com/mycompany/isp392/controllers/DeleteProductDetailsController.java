package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(name = "DeleteProductDetailsController", urlPatterns = {"/DeleteProductDetailsController"})
public class DeleteProductDetailsController extends HttpServlet {

    private static final String ERROR = "GetProductsController";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productDetailID = Integer.parseInt(request.getParameter("id"));
            int parentID = Integer.parseInt(request.getParameter("parentid"));
            ProductDAO productDAO = new ProductDAO();
            boolean check = productDAO.deleteProductDetail(productDetailID);
            if (check) {
                request.setAttribute("SUCCESS_MESSAGE", "Product detail deleted successfully!");
                request.setAttribute("newProductID", parentID);
                url = SUCCESS;
            }
        } catch (SQLException e) {
            log("Error at DeleteProductDetailsController: " + e.toString());
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
