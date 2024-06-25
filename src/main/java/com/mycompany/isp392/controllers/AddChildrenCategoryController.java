package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddChildrenCategoryController", urlPatterns = {"/AddChildrenCategoryController"})
public class AddChildrenCategoryController extends HttpServlet {

    private static final String ERROR = "addChildrenCategory.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CategoryDAO dao = new CategoryDAO();
        boolean checkValidation = true;
        CategoryError error = new CategoryError();
        try {
            String cdCategoryName = request.getParameter("cdCategoryName");
            int parentID = Integer.parseInt(request.getParameter("parentID"));

            if (dao.checkChildrenCategoryDuplicate(cdCategoryName, parentID)) {
                error.setCdCategoryNameError("This children category already exists.");
                checkValidation = false;
            }

            if (checkValidation) {
                int categoryID = dao.getLatestCdCategoryID() + 1;
                ChildrenCategoryDTO cdCategory = new ChildrenCategoryDTO(categoryID, cdCategoryName, parentID, 1);
                boolean checkChildCategory = dao.addChildrenCategory(cdCategory);
                if (checkChildCategory) {
                    request.setAttribute("MESSAGE", "BRAND ADDED SUCCESSFULLY !");
                    url = SUCCESS;
                } else {
                    error.setError("UNABLE TO ADD CHILDREN CATEGORY TO DATABASE !");
                    request.setAttribute("CHILDREN_CATEGORY_ERROR", error);
                }
            } else {
                request.setAttribute("CHILDREN_CATEGORY_ERROR", error);
            }

        } catch (Exception e) {
            log("Error at AddChildrenCategoryController: " + e.toString());
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
