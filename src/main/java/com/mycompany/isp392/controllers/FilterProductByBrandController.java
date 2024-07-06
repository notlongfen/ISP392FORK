package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilterProductByBrandController extends HttpServlet {

    private static final String SUCCESS_PAGE = "US_AllProducts.jsp";
    private static final Logger LOGGER = Logger.getLogger(FilterProductByBrandController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int brandID = Integer.parseInt(request.getParameter("brandID"));

            ProductDAO productDao = new ProductDAO();
            BrandDAO brandDao = new BrandDAO();
            List<ProductDTO> products = productDao.getProductByBrand(brandID);
            List<ProductDetailsDTO> productDetails = productDao.getProductDetailsByBrand(brandID);
            List<BrandDTO> brands = brandDao.getAllBrands();

            request.setAttribute("products", products);
            request.setAttribute("productDetails", productDetails);
            request.setAttribute("brands", brands);
            request.setAttribute("brandID", brandID);

            request.getRequestDispatcher(SUCCESS_PAGE).forward(request, response);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid Brand ID", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Brand ID");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database Error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected Error", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected Error");
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
        return "Servlet for filtering products by brand";
    }
}
