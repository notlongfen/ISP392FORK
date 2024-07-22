package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ManageProductDTO;
import com.mycompany.isp392.promotion.*;
import com.mycompany.isp392.user.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import utils.DbUtils;

@WebServlet(name = "EditPromotionController", urlPatterns = {"/EditPromotionController"})
@MultipartConfig
public class EditPromotionController extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "images";
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
            Part filePart = request.getPart("image");

            String oldPromotionName = request.getParameter("oldPromotionName");
            Date oldStartDate = Date.valueOf(request.getParameter("oldStartDate"));
            Date oldEndDate = Date.valueOf(request.getParameter("oldEndDate"));
            int oldDiscountPer = Integer.parseInt(request.getParameter("oldDiscountPer"));
            int oldCondition = Integer.parseInt(request.getParameter("oldCondition"));
            String oldDescription = request.getParameter("oldDescription");
            int oldStatus = Integer.parseInt(request.getParameter("oldStatus"));
            String oldImage = request.getParameter("oldImage");
            PromotionDAO dao = new PromotionDAO();
            
            String imagePath;
            if (filePart != null && filePart.getSize() > 0) {
                String path = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(path);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String fileName = UUID.randomUUID().toString() + "_"
                        + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                imagePath = UPLOAD_DIRECTORY + File.separator + fileName;
                filePart.write(path + File.separator + fileName);
            } else {
                imagePath = request.getParameter("oldImage");
            }

            // Check for duplicate promotion name, excluding the current promotion being edited
            if (!promotionName.equals(oldPromotionName) && dao.checkPromotionDuplicate(promotionName, status)) {
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
                PromotionDTO promotion = new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition, description, status, imagePath);
                boolean check = dao.editPromotion(promotion);
                List<String> oldList = new ArrayList<>();
                List<String> newList = new ArrayList<>();
                if (!promotionName.equals(oldPromotionName)) {
                    oldList.add("Name: " + oldPromotionName);
                    newList.add("Name: " + promotionName);
                }

                if (!startDate.equals(oldStartDate)) {
                    oldList.add("Start Date: " + oldStartDate.toString());
                    newList.add("Start Date: " + startDate.toString());
                }

                if (!endDate.equals(oldEndDate)) {
                    oldList.add("End Date: " + oldEndDate.toString());
                    newList.add("End Date: " + endDate.toString());
                }

                if (discountPer != oldDiscountPer) {
                    oldList.add("Discount Per: " + String.valueOf(oldDiscountPer));
                    newList.add("Discount Per: " + String.valueOf(discountPer));
                }

                if (condition != oldCondition) {
                    oldList.add("Condition: "+ String.valueOf(oldCondition));
                    newList.add("Condition: "+ String.valueOf(condition));
                }

                if (!description.equals(oldDescription)) {
                    oldList.add("Description: " + oldDescription);
                    newList.add("Description: " + description);
                }

                if (status != oldStatus) {
                    oldList.add("Status: " + String.valueOf(oldStatus));
                    newList.add("Status: " + String.valueOf(status));
                }
                if (!oldImage.equals(imagePath)) {
                    oldList.add(oldImage);
                    newList.add(imagePath);
                }
                if (oldList.size() > 0 && newList.size() > 0) {
                        UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                        int empID = user.getUserID();
                        ManagePromotionDTO manage = new ManagePromotionDTO(promotionID, empID, oldList, newList, "Edit");
                        DbUtils.addCheckLogToDB("ManagePromotions", "PromotionID", manage);
                    }
                
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
