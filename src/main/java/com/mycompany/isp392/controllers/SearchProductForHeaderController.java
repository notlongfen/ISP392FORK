/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.category.CategoryDAO;
import com.mycompany.isp392.category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "SearchProductForHeaderController", urlPatterns = {"/SearchProductForHeaderController"})
public class SearchProductForHeaderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "US_AllProducts.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO productDAO = new ProductDAO();
            BrandDAO brandDAO = new BrandDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            // Get filters from request
            String[] brandFilters = request.getParameterValues("brands[]");
            String[] priceFilters = request.getParameterValues("prices[]");
            String[] categoryFilters = request.getParameterValues("categories[]");

            // Check for brandID query parameter and add it to the filters
            String brandID = request.getParameter("brandID");
            if (brandID != null && !brandID.isEmpty()) {
                brandFilters = new String[]{brandID}; // Override any existing brand filters
            }

            String categoryID = request.getParameter("categoryID");
            if (categoryID != null && !categoryID.isEmpty()) {
                categoryFilters = new String[]{categoryID}; // Override any existing brand filters
            }

            // Get search query parameter
            String searchQuery = request.getParameter("search");

            // Get pagination parameters
            int page = 1;
            int recordsPerPage = 7;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            // Get paginated and filtered products
            List<ProductDTO> filteredProducts = productDAO.getFilteredProducts(brandFilters, priceFilters, categoryFilters, searchQuery, page, recordsPerPage);

            // Get all product details and group by product ID and color
            Map<Integer, Map<String, ProductDetailsDTO>> productDetailsByColor = new HashMap<>();
            for (ProductDTO product : filteredProducts) {
                List<ProductDetailsDTO> details = productDAO.getProductDetails(product.getProductID());
                Map<String, ProductDetailsDTO> detailsByColor = details.stream()
                        .filter(detail -> detail.getStockQuantity() > 0 && detail.getStatus() == 1)
                        .collect(Collectors.toMap(ProductDetailsDTO::getColor, detail -> detail, (existing, replacement) -> existing));
                productDetailsByColor.put(product.getProductID(), detailsByColor);
            }

            // Get all brands for the sidebar
            List<BrandDTO> allBrands = brandDAO.getAllBrands();

            // Get all categories for the sidebar
            List<CategoryDTO> allCategories = categoryDAO.getActiveCategory();

            // Get total number of products for pagination
            int totalProducts = productDAO.getTotalFilteredProductsCount(brandFilters, priceFilters, categoryFilters, searchQuery);

            int totalPages = (int) Math.ceil(totalProducts * 1.0 / recordsPerPage);

            request.setAttribute("products", filteredProducts);
            request.setAttribute("productDetailsByColor", productDetailsByColor);
            request.setAttribute("brands", allBrands);
            request.setAttribute("categories", allCategories);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchQuery", searchQuery); // Add search query to the request

            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ViewAllProductController: " + e.toString());
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
