/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.user.*;
import com.mycompany.isp392.order.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "GetOrderInforController", urlPatterns = {"/GetOrderInforController"})
public class GetOrderInforController extends HttpServlet {

    private static final String ERROR = "AD_OrderList.jsp";
    private static final String SUCCESS = "AD_EditOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        OrderError error = new OrderError();
        OrderDAO orderDao = new OrderDAO();
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            OrderDTO order = orderDao.getOrderInfo(orderID);
            List<OrderDetailsDTO> orderDetailsList = orderDao.getListOrderDetailsByOrderID(orderID);
            if (order != null) {
                request.setAttribute("ORDER_INFOR", order);
                request.setAttribute("ORDER_DETAIL_LIST", orderDetailsList);
                url = SUCCESS;
            } else {
                error.setError("UNABLE TO GET ORDER INFORMATION !");
                request.setAttribute("ORDER_ERROR", order);
            }
        } catch (Exception e) {
            log("Error at GetOrderInforController: " + e.toString());
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
