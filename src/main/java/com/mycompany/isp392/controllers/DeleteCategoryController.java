/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "DeleteCategoryController", urlPatterns = {"/DeleteCategoryController"})
public class DeleteCategoryController extends HttpServlet {

    //fill out
    private static final String ERROR = "SearchCategory.jsp";
    private static final String SUCCESS = "SearchCategory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CategoryDAO dao = new CategoryDAO();
        CategoryError error = new CategoryError();
        try {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            List<ChildrenCategoryDTO> childrenList = dao.getChildrenCategories(categoryID);
            if (!childrenList.isEmpty()) {
                boolean allChildrenDeleted = true;
                for (ChildrenCategoryDTO child : childrenList) {
                    boolean checkDeleteChild = dao.deleteChildrenCategory(child.getCdCategoryID());
                    if (!checkDeleteChild) {
                        allChildrenDeleted = false;
                        error.setError("Unable to delete child category " + child.getCdCategoryID());
                        request.setAttribute("CHILDREN_CATEGORY_ERROR", error);
                        break;
                    }
                }
                if (allChildrenDeleted) {
                    boolean checkDelete = dao.deleteCategory(categoryID);
                    if (checkDelete) {
                        url = SUCCESS;
                    } else {
                        error.setError("Unable to delete category from database");
                        request.setAttribute("CATEGORY_ERROR", error);
                    }
                }
            } else {
                boolean checkDelete = dao.deleteCategory(categoryID);
                if (checkDelete) {
                    url = SUCCESS;
                } else {
                    error.setError("Unable to delete category from database");
                    request.setAttribute("CATEGORY_ERROR", error);
                }
            }
        } catch (Exception e) {
            log("Error at DeleteCategoryController: " + e.toString());
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
