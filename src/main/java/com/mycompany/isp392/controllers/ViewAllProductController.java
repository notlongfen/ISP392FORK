package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.category.CategoryDAO;
import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "ViewAllProductController", urlPatterns = {"/ViewAllProductController"})
public class ViewAllProductController extends HttpServlet {

    private static final String SUCCESS_PAGE = "US_AllProducts.jsp";
    private static final String ERROR_PAGE = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
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

            // Get pagination parameters
            int page = 1;
            int recordsPerPage = 10;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            // Get paginated and filtered products
            List<ProductDTO> filteredProducts = productDAO.getFilteredProducts(brandFilters, priceFilters, categoryFilters, page, recordsPerPage);

            // Get all product details and group by product ID and color
            Map<Integer, Map<String, ProductDetailsDTO>> productDetailsByColor = new HashMap<>();
            for (ProductDTO product : filteredProducts) {
                List<ProductDetailsDTO> details = productDAO.getProductDetails(product.getProductID());
                Map<String, ProductDetailsDTO> detailsByColor = details.stream()
                        .collect(Collectors.toMap(ProductDetailsDTO::getColor, detail -> detail, (existing, replacement) -> existing));
                productDetailsByColor.put(product.getProductID(), detailsByColor);
            }

            // Get all brands for the sidebar
            List<BrandDTO> allBrands = brandDAO.getAllBrands();

            // Get all categories for the sidebar
            List<CategoryDTO> allCategories = categoryDAO.getActiveCategory();

            // Get total number of products for pagination
            int totalProducts = productDAO.getTotalFilteredProductsCount(brandFilters, priceFilters, categoryFilters);

            int totalPages = (int) Math.ceil(totalProducts * 1.0 / recordsPerPage);

            request.setAttribute("products", filteredProducts);
            request.setAttribute("productDetailsByColor", productDetailsByColor);
            request.setAttribute("brands", allBrands);
            request.setAttribute("categories", allCategories);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            url = SUCCESS_PAGE;
        } catch (Exception e) {
            log("Error at ViewAllProductController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
