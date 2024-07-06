package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ViewAllProductController extends HttpServlet {

    private static final String SUCCESS_PAGE = "US_AllProducts.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final int ROWS_PER_PAGE = 10; // Number of products per page

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            ProductDAO productDAO = new ProductDAO();
            BrandDAO brandDAO = new BrandDAO();

            // Get the total number of products
            int totalProducts = productDAO.getTotalProductCount();

            // Calculate the total number of pages
            int totalPages = (int) Math.ceil((double) totalProducts / ROWS_PER_PAGE);

            // Get the current page from the request parameter, default is 1 if not provided
            int currentPage = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                try {
                    currentPage = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    currentPage = 1;
                }
            }

            // Fetch paginated products and additional data
            List<ProductDTO> paginatedProducts = productDAO.getAllProductPags(currentPage, ROWS_PER_PAGE);
            List<ProductDetailsDTO> allProductDetails = productDAO.getAllProductDetails();
            List<BrandDTO> allBrands = brandDAO.getAllBrands();

            if (paginatedProducts != null && allProductDetails != null && allBrands != null) {
                request.setAttribute("products", paginatedProducts);
                request.setAttribute("productDetails", allProductDetails);
                request.setAttribute("brands", allBrands);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("totalPages", totalPages);
                url = SUCCESS_PAGE; // Success, forward to success page
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return "ViewAllProductController Servlet";
    }

    // Method to generate HTML for products based on request attributes
    private String getProductsHtml(HttpServletRequest request) {
        StringBuilder htmlBuilder = new StringBuilder();
        List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
        List<ProductDetailsDTO> productDetails = (List<ProductDetailsDTO>) request.getAttribute("productDetails");
        Set<String> seenImages = new HashSet<>(); // HashSet to track unique images

        if (products != null && productDetails != null) {
            for (ProductDTO product : products) {
                for (ProductDetailsDTO productDetail : productDetails) {
                    if (product.getProductID() == productDetail.getProductID()) {
                        String image = productDetail.getImage();
                        if (!seenImages.contains(image)) {
                            seenImages.add(image);

                            // Build HTML for product
                            htmlBuilder.append("<div class=\"col-md-3 col-sm-6\">")
                                    .append("<div class=\"product-grid\">")
                                    .append("<div class=\"product-image\">")
                                    .append("<a href=\"MainController?action=Get_product_detail&productID=").append(product.getProductID()).append("\" class=\"image\">")
                                    .append("<img src=\"images/newImage/").append(image).append("\" alt=\"").append(product.getProductName()).append("\">")
                                    .append("</a>")
                                    .append("<ul class=\"product-links\">")
                                    .append("<li><a href=\"#\"><i class=\"fa fa-heart\"></i></a></li>")
                                    .append("</ul>")
                                    .append("<a href=\"\" class=\"add-to-cart\">Add to Cart</a>")
                                    .append("</div>")
                                    .append("<div class=\"product-content\">")
                                    .append("<h3 class=\"title\"><a href=\"MainController?action=Get_product_detail&productID=").append(product.getProductID()).append("\">").append(product.getProductName()).append("</a></h3>")
                                    .append("<div class=\"price\">").append(productDetail.getPrice()).append(" <span></span></div>")
                                    .append("</div>")
                                    .append("</div>")
                                    .append("</div>");
                        }
                    }
                }
            }
        }
        return htmlBuilder.toString();
    }

    // Method to generate HTML for pagination based on request attributes
    private String getPaginationHtml(HttpServletRequest request) {
        StringBuilder htmlBuilder = new StringBuilder();
        int currentPage = (int) request.getAttribute("currentPage");
        int totalPages = (int) request.getAttribute("totalPages");
        htmlBuilder.append("<nav aria-label=\"Page navigation\">")
                .append("<ul class=\"pagination justify-content-center mt-3\">")
                .append("<li class=\"page-item ").append(currentPage == 1 ? "disabled" : "").append("\">")
                .append("<a class=\"page-link\" href=\"?page=").append(currentPage - 1).append("\" aria-label=\"Previous\">")
                .append("<span aria-hidden=\"true\">&laquo;</span>")
                .append("</a>")
                .append("</li>");
        for (int i = 1; i <= totalPages; i++) {
            htmlBuilder.append("<li class=\"page-item ").append(currentPage == i ? "active" : "").append("\">")
                    .append("<a class=\"page-link\" href=\"?page=").append(i).append("\">").append(i).append("</a>")
                    .append("</li>");
        }
        htmlBuilder.append("<li class=\"page-item ").append(currentPage == totalPages ? "disabled" : "").append("\">")
                .append("<a class=\"page-link\" href=\"?page=").append(currentPage + 1).append("\" aria-label=\"Next\">")
                .append("<span aria-hidden=\"true\">&raquo;</span>")
                .append("</a>")
                .append("</li>")
                .append("</ul>")
                .append("</nav>");
        return htmlBuilder.toString();
    }
}
