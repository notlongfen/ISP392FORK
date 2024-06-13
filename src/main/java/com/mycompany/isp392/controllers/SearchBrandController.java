package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBrandController
 */
public class SearchBrandController extends HttpServlet {
    private static final String ERROR = "brand.jsp";  // Assuming 'brand.jsp' handles both success and error cases
    private static final String SUCCESS = "brand.jsp"; // Redirect here after searching

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String brandName = request.getParameter("brandName"); // Parameter for search query
            BrandDAO brandDAO = new BrandDAO();
            List<BrandDTO> brands = brandDAO.searchForBrand(brandName); // Search for brands
            if (brands != null && !brands.isEmpty()) {
                request.setAttribute("brands", brands);  // Save search results in request scope
                request.setAttribute("MESSAGE", "Brands found!");
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "No brands found.");
            }
        } catch (Exception e) {
            log("Error at SearchBrandController: " + e.toString());
            request.setAttribute("ERROR", "Database error: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response); // Forward to JSP
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Handle GET the same way as POST
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Handle POST requests
    }

    @Override
    public String getServletInfo() {
        return "Searches for brands by name and displays the results.";
    }
}
