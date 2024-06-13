/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpSession;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final int US = 2;
    private static final String US_PAGE = "user.jsp";
    private static final int AD = 1;
    private static final String AD_PAGE = "admin.jsp";
    private String clientID;
    public void initClientID() throws ServletException{
        Dotenv dotenv = Dotenv.configure().directory("C://Users//tuan tran//Desktop//ISP392_Project//.env").load();
        clientID = dotenv.get("GOOGLE_CLIENT_ID");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        initClientID();
        request.setAttribute("CLIENT_ID", clientID);
        String url = ERROR;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(email, password);
            if (loginUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                int roleID = loginUser.getRoleID();
                if (US == roleID) {
                    url = US_PAGE;
                } else if (AD == roleID) {
                    url = AD_PAGE;
                } else {
                    request.setAttribute("ERROR", "Your role is not supported");
                }
            } else {
                request.setAttribute("ERROR", "Incorrect UserID or Password");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
