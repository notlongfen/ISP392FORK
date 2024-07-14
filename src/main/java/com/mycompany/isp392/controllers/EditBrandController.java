package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandError;
import com.mycompany.isp392.brand.ManageBrandDTO;
import com.mycompany.isp392.user.UserDTO;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "EditBrandController", urlPatterns = {"/EditBrandController"})
public class EditBrandController extends HttpServlet {

    private static final String ERROR = "GetSpecificBrandController";
    private static final String SUCCESS = "GetBrandsController";
    private static final String UPLOAD_DIRECTORY = "images";
    private static final int IMAGE_WIDTH = 500; // Set desired image width
    private static final int IMAGE_HEIGHT = 500; // Set desired image height

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        BrandError brandError = new BrandError();
        boolean checkValidation = true;
        try {
            HttpSession session = request.getSession();
            String newBrandName = request.getParameter("newBrandName");
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            int empID = user.getUserID();
            String oldBrandName = request.getParameter("oldBrandName");
            int oldStatus = Integer.parseInt(request.getParameter("oldStatus"));
            int newStatus = Integer.parseInt(request.getParameter("status"));

            Part filePart = request.getPart("brandImage");
            ManageBrandDTO manage = null;
            BrandDAO brandDAO = new BrandDAO();

            if (brandDAO.checkBrandExists(newBrandName, brandID)) {
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
                File outputFile = new File(path + File.separator + fileName);

                // Resize the image
                Thumbnails.of(filePart.getInputStream())
                        .size(IMAGE_WIDTH, IMAGE_HEIGHT)
                        .toFile(outputFile);

                filePart.write(path + File.separator + fileName);
            }

            if (checkValidation) {
                boolean check = brandDAO.updateBrand(newBrandName, imagePath, brandID, newStatus);
                if (check) {
                    List<String> oldList = new ArrayList<>();
                    List<String> newList = new ArrayList<>();

                    if (!oldBrandName.equals(newBrandName)) {
                        oldList.add(oldBrandName);
                        newList.add(newBrandName);
                    }

                    if (oldStatus != newStatus) {
                        oldList.add(String.valueOf(oldStatus));
                        newList.add(String.valueOf(newStatus));
                    }

                    if (!oldList.isEmpty() && !newList.isEmpty()) {
                        String action = request.getParameter("edit");
                        manage = new ManageBrandDTO(brandID, empID, oldList, newList, action);
                        boolean checkAdd = brandDAO.addManageBrand(manage);
                        if(checkAdd) {
                            request.setAttribute("SUCCESS_MESSAGE", "INFORMATION UPDATED SUCCESSFULLY!");
                            url = SUCCESS;
                        } else {
                            brandError.setError("UNABLE TO UPDATE INFORMATION!");
                            request.setAttribute("BRAND_ERROR", brandError);
                        }
                    }

                    // request.setAttribute("SUCCESS_MESSAGE", "INFORMATION UPDATED SUCCESSFULLY!");
                    // url = SUCCESS;
                }
            } else {
                brandError.setError("UNABLE TO UPDATE INFORMATION!");
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
        return "EditBrandController";
    }
}
