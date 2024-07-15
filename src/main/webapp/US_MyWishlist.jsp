<%-- 
    Document   : MyWishlist
    Created on : May 30, 2024, 6:57:42 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Wishlists</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .custom-margin {
                margin-top: 7rem;
            }
            .profile-sidebar {
                background-color: #fff;
                border-radius: 5px;
                /*box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
                margin-bottom: 20px;
                width: 300px;
                height: 500px;
                border: none;
            }

            .no-border {
                border: none;
            }

            .custom-border {
                border: 2px solid black; /* Đặt độ rộng của border thành 2px */
                padding: 20px; /* Thêm padding để tăng diện tích của border */
            }

            .inline {
                display: inline; /* Sử dụng display: inline để các phần tử hiển thị trên cùng một dòng */
                margin-right: 20px; /* Tạo một khoảng cách ngang giữa các phần tử */
                vertical-align: bottom;
            }

            .resized-image {
                width: 1280px;
                height: 1280px;
                object-fit: contain;
            }

            .same-size {
                width: 100px; /* Thiết lập kích thước width của các phần tử cùng một giá trị */
                margin-right: 5px;
            }

            .no-wrap {
                white-space: nowrap;
                margin-left: -80px;
            }

            .wishlist-item {
                border-bottom: 1px solid #e0e0e0;
                padding-bottom: 1rem;
                margin-bottom: 1rem;
            }

            .wishlist-item:last-child {
                border-bottom: none;
            }

            h1 {
                margin-bottom: 2rem;
            }

            .img-fluid {
                max-width: 100%;
                height: auto;
            }

            .text-end .text-decoration-line-through {
                color: #888;
                font-size: 1rem;
            }

            .text-end .fs-4 {
                font-size: 1.5rem;
            }

            .text-muted.small {
                font-size: 0.875rem;
            }
            .nav-link {
                transition: background-color 0.3s, color 0.3s;
                font-size: 18px;
                color: black;
            }

            .nav-link:hover,
            .nav-link.active {
                color: white;
                background-color: black;
            }

            .remove i {
                color: #696969; /* Màu mặc định của biểu tượng trái tim */
                transition: color 0.3s ease; /* Hiệu ứng chuyển màu mượt */
                font-size: 30px;
                margin-top: 20px;
            }

            .remove i:hover {
                color: #C53337; /* Màu khi hover */
            }
        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>
        <div class="container-fluid custom-margin">
            <div class="row">

                <!-- Wishlist Section -->
                <div class="col-lg-11 ml-5">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h1>My Wishlist</h1>
                    </div>
                    <%
     List<ProductDetailsDTO> listProduct = (List<ProductDetailsDTO>) session.getAttribute("wishlist");
     if (listProduct != null && listProduct.size() > 0) {
     for (ProductDetailsDTO list : listProduct) {
                    %>
                    <div class="wishlist-item row mb-4 d-flex">
                        <div class="col-md-2 text-center">
                            <img src="<%= list.getImage() %>" alt="Image" class="img-fluid resized-image">
                        </div>
                        <div class="col-md-7">
                            <h5><%= list.getProductName() %></h5>
                            <p style="margin-top: 20px;"><%= list.getBrandName() %></p>
                            <div class="text-left">
                                <a href="MainController?action=deleteWishlist&productID=<%=list.getProductID()%>&producDetailID=<%=list.getProductDetailsID()%>" class="remove"><i class="remove-btn fas fa-trash"></i></a>
                            </div>
                        </div>
                        <div class="col-md-3 text-end">
                            <p class="inline text-danger fs-4 same-size" >$<%= list.getPrice() %></p>
                            <br>
                            <button class="btn btn-dark" style="width: 150px; margin-top: 90px;">Add to Cart</button>
                        </div>
                    </div>
                    <%
                        }
} else {
                    %>
                    <h1>No products found</h1>

                    <%
                    }
                    %>

                </div>
            </div>
        </div>
        <%@include file="US_footer.jsp" %>
        <%@include file="US_RequestSupport.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var navLinks = document.querySelectorAll('.profile-sidebar .nav-link');
                navLinks.forEach(function (link) {
                    link.addEventListener('click', function () {
                        navLinks.forEach(function (nav) {
                            nav.classList.remove('active');
                        });
                        link.classList.add('active');
                    });
                });
            });
        </script>
    </body>
</html>
