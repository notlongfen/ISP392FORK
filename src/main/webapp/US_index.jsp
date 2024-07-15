<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.brand.BrandDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="com.mycompany.isp392.category.ChildrenCategoryDTO"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <style>
            body {
                margin: 0;
                padding: 0;
                height: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .main_slider_content {
                text-align: left; /* Canh trái toàn b? n?i dung trong .main_slider_content */
                color: white; /* ??i màu ch? thành tr?ng */
                position: relative;
                top: 50px; /* ?i?u ch?nh v? trí c?a toàn b? n?i dung */
                left: 30px; /* ?i?u ch?nh kho?ng cách t? l? trái */
            }

            .collection_text {
                display: block; /* ??m b?o r?ng các ph?n t? này là kh?i ?? d? c?n ch?nh */
                color: white; /* ??i màu ch? thành tr?ng */
                /*margin: 50px 0;  Kho?ng cách gi?a các ph?n t? */
                font-size: 15px;
                margin-top: -190px; /* ?i?u ch?nh giá tr? này ?? d?ch chuy?n lên trên */
                margin-bottom: 40px;
            }

            .detail_text {
                display: block; /* ??m b?o r?ng các ph?n t? này là kh?i ?? d? c?n ch?nh */
                color: white; /* ??i màu ch? thành tr?ng */
                /*margin: 10px 0;  Kho?ng cách gi?a các ph?n t? */
                font-size: 15px;
                margin-bottom: 5px;
            }

            .red_button a {
                background-color: #C53337; /* Màu n?n nút ?? */
                color: white; /* Màu ch? tr?ng */
                padding: 10px 20px; /* Kho?ng cách bên trong nút */
                text-decoration: none; /* Lo?i b? g?ch chân */
                border-radius: 5px; /* Bo góc cho nút */
                margin-top: 20px; /* Kho?ng cách phía trên nút */
                display: inline-block; /* ??m b?o nút hi?n th? nh? m?t kh?i */
            }


            /*Bestseller*/

            /*banner*/

            .banner {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
            }

            .banner-container {
                display: flex;
                justify-content: center;
                align-items: center;
            }


            .banener-row {
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .banner_item {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 300px;
                height: 300px;
                background-size: cover;
                background-position: center;
                margin: 10px;
            }

            .banner_category {
                background: #FFF;
                padding: 10px;
            }

            .banner_category a {
                text-decoration: none;
                color: #000;
                font-size: 40px;
                margin-top: -10px; /* ?i?u ch?nh giá tr? âm ?? nhích lên trên */
            }

            /*brand*/

            .brand-title span {
                font-size: 20px;
                border-bottom: 5px solid #FE4C50;
                /*???ng g?ch d??i màu*/
            }

            #slider-home  {
                border-radius: 15px; /* ?i?u ch?nh giá tr? này ?? thay ??i ?? bo góc */
                border: 1px solid grey; /* Thêm vi?n xung quanh hình ?nh, thay #000 b?ng màu mong mu?n */
                padding: 10px;
            }

            /*Support*/
            .product_image1 {
                width: 220px;  /* Đặt kích thước cố định cho phần tử cha */
                height: 200px; /* Đặt kích thước cố định cho phần tử cha */
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

            <!-- Banner -->
            <div class="banner">
                <div class="banner-container">
                    <div class="banener-row">
                        <form action="MainController" class="col-md-4">
                            <div class="col-md-4" >
                                <div class="banner_item align-items-center mx-5" style="background-image:url('images/s3.png'); right: 100px">
                                    <div class="banner_category">
                                        <a href="MainController?action=Category&category=Women">Women's</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <form action="MainController" method="post" class="col-md-4">
                            <div class="col-md-4">
                                <div class="banner_item align-items-center mx-5" style="background-image:url('images/s2.png'); left: -10px">
                                    <div class="banner_category">
                                        <a href="MainController?action=Category&category=Men">men's</a>
                                    </div>
                                </div>
                            </div>
                        </form>
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
                                    <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center active is-checked" data-filter="*">All</li>
                                        <%
                                            List<CategoryDTO> categoriesList = (List<CategoryDTO>) request.getAttribute("CATEGORIES_LIST");
                                            if (categoriesList != null) {
                                                for (CategoryDTO category : categoriesList) {
                                        %>
                                    <li class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center" data-filter=".category<%= category.getCategoryID() %>"><%= category.getCategoryName() %></li>
                                        <%
                                                }
                                            }
                                        %>
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
                                            List<ChildrenCategoryDTO> categories = (List<ChildrenCategoryDTO>) request.getAttribute("CATEGORY_LIST_" + product.getProductID());
                                            if (categories != null) {
                                                for (ChildrenCategoryDTO category : categories) {
                                                    categoryClass += "category" + category.getParentID() + " ";
                                                }
                                            }
                                            List<ProductDetailsDTO> productDetailsList = (List<ProductDetailsDTO>) request.getAttribute("PRODUCT_DETAILS_LIST_" + product.getProductID());
                                            if (productDetailsList != null && !productDetailsList.isEmpty()) {
                                                ProductDetailsDTO firstDetail = productDetailsList.get(0);
                                %>
                                <div class="product-item <%= categoryClass.trim() %>">
                                    <div class="product discount product_filter">
                                        <div class="product_image1">
                                            <a href="MainController?action=Get_product_detail&productID=<%= product.getProductID() %>&color=<%= firstDetail.getColor() %>" class="image">
                                                <img src="<%= firstDetail.getImage().split(";")[0] %>" alt="<%= product.getProductName() %>">
                                            </a>
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="MainController?action=Get_product_detail&productID=<%= product.getProductID() %>&color=<%= firstDetail.getColor() %>"><%= product.getProductName() %></a></h6>
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
                                                List<ChildrenCategoryDTO> categories = (List<ChildrenCategoryDTO>) request.getAttribute("CATEGORY_LIST_" + product.getProductID());
                                                if (categories != null) {
                                                    for (ChildrenCategoryDTO category : categories) {
                                                        categoryClass += "category" + category.getParentID() + " ";
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
                                                    <a href="MainController?action=Get_product_detail&productID=<%= product.getProductID() %>&color=<%= firstDetail.getColor() %>" class="image">
                                                        <img src="<%= firstDetail.getImage().split(";")[0] %>" alt="<%= product.getProductName() %>">
                                                    </a>
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
                                    <a href="ViewAllProductController?brandID=<%= brand.getBrandID() %>">
                                        <img src="<%= brand.getImage() %>" alt="<%= brand.getBrandName() %>" class="img-responsive" style="width: 180px; height: 180px;">
                                    </a>
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
