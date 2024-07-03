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
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "SearchChildrenCategoryController", urlPatterns = {"/SearchChildrenCategoryController"})
public class SearchChildrenCategoryController extends HttpServlet {

    private static final String ERROR = "AD_ChildrenCategory.jsp";
    private static final String SUCCESS = "AD_ChildrenCategory.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CategoryDAO dao = new CategoryDAO();
            String searchText = request.getParameter("searchText");
            int parentID;
            List<ChildrenCategoryDTO> listChildrenCategory;
            int parentStatus;
            
            if(request.getParameter("parentID") != null){
                parentID = Integer.parseInt(request.getParameter("parentID"));
            } else {
                parentID = (Integer) session.getAttribute("PARENT_CATEGORY_ID");
            }    
            
            if (searchText == null || searchText.isEmpty()) {
                listChildrenCategory = dao.searchChildrenCategories(parentID);
                parentStatus = dao.getParentCategoryStatus(parentID);
            } else {
                listChildrenCategory = dao.searchChildrenCategoriesByText(searchText, parentID);
                parentStatus = dao.getParentCategoryStatus(parentID);
            }

            if (listChildrenCategory != null && !listChildrenCategory.isEmpty()) {
                request.setAttribute("PARENT_CATEGORY_ID", parentID);
                request.setAttribute("PARENT_CATEGORY_STATUS", parentStatus);
                request.setAttribute("CHILD_CATEGORY_LIST", listChildrenCategory);
                request.setAttribute("MESSAGE", "CHILD CATEGORY FOUND !");
                url = SUCCESS;
            } else {
                request.setAttribute("PARENT_CATEGORY_ID", parentID);
                request.setAttribute("PARENT_CATEGORY_STATUS", parentStatus);
                request.setAttribute("MESSAGE", "NO CHILD CATEGORY FOUND !");
            }
        } catch (Exception e) {
            log("Error at SearchChildrenCategoryController: " + e.toString());
            request.setAttribute("ERROR", "Database error: " + e.getMessage());
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
