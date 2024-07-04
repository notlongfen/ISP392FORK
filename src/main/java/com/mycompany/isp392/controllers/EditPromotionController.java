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

    private static final String ERROR = "CopyPromotionController";
    private static final String SUCCESS = "GetPromotionListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        PromotionError error = new PromotionError();
        boolean checkValidation = true;

        try {
            int promotionID = Integer.parseInt(request.getParameter("promotionID"));
            String promotionName = request.getParameter("promotionName");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            int discountPer = Integer.parseInt(request.getParameter("discountPer"));
            int condition = Integer.parseInt(request.getParameter("condition"));
            String description = request.getParameter("description");
            int status = Integer.parseInt(request.getParameter("status"));
            PromotionDAO dao = new PromotionDAO();
            
            if (dao.checkPromotionDuplicate(promotionName, status)) {
                error.setPromotionNameError("This promotion already exists.");
                checkValidation = false;
            }
            if (promotionName.contains(" ")) {
                error.setPromotionNameError("Promotion name cannot contain any spaces.");
                checkValidation = false;
            }
            if (endDate.before(startDate)) {
                error.setEndDateError("End date must be after start date.");
                checkValidation = false;
            }
            if (discountPer < 0) {
                error.setDiscountPerError("Discount percentage cannot be under 0.");
                checkValidation = false;
            }
            if (condition < 0) {
                error.setConditionError("Condition cannot be under 0.");
                checkValidation = false;
            }

            if (checkValidation) {
                PromotionDTO promotion = new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition, description, status);
                boolean check = dao.editPromotion(promotion);
                if (check) {
                    request.setAttribute("SUCCESS_MESSAGE", "INFORMATION UPDATED SUCCESSFULLY !");
                    url = SUCCESS;
                }
            } else {
                error.setError("UNABLE TO UPDATE INFORMATION !");
                request.setAttribute("PROMOTION_ERROR", error);
            }

        } catch (Exception e) {
            log("Error at EditPromotionController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
