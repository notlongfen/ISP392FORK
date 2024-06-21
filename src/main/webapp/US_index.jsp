<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>ISP392Shop</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Colo Shop Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css"
              href="styles/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css"
              rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css"
              href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css"
              href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css"
              href="plugins/OwlCarousel2-2.2.1/animate.css">
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
            #openFormButton {
                background-color: #C53337;
                color: white;
                padding: 16px;
                border: none;
                cursor: pointer;
                /*opacity: 0.8;*/
                position: fixed;
                bottom: 23px;
                right: 28px;
                width: 56px;
                height: 56px;
                border-radius: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 16px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .form-popup {
                display: none;
                position: fixed;
                bottom: 0;
                right: 15px;
                border: 3px solid #f1f1f1;
                z-index: 9;
                background-color: white;
                width: 300px;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 400px;
                height: 500px;
            }

            .form-container {
                display: flex;
                flex-direction: column;
            }

            .form-container input[type="text"],
            .form-container textarea {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                border: none;
                background: #f1f1f1;
            }

            .form-container input[type="text"]:focus,
            .form-container textarea:focus {
                background-color: #ddd;
                outline: none;
            }

            .form-container .btn {
                background-color: #C53337;
                color: white;
                padding: 16px 20px;
                border: none;
                cursor: pointer;
                width: 100%;
                margin-bottom:10px;
                /*opacity: 0.8;*/
            }

            .form-container .cancel {
                background-color: #33363F;
            }

            .form-container .btn:hover,
            .open-button:hover {
                opacity: 10;
            }

            a.btn {
                padding: 10px 15px;
                font-size: 15px;
                border: 1px solid;
                border-radius: 5px;
                cursor: pointer;
                margin-right: 10px;
                width: 150px;
                height: 50px;
                text-align: center;
                /* C?n gi?a n?i dung ngang */
                justify-content: center;
                /* C?n gi?a n?i dung d?c */
                align-items: center;
                display: flex;
            }
            a.btn:hover{
                background-color: #FFF;
                color: #c53337;
            }
            a.btn:active{
                background-color: #c53337;
                color: #fff;
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
                        <div class="col-md-4">
                            <div class="banner_item align-items-center mx-5" style="background-image:url('images/s3.png'); right: 100px">
                                <div class="banner_category">
                                    <a href="US_Women.jsp">women's</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="banner_item align-items-center mx-5" style="background-image:url('images/s2.png'); left: -10px">
                                <div class="banner_category">
                                    <a href="US_Men.jsp">men's</a>
                                </div>
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
                        <ul
                            class="arrivals_grid_sorting clearfix button-group filters-button-group">
                            <li
                                class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center active is-checked"
                                data-filter="*">all</li>
                            <li
                                class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center"
                                data-filter=".women">women's</li>
                            <li
                                class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center"
                                data-filter=".men">men's</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="product-grid"
                         data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>

                        <!-- Product 1 -->

                        <div class="product-item men">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="images/s5.png" alt>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div
                                    class="product_bubble product_bubble_red d-flex flex-column align-items-center " style="background-color: #C53337; right: 20px;" ><span >-$20</span></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html">Samsung CF591 Series
                                            Curved 27-Inch FHD Monitor</a></h6>
                                    <div class="product_price" style="color: #C53337">$520.00<span>$590.00</span></div>
                                </div>
                            </div>
                            <!--                            <div class="red_button add_to_cart_button"><a href="#">add to
                                                                cart</a></div>-->
                        </div>

                        <!-- Product 2 -->
                        <div class="product-item men">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="images/s5.png" alt>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div
                                    class="product_bubble product_bubble_left product_bubble_green d-flex flex-column align-items-center"><span>new</span></div>

                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html">Samsung CF591 Series
                                            Curved 27-Inch FHD Monitor</a></h6>
                                    <div class="product_price" style="color: #C53337">$520.00</div>
                                </div>
                            </div>
                            <!--                            <div class="red_button add_to_cart_button"><a href="#">add to
                                                                cart</a></div>-->
                        </div>

                        <!-- Product 2 -->
                        <div class="product-item women">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="images/s5.png" alt>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html">Samsung CF591 Series
                                            Curved 27-Inch FHD Monitor</a></h6>
                                    <div class="product_price" style="color: #C53337">$520.00</div>
                                </div>
                            </div>
                            <!--<div class="red_button add_to_cart_button"><a href="#">add to
                                                                cart</a></div>-->
                        </div>

                        <!-- Product 2 -->
                        <div class="product-item women">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="images/s5.png" alt>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html">Samsung CF591 Series
                                            Curved 27-Inch FHD Monitor</a></h6>
                                    <div class="product_price" style="color: #C53337">$520.00</div>
                                </div>
                            </div>
                            <!--                            <div class="red_button add_to_cart_button"><a href="#">add to
                                                                cart</a></div>-->
                        </div>

                        <!-- Product 2 -->
                        <div class="product-item men">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="images/s5.png" alt>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html">Samsung CF591 Series
                                            Curved 27-Inch FHD Monitor</a></h6>
                                    <div class="product_price" style="color: #C53337">$520.00</div>
                                </div>
                            </div>
                            <!--                            <div class="red_button add_to_cart_button"><a href="#">add to
                                                                cart</a></div>-->
                        </div>

                        <!-- Product 2 -->
                        <div class="product-item men">
                            <div class="product discount product_filter">
                                <div class="product_image">
                                    <img src="images/s5.png" alt>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a href="single.html">Samsung CF591 Series
                                            Curved 27-Inch FHD Monitor</a></h6>
                                    <div class="product_price" style="color: #C53337">$520.00</div>
                                </div>
                            </div>
                            <!--                            <div class="red_button add_to_cart_button"><a href="#">add to
                                                                cart</a></div>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
<!--    <div class="mt-5 ml-lg-5 mr-lg-5 " style="text-align: center; display: inline-block">
        <a href="US_AllProducts.jsp" class="btn" style="background-color: #C53337; color: white; right: 400px; ">Show more</a>
    </div>-->
   

    <!-- Best Sellers -->

    <div class="best_sellers">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <div class="section_title new_arrivals_title ">
                        <h2>Best Sellers</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="product_slider_container">
                        <div class="owl-carousel owl-theme product_slider">

                            <!-- Slide 1 -->

                            <div class="owl-item product_slider_item">
                                <div class="product-item">
                                    <div class="product discount">
                                        <div class="product_image">
                                            <img src="images/s5.png" alt>
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div
                                            class="product_bubble product_bubble_red d-flex flex-column align-items-center " style="background-color: #C53337; right: 20px;" ><span >-$20</span></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP
                                                    Digital Camera (Silver)</a></h6>
                                            <div class="product_price" style="color: #C53337">$520.00<span>$590.00</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Slide 2 -->
                            <div class="owl-item product_slider_item">
                                <div class="product-item">
                                    <div class="product discount">
                                        <div class="product_image">
                                            <img src="images/s5.png" alt>
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div
                                            class="product_bubble product_bubble_red d-flex flex-column align-items-center " style="background-color: #C53337; right: 20px;" ><span >-$20</span></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP
                                                    Digital Camera (Silver)</a></h6>
                                            <div class="product_price" style="color: #C53337">$520.00<span>$590.00</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Slide 4 -->
                            <div class="owl-item product_slider_item">
                                <div class="product-item">
                                    <div class="product discount">
                                        <div class="product_image">
                                            <img src="images/s5.png" alt>
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div
                                            class="product_bubble product_bubble_red d-flex flex-column align-items-center " style="background-color: #C53337; right: 20px;" ><span >-$20</span></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP
                                                    Digital Camera (Silver)</a></h6>
                                            <div class="product_price" style="color: #C53337">$520.00<span>$590.00</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Slide 5 -->
                            <div class="owl-item product_slider_item">
                                <div class="product-item">
                                    <div class="product discount">
                                        <div class="product_image">
                                            <img src="images/s5.png" alt>
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div
                                            class="product_bubble product_bubble_red d-flex flex-column align-items-center " style="background-color: #C53337; right: 20px;" ><span >-$20</span></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP
                                                    Digital Camera (Silver)</a></h6>
                                            <div class="product_price" style="color: #C53337">$520.00<span>$590.00</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Slide 6 -->
                            <div class="owl-item product_slider_item">
                                <div class="product-item">
                                    <div class="product discount">
                                        <div class="product_image">
                                            <img src="images/s5.png" alt>
                                        </div>
                                        <div class="favorite favorite_left"></div>
                                        <div
                                            class="product_bubble product_bubble_red d-flex flex-column align-items-center " style="background-color: #C53337; right: 20px;" ><span >-$20</span></div>
                                        <div class="product_info">
                                            <h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP
                                                    Digital Camera (Silver)</a></h6>
                                            <div class="product_price" style="color: #C53337">$520.00<span>$590.00</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                       

                        <!-- Slider Navigation -->

                        <div
                            class="product_slider_nav_left product_slider_nav d-flex align-items-center justify-content-center flex-column">
                            <i class="fa fa-chevron-left" aria-hidden="true"></i>
                        </div>
                        <div
                            class="product_slider_nav_right product_slider_nav d-flex align-items-center justify-content-center flex-column">
                            <i class="fa fa-chevron-right" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <!----Brand-->
    <div class="brands">
        <div class="container">
            <div class="brands-inner">
                <div class="brand-title">
                    <span>Brands</span>
                </div>

                <div id="slider-home" class="brands">
                    <div id="brand-carousel" class="owl-carousel owl-theme">
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>
                        <div class="item">
                            <img src="images/s4.png" alt="slide-1" class="img-responsive" style="width: 180px; height: 180px;">
                        </div>                   
                    </div>
                </div>
                <!--Slider-->

            </div>
        </div>
    </div>
    <!----Brand-->

    <!-- Benefit -->

    <div class="benefit">
        <div class="container">
            <div class="row benefit_row" style="padding: 30px;">
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center" style="width: 400 px; height: 100px;">
                        <div class="benefit_icon"><i class="fa fa-truck"
                                                     aria-hidden="true" style="color: #C53337"></i></div>
                        <div class="benefit_content">
                            <p><b>FREE SHIPPING</b></p>
                            <p style="font-size: 10px">Suffered Alteration in Some Form</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center" style="width: 400 px; height: 100px;">
                        <div class="benefit_icon"><i class="fa fa-money"
                                                     aria-hidden="true" style="color: #C53337"></i></div>
                        <div class="benefit_content">
                            <p><b>CATCH ON DELIVERY</b></p>
                            <p style="font-size: 10px">The Internet Tend To Repeat</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center" style="width: 400 px; height: 100px;">
                        <div class="benefit_icon"><i class="fa fa-undo"
                                                     aria-hidden="true" style="color: #C53337"></i></div>
                        <div class="benefit_content">
                            <p><b>45 DAYS RETURN</b></p>
                            <p style="font-size: 10px">Making it Look Like Readable</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center" style="width: 400 px; height: 100px;">
                        <div class="benefit_icon"><i class="fa fa-clock-o"
                                                     aria-hidden="true" style="color: #C53337"></i></div>
                        <div class="benefit_content">
                            <p><b>OPENING ALL WEEK</b></p>
                            <p style="font-size: 10px">8AM - 09PM</p>
                        </div>
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

<!-- Khoi tao Owl Carousel -->
<script>
    $(document).ready(function () {
        $("#brand-carousel").owlCarousel({
            loop: true,
            margin: 10,
            nav: false, // ?n các nút ?i?u h??ng prev và next
            dots: true, // Hi?n th? các nút ?i?u khi?n trang (pagination)
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
<script src="js/custom.js"></script>
<script src="js/brand.js"></script>
</body>

</html>
