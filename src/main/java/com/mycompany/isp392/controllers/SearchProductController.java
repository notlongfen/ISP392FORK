package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SearchProductController extends HttpServlet {

    private static final String ERROR = "product.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchText = request.getParameter("search");
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            ProductDAO productDAO = new ProductDAO();
            BrandDAO brandDAO = new BrandDAO();
            List<ProductDTO> productList = productDAO.searchProducts(searchText);
            List<BrandDTO> brandList = brandDAO.searchBrandByBrandID(brandID);
            if (productList != null) {
                request.setAttribute("PRODUCT_LIST", productList);
                request.setAttribute("BRAND_LIST", brandList);
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "NO PRODUCT FOUND !");

            }
        } catch (SQLException e) {
            log("Error at SearchProductController: " + e.toString());
            request.setAttribute("ERROR", "Database error: " + e.getMessage());
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
