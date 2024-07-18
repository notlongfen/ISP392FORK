package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.*;
import com.mycompany.isp392.user.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DbUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteProductController", urlPatterns = {"/DeleteProductController"})
public class DeleteProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productID = Integer.parseInt(request.getParameter("id"));
            ProductDAO productDAO = new ProductDAO();
            boolean check = productDAO.deleteProduct(productID) && productDAO.deleteProductDetails(productID);
            if (check) {
                List<String>oldField = new ArrayList<>();
                UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                oldField.add(String.valueOf(productID));
                ManageProductDTO manageProductDTO = new ManageProductDTO(productID,user.getUserID(), oldField, new ArrayList<>(), "Delete"); 
                DbUtils.addCheckLogToDB("ManageProducts", "ProductID", manageProductDTO);
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
        try {
            processRequest(request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
