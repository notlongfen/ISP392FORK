package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ManageProductDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.user.UserDTO;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteProductDetailsController", urlPatterns = {"/DeleteProductDetailsController"})
public class DeleteProductDetailsController extends HttpServlet {

    private static final String ERROR = "GetProductsController";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productDetailID = Integer.parseInt(request.getParameter("id"));
            int parentID = Integer.parseInt(request.getParameter("parentid"));
            ProductDAO productDAO = new ProductDAO();
            boolean check = productDAO.deleteProductDetail(productDetailID);
            if (check) {
                List<String> oldField = new ArrayList<>();
                List<String> newField = new ArrayList<>();
                UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                oldField.add("Status: " + String.valueOf(1));
                newField.add("Status: " + String.valueOf(0));
                boolean pdetail = true;
                ManageProductDTO manageProduct = new ManageProductDTO(productDetailID, user.getUserID(), oldField, newField, "Delete", pdetail);
                DbUtils.addCheckLogToDB("OverseeProductDetail", "ProductDetailsID", manageProduct);
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
