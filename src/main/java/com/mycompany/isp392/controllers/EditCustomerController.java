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

/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "EditCustomerController", urlPatterns = {"/EditCustomerController"})
public class EditCustomerController extends HttpServlet {

    private static final String ERROR = "editCustomer.jsp";
    private static final String SUCCESS = "systemManager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError();
        UserDAO dao = new UserDAO();
        boolean checkValidation = true;
        try {
            //call 
            int userID = Integer.parseInt(request.getParameter("userID"));
            String userName = request.getParameter("userName");
            String oldEmail = request.getParameter("oldEmail");
            String email = request.getParameter("email");
            int oldPhone = Integer.parseInt(request.getParameter("oldPhone"));
            int phone = Integer.parseInt(request.getParameter("phone"));
            int status = Integer.parseInt(request.getParameter("status"));
            int points = Integer.parseInt(request.getParameter("points"));
            Date birthday = Date.valueOf(LocalDate.parse(request.getParameter("birthday"), DateTimeFormatter.ISO_DATE));
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");
            String address = request.getParameter("address");

            //error handling
            //check email exists
            if (!oldEmail.equals(email)) {
                if (dao.checkEmailExists(email) != -1) {
                    error.setEmailError("Email already exists.");
                    checkValidation = false;
                }
            }
            // Check phone exists
            if (oldPhone != phone) {
                if (dao.checkPhoneExists(phone)) {
                    error.setPhoneError("Phone number already exists.");
                    checkValidation = false;
                }
            }
            // Check phone length
            if (String.valueOf(phone).length() != 9 && String.valueOf(phone).length() != 10) {
                error.setPhoneError("Phone number must be 9 or 10 digits.");
                checkValidation = false;
            }
            // Check age > 16
            LocalDate birthDate = birthday.toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();
            if (age < 16) {
                error.setBirthdayError("Customer must be over 16 years old");
                checkValidation = false;
            }

            //execute
            if (checkValidation) {
                UserDTO user = new UserDTO(userID, userName, email, "", 4, phone, status);
                CustomerDTO customer = new CustomerDTO(userID, points, birthday, city, district, ward, address);
                boolean checkUpdate = dao.editUserAndCustomer(user, customer);
                if (checkUpdate) {
                    url = SUCCESS;
                } else {
                    error.setError("Unable to update database");
                    request.setAttribute("EDIT_ERROR", error);
                }
            } else {
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
