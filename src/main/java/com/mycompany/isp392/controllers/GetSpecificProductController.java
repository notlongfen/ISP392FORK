package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.category.CategoryDAO;
import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.category.CategoryUtils;
import com.mycompany.isp392.category.ChildrenCategoryDTO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "GetSpecificProductController", urlPatterns = {"/GetSpecificProductController"})
public class GetSpecificProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String EDIT_PRODUCT_PAGE = "AD_EditProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            ProductDTO product = productDAO.selectProduct(productID);
            List<ChildrenCategoryDTO> categoryList = categoryDAO.getListCDCategory();
            List<CategoryDTO> productCategories = categoryDAO.getCategoriesByProductID(productID);
            if (product != null) {
                request.setAttribute("PRODUCT", product);
                request.setAttribute("BRAND_LIST", brandDAO.getAllBrands());
                request.setAttribute("CATEGORY_LIST", categoryList);
                request.setAttribute("PRODUCT_CATEGORIES", productCategories);
                url = EDIT_PRODUCT_PAGE;
            } else {
                request.setAttribute("ERROR_MESSAGE", "Product not found!");
            }
        } catch (Exception e) {
            log("Error at GetSpecificProductController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "Error: " + e.getMessage());
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
