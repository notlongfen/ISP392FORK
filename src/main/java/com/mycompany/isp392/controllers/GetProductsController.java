package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetProductsController extends HttpServlet {

    private static final String ERROR = "US_SignIn.jsp";
    private static final String PRODUCTS_PAGE = "AD_ProductList.jsp";
    private static final String PRODUCT_DETAILS_PAGE = "AD_ProductDetail.jsp";
    private static final String ADD_PRODUCT_DETAIL_PAGE = "CreateProductDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            BrandDAO dao = new BrandDAO();
            ProductDAO productDAO = new ProductDAO();
            if (request.getParameter("productID") != null) {
                int productID = Integer.parseInt(request.getParameter("productID"));
                List<ProductDetailsDTO> productDetailsList = productDAO.getProductDetails(productID);
                ProductDTO parentProduct = productDAO.selectProduct(productID);
                request.setAttribute("PARENT_PRODUCT", parentProduct);
                request.setAttribute("PRODUCT_DETAILS_LIST", productDetailsList);
                url = PRODUCT_DETAILS_PAGE;
            }else if (request.getAttribute("newProductID") != null) {
                int newProductID = (int) request.getAttribute("newProductID");
                List<ProductDetailsDTO> productDetailsList = productDAO.getProductDetails(newProductID);
                ProductDTO parentProduct = productDAO.selectProduct(newProductID);
                request.setAttribute("PARENT_PRODUCT", parentProduct);
                request.setAttribute("PRODUCT_DETAILS_LIST", productDetailsList);
                url = PRODUCT_DETAILS_PAGE;
            } else if (request.getParameter("parentProductID") != null) {
                int parentProductID = Integer.parseInt(request.getParameter("parentProductID"));
                String parentProductName = request.getParameter("parentProductName");
                request.setAttribute("PARENT_PRODUCT_ID", parentProductID);
                request.setAttribute("PARENT_PRODUCT_NAME", parentProductName);
                url = ADD_PRODUCT_DETAIL_PAGE;          
            } else {
                List<ProductDTO> productList = productDAO.getAllProducts();
                List<BrandDTO> brands = dao.getAllBrands();
                request.setAttribute("BRAND_LIST", brands);
                request.setAttribute("PRODUCT_LIST", productList);
                url = PRODUCTS_PAGE;
            }
        } catch (SQLException e) {
            log("Error at GetProductsController: " + e.toString());
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
