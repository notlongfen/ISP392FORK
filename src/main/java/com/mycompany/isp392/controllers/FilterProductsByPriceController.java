/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.util.List;

/**
 *
 * @author jojo
 */
public class FilterProductsByPriceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ProductDAO dao = new ProductDAO();
            BrandDAO brandDAO = new BrandDAO();

            try {
                List<ProductDTO> products;
                List<ProductDetailsDTO> productDetails;
                List<BrandDTO> brands = brandDAO.getAllBrands();
                String priceRange = request.getParameter("priceRange");
                if (priceRange != null && !priceRange.isEmpty()) {
                    products = dao.getProductsByPriceRange(priceRange);
                    productDetails = dao.getProductDetailsByProductList(products);
                } else {
                    products = dao.getAllProducts();
                    productDetails = dao.getAllProductDetails();
                }

                request.setAttribute("products", products);
                request.setAttribute("productDetails", productDetails);
                request.setAttribute("brands", brands);
                request.setAttribute("priceRange", priceRange); // Set price range attribute

                request.getRequestDispatcher("US_AllProducts.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching data from the database");
            }

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
