package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AddBrandController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String ERROR = "AD_AddBrands.jsp";
    private static final String SUCCESS = "GetBrandsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        BrandError brandError = new BrandError();
        boolean checkValidation = true;

        try {
            String brandName = request.getParameter("brandName");
            Part filePart = request.getPart("brandImage");

            if (filePart == null || filePart.getSize() == 0) {
                brandError.setError("Brand image is required.");
                checkValidation = false;
            }

            BrandDAO brandDAO = new BrandDAO();
            if (brandDAO.checkBrandExists(brandName)) {
                brandError.setBrandNameError("Brand Name already exists.");
                checkValidation = false;
            }

            String imagePath = "";
            if (checkValidation) {
                String path = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(path);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                imagePath = UPLOAD_DIRECTORY + File.separator + fileName;
                filePart.write(path + File.separator + fileName);
            }

            if (checkValidation) {
                boolean check = brandDAO.addBrand(brandName, imagePath);
                if (check) {
                    request.setAttribute("MESSAGE", "BRAND ADDED SUCCESSFULLY !");
                    url = SUCCESS;
                } else {
                    brandError.setError("UNABLE TO ADD BRAND TO DATABASE !");
                    request.setAttribute("BRAND_ERROR", brandError);
                }
            } else {
                request.setAttribute("BRAND_ERROR", brandError);
            }
        } catch (SQLException e) {
            log("Error at AddBrandController: " + e.toString());
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
        return "AddBrandController";
    }
}
