/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.user.CustomerDTO;
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
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 84773
 */
@WebServlet(name = "SaveProfileController", urlPatterns = {"/SaveProfileController"})
public class SaveProfileController extends HttpServlet {

    public static final String ERROR = "US_SignIn.jsp";
    public static final String SUCCESS = "US_MyProfile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError();
        UserDAO dao = new UserDAO();
        try {
            HttpSession session = request.getSession();
            CustomerDTO user = (CustomerDTO) session.getAttribute("cust");
            if (user != null) {
                int userID = Integer.parseInt(request.getParameter("userID"));
                String userName = request.getParameter("userName");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String ward = request.getParameter("ward");
                String district = request.getParameter("district");
                String city = request.getParameter("city");
                Date birthday = Date.valueOf(LocalDate.parse(request.getParameter("birthday"), DateTimeFormatter.ISO_DATE));
                int phone = Integer.parseInt(request.getParameter("phone"));

                CustomerDTO cust = new CustomerDTO(userID, userName, email, address, ward, district, city, birthday, phone);
                boolean checkUpdate = dao.updateUserAndCustomer(cust);
                if (checkUpdate) {
                    if(user!=null){
                        user.setUserName(userName);
                        user.setEmail(email);
                        user.setAddress(address);
                        user.setWard(ward);
                        user.setDistrict(district);
                        user.setDistrict(city);
                        user.setBirthday(birthday);
                        user.setPhone(phone);
                        session.setAttribute("cust", user);
                    }
                    url = SUCCESS;
                } else {
                    error.setError("UNABLE TO UPDATE PROFILE INFORMATION !");
                    request.setAttribute("USER_ERROR", error);
                }
            } else {
                error.setError("USER NOT LOGGED IN !");
                request.setAttribute("USER_ERROR", error);
            }

        } catch (Exception e) {
            log("error at SaveProfileController: " + e.toString());
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
protected void doGet (HttpServletRequest request, HttpServletResponse response)
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
protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override 
    public String getServletInfo () {
        return "Short description";
        }// </editor-fold>

    }

