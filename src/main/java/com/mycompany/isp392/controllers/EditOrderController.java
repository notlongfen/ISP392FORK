
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.order.ManageOrderDTO;
import com.mycompany.isp392.order.OrderDAO;
import com.mycompany.isp392.product.ManageProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.user.UserDTO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditOrderController", urlPatterns = {"/EditOrderController"})
public class EditOrderController extends HttpServlet {

    private static final String ERROR = "GetOrderInforController";
    private static final String SUCCESS = "GetOrderListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int status = Integer.parseInt(request.getParameter("status"));

            int oldStatus = Integer.parseInt(request.getParameter("oldStatus"));

            OrderDAO orderDAO = new OrderDAO();
            boolean check = orderDAO.editOrderStatus(orderID, status);
            if (check) {
                url = "SendMailServlet";
                request.setAttribute("REQFROM", "Update_Order_Status");
                request.setAttribute("MESSAGE", "Order status updated successfully! With status" + status);
                request.setAttribute("status", status);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher(url).include(request, response);

                List<String> oldList = new ArrayList<>();
                List<String> newList = new ArrayList<>();

                if (oldStatus != status) {
                    oldList.add("Status: " + String.valueOf(oldStatus));
                    newList.add("Status: " + String.valueOf(status));
                }

                if (oldList.size() > 0 && newList.size() > 0) {
                    UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                    ManageOrderDTO manageOrder = null;
                    if (oldStatus == 1 && status == 0) {
                        manageOrder = new ManageOrderDTO(orderID, user.getUserID(), oldList, newList, "Delete");
                    } else {
                        manageOrder = new ManageOrderDTO(orderID, user.getUserID(), oldList, newList, "Edit");
                    }
                    DbUtils.addCheckLogToDB("ManageOrders", "OrderID", manageOrder);
                    
                }

            }
            if (check) {
                request.setAttribute("SUCCESS_MESSAGE", "Order status updated successfully!");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateOrderController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
