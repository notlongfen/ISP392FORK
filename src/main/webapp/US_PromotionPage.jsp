
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.isp392.promotion.PromotionDTO" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Promotion</title>
        <meta charset="UTF-8">
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
                color: black; /* ??i màu ch? thành tr?ng */
                /*position: relative;*/
                top: 50px; /* ?i?u ch?nh v? trí c?a toàn b? n?i dung */
                left: 30px; /* ?i?u ch?nh kho?ng cách t? l? trái */
            }

            .main_slider_content_r {
                text-align: right;
                top: 50px; /* ?i?u ch?nh v? trí c?a toàn b? n?i dung */
                left: 80px; /* ?i?u ch?nh kho?ng cách t? l? trái */

            }

            .collection_text {
                display: block; /* ??m b?o r?ng các ph?n t? này là kh?i ?? d? c?n ch?nh */
                color: black; /* ??i màu ch? thành tr?ng */
                /*margin: 50px 0;  Kho?ng cách gi?a các ph?n t? */
                font-size: 15px;
                margin-top: -190px; /* ?i?u ch?nh giá tr? này ?? d?ch chuy?n lên trên */
                margin-bottom: 40px;
            }

            .detail_text {
                display: block; /* ??m b?o r?ng các ph?n t? này là kh?i ?? d? c?n ch?nh */
                color: black; /* ??i màu ch? thành tr?ng */
                /*margin: 10px 0;  Kho?ng cách gi?a các ph?n t? */
                font-size: 15px;
                margin-bottom: 5px;
            }

            .grey a {
                background-color: #D5D5D5; /* Màu nền nút đỏ */
                color: black;
                padding: 10px 20px; /* Khoảng cách bên trong nút */
                text-decoration: none; /* Loại bỏ gạch chân */
                border-radius: 5px;  /* Bo góc cho nút */
                margin-top: 10px; /* Khoảng cách phía trên nút */
                display: inline-block; /* Đảm bảo nút hiển thị như một khối */
                width: 200px; /* Đặt chiều rộng cố định cho nút */
                text-align: center; /* Canh giữa chữ trong nút */
            }
            button.red {
                background-color: #c53337; /* Assuming this is the color used for "Summer Sale" */
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 10px;
                display: inline-block;
                width: 200px;
                text-align: center;
                border: none; /* Adding border for similar look */
                cursor: pointer; /* Adding cursor to indicate clickable button */
            }
            
/*            .promotion_text_box {
                background-color: rgba(255, 255, 255, 0.8);  Semi-transparent white background 
                padding: 20px;  Add padding for spacing 
                border-radius: 5px;  Rounded corners for a smoother look 
                margin: 0 auto;  Center the box horizontally 
                width: fit-content;
                height: 200px;           
            }
            */
            .text-outline {
                color: white;
                text-shadow:
                    -1px -1px 0 #000,  
                    1px -1px 0 #000,
                    -1px 1px 0 #000,
                    1px 1px 0 #000; 
            }
        </style>
    </head>
    <body>

        <div class="super_container">
            <%@include file="US_header.jsp" %>

            <!-- Slider -->
            <%
               List<PromotionDTO> promotionList = (List<PromotionDTO>) request.getAttribute("PROMOTION_LIST");
               if (promotionList != null && !promotionList.isEmpty()) {
                   for (PromotionDTO promotion : promotionList) {
                        String imagePath = promotion.getImage();
                        String imageUrl = imagePath.replace("\\", "/");
            %>

            <div class="main_slider mt-1" style="background-image: url('<%= imageUrl%>')">
                <div class="container fill_height">
                    <div class="row align-items-center fill_height">
                        <div class="col">
                            <div class="main_slider_content">
                                <p class="collection_text text-outline">YOUR POINTS MUST BE HIGHER THAN <%= promotion.getCondition()%> 🌟</p>
                                <h1 class="text-outline" style= "font-size: 90px; margin-bottom: 40px;">SALE UP TO <%= promotion.getDiscountPer()%>%</h1>
                                <h8 class="detail_text text-outline"><%= promotion.getDescription() %></h8>
                                <div class="grey shop_now_button"><a style="font-size: 20px;"><b><%= promotion.getPromotionName()%></b></a></div>
                                <button onclick="copyPromotionName('<%= promotion.getPromotionName() %>')" class="red shop_now_button" id="copyPromotionName" style="font-size: 20px; margin-top: 20px;">Get Coupon</button>

                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <%
                }
            } else {
            %>
            <p>No promotions available at the moment.</p>
            <% 
                }
            %> 
            <%@include file="US_footer.jsp" %>
            <%@include file="US_RequestSupport.jsp" %>

        </div>

        <script>
            function copyPromotionName(promotionName) {
                navigator.clipboard.writeText(promotionName).then(function () {
                    alert('Promotion name copied to clipboard: ' + promotionName);
                }, function (err) {
                    console.error('Could not copy text: ', err);
                });
            }
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
