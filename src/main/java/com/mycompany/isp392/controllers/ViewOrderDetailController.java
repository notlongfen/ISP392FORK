/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.support.*;
import com.mycompany.isp392.user.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "ViewSupportController", urlPatterns = {"/ViewSupportController"})
public class ViewOrderDetailController extends HttpServlet {

    private static final String ERROR = "AD_OrderList.jsp";
    private static final String SUCCESS = "AD_ViewOrderDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        OrderError error = new SupportError();
        UserDAO userDao = new UserDAO();
        SupportDAO supportDao = new SupportDAO();
        try {
            int supportID = Integer.parseInt(request.getParameter("supportID"));
            UserDTO user = userDao.getUserInfo(supportID);
            SupportDTO support = supportDao.getSupportInfo(supportID);
            ProcessSupportDTO process = supportDao.getInfoProcessSupport(supportID);
            if (user != null) {
                request.setAttribute("USER_SUPPORT", user);
                request.setAttribute("SUPPORT_DETAIL", support);
                request.setAttribute("PROCESS_SUPPORT", process);
                url = SUCCESS;
            } else {
                error.setError("UNABLE TO GET SUPPORT INFORMATION !");
                request.setAttribute("SUPPORT_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at ViewSupportController: " + e.toString());
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
