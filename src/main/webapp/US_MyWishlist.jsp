<%-- 
    Document   : MyWishlist
    Created on : May 30, 2024, 6:57:42 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            .custom-border {
                border: 2px solid black; /* Đặt độ rộng của border thành 2px */
                padding: 20px; /* Thêm padding để tăng diện tích của border */
            }

            .inline {
                display: inline; /* Sử dụng display: inline để các phần tử hiển thị trên cùng một dòng */
                margin-right: 20px; /* Tạo một khoảng cách ngang giữa các phần tử */
                vertical-align: bottom;
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
        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>
        <div class="container-fluid custom-margin">
            <div class="row">
                <!-- Profile Sidebar -->
                <div class="col-lg-3 profile-sidebar " >
                    <div class="card h-100">
                        <div>
                            <div class="user-side-bar" style="padding-left: 30px">
                                <h5 class="card-title" style="padding-top: 20px" >Hello 🌟</h5>
                                <h2 class="card-text" style="font-size: medium; font-weight: bold">Nguyen Van A</h2>
                            </div>
                            <hr>
                            <div >
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link " aria-current="page" href="US_MyProfile.jsp"><div class="fas fa-user mr-2"></div> Personal Information</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link " href="US_MyOrder.jsp"><div class="fas fa-box mr-2"></div> My Order</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link active" href="US_MyWishlist.jsp"><div class="fas fa-heart mr-2"></div> ️My Wishlists</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>



                <!-- Wishlist Section -->
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h1>My Wishlist</h1>
                        <div class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-dark" type="button">Filter</button>
                        </div>
                    </div>
                    <div class="wishlist-item row mb-4 d-flex">
                        <div class="col-md-2 text-center">
                            <img src="images/product_6.png" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'" class="img-fluid">
                        </div>
                        <div class="col-md-7">
                            <h5>Air Jordan 1 x Off-White Retro High OG ‘Chicago’</h5>
                            <p>Shoes</p>
                            <p class="inline">Size: 41.5</p>
                            <p class="inline">Quantity: 2</p>
                            <p><i class="fa fa-trash-o fa-2x"style="margin-top: 20px" ></i></p>
                        </div>
                        <div class="col-md-3 text-end">
                            <p class="inline text-danger fs-4 same-size" >$5,000</p>
                            <br>
                            <button class="btn btn-dark" style="width: 150px; margin-top: 90px;">Add to Cart</button>
                        </div>
                    </div>
                    <div class="wishlist-item row mb-4">
                        <div class="col-md-2 text-center">
                            <img src="images/product_8.png" alt="Jordan 1 Retro High Light Smoke Grey" class="img-fluid">
                        </div>
                        <div class="col-md-7">
                            <h5>Jordan 1 Retro High Light Smoke Grey Night</h5>
                            <p>Shoes</p>
                            <p class="inline">Size: 41.5</p>
                            <p class="inline">Quantity: 2</p>
                            <p><i class="fa fa-trash-o fa-2x"style="margin-top: 20px" ></i></p>
                        </div>
                        <div class="col-md-3 text-end">
                            <p class="inline text-decoration-line-through same-size" >$1,000</p>
                            <p class="inline text-danger fs-4 same-size" >$500</p>
                            <p class="text-muted small no-wrap" style="font-size: 15px">Sale ends 6/13/2024 at 10:00 PM</p>
                            <button class="btn btn-dark" style="width: 150px; margin-top: 50px;">Add to Cart</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="US_footer.jsp" %>

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
