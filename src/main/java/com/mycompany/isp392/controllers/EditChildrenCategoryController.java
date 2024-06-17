package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.CategoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditChildrenCategoryController extends HttpServlet {

    private static final String ERROR = "SearchCategory.jsp";
    private static final String SUCCESS = "SearchCategory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int cdCategoryID = Integer.parseInt(request.getParameter("cdCategoryID"));
            String newName = request.getParameter("categoryName");

            CategoryDAO categoryDAO = new CategoryDAO();
            boolean check = categoryDAO.updateChildrenCategory(cdCategoryID, newName);
            if (check) {
                request.setAttribute("MESSAGE", "Child category updated successfully!");
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "Failed to update child category.");
            }
        } catch (Exception e) {
            log("Error at UpdateChildrenCategoryController: " + e.toString());
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
