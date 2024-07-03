<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.brand.BrandDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="com.mycompany.isp392.category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<html lang="en">
<head>
    <title>ISP392Shop</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Colo Shop Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<body>

<div class="super_container">
    <%@include file="US_header.jsp" %>

    <!-- Slider -->
    <div class="main_slider mt-1" style="background-image:url(images/s1.png)">
        <div class="container fill_height">
            <div class="row align-items-center fill_height">
                <div class="col">
                    <div class="main_slider_content">
                        <h8 class="collection_text">Spring / Summer Collection 2024</h8>
                        <h1 style="color: #FFF; font-size: 90px; margin-bottom: 40px;">SALE UP TO 50%</h1>
                        <h8 class="detail_text">View detail product below</h8>
                        <div class="red_button shop_now_button"><a href="US_AllProducts.jsp">Shop now</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- New Arrivals -->
    <div class="new_arrivals">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <div class="section_title new_arrivals_title">
                        <h2>New Arrivals</h2>
                    </div>
                </div>
            </div>
            <div class="row align-items-center">
                <div class="col text-center">
                    <div class="new_arrivals_sorting">
                        <ul class="arrivals_grid_sorting clearfix button-group filters-button-group">
                            <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center active" data-filter="*">All</li>
                            <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center" data-filter=".women">Women's</li>
                            <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center" data-filter=".men">Men's</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="product-grid" data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>
                        <!-- Loop through New Arrivals -->
                        <%
                            List<ProductDTO> newArrivalsList = (List<ProductDTO>) request.getAttribute("NEW_ARRIVALS_LIST");
                            if (newArrivalsList != null) {
                                for (ProductDTO product : newArrivalsList) {
                                    String categoryClass = "";
                                    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("CATEGORY_LIST_" + product.getProductID());
                                    if (categories != null) {
                                        for (CategoryDTO category : categories) {
                                            categoryClass += category.getCategoryName().toLowerCase() + " ";
                                        }
                                    }
                                    List<ProductDetailsDTO> productDetailsList = (List<ProductDetailsDTO>) request.getAttribute("PRODUCT_DETAILS_LIST_" + product.getProductID());
                                    if (productDetailsList != null && !productDetailsList.isEmpty()) {
                                        ProductDetailsDTO firstDetail = productDetailsList.get(0);
                        %>
                        <div class="product-item <%= categoryClass.trim() %>">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="<%= firstDetail.getImage().split(";")[0] %>" alt="<%= product.getProductName() %>">
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html"><%= product.getProductName() %></a></h6>
                                    <div class="product_price" style="color: #C53337">$<%= firstDetail.getPrice() %></div>
                                </div>
                            </div>
                        </div>
                        <% 
                                    }
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Best Sellers -->
    <div class="best_sellers">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <div class="section_title new_arrivals_title">
                        <h2>Best Sellers</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="product_slider_container">
                        <div class="owl-carousel owl-theme product_slider">
                            <!-- Loop through Best Sellers -->
                            <%
                                List<ProductDTO> bestSellersList = (List<ProductDTO>) request.getAttribute("BEST_SELLERS_LIST");
                                if (bestSellersList != null) {
                                    for (ProductDTO product : bestSellersList) {
                                        String categoryClass = "";
                                        List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("CATEGORY_LIST_" + product.getProductID());
                                        if (categories != null) {
                                            for (CategoryDTO category : categories) {
                                                categoryClass += category.getCategoryName().toLowerCase() + " ";
                                            }
                                        }
                                        List<ProductDetailsDTO> productDetailsList = (List<ProductDetailsDTO>) request.getAttribute("PRODUCT_DETAILS_LIST_" + product.getProductID());
                                        if (productDetailsList != null && !productDetailsList.isEmpty()) {
                                            ProductDetailsDTO firstDetail = productDetailsList.get(0);
                            %>
                            <div class="owl-item product_slider_item <%= categoryClass.trim() %>">
                                <div class="product-item">
                                    <div class="product discount">
                                        <div class="product_image">
                                            <img src="<%= firstDetail.getImage().split(";")[0] %>" alt="<%= product.getProductName() %>">
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="single.html"><%= product.getProductName() %></a></h6>
                                            <div class="product_price" style="color: #C53337">$<%= firstDetail.getPrice() %></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% 
                                        }
                                    }
                                }
                            %>
                        </div>
                        <!-- Slider Navigation -->
                        <div class="product_slider_nav_left product_slider_nav d-flex align-items-center justify-content-center flex-column">
                            <i class="fa fa-chevron-left" aria-hidden="true"></i>
                        </div>
                        <div class="product_slider_nav_right product_slider_nav d-flex align-items-center justify-content-center flex-column">
                            <i class="fa fa-chevron-right" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Brand Section -->
    <div class="brands">
        <div class="container">
            <div class="brands-inner">
                <div class="brand-title">
                    <span>Brands</span>
                </div>
                <div id="slider-home" class="brands">
                    <div id="brand-carousel" class="owl-carousel owl-theme">
                        <!-- Loop through Brands -->
                        <%
                            List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute("BRAND_LIST");
                            if (brandList != null) {
                                for (BrandDTO brand : brandList) {
                        %>
                        <div class="item">
                            <img src="<%= brand.getImage() %>" alt="<%= brand.getBrandName() %>" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="US_footer.jsp" %>
    <button id="openFormButton"><i class='fas fa-pen'></i></button>

    <div id="popupForm" class="form-popup">
        <form class="form-container">
            <h4 style="text-align: center">PLEASE FILL IN THE BLANKS TO REQUEST SUPPORT</h4>
            <label for="email"><b>Email</b></label>
            <input type="text" id="email" name="email" required>

            <label for="content"><b>Content</b></label>
            <textarea id="content" name="content" required></textarea>

            <button type="submit" class="btn">Send</button>
            <button type="button" class="btn cancel" id="closeFormButton">Close</button>
        </form>
    </div>
</div>

<!-- Initialize Owl Carousel -->
<script>
    $(document).ready(function () {
        $("#brand-carousel").owlCarousel({
            loop: true,
            margin: 10,
            nav: false,
            dots: true,
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 2
                },
                1000: {
                    items: 4
                }
            }
        });

        // Isotope filter
        var $grid = $('.product-grid').isotope({
            itemSelector: '.product-item',
            layoutMode: 'fitRows'
        });

        $('.filters-button-group').on('click', 'li', function () {
            var filterValue = $(this).attr('data-filter');
            $grid.isotope({ filter: filterValue });
            $('.filters-button-group li').removeClass('active');
            $(this).addClass('active');
        });
    });

    document.getElementById('openFormButton').addEventListener('click', function () {
        document.getElementById('popupForm').style.display = 'block';
    });

    document.getElementById('closeFormButton').addEventListener('click', function () {
        document.getElementById('popupForm').style.display = 'none';
    });
</script>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="US_js/custom.js"></script>
<script src="US_js/brand.js"></script>
</body>
</html>
