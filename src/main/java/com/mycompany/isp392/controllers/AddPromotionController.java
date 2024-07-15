package com.mycompany.isp392.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.mycompany.isp392.promotion.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;
import net.coobird.thumbnailator.Thumbnails;

@MultipartConfig
@WebServlet(name = "AddPromotionController", urlPatterns = {"/AddPromotionController"})
public class AddPromotionController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String ERROR = "AD_CreatePromotion.jsp";
    private static final String SUCCESS = "GetPromotionListController";
    private static final int IMAGE_WIDTH = 500; // Set desired image width
    private static final int IMAGE_HEIGHT = 500; // Set desired image height

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
            String description = request.getParameter("description");
            Collection<Part> fileParts = request.getParts();
            int status = 1;

            StringBuilder imagePathBuilder = new StringBuilder();

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

            // Image upload logic
            for (Part filePart : fileParts) {
                if (filePart.getName().equals("images") && filePart.getSize() > 0) {
                    String path = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                    File uploadDir = new File(path);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String imagePath = UPLOAD_DIRECTORY + File.separator + fileName;
                    File outputFile = new File(path + File.separator + fileName);

                    Thumbnails.of(filePart.getInputStream())
                            .size(IMAGE_WIDTH, IMAGE_HEIGHT)
                            .toFile(outputFile);

                    if (imagePathBuilder.length() > 0) {
                        imagePathBuilder.append(";");
                    }
                    imagePathBuilder.append(imagePath);
                }
            }

            String imagePaths = imagePathBuilder.toString();

            if (checkValidation) {
                int promotionID = dao.getLatestPromotionID() + 1;
                PromotionDTO promotion = new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, imagePaths, condition, description, 1);
                boolean checkPromotion = dao.addPromotion(promotion);
                if (checkPromotion) {
                    request.setAttribute("SUCCESS_MESSAGE", "PROMOTION ADDED SUCCESSFULLY !");
                    url = SUCCESS;
                } else {
                    error.setError("UNABLE TO ADD PROMOTION TO DATABASE !");
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
