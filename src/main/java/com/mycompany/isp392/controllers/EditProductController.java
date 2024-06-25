package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "EditProductController", urlPatterns = {"/EditProductController"})

public class EditProductController extends HttpServlet {

    private static final String ERROR = "product.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        boolean checkValidation = true;
        ProductDAO productDAO = new ProductDAO();
        try {
            String productID = request.getParameter("productID");
            String newName = request.getParameter("newName");
            String newDescription = request.getParameter("newDescription");

            if (productDAO.checkProductExists(newName)) {
                productError.setProductNameError("Product already exists.");
                checkValidation = false;
            }

            if (checkValidation) {
                boolean check = productDAO.editProduct(productID, newName, newDescription);
                if (check) {
                    request.setAttribute("MESSAGE", "INFORMATION UPDATED SUCCESSFULLY !");
                    url = SUCCESS;
                }
            } else {
                productError.setError("UNABLE TO UPDATE INFORMATION !");
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (SQLException e) {
            log("Error at EditProductController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "Error updating product: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
