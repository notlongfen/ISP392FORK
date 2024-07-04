package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EditChildrenCategoryController extends HttpServlet {

    private static final String ERROR = "AD_EditChildrenCategory.jsp";
    private static final String SUCCESS = "SearchChildrenCategoryController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CategoryError categoryError = new CategoryError();

        try {
            HttpSession session = request.getSession();
            int cdCategoryID = Integer.parseInt(request.getParameter("cdCategoryID"));
            String newName = request.getParameter("categoryName");
            int parentID = Integer.parseInt(request.getParameter("parentID"));
            int newStatus = Integer.parseInt(request.getParameter("status"));

            CategoryDAO categoryDAO = new CategoryDAO();
            boolean check = categoryDAO.updateChildrenCategory(cdCategoryID, newName, newStatus);
            if (check) {
                session.setAttribute("PARENT_CATEGORY_ID", parentID);
                request.setAttribute("SUCCESS_MESSAGE", "CHILDREN CATEGORY UPDATED SUCCESSFULLY !");
                url = SUCCESS;
            } else {
                categoryError.setError("UNABLE TO UPDATE INFORMATION !");
                request.setAttribute("CATEGORY_ERROR", categoryError);
            }
        } catch (Exception e) {
            log("Error at EditChildrenCategoryController: " + e.toString());
            request.setAttribute("ERROR", "Error updating child category: " + e.getMessage());
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
