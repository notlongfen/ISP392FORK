/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.ChildrenCategoryDTO;
import com.mycompany.isp392.order.OrderDAO;
import com.mycompany.isp392.order.OrderDetailsDTO;
import com.mycompany.isp392.order.OrderError;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.user.CustomerDTO;
import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QNhu
 */
@WebServlet(name = "ViewUSOrderController", urlPatterns = {"/ViewUSOrderController"})
public class ViewUSOrderController extends HttpServlet {

    private static final String ERROR = "US_index.jsp";
    private static final String SUCCESS = "US_MyOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        OrderError error = new OrderError();
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        try {
            CustomerDTO cust = (CustomerDTO) session.getAttribute("LOGIN_USER");
            int custID = cust.getUserID();
            session.setAttribute("custID", custID);
            List<ProductDetailsDTO> product = new ArrayList<>();
            List<ChildrenCategoryDTO> category = new ArrayList<>();
            List<OrderDetailsDTO> order = new ArrayList<>();

            if (cust != null) {
                OrderDAO orderDao = new OrderDAO();
                order = orderDao.viewOrder(custID);
                if (order != null) {
                    for (OrderDetailsDTO orderDetailsDTO : order) {
                        int productID = orderDetailsDTO.getProductID();
                        product = orderDao.viewProductDetailsInOrder(productID);
                        category = orderDao.viewCateOfProduct(productID);
                    }
                }
            }
            request.setAttribute("PRODUCT", product);
            request.setAttribute("CATEGORY", category);
            request.setAttribute("ORDER", order);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ViewUSOrderController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
