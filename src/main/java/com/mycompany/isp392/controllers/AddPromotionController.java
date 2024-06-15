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
import java.time.format.DateTimeFormatter;
import com.mycompany.isp392.promotion.*;

/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "AddPromotionController", urlPatterns = {"/AddPromotionController"})
public class AddPromotionController extends HttpServlet {

    //temp
    private static final String ERROR = "addPromotion.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        PromotionDAO dao = new PromotionDAO();
        boolean checkValidation = true;
        PromotionError error = new PromotionError();
        try {
            String promotionName = request.getParameter("promotionName");
            Date startDate = Date.valueOf(LocalDate.parse(request.getParameter("startDate"), DateTimeFormatter.ISO_DATE));
            Date endDate = Date.valueOf(LocalDate.parse(request.getParameter("endDate"), DateTimeFormatter.ISO_DATE));
            int discountPer = Integer.parseInt(request.getParameter("discountPer"));
            int condition = Integer.parseInt(request.getParameter("condition"));

            if (promotionName.length() > 20) {
                error.setPromotionNameError("Promotion name cannot be over 20 characters");
                checkValidation = false;
            }
            if(dao.checkPromotionDuplicate(promotionName)){
                error.setPromotionNameError("This promotion already exists");
                checkValidation=false;
            }
            if(promotionName.contains(" ")){
                error.setPromotionNameError("Promotion name cannot contain any spaces");
                checkValidation=false;
            }
            if (endDate.before(startDate)) {
                error.setEndDateError("End date must be after start date");
                checkValidation = false;
            }
            if (condition < 0) {
                error.setConditionError("Condition cannot be under 0");
                checkValidation = false;
            }

            if (checkValidation) {
                int promotionID = dao.getLatestPromotionID() + 1;
                PromotionDTO promotion = new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition, 1);
                boolean checkPromotion = dao.addPromotion(promotion);
                if (checkPromotion) {
                    url = SUCCESS;
                } else {
                    error.setError("Unable to add promotion to database");
                    request.setAttribute("PROMOTION_ERROR", error);
                }
            } else {
                request.setAttribute("PROMOTION_ERROR", error);
            }
            
        } catch (Exception e) {
            log("Error at AddPromotionController: " + e.toString());
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
