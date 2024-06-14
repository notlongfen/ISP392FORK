/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.user.CustomerDTO;
import com.mycompany.isp392.user.EmployeeDTO;
import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import com.mycompany.isp392.user.UserError;
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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "AddManagerController", urlPatterns = {"/AddManagerController"})
public class AddManagerController extends HttpServlet {

  
   private static final String ERROR = "addManager.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO dao = new UserDAO();
        UserError userError = new UserError();
        boolean checkValidation = true;

        try {
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            int phone = Integer.parseInt(request.getParameter("phone"));
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            String position = request.getParameter("position");

            // Check email exists
            if (dao.checkEmailExists(email)) {
                userError.setEmailError("Email already exists.");
                checkValidation = false;
            }

            // Check phone exists
            if (dao.checkPhoneExists(phone)) {
                userError.setPhoneError("Phone number already exists.");
                checkValidation = false;
            }

            // Check phone length
            if (String.valueOf(phone).length() != 9 && String.valueOf(phone).length() != 10) {
                userError.setPhoneError("Phone number must be 9 or 10 digits.");
                checkValidation = false;
            }

            // Check password confirmation
            if (!password.equals(confirmPassword)) {
                userError.setConfirmError("Passwords do not match.");
                checkValidation = false;
            }

            // Insert new user
            if (checkValidation) {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                UserDTO newUser = new UserDTO(userName, email, hashedPassword, roleID, phone, 1);
                EmployeeDTO newEmployee = new EmployeeDTO(position);
                boolean checkAddManager = dao.addManager(newUser, newEmployee);

                if (checkAddManager) {
                    url = SUCCESS;
                } else {
                    userError.setError("Failed to add. Please try again.");
                    request.setAttribute("USER_ERROR", userError);
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at AddManagerController: " + e.toString());
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