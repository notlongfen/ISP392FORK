/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.mycompany.isp392.support.SupportDAO;
import com.mycompany.isp392.user.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "RequestSupportController", urlPatterns = { "/RequestSupportController" })
public class RequestSupportController extends HttpServlet {
    private static final String ERROR = "US_RequestSupport.jsp";
    private static final String SUCCESS = "US_index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        String url = ERROR;
//        try {
//            String customerEmail = request.getParameter("email");
//            String title = request.getParameter("title");
//            String content = request.getParameter("content");
//            SupportDAO supportDAO = new SupportDAO();
//            boolean check = supportDAO.insertToSupport(customerEmail, title, content);
//            if (check) {
//                request.getRequestDispatcher(url).include(request, response);
//                url = SUCCESS;
//            }
//        } catch (Exception e) {
//            log("Error at RequestSupportController: " + e.toString());
//
//        } finally {
//            request.getRequestDispatcher(url).forward(request, response);
//        }
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
        String url = ERROR;
        try {
            String customerEmail = request.getParameter("email");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            SupportDAO supportDAO = new SupportDAO();
            boolean check = supportDAO.insertToSupport(customerEmail, title, content);
            if (check) {
//                url = "SendMailServlet";
////                request.setAttribute("action", "RequestSupportController");
//                request.getRequestDispatcher(url).include(request, response);
                url = SUCCESS;
                request.setAttribute("MESSAGE", "We have received your request! Have a wonderful shopping experience");
            }else{
                request.setAttribute("ERROR", "Something went wrong with you");
            }
        } catch (Exception e) {
            log("Error at RequestSupportController: " + e.toString());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
