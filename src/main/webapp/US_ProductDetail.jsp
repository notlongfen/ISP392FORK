 <%-- 
    Document   : ProductDetail2
    Created on : May 21, 2024, 8:07:29 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Single Product</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" href="plugins/themify-icons/themify-icons.css">
        <link rel="stylesheet" type="text/css" href="plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="styles/single_styles.css">
        <link rel="stylesheet" type="text/css" href="styles/single_responsive.css">    

        <style>
            #add_to_wishlist:hover {
                background-color: #f0f0f0; /* Màu nền màu nâu nhạt khi nút được hover */
                color: black; /* Màu chữ */
                transform: scale(1.05); /* Scale nút lên 1.05 khi hover */
            }

            #add_to_cart:hover {
                /*color: black;  Màu chữ */
                transform: scale(1.05); /* Scale nút lên 1.05 khi hover */
            }

            .quantity .btn {
                width: 40px;
                height: 40px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .quantity .border {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 40px;
                font-size: 1rem;
            }

            .sizes {
                display: flex;
                flex-wrap: wrap;
            }
            .size {
                flex: 1 1 30%; /* Điều chỉnh chiều rộng để mỗi hàng có 3 nút */
                /*font-size: 30px;  Tăng kích thước chữ để nút to hơn */
                /*padding: 10px 15px;  Tăng padding để nút to hơn */
                /*margin: 30px;  Khoảng cách giữa các nút */
                /*box-sizing: border-box;  Đảm bảo padding và margin không ảnh hưởng đến kích thước nút */
            }
        </style>
    </head>

    <body>

        <div class="super_container">           

            <%@include file="US_header.jsp" %>

            <div class="fs_menu_overlay"></div>

            <!-- Hamburger Menu -->
            <div class="container single_product_container">
                <div class="row">
                    <div class="col">

                        <!-- Breadcrumbs -->

                        <div class="breadcrumbs d-flex flex-row align-items-center">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="categories.html"><i class="fa fa-angle-right" aria-hidden="true"></i>Men's</a></li>
                                <li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>Jordan 1 Retro High Light Smoke Grey</a></li>
                            </ul>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-7">
                        <div class="single_product_pics">
                            <div class="row">
                                <div class="col-lg-3 thumbnails_col order-lg-1 order-2">
                                    <div class="single_product_thumbnails">
                                        <ul>
                                            <li><img src="images/s9.png" alt="" data-image="images/s9.png"></li>
                                            <li class="active"><img src="images/s9.png" alt="" data-image="images/s9.png"></li>
                                            <li><img src="images/s9.png" alt="" data-image="images/s9.png"></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-lg-9 image_col order-lg-2 order-1">
                                    <div class="single_product_image">
                                        <div class="single_product_image_background" style="background-image:url(images/s9.png)"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="product_details">
                            <div class="product_details_title">
                                <h2>Jordan 1 Retro High Light Smoke Grey</h2>
                                <p>Nam tempus turpis at metus scelerisque placerat nulla deumantos solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis ut...</p>
                            </div>
                            <div class="free_delivery d-flex flex-row align-items-center justify-content-center">
                                <span class="ti-truck"></span><span>free delivery</span>
                            </div>
                            <div class="original_price"><span>$629.99</span></div>
                            <div class="product_price" style="color: #C53337"><span>$495.00</span></div>
                            <!--                            <ul class="star_rating">
                                                            <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                            <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                            <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                            <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                            <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                                        </ul>-->
                            <div style="margin-top: 30px;">
                                <label style="font-size: 20px;">Select size:</label>
                                <div class="sizes">
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 37.5</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 38</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 39</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 39.5</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 40</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 40.5</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 41</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 41.5</button>
                                    <button class="size btn btn-outline-dark btn-sm m-1">US 42</button>
                                </div>
                            </div>

                            <div class="quantity d-flex flex-column flex-sm-row align-items-sm-center mb-5">
                                <div class="col-md-4 text-sm-right text-center ">
                                    <span style="font-size: 20px;">Quantity: </span>
                                </div>
                                <div class="col-md-8 d-flex justify-content-center align-items-center">
                                    <button id="decrease" class="btn btn-dark me-2">-</button>
                                    <span class="border px-3 py-2" style="width: 80px; text-align: center; width: 100%" id="quantity">1</span>
                                    <button id="increase" class="btn btn-dark ms-2">+</button>
                                </div>
                            </div>

                            <div class="row justify-content-center">
                                <div class="col">
                                    <div class="d-grid gap-2">
                                        <button id="add_to_cart" class="btn btn-dark" style="background: black; border: 3px solid black;"><b>Add to Cart</b></button>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-2 justify-content-center">
                                <div class="col">
                                    <div class="d-grid gap-2">
                                        <button id="add_to_wishlist" class="btn btn-outline-dark" style="background: white; border: 2px solid black;">
                                            <b>Favorite</b></button>
                                        
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>


                    <!-- Tabs -->

                    <div class="tabs_section_container">

                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="tabs_container"> <hr>                              
                                        <ul class="tabs d-flex flex-sm-row flex-column align-items-left align-items-md-center justify-content-center">
                                            <li ><span style="font-size: 50px; color: #C53337"><b>Description</b></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">

                                    <!-- Tab Description -->

                                    <div id="tab_1" class="tab_container active">
                                        <div class="row">
                                            <div class="col-lg-5 desc_col">
                                                <!--                                                <div class="tab_title">
                                                                                                    <h4>Description</h4>
                                                                                                </div>-->
                                                <div class="tab_text_block">
                                                    <h2>Jordan 1 Retro High 
                                                        Light Smoke Grey</h2>
                                                    <p>Nam tempus turpis at metus scelerisque placerat nulla deumantos solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis ut...</p>
                                                </div>
                                                <div class="tab_image">
                                                    <img src="images/s9.png" alt="">
                                                </div>
                                                <div class="tab_text_block">
                                                    <h2>Jordan 1 Retro High 
                                                        Light Smoke Grey</h2>
                                                    <p>Nam tempus turpis at metus scelerisque placerat nulla deumantos solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis ut...</p>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 offset-lg-2 desc_col">
                                                <div class="tab_image">
                                                    <img src="images/s9.png" alt="">
                                                </div>
                                                <div class="tab_text_block">
                                                    <h2>Jordan 1 Retro High 
                                                        Light Smoke Grey</h2>
                                                    <p>Nam tempus turpis at metus scelerisque placerat nulla deumantos solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis ut...</p>
                                                </div>
                                                <div class="tab_image desc_last">
                                                    <img src="images/s9.png" alt="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Tab Additional Info -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer -->
            <%@include file="US_footer.jsp" %>
            <script src="javascript/jquery-3.2.1.min.js"></script>
            <script src="styles/bootstrap4/popper.js"></script>
            <script src="styles/bootstrap4/bootstrap.min.js"></script>
            <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
            <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
            <script src="plugins/easing/easing.js"></script>
            <script src="plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
            <script src="javascript/single_custom.js"></script>
            <script src="javascript/single_product.js"></script>
    </body>

</html>
