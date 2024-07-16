<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.brand.BrandDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<%@page import="com.mycompany.isp392.category.CategoryDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>All Products</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Add your CSS styles here */
            .price-button {
                display: flex;
                padding: 5px 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                text-align: center;
                font-size: 13px;
                color: #333;
                background-color: #fff;
                margin: 10px 0;
                text-decoration: none;
                width: 150px; /* Adjust the width as needed */
                margin: 5px auto; /* Center the box horizontally */
                transition: background-color 0.3s ease;
            }
            .price-button:hover {
                background-color: #f0f0f0;
            }
            .button-container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            a {
                text-decoration: none;
                color: #000;
            }
            a:hover {
                color: #c53337;
                text-decoration: none;
            }
            .product-card {
                border: 1px solid #eee;
                border-radius: 5px;
                padding: 10px;
                text-align: center;
                margin-bottom: 20px;
            }
            .product-card img {
                max-width: 100%;
                height: auto;
            }
            .container-fluid-a {
                background: #1E1E27;
                color: #fff;
            }
            .product-grid {
                font-family: 'Poppins', sans-serif;
                text-align: center;
            }
            .product-grid .product-image {
                overflow: hidden;
                position: relative;
                z-index: 1;
            }
            .product-grid .product-image a.image {
                display: block;
            }
            .product-grid .product-image img {
                width: 100%;
                height: auto;
            }
            .product-grid .product-discount-label {
                color: #fff;
                background: #c53337;
                font-size: 13px;
                font-weight: 600;
                line-height: 25px;
                padding: 0 20px;
                position: absolute;
                top: 10px;
                left: 0;
            }
            .product-grid .product-links {
                padding: 0;
                margin: 0;
                list-style: none;
                position: absolute;
                top: 10px;
                right: -50px;
                transition: all .5s ease 0s;
            }
            .product-grid:hover .product-links {
                right: 10px;
            }
            .product-grid .product-links li button {
                color: #333;
                background: transparent;
                font-size: 17px;
                line-height: 38px;
                width: 38px;
                height: 38px;
                display: block;
                transition: all 0.3s;
            }
            .product-grid .product-links li button:hover {
                color: #c53337;
            }
            .product-grid .add-to-cart {
                background: black;
                color: #fff;
                font-size: 16px;
                text-transform: uppercase;
                letter-spacing: 2px;
                width: 100%;
                padding: 10px 26px;
                position: absolute;
                left: 0;
                bottom: -60px;
                transition: all 0.3s ease 0s;
            }
            .sidebar-links a:hover {
                color: red;
            }
            .product-grid:hover .add-to-cart {
                bottom: 0;
            }
            .product-grid .add-to-cart:hover {
                background: rgb(199, 30, 61);
            }
            .product-grid .product-content {
                background: #fff;
                padding: 15px;
            }
            .product-grid .title {
                font-size: 16px;
                font-weight: 600;
                text-transform: capitalize;
                margin: 0 0 7px;
            }
            .product-grid .title a {
                color: black;
                transition: all 0.3s ease 0s;
                text-decoration: none;
            }
            .product-grid .title a:hover {
                color: #a5ba8d;
            }
            .product-grid .price {
                color: #c53337;
                font-size: 14px;
                font-weight: 600;
            }
            .product-grid .price span {
                color: #888;
                font-size: 13px;
                font-weight: 400;
                text-decoration: line-through;
                margin-left: 3px;
                display: inline-block;
            }
            .pagination-container {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .pagination-container .pagination {
                display: flex;
                list-style: none;
                padding: 0;
            }
            .pagination-container .pagination li {
                margin: 0 5px;
            }
            .pagination-container .pagination li a {
                display: block;
                padding: 10px 15px;
                border: 1px solid #ddd;
                color: #333;
                text-decoration: none;
            }
            .pagination-container .pagination li a:hover {
                background-color: #f0f0f0;
                border-color: #ddd;
            }
            .pagination-container .pagination .active a {
                background-color: #c53337;
                color: #fff;
                border-color: #c53337;
            }
            .pagination-container .pagination .disabled a {
                color: #999;
                pointer-events: none;
            }

            .product_image1 {
                width: 270px;  /* Đặt kích thước cố định cho phần tử cha */
                height: 250px; /* Đặt kích thước cố định cho phần tử cha */
                overflow: hidden; /* Ẩn phần nào của hình ảnh vượt quá kích thước khung chứa */
            }

            .product_image1 img {
                width: 100%;
                height: 100%;
                object-fit: cover; /* Bạn có thể thử 'cover' hoặc 'contain' tùy theo mong muốn */
            }
        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>
        <div class="container-fluid-a p-3 mt-5">
            <h2 style="color: #fff; padding-left: 40px;">All Products</h2>
            <p style="padding-left: 40px; color: #fff">Buy and Sell items for all people on ISP392. Every item is ISP392 Verified.</p>
        </div>
        <div class="container mt-5 mb-5">
            <div class="row">
                <div class="col-md-3">
                    <hr>
                    <div>
                        <h4 style="color: #c53337">FILTER</h4>
                    </div>
                    <hr>
                    <div>
                        <h4 style="color: #c53337">CATEGORY</h4>
                        <ul class="list-unstyled">
                            <% List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
                                if (categories != null) {
                                    for (CategoryDTO category : categories) {
                            %>
                            <li><label><input type="checkbox" name="category" class="category-filter" value="<%= category.getCategoryID() %>"> <%= category.getCategoryName() %></label></li>
                                    <%    }
                            } %>
                        </ul>
                    </div>
                    <hr>
                    <div>
                        <h4 style="color: #c53337">BRANDS</h4>
                        <ul class="list-unstyled">
                            <% List<BrandDTO> brands = (List<BrandDTO>) request.getAttribute("brands");
                                if (brands != null) {
                                    for (BrandDTO brand : brands) {
                            %>
                            <li><label><input type="checkbox" name="brand" class="brand-filter" value="<%= brand.getBrandID() %>"> <%= brand.getBrandName() %></label></li>
                                    <%    }
                            } %>
                        </ul>
                    </div>
                    <hr>
                    <div>
                        <h4 style="color: #c53337">PRICE</h4>
                        <ul class="list-unstyled">
                            <li><label><input type="checkbox" name="price" class="price-filter" value="0-2000000"> Below 2.000.000</label></li>
                            <li><label><input type="checkbox" name="price" class="price-filter" value="2000000-5000000"> From 2.000.000 to 5.000.000</label></li>
                            <li><label><input type="checkbox" name="price" class="price-filter" value="5000000-10000000"> From 5.000.000 to 10.000.000</label></li>
                            <li><label><input type="checkbox" name="price" class="price-filter" value="10000000plus"> Above 10.000.000</label></li>
                        </ul>
                    </div>

                    <hr>
                </div>
                <div class="col-md-9">
                    <h5 style="color: grey;">
                        <a style="color: grey; text-decoration: none" href="US_index.jsp">Home ></a>
                        <a style="color: grey; text-decoration: none" href="ViewAllProductController">All Products</a>
                    </h5>
                    <div class="row" id="products-container">
                        <% 
                            List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
                            List<ProductDetailsDTO> productDetails = (List<ProductDetailsDTO>) request.getAttribute("productDetails");

                            if (products != null && productDetails != null) {
                                for (ProductDTO product : products) {
                                    ProductDetailsDTO firstDetail = null;
                                    for (ProductDetailsDTO detail : productDetails) {
                                        if (detail.getProductID() == product.getProductID()) {
                                            firstDetail = detail;
                                            break;
                                        }
                                    }
                                    if (firstDetail != null) {
                        %>
                        <div class="col-md-4 mb-4">
                            <div class="product-grid">
                                <div class="product-image">
                                    <a href="MainController?action=Get_product_detail&productID=<%= product.getProductID() %>&color=<%= firstDetail.getColor() %>" >
                                        <div class="product_image1">
                                            <img  src="<%= firstDetail.getImage().split(";")[0] %>" alt="<%= product.getProductName() %>">
                                        </div>
                                    </a>
                                    <ul class="product-links">
                                        <li><button type="submit" name="action" value="AddToWishlist"><i class="fa fa-heart"></i></button></li>
                                    </ul>
                                </div>
                                <div class="product-content">
                                    <h3 class="title"><a href="MainController?action=Get_product_detail&productID=<%= product.getProductID() %>&color=<%= firstDetail.getColor() %>"><%= product.getProductName() %></a></h3>
                                    <div class="price">$<%= firstDetail.getPrice() %> <span></span></div>
                                </div>
                            </div>
                        </div>
                        <% 
                                    }
                                }
                            }
                        %>
                    </div>
                    <div id="pagination-container" class="mt-3">
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center mt-3">
                                <li class="page-item <%= (request.getAttribute("currentPage") != null && (int) request.getAttribute("currentPage") == 1) ? "disabled" : "" %>">
                                    <a class="page-link" href="#" onclick="loadFilteredProducts(<%= (int) request.getAttribute("currentPage") - 1 %>)" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <% 
                                    int totalPages = (int) request.getAttribute("totalPages");
                                    int currentPage = (int) request.getAttribute("currentPage");
                                    for (int i = 1; i <= totalPages; i++) { 
                                %>
                                <li class="page-item <%= currentPage == i ? "active" : "" %>">
                                    <a class="page-link" href="#" onclick="loadFilteredProducts(<%= i %>)"><%= i %></a>
                                </li>
                                <% } %>
                                <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                                    <a class="page-link" href="#" onclick="loadFilteredProducts(<%= currentPage + 1 %>)" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="US_footer.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                                        $(document).ready(function () {
                                            function getSelectedValues(selector) {
                                                return $(selector + ':checked').map(function () {
                                                    return this.value;
                                                }).get();
                                            }

                                            function loadFilteredProducts(page = 1) {
                                                var selectedBrands = getSelectedValues('.brand-filter');
                                                var selectedPrices = getSelectedValues('.price-filter');
                                                var selectedCategories = getSelectedValues('.category-filter');

                                                $.ajax({
                                                    url: 'ViewAllProductController',
                                                    type: 'GET',
                                                    data: {
                                                        brands: selectedBrands,
                                                        prices: selectedPrices,
                                                        categories: selectedCategories,
                                                        page: page
                                                    },
                                                    success: function (data) {
                                                        $('#products-container').html($(data).find('#products-container').html());
                                                        $('#pagination-container').html($(data).find('#pagination-container').html());
                                                    },
                                                    error: function () {
                                                        alert('Error loading products. Please try again.');
                                                    }
                                                });
                                            }

                                            // Check the brand filter if brandID is present in the URL
                                            var brandID = new URLSearchParams(window.location.search).get('brandID');
                                            if (brandID) {
                                                $('input[name="brand"][value="' + brandID + '"]').prop('checked', true);
                                            }

                                            // Event listeners for the checkboxes
                                            $('.brand-filter, .price-filter, .category-filter').on('change', function () {
                                                loadFilteredProducts();
                                            });

                                            // Initial load
                                            loadFilteredProducts();
                                        });

                                        function resetFilters() {
                                            $('input[type="checkbox"]').prop('checked', false);
                                            loadFilteredProducts();
                                        }
        </script>
    </body>
</html>