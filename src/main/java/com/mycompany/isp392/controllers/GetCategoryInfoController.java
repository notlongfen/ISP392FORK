/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "GetCategoryInfoController", urlPatterns = {"/GetCategoryInfoController"})
public class GetCategoryInfoController extends HttpServlet {

    private static final String ERROR = "AD_CategoriesList.jsp";
    private static final String SUCCESS = "AD_EditCategories.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CategoryDAO dao = new CategoryDAO();
        CategoryError error = new CategoryError();
        try {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            CategoryDTO category = dao.getCategoryInfoByID(categoryID);
            if (category != null) {
                request.setAttribute("CATEGORY", category);                
                url = SUCCESS;
            } else {
                error.setError("UNABLE TO GET CATEGORY INFO FROM DATABASE !");
                request.setAttribute("CATEGORY_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at GetCategoryInfoController: " + e.toString());
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
