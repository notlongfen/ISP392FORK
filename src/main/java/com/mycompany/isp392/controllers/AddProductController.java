package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddProductController extends HttpServlet {

    private static final String ERROR = "AD_CreateProduct.jsp";
    private static final String SUCCESS = "GetProductsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductDAO productDAO = new ProductDAO();

        ProductError productError = new ProductError();
        boolean checkValidation = true;
        try {
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int numberOfPurchase = 0;
            int status = 1;
            int brandID = Integer.parseInt(request.getParameter("brandID"));

            if (productDAO.checkProductExists(productName)) {
                productError.setProductNameError("Product is already exists.");
                checkValidation = false;
            }
            if (numberOfPurchase < 0) {
                productError.setNumberOfPurchaseError("Number of Purchase cannot be less than 0.");
                checkValidation = false;
            }
            if (checkValidation) {
                ProductDTO product = new ProductDTO(0, productName, description, numberOfPurchase, status, brandID);
                boolean check = productDAO.addProduct(product);
                if (check) {
                    int productID = productDAO.getLatestProductID();
                    request.setAttribute("MESSAGE", "Product added successfully!");
                    request.setAttribute("PRODUCT_ID", productID);
                    url = SUCCESS;
                } else {
                    productError.setError("UNABLE TO ADD PRODUCT TO DATABASE !");
                    request.setAttribute("PRODUCT_ERROR", productError);
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (NumberFormatException | SQLException e) {
            log("Error at AddProductController: " + e.toString());
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
        return "AddProductController";
    }
}
