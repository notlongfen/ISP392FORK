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
@WebServlet(name = "EditEmployeeProfileController", urlPatterns = {"/EditEmployeeProfileController"})
public class EditEmployeeProfileController extends HttpServlet {

    private static final String ERROR = "AD_EditProfile.jsp";
    private static final String SYSTEM_MANAGER = "SearchUserController";
    private static final String SHOP_MANAGER_AND_STAFF = "GetProductsController";
    private static final String LOGIN = "US_SignIn.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError();
        UserDAO dao = new UserDAO();
        boolean checkValidation = true;
        boolean needRelogin = false;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            //call
            int userID = Integer.parseInt(request.getParameter("userID"));
            String userName = request.getParameter("userName");
            String oldEmail = request.getParameter("oldEmail");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            int oldPhone = Integer.parseInt(request.getParameter("oldPhone"));
            int phone = Integer.parseInt(request.getParameter("phone"));
            
            String oldPassword = dao.getHashedPassword(userID);
            int status = loginUser.getStatus();
            int roleID = loginUser.getRoleID();

            //error handling
            //check email exists
            if (!oldEmail.equals(email)) {
                needRelogin = true;
                if(dao.checkEmailExists(email) != -1){
                    error.setEmailError("Email already exists.");
                    checkValidation = false;
                }
            }
            //check if phone exists
            if (oldPhone != phone && dao.checkPhoneExists(phone)) {
                error.setPhoneError("Phone number already exists.");
                checkValidation = false;
            }
            //check if password and confirmation password matches
            if (!password.isEmpty() || !confirm.isEmpty()) {
                if (!password.equals(confirm)) {
                    error.setPasswordError("The 2 passwords don't match");
                    checkValidation = false;
                }
            }
            if (oldPassword == null){
                error.setPasswordError("Error connecting to database");
                checkValidation = false;
            }
            
            //hash password & execute
            if (checkValidation) {
                String hashedPassword = oldPassword;
                if(!password.isEmpty()){
                    hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    needRelogin = true;
                }
                UserDTO user = new UserDTO(userID, userName, email, hashedPassword, roleID, phone, status);
                boolean checkUpdate = dao.editEmployee(user);
 
                if (checkUpdate) {
                    if(needRelogin){
                        session.invalidate();
                        url = LOGIN;
                    } else {
                        loginUser.setUserName(userName);
                        loginUser.setPhone(phone);
                        session.setAttribute("LOGIN_USER", loginUser);
                        if(roleID == 1){
                            request.setAttribute("SUCCESS_MESSAGE", "Successfuly updated profile!");
                            url = SYSTEM_MANAGER;
                        } else {
                            request.setAttribute("SUCCESS_MESSAGE", "Successfuly updated profile!");
                            url= SHOP_MANAGER_AND_STAFF;
                        }
                    }
                } else {
                    error.setError("Unable to update database");
                    request.setAttribute("EDIT_ERROR", error);
                }
            } else {
                request.setAttribute("EDIT_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at EditEmployeeProfileController: " + e.toString());
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
