package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.brand.BrandError;
import com.mycompany.isp392.brand.ManageBrandDTO;
import com.mycompany.isp392.user.UserDTO;

import net.coobird.thumbnailator.Thumbnails;
import utils.DbUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private static final int IMAGE_WIDTH = 500; // Set desired image width
    private static final int IMAGE_HEIGHT = 500; // Set desired image height

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
            if (brandDAO.checkBrandNameExists(brandName)) {
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
                File outputFile = new File(path + File.separator + fileName);

                // Resize the image
                Thumbnails.of(filePart.getInputStream())
                        .size(IMAGE_WIDTH, IMAGE_HEIGHT)
                        .toFile(outputFile);

                filePart.write(path + File.separator + fileName);
            }

            if (checkValidation) {
                boolean check = brandDAO.addBrand(brandName, imagePath);
                if (check) {
                    List<String> newList = new ArrayList<>();
                    BrandDTO brand = brandDAO.getBrandByName(brandName);
                    String action = request.getParameter(brandName);
                    UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                    ManageBrandDTO manage = new ManageBrandDTO(brand.getBrandID(), user.getUserID(), new ArrayList<>(), newList, action);
                    DbUtils.addCheckLogToDB("ManageBrands", "BrandID", manage);
                    request.setAttribute("SUCCESS_MESSAGE", "BRAND ADDED SUCCESSFULLY !");
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
        try {
            processRequest(request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "AddBrandController";
    }
}
