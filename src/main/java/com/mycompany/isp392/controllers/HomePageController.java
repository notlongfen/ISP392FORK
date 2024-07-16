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
import java.util.*;
import java.util.stream.Collectors;

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

            List<ProductDTO> allProducts = productDAO.getAllProducts();
            List<ProductDTO> activeProducts = allProducts.stream().filter(p -> p.getStatus() == 1).collect(Collectors.toList());

            List<BrandDTO> brands = brandDAO.getAllBrands();
            List<BrandDTO> activeBrands = brands.stream().filter(b -> b.getStatus() == 1).collect(Collectors.toList());

            List<CategoryDTO> categories = categoryDAO.getActiveCategory();

            // Fetch product details and group by product ID and color
            Map<Integer, Map<String, List<ProductDetailsDTO>>> productDetailsByColor = new HashMap<>();
            for (ProductDTO product : activeProducts) {
                List<ProductDetailsDTO> details = productDAO.getProductDetails(product.getProductID());
                Map<String, List<ProductDetailsDTO>> detailsByColor = details.stream()
                        .collect(Collectors.groupingBy(ProductDetailsDTO::getColor));
                productDetailsByColor.put(product.getProductID(), detailsByColor);
            }

            // Fetch categories for each product
            Map<Integer, List<ChildrenCategoryDTO>> categoryListByProduct = new HashMap<>();
            for (ProductDTO product : activeProducts) {
                List<ChildrenCategoryDTO> cdCategories = categoryDAO.getCdCategoriesByProductID(product.getProductID());
                categoryListByProduct.put(product.getProductID(), cdCategories);
            }

            // Sorting for new arrivals by latest import date
            Map<Integer, java.sql.Date> latestImportDates = productDAO.getLatestImportDates();
            List<ProductDTO> newArrivals = new ArrayList<>(activeProducts);
            newArrivals.sort((p1, p2) -> {
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
            });

            // Sorting for best sellers by number of purchases
            List<ProductDTO> bestSellers = new ArrayList<>(activeProducts);
            bestSellers.sort(Comparator.comparingInt(ProductDTO::getNumberOfPurchase).reversed());

            request.setAttribute("NEW_ARRIVALS_LIST", newArrivals);
            request.setAttribute("BEST_SELLERS_LIST", bestSellers);
            request.setAttribute("BRAND_LIST", activeBrands);
            request.setAttribute("CATEGORIES_LIST", categories);
            request.setAttribute("PRODUCT_DETAILS_BY_COLOR", productDetailsByColor);
            request.setAttribute("CATEGORY_LIST_BY_PRODUCT", categoryListByProduct);

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
