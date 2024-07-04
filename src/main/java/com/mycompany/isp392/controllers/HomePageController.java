package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.category.CategoryDAO;
import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.category.ChildrenCategoryDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HomePageController", urlPatterns = {"/HomePageController"})
public class HomePageController extends HttpServlet {

    private static final String ERROR = "US_SignIn.jsp";
    private static final String INDEX_PAGE = "US_index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            BrandDAO brandDAO = new BrandDAO();
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            // Fetch all products
            List<ProductDTO> allProducts = productDAO.getAllProducts();
            List<ProductDTO> allProducts1 = productDAO.getAllProducts();
            List<ProductDTO> activeProducts = new ArrayList<>();
             List<ProductDTO> activeProducts1 = new ArrayList<>();
            List<BrandDTO> brands = brandDAO.getAllBrands();
            List<BrandDTO> activeBrands = new ArrayList<>();
            List<CategoryDTO> categories = categoryDAO.getActiveCategory(); // Fetch active categories

            // Filter active products
            for (ProductDTO product : allProducts) {
                if (product.getStatus() == 1) {
                    activeProducts.add(product);
                }
            }
              for (BrandDTO brand : brands) {
                if (brand.getStatus() == 1) {
                    activeBrands.add(brand);
                }
            }
             for (ProductDTO product : allProducts1) {
                if (product.getStatus() == 1) {
                    activeProducts1.add(product);
                }
            }
            for (ProductDTO product : activeProducts) {
                List<ChildrenCategoryDTO> cdCategories = categoryDAO.getCdCategoriesByProductID(product.getProductID());
                List<ProductDetailsDTO> productDetailsList = productDAO.getProductDetails(product.getProductID());
                request.setAttribute("CATEGORY_LIST_" + product.getProductID(), cdCategories);
                request.setAttribute("PRODUCT_DETAILS_LIST_" + product.getProductID(), productDetailsList);
            }
             for (ProductDTO product : activeProducts1) {
                List<ChildrenCategoryDTO> cdCategories = categoryDAO.getCdCategoriesByProductID(product.getProductID());
                List<ProductDetailsDTO> productDetailsList = productDAO.getProductDetails(product.getProductID());
                request.setAttribute("CATEGORY_LIST_" + product.getProductID(), cdCategories);
                request.setAttribute("PRODUCT_DETAILS_LIST_" + product.getProductID(), productDetailsList);
            }

            // Fetch the latest import dates
            Map<Integer, java.sql.Date> latestImportDates = productDAO.getLatestImportDates();

            // Sorting for new arrivals
            Collections.sort(activeProducts1, new Comparator<ProductDTO>() {
                @Override
                public int compare(ProductDTO p1, ProductDTO p2) {
                    Date date1 = latestImportDates.get(p1.getProductID());
                    Date date2 = latestImportDates.get(p2.getProductID());
                    if (date1 == null && date2 == null) {
                        return 0;
                    } else if (date1 == null) {
                        return 1;
                    } else if (date2 == null) {
                        return -1;
                    }
                    return date2.compareTo(date1);
                }
            });

            request.setAttribute("NEW_ARRIVALS_LIST", activeProducts1);

            // Sorting for best sellers
            Collections.sort(activeProducts, new Comparator<ProductDTO>() {
                @Override
                public int compare(ProductDTO p1, ProductDTO p2) {
                    return Integer.compare(p2.getNumberOfPurchase(), p1.getNumberOfPurchase());
                }
            });

            request.setAttribute("BEST_SELLERS_LIST", activeProducts);

            request.setAttribute("BRAND_LIST", activeBrands);
            request.setAttribute("CATEGORIES_LIST", categories); // Set categories as a request attribute

            url = INDEX_PAGE;
        } catch (Exception e) {
            log("Error at HomePageController: " + e.toString());
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
        return "Home Page Controller";
    }
}

