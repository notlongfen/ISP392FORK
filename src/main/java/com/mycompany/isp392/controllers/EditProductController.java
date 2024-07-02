package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditProductController", urlPatterns = {"/EditProductController"})
public class EditProductController extends HttpServlet {

    private static final String ERROR = "GetSpecificProductController";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ProductError productError = new ProductError();
        boolean checkValidation = true;
        ProductDAO productDAO = new ProductDAO();
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int numberOfPurchasing = Integer.parseInt(request.getParameter("numberOfPurchasing"));
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            String[] categoryIDs = request.getParameterValues("categoryIDs");
            int status = Integer.parseInt(request.getParameter("status"));

            if (productDAO.checkProductExists(productName, productID)) {
                productError.setProductNameError("Product already exists.");
                checkValidation = false;
            }

            if (checkValidation) {
                boolean check = productDAO.editProduct(productID, productName, description, numberOfPurchasing, brandID, categoryIDs, status);
                if (check) {
                    request.getSession().setAttribute("SUCCESS_MESSAGE", "Product updated successfully!");
                    response.sendRedirect(SUCCESS);
                    return;
                } else {
                    productError.setError("UNABLE TO UPDATE INFORMATION!");
                    request.getSession().setAttribute("PRODUCT_ERROR", productError);
                }
            } else {
                request.getSession().setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            log("Error at EditProductController: " + e.toString());
            request.getSession().setAttribute("ERROR_MESSAGE", "Error updating product: " + e.getMessage());
        }
         request.getRequestDispatcher(ERROR).forward(request, response);   
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
