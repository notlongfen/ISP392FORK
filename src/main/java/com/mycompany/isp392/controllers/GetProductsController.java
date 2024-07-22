package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.category.CategoryDAO;
import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.product.ProductError;
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
    private static final String ADD_PRODUCT_DETAIL_PAGE = "AD_CreateProductDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            BrandDAO dao = new BrandDAO();
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            // Retrieve and clear success message from session
             String successMessage = (String) request.getSession().getAttribute("SUCCESS_MESSAGE");
            if (successMessage != null) {
                request.setAttribute("SUCCESS_MESSAGE", successMessage);
                request.getSession().removeAttribute("SUCCESS_MESSAGE");
            }
            // Retrieve and clear error message from session
            ProductError productError = (ProductError) request.getSession().getAttribute("PRODUCT_ERROR");
            if (productError != null) {
                request.setAttribute("PRODUCT_ERROR", productError);
                request.getSession().removeAttribute("PRODUCT_ERROR");
            }

            // Retrieve and clear general error message from session
            String errorMessage = (String) request.getSession().getAttribute("ERROR_MESSAGE");
            if (errorMessage != null) {
                request.setAttribute("ERROR_MESSAGE", errorMessage);
                request.getSession().removeAttribute("ERROR_MESSAGE");
            }

            int entriesPerPage = 15; // Fixed number of entries per page
            int currentPage = 1;
            if (request.getParameter("page") != null) {
                currentPage = Integer.parseInt(request.getParameter("page"));
            }

            // get product details page
            if (request.getParameter("productID") != null) {
                int productID = Integer.parseInt(request.getParameter("productID"));
                List<ProductDetailsDTO> productDetailsList = productDAO.getProductDetails(productID);
                ProductDTO parentProduct = productDAO.selectProduct(productID);
                request.setAttribute("PARENT_PRODUCT", parentProduct);
                request.setAttribute("PRODUCT_DETAILS_LIST", productDetailsList);
                url = PRODUCT_DETAILS_PAGE;
            } else if (request.getAttribute("newProductID") != null) {
                int newProductID = (int) request.getAttribute("newProductID");
                List<ProductDetailsDTO> productDetailsList = productDAO.getProductDetails(newProductID);
                ProductDTO parentProduct = productDAO.selectProduct(newProductID);
                request.setAttribute("PARENT_PRODUCT", parentProduct);
                request.setAttribute("PRODUCT_DETAILS_LIST", productDetailsList);
                url = PRODUCT_DETAILS_PAGE;
                //Add product details page
            } else if (request.getParameter("parentProductID") != null) {
                int parentProductID = Integer.parseInt(request.getParameter("parentProductID"));
                String parentProductName = request.getParameter("parentProductName");
                request.setAttribute("PARENT_PRODUCT_ID", parentProductID);
                request.setAttribute("PARENT_PRODUCT_NAME", parentProductName);
                url = ADD_PRODUCT_DETAIL_PAGE;
            } else {
                int totalProducts = productDAO.getTotalProducts();
                List<ProductDTO> productList = productDAO.getProductsByPage(currentPage, entriesPerPage);
                List<BrandDTO> brands = dao.getAllBrands();
                request.setAttribute("BRAND_LIST", brands);
                request.setAttribute("PRODUCT_LIST", productList);
                request.setAttribute("CURRENT_PAGE", currentPage);
                request.setAttribute("TOTAL_PAGES", (int) Math.ceil((double) totalProducts / entriesPerPage));

                for (ProductDTO product : productList) {
                    List<CategoryDTO> categories = categoryDAO.getCategoriesByProductID(product.getProductID());
                    request.setAttribute("CATEGORY_LIST_" + product.getProductID(), categories);
                }
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
