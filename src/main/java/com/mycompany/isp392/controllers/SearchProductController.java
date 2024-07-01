package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SearchProductController extends HttpServlet {

    private static final String ERROR = "GetProductsController";
    private static final String SUCCESS = "AD_ProductList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchText = request.getParameter("searchText");
            BrandDAO dao = new BrandDAO();
            ProductDAO productDAO = new ProductDAO();
            List<BrandDTO> brands = dao.getAllBrands();
            List<ProductDTO> productList = productDAO.searchProducts(searchText);
            if (productList != null) {
                request.setAttribute("PRODUCT_LIST", productList);
                request.setAttribute("BRAND_LIST", brands);
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
