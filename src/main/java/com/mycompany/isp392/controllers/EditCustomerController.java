/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import com.mycompany.isp392.user.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "EditCustomerController", urlPatterns = {"/EditCustomerController"})
public class EditCustomerController extends HttpServlet {

    private static final String ERROR = "AD_EditCustomer.jsp";
    private static final String SUCCESS = "SearchUserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError();
        UserDAO dao = new UserDAO();
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            int userID = Integer.parseInt(request.getParameter("userID"));
            int status = Integer.parseInt(request.getParameter("status"));
            int oldStatus = Integer.parseInt(request.getParameter("oldStatus"));
            int empID = loginUser.getUserID();

            ManageUserDTO manage = null;

            boolean checkUpdate = dao.updateCustomerStatus(userID, status);
            if (checkUpdate) {
                List<String> oldList = new ArrayList<>();
                List<String> newList = new ArrayList<>();

                if (oldStatus != status) {
                    oldList.add(String.valueOf(oldStatus));
                    newList.add(String.valueOf(status));
                }

                if (!oldList.isEmpty() && !newList.isEmpty()) {
                    String action = request.getParameter("edit");
                    manage = new ManageUserDTO(userID, empID, oldList, newList, action);
                    boolean checkAdd = DbUtils.addCheckLogToDB("SuperviseCustomers", "UserID", manage);
                    if (checkAdd) {
                        request.setAttribute("SUCCESS_MESSAGE", "CUSTOMER EDITED SUCCESSFULLY !");
                        url = SUCCESS;
                    } else {
                        error.setError("Unable to update database");
                        request.setAttribute("EDIT_ERROR", error);
                    }
                }
            } else {
                error.setError("Unable to update database");
                request.setAttribute("EDIT_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at EditCustomerController: " + e.toString());
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
