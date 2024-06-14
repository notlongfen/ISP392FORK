package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SearchBrandController extends HttpServlet {
    private static final String ERROR = "brand.jsp";  
    private static final String SUCCESS = "brand.jsp"; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String brandName = request.getParameter("brandName"); 
            BrandDAO brandDAO = new BrandDAO();
            List<BrandDTO> brands = brandDAO.searchForBrand(brandName); 
            if (brands != null && !brands.isEmpty()) {
                request.setAttribute("brands", brands);  
                request.setAttribute("MESSAGE", "Brands found!");
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "No brands found.");
            }
        } catch (Exception e) {
            log("Error at SearchBrandController: " + e.toString());
            request.setAttribute("ERROR", "Database error: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response); 
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
