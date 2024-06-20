<%-- 
   Document   : MyOrder
   Created on : May 29, 2024, 11:02:32 PM
   Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Order</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            body {
                background-color: #f8f9fa;
                margin: 0;
            }

            .custom-margin {
                margin-top: 7rem;
            }

            .profile-container, .profile-sidebar {
                padding: 20px;
                background-color: #fff;
                /*border-radius: 10px;*/
                /*box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
            }
            .profile-sidebar {
                height: 500px;
                overflow-y: auto;
                margin-bottom: 20px;
            }
            .btn.middle {
                background-color: black;
                color: white;
                display: block;
                margin: 20px auto 0;
            }
            .nav-link {
                transition: background-color 0.3s, color 0.3s;
                font-size: 18px;
                color: black;
            }
            .nav-link:hover, .nav-link.active {
                color: white;
                background-color: black;
            }
            .status-box {
                background-color: #FFFF80;
                color: #155724;
                padding: 10px;
                display: inline-block;
                border-radius: 5px;
            }
            .border-bottom {
                border-bottom: 2px solid #000;
            }
            .active {
                background: #33cc33;
                color: #000;
                padding: 10px;
                display: inline-block;
                border-radius: 5px;
            }
            .inactive-button {
                background-color: white;
                color: #ccc;
                border: 2px solid #ccc;
                cursor: not-allowed;
            }

            .status-box.in-delivery {
                background-color: #28a745; /* Green background */
                color: white; /* White text */
            }

            .number_of_product{
                color: #ccc;
                font-size: 16px;
                padding-left: 20px;
            }

            .fixed-sidebar {
                position: fixed;
                top: 0;
                width: 23%;
                z-index: 1000;
            }

            #myBtn {
                /*display: none;*/
                /*Ẩn ban đầu*/
                position: fixed; /* Cố định nút ở góc phải dưới */
                bottom: 20px;
                right: 20px;
                z-index: 99; /* Đảm bảo nút hiển thị trên các phần tử khác */
                border: none; /* Loại bỏ đường viền */
                background-color: #ccc; /* Màu nền */
                color: black; /* Màu chữ */
                padding: 15px; /* Kích thước của nút */
                border-radius: 40%; /* Hình dáng là hình tròn */
                cursor: pointer; /* Biến con trỏ thành biểu tượng chỉ vào */
                transition: background-color 0.3s; /* Hiệu ứng chuyển đổi màu nền */
            }
            #myBtn:hover {
                background-color: #002752; /* Màu nền khi di chuột qua */
                color: #fff; /* Màu chữ khi di chuột qua */
                transition: background-color 0.3s, color 0.3s; /* Hiệu ứng chuyển đổi */
            }

            #myBtn i {
                font-size: 20px; /* Kích thước của biểu tượng */
            }
        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>

        <div class="container-fluid custom-margin">
            <div class="row">
                <div class="col-lg-3 profile-sidebar m" id="sidebar" >
                    <div class="card h-100">
                        <div class="user-side-bar" style="padding-left: 30px">
                            <h5 class="card-title" style="padding-top: 20px">Hello 🌟</h5>
                            <h2 class="card-text" style="font-size: medium; font-weight: bold">Nguyen Van A</h2>
                        </div>
                        <hr>
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link " href="US_MyProfile.jsp"><i class="fas fa-user mr-2"></i> Personal Information</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="US_MyOrder.jsp"><i class="fas fa-box mr-2"></i> My Order</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="US_MyWishlist.jsp"><i class="fas fa-heart mr-2"></i> My Wishlists</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h1 >My Order</h1>
                        <div class="search_filter form-inline">
                            <div class="input-group mr-2">
                                <input type="text" class="form-control" placeholder="Search">
                            </div>
                            <div class="input-group">
                                <select class="form-control btn-dark" style="color: white">
                                    <option value="All" selected>Filter</option>
                                    <option value="in_process">In Process</option>
                                    <option value="in_delivery">In Delivery</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div class="mb-3 border-bottom order-item">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="images/product_6.png" class="img-fluid rounded-start" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'">
                                </div>
                                <div class="col-md-4">
                                    <div class="card-body">
                                        <div class="d-flex">
                                            <h5 class="card-title">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                        </div>
                                        <div class="d-flex justify-content-start">
                                            <p class="card-text mb-0"><strong>Category:</strong> Shoes</p>
                                            <p class="card-text mb-0 pl-3"><strong>Size:</strong> 41.3</p>
                                            <p class="card-text mb-0 pl-3"><strong>Quantity:</strong> 1</p>
                                        </div>
                                        <div class="status-box mt-5">In Process</div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="d-flex align-items-center">
                                        <p class="card-text text-danger fs-4 mt-3">$5,000</p>
                                    </div>
                                </div>
                                <div class="col-md-3 d-flex flex-column justify-content-center align-items-center ">
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-primary mb-2" type="button" style="background: white; color: black; border: 2px solid black;">View Order</button>
                                        <button class="btn btn-danger cancel-order" type="button">Cancel Order</button>
                                        <div class="d-flex justify-content-between mt-4">
                                            <h3>Total:</h3>
                                            <p class="text-danger fs-4 pl-3">$5,000</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="number_of_product"> 2 Products</div>
                    </div>


                    <div>
                        <div class="mb-3 border-bottom order-item">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="images/product_6.png" class="img-fluid rounded-start" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'">
                                </div>
                                <div class="col-md-4">
                                    <div class="card-body">
                                        <div class="d-flex">
                                            <h5 class="card-title">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                        </div>
                                        <div class="d-flex justify-content-start">
                                            <p class="card-text mb-0"><strong>Category:</strong> Shoes</p>
                                            <p class="card-text mb-0 pl-3"><strong>Size:</strong> 41.3</p>
                                            <p class="card-text mb-0 pl-3"><strong>Quantity:</strong> 1</p>
                                        </div>
                                        <div class="status-box mt-5 in-delivery">In Delivery</div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="d-flex align-items-center">
                                        <p class="card-text text-danger fs-4 mt-3">$5,000</p>
                                    </div>
                                </div>
                                <div class="col-md-3 d-flex flex-column justify-content-center align-items-center">
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-primary mb-2" type="button" style="background: white; color: black; border: 2px solid black;">View Order</button>
                                        <button class="btn btn-danger cancel-order" type="button">Cancel Order</button>
                                        <div class="d-flex justify-content-between mt-4">
                                            <h3>Total:</h3>
                                            <p class="text-danger fs-4 pl-3">$5,000</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="number_of_product"></div>
                    </div>

                    <div>
                        <div class="mb-3 border-bottom order-item">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="images/product_6.png" class="img-fluid rounded-start" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'">
                                </div>
                                <div class="col-md-4">
                                    <div class="card-body">
                                        <div class="d-flex">
                                            <h5 class="card-title">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                        </div>
                                        <div class="d-flex justify-content-start">
                                            <p class="card-text mb-0"><strong>Category:</strong> Shoes</p>
                                            <p class="card-text mb-0 pl-3"><strong>Size:</strong> 41.3</p>
                                            <p class="card-text mb-0 pl-3"><strong>Quantity:</strong> 1</p>
                                        </div>
                                        <div class="status-box mt-5 in-delivery">In Delivery</div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="d-flex align-items-center">
                                        <p class="card-text text-danger fs-4 mt-3">$5,000</p>
                                    </div>
                                </div>
                                <div class="col-md-3 d-flex flex-column justify-content-center align-items-center">
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-primary mb-2" type="button" style="background: white; color: black; border: 2px solid black;">View Order</button>
                                        <button class="btn btn-danger cancel-order" type="button">Cancel Order</button>
                                        <div class="d-flex justify-content-between mt-4">
                                            <h3>Total:</h3>
                                            <p class="text-danger fs-4 pl-3">$5,000</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="number_of_product"></div>
                    </div>

                </div>
            </div>
            <button onclick="topFunction()" id="myBtn" title="Go to top">
                <i class="fas fa-arrow-up"></i>
            </button>
        </div>
        <%@include file="US_footer.jsp" %>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // Highlight active link in the sidebar
                document.querySelectorAll('.profile-sidebar .nav-link').forEach(function (link) {
                    link.addEventListener('click', function () {
                        document.querySelectorAll('.profile-sidebar .nav-link').forEach(nav => nav.classList.remove('active'));
                        link.classList.add('active');
                    });
                });

                // Disable cancel button for "In Delivery" status
                document.querySelectorAll('.order-item').forEach(function (item) {
                    const statusBox = item.querySelector('.status-box');
                    const cancelButton = item.querySelector('.cancel-order');
                    if (statusBox.textContent.trim() === 'In Delivery') {
                        cancelButton.classList.add('inactive-button');
                        cancelButton.disabled = true;
                    }
                });
            });

            let mybutton = document.getElementById('myBtn');

            window.addEventListener('scroll', scrollButton);

            function scrollButton() {
                if (window.pageYOffset > 0) {
                    mybutton.style.display = 'block';
                } else {
                    mybutton.style.display = 'none';
                }
            }

            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }

        </script>
    </body>
</html>
