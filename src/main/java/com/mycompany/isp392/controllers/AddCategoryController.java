
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.*;
import com.mycompany.isp392.user.UserDTO;

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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import utils.DbUtils;


@WebServlet(name = "AddCategoryController", urlPatterns = {"/AddCategoryController"})
@MultipartConfig
public class AddCategoryController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String ERROR = "AD_CreateCategories.jsp";
    private static final String SUCCESS = "SearchCategoryController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CategoryDAO dao = new CategoryDAO();
        boolean checkValidation = true;
        CategoryError categoryError = new CategoryError();
        try {
            String categoryName = request.getParameter("categoryName");
            String description = request.getParameter("description");
            Part filePart = request.getPart("image");

            if (filePart == null || filePart.getSize() == 0) {
                categoryError.setError("Category image is required.");
                checkValidation = false;
            }
            if(dao.checkCategoryDuplicate(categoryName)){
                categoryError.setCategoryNameError("This category already exists.");
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
            
            if(checkValidation){
                int categoryID = dao.getLatestCategoryID() + 1;
                CategoryDTO category = new CategoryDTO(categoryID, categoryName, description, 1, imagePath);
                UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                boolean checkCategory = dao.addCategory(category);
                if(checkCategory){
                    List<String> newList = new ArrayList<>();
                    newList.add("Name: " + categoryName);
                    newList.add("Description: " + description);
                    newList.add(imagePath);
                    ManageCategoryDTO manageCategory = new ManageCategoryDTO(categoryID, user.getUserID(), new ArrayList<>(), newList, "Add");
                    DbUtils.addCheckLogToDB("ManageCategories", "Categories", manageCategory);

                   request.setAttribute("SUCCESS_MESSAGE", "CATEGORY ADDED SUCCESSFULLY !");
                    url = SUCCESS;
                }else {
                    categoryError.setError("UNABLE TO ADD CATEGORY TO DATABASE !");
                    request.setAttribute("CATEGORY_ERROR", categoryError);
                }
            }else{
                request.setAttribute("CATEGORY_ERROR",categoryError);
            }
            
        } catch (SQLException e) {
            log("Error at AddCategoryController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
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
        return "AddCategoryController";
    }// </editor-fold>

}
