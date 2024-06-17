
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.promotion.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

@WebServlet(name = "EditPromotionController", urlPatterns = {"/EditPromotionController"})
public class EditPromotionController extends HttpServlet {

    private static final String ERROR = "editPromotion.jsp";
    private static final String SUCCESS = "shopManager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        PromotionError promotionError = new PromotionError();
        boolean checkValidation = true;
        PromotionError error = new PromotionError();

        try {
            int promotionID = Integer.parseInt(request.getParameter("promotionID"));
            String promotionName = request.getParameter("promotionName");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            int discountPer = Integer.parseInt(request.getParameter("discountPer"));
            int condition = Integer.parseInt(request.getParameter("condition"));
            int status = Integer.parseInt(request.getParameter("status"));
            PromotionDAO dao = new PromotionDAO();
            PromotionDTO promotion = new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition, status);
            if (promotionName.contains(" ")) {
                error.setPromotionNameError("Promotion name cannot contain any spaces");
                checkValidation = false;
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
                boolean check = dao.editPromotion(promotion);
                if (check) {
                    url = SUCCESS;
                } else {
                    promotionError.setError("Failed to edit promotion. Please try again.");
                    request.setAttribute("PROMOTION_ERROR", promotionError);
                }
            }
        } catch (Exception e) {
            log("Error at EditPromotionController: " + e.toString());
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
