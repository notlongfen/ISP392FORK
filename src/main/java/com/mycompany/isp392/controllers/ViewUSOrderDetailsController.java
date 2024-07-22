/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.mycompany.isp392.order.*;
import com.mycompany.isp392.user.*;
import com.mycompany.isp392.product.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "ViewUSOrderDetailsController", urlPatterns = {"/ViewUSOrderDetailsController"})
public class ViewUSOrderDetailsController extends HttpServlet {

    private static final String ERROR = "US_MyOrder.jsp";
    private static final String SUCCESS = "US_OrderDetails.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            CustomerDTO userDTO = (CustomerDTO) request.getSession().getAttribute("CUST");
            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            OrderDAO orderDAO = new OrderDAO();
            String orderID = request.getParameter("orderID");
            if (orderID != null) {
                int orderIDInt = Integer.parseInt(orderID);
                OrderDTO order = orderDAO.getOrderInfo(orderIDInt);
                List<OrderDetailsDTO> orderDetails = orderDAO.getListOrderDetailsByOrderID(orderIDInt);
                List<ManageOrderDTO> orderStatus = orderDAO.getListOrderStatusByOrderID(orderIDInt);
                List<OrderDetailsDTO> orderItems = orderDAO.getOrderItems(orderIDInt);
                double cartTotalPrice = orderDAO.getTotalPriceInCartByOrderID(orderIDInt);
                request.setAttribute("DETAILS_OF_ORDER", order);
                request.setAttribute("DETAILS_OF_ORDER_DETAILS", orderDetails);
                request.setAttribute("ITEMS_OF_ORDER", orderItems);
                request.setAttribute("STATUS_OF_ORDER", orderStatus);
                request.setAttribute("CART_TOTAL_PRICE", cartTotalPrice);
                url = SUCCESS;
            } else {
                int custID = user.getUserID();
                OrderDTO latestOrder = orderDAO.getRecentOrderOfCustomer(custID);
                List<OrderDetailsDTO> orderDetails = orderDAO.getLastOrderDetails(latestOrder.getOrderID());
                List<OrderDetailsDTO> orderItems = orderDAO.getOrderItems(latestOrder.getOrderID());
                double cartTotalPrice = orderDAO.getTotalPriceInCartByOrderID(latestOrder.getOrderID());
                request.setAttribute("DETAILS_OF_ORDER", latestOrder);
                request.setAttribute("DETAILS_OF_ORDER_DETAILS", orderDetails);
                request.setAttribute("ITEMS_OF_ORDER", orderItems);
                request.setAttribute("CART_TOTAL_PRICE", cartTotalPrice);
                url = SUCCESS;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
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
