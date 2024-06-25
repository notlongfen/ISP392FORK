package com.mycompany.isp392.controllers;

import com.mycompany.isp392.category.CategoryDAO;
import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.category.ChildrenCategoryDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCategoryController extends HttpServlet {

    private static final String ERROR = "SearchCategory.jsp";
    private static final String SUCCESS = "SearchCategory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchText = request.getParameter("searchText");
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categories = categoryDAO.searchCategories(searchText);
            Map<CategoryDTO, List<ChildrenCategoryDTO>> categoryChildrenMap = new HashMap<>();

            if (!categories.isEmpty()) {
                for (CategoryDTO category : categories) {
                    List<ChildrenCategoryDTO> childrenCategories = categoryDAO.searchChildrenCategories(category.getCategoryID());
                    categoryChildrenMap.put(category, childrenCategories);
                }
                request.setAttribute("categoryChildrenMap", categoryChildrenMap);
                request.setAttribute("MESSAGE", "CATEGORY AND RELATED CHILDREN CATEGORIES FOUND !");
                
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "NO CATEGORY FOUND !");
            }
        } catch (Exception e) {
            log("Error at SearchCategoryController: " + e.toString());
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
