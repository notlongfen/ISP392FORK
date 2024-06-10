/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String WELCOME = "login.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String REGISTER_PAGE = "Sign_Up";
    private static final String REGISTER_PAGE_VIEW = "signup.jsp";

    private static final String ADD_PRODUCT_PAGE = "Add_View";
    private static final String ADD_PRODUCT_PAGE_VIEW = "product.jsp";

    private static final String ADD_PRODUCT = "Ađd_Product";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";

    private static final String REGISTER = "Sign_In";
    private static final String REGISTER_CONTROLLER = "RegisterController";
    
    private static final String SENDMAIL = "Send Email";
    private static final String SEND_EMAIL_CONTROLLER = "SendMailServlet";
    
    private static final String SEARCH_SUPPORT = "Search support";
    private static final String SEARCH_SUPPORT_CONTROLLER = "SearchSupportController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (REGISTER_PAGE.equals(action)) {
                url = REGISTER_PAGE_VIEW;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;
            }else if (SENDMAIL.equals(action)){
                url = SEND_EMAIL_CONTROLLER;
            }else if (ADD_PRODUCT.equals(action)){
                url = ADD_PRODUCT_CONTROLLER;
            }

        } catch (Exception e) {
            log("error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
