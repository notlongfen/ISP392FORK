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
import org.mindrot.jbcrypt.BCrypt;
import com.mycompany.isp392.user.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "EditEmployeeController", urlPatterns = {"/EditEmployeeController"})
public class EditEmployeeController extends HttpServlet {

    private static final String ERROR = "GetUserInfoController";
    private static final String SUCCESS = "SearchUserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError();
        UserDAO dao = new UserDAO();
        boolean checkValidation = true;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            //call
            int userID = Integer.parseInt(request.getParameter("userID"));
            String userName = request.getParameter("userName");
            String oldEmail = request.getParameter("oldEmail");
            String email = request.getParameter("email");
            String currentPassword = request.getParameter("currentPassword");
            String password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            int oldPhone = Integer.parseInt(request.getParameter("oldPhone"));
            int phone = Integer.parseInt(request.getParameter("phone"));
            int status = Integer.parseInt(request.getParameter("status"));
            
            //error handling
            //check email exists
            if (!oldEmail.equals(email) && dao.checkEmailExists(email) != -1) {
                error.setEmailError("Email already exists.");
                checkValidation = false;
            }
            //check if phone exists
            if (oldPhone != phone && dao.checkPhoneExists(phone)) {
                error.setPhoneError("Phone number already exists.");
                checkValidation = false;
            }
            if(status == 0 && loginUser.getUserID() == userID ){
                error.setUserIDError("You cannot delete your own account.");
                checkValidation = false;
            }

            //hash password & execute
            if (checkValidation) {
                String hashedPassword = currentPassword;
                if(!password.isEmpty()){
                    hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                }
                UserDTO user = new UserDTO(userID, userName, email, hashedPassword, roleID, phone, status);
                boolean checkUpdate = dao.editEmployee(user);

                if (checkUpdate) {
                    if(loginUser!=null && loginUser.getUserID() == userID){
                        loginUser.setUserName(userName);
                        loginUser.setEmail(email);
                        loginUser.setRoleID(roleID);
                        loginUser.setPhone(phone);
                        loginUser.setStatus(status);
                        session.setAttribute("LOGIN_USER", loginUser);
                    }
                    request.setAttribute("SUCCESS_MESSAGE", "EMPLOYEE EDITED SUCCESSFULLY !");
                    url = SUCCESS;
                } else {
                    error.setError("Unable to update database");
                    request.setAttribute("EDIT_ERROR", error);
                }
            } else {
                request.setAttribute("EDIT_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at EditEmployeeController: " + e.toString());
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
