package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "EditBrandController", urlPatterns = {"/EditBrandController"})
public class EditBrandController extends HttpServlet {

    private static final String ERROR = "GetSpecificBrandController";
    private static final String SUCCESS = "GetBrandsController";
    private static final String UPLOAD_DIRECTORY = "images";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        BrandError brandError = new BrandError();
        boolean checkValidation = true;
        try {
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            String brandName = request.getParameter("brandName");
            Part filePart = request.getPart("brandImage");
            int status = Integer.parseInt(request.getParameter("status"));

            BrandDAO brandDAO = new BrandDAO();
            if (brandDAO.checkBrandExists(brandName, brandID)) {
                brandError.setBrandNameError("Brand already exists.");
                checkValidation = false;
            }

            String imagePath = brandDAO.getBrandImagePath(brandID); // Get current image path
            if (filePart != null && filePart.getSize() > 0) {
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
                boolean check = brandDAO.updateBrand(brandName, imagePath, brandID, status);
                if (check) {
                    request.setAttribute("SUCCESS_MESSAGE", "INFORMATION UPDATED SUCCESSFULLY !");
                    url = SUCCESS;
                }
            } else {
                brandError.setError("UNABLE TO UPDATE INFORMATION !");
                request.setAttribute("BRAND_ERROR", brandError);
            }
        } catch (SQLException e) {
            log("Error at UpdateBrandController: " + e.toString());
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
