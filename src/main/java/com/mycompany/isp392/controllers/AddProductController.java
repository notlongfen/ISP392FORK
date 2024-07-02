package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.*;
import com.mycompany.isp392.category.CategoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddProductController extends HttpServlet {

    private static final String ERROR = "GetBrandsController";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        ProductError productError = new ProductError();
        boolean checkValidation = true;
        try {
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int numberOfPurchase = 0;
            int status = 1;
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            String[] categoryArray = request.getParameterValues("categories");

            if (productDAO.checkProductExistsOnlyName(productName)) {
                productError.setProductNameError("Product already exists.");
                checkValidation = false;
            }
         
            if (checkValidation) {
                ProductDTO product = new ProductDTO( productName, description, numberOfPurchase, status, brandID);
                boolean check = productDAO.addProduct(product);
                if (check) {
                    int productID = productDAO.getLatestProductID();
                    for (String categoryID : categoryArray) {
                        categoryDAO.addProductCategory(productID, Integer.parseInt(categoryID));
                    }
                    request.getSession().setAttribute("SUCCESS_MESSAGE", "Product added successfully!");
                    request.getSession().setAttribute("PRODUCT_ID", productID);
                    response.sendRedirect(SUCCESS);
                    return;
                } else {
                    productError.setError("Unable to add product to the database!");
                    request.getSession().setAttribute("PRODUCT_ERROR", productError);
                }
            } else {
                request.getSession().setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            log("Error at AddProductController: " + e.toString());
            request.getSession().setAttribute("ERROR_MESSAGE", "Error adding product: " + e.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);
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
        return "AddProductController";
    }
}
