<%-- 
    Document   : index
    Created on : May 30, 2024, 3:36:41 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Statistic</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="AD_css/ruang-admin.min.css" rel="stylesheet">
        <style>
            /*7 days 2 weeks 1 month*/
            .btn {
                padding: 10px 20px;
                margin: 5px;
                border: none;
                border-radius: 5px;
                background-color: #f0f0f0;
                color: #000;
                cursor: pointer;
                font-size: 16px;
            }
            .btn:hover {
                background-color: #ddd;
            }
            .btn.active {
                background-color: #6c63ff;
                color: #fff;
            }
            /* Thêm CSS để căn giữa các nút */
            .btn-group-center {
                display: flex;
                justify-content: center;
                width: 100%;
                margin-top: 10px; /* Thêm khoảng cách nếu cần */
            }


            .product {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                border-bottom: 1px solid #ccc;
            }

            .product span:nth-child(1) {
                width: 20%;
                font-weight: bold;
            }
            .product span:nth-child(2) {
                width: 70%;
                text-align: center;
            }
            .product span:nth-child(3) {
                width: 20%;
                text-align: right;
            }

            .product span {
                font-weight: bold;
            }
            .top1 span:nth-child(1) {
                color: #FF9900;
            }
            .top2 span:nth-child(1) {
                color: #33CC33;
            }
            .top3 span:nth-child(1) {
                color: #FF6666;
            }
            .top4 span:nth-child(1) {
                color: #66CCFF;
            }
            .top5 span:nth-child(1) {
                color: #FF9900;
            }


            /*Customer*/
            .customers {
                margin-top: 15px;
                margin-left: 20px;
            }
            .customer-row {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 0;
            }
            .icon {
                display: flex;
                align-items: center;
            }
            .icon span {
                margin-right: 50px; /* Khoảng cách giữa số và hình ảnh */
            }
            .icon svg {
                margin-right: 20px;
                width: 20px;
                height: 20px;
            }

            /*Order*/
            .dashboard1 {
                display: flex;
                gap: 20px;
                padding: 20px;
                height: 275px;
                justify-content: center;
                align-items: center;
            }
            .card1 {
                width: 230px;
                padding: 20px;
                border-radius: 10px;
                color: #fff;
                text-align: center;
                height: 122px;
            }
            .in-progress {
                background-color: #ffe4e4;
                color: #721c24;
            }
            .in-delivery {
                background-color: #DBE3FF;
                color: #0c5460;
            }
            .items-delivered {
                background-color: #d4edda;
                color: #155724;
            }
            .items-canceled {
                background-color: #ffa497;
                color: #721c24;
            }
            .card1 .count {
                font-size: 2em;
                margin-bottom: 10px;
                color: #000;
            }
            .icon1 {
                margin-left: 150px;
                margin-top: -20px;
            }
            .arrow-down {
                font-size: 0.5em;
                vertical-align: middle;
                margin-left: 5px;
                color: #721c24;
            }
            .arrow-up {
                font-size: 0.5em;
                vertical-align: middle;
                margin-left: 5px;
                color: #0c5460;
            }

        </style>
    </head>

    <body id="page-top">
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="AD_sidebar.jsp"%>
            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!---Header --->
                    <%@include file="AD_header.jsp" %>
                    <!-- Container Fluid-->
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="./">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                            </ol>
                        </div>

                        <div class="row mb-3">
                            <!-- Earnings (Monthly) Card Example -->
                            <div class=" mb-4" style="margin-left: 15px;">
                                <div class="card" style="height: 150px; width: 330px;">
                                    <div class="card-body">
                                        <div class="row align-items-center">
                                            <div class="col mr-2">
                                                <!--<div class="text-xs font-weight-bold text-uppercase mb-2">Total incomes</div>-->
                                                <div class=" mb-0 font-weight-bold text-gray mb-2" style="font-size: 20px;">TOTAL INCOMES</div>
                                                <div class=" mb-0 font-weight-bold text-gray mb-2" style="font-size: 20px;">Today: ....$</div>
                                                <div class=" mb-0 font-weight-bold text-gray" style="font-size: 20px;">Yesterday: ....$</div>                            
                                            </div>
                                            <div class="col-auto">
                                                <i class="far fa-file-alt fa-2x text-warning"></i>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Earnings (Annual) Card Example -->
                            <div class=" col-md-3 mb-4">
                                <div class="card" style="height: 150px; width: 330px;">
                                    <div class="card-body">
                                        <div class="row align-items-center">
                                            <div class="col mr-2">
                                                <!--<div class="text-xs font-weight-bold text-uppercase mb-2">Total incomes</div>-->
                                                <div class=" mb-0 font-weight-bold text-gray mb-2" style="font-size: 20px;">NUMBER OF ORDERS</div>
                                                <div class=" mb-0 font-weight-bold text-gray mb-2" style="font-size: 20px;">Today: ....$</div>
                                                <div class=" mb-0 font-weight-bold text-gray" style="font-size: 20px;">Yesterday: ....$</div>                            
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-calendar fa-2x text-primary"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- New User Card Example -->
                            <div class="mb-4" style="margin-left: 45px;">
                                <!-- All User Card Example -->
                                <div class="card mb-2" style="height: 70px; width: 330px;"> 
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">CANCEL ORDER: 5</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class='fas fa-box-open' style=" color: #FF6347"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- All Product Card Example -->
                                <div class="card " style="height: 70px; width: 330px;">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">COMPLETED ORDER: 5</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class='fas fa-box' style="color: #218838"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Area Chart -->
                            <div class="col-xl-8 col-lg-7">
                                <div class="card mb-4">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Revenue Report</h6>
                                    </div>
                                    <div class="btn-group-center">
                                        <button class="btn" onclick="activateButton(this)">Total incomes in year</button>
                                        <button class="btn" onclick="activateButton(this)">Number of orders in year</button>              
                                    </div>
                                    <div class="card-body">
                                        <div class="chart-area">
                                            <canvas id="myAreaChart"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <!-- Pie Chart -->
                            <div class="col-xl-4 col-lg-5">
                                <div class="card mb-4" style="height: 473px;">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Top 5 Products Sold</h6>                                    
                                    </div>
                                    <div class="card-body">
                                        <div class="mb-3">
                                            <div class="product top1">
                                                <span>TOP 1:</span>
                                                <span>Air Force 1</span>
                                                <span style="font-size: 13px">600 of 800 items</span>
                                            </div>
                                            <div class="product top2">
                                                <span>TOP 2:</span>
                                                <span>Air Force 1</span>
                                                <span style="font-size: 13px">600 of 800 items</span>
                                            </div>
                                            <div class="product top3">
                                                <span>TOP 3:</span>
                                                <span>Air Force 1</span>
                                                <span style="font-size: 13px">600 of 800 items</span>
                                            </div>
                                            <div class="product top4">
                                                <span>TOP 4:</span>
                                                <span>Air Force 1</span>
                                                <span style="font-size: 13px">600 of 800 items</span>
                                            </div>
                                            <div class="product top5">
                                                <span>TOP 5:</span>
                                                <span>Air Force 1</span>
                                                <span style="font-size: 13px">600 of 800 items</span>
                                            </div>

                                        </div>

                                    </div>

                                </div>
                            </div>
                            <!-- Invoice Example -->

                            <!-- Message From Customer-->




                        </div>
                        <!--Row-->



                        <!-- Modal Logout -->
                        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelLogout"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabelLogout">Ohh No!</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure you want to logout?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                        <a href="login.html" class="btn btn-primary">Logout</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!---Container Fluid-->
                </div>
                <!-- Footer -->

                <!-- Footer -->
            </div>
        </div>

        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <script>
            function activateButton(button) {
                var buttons = document.querySelectorAll('.btn');
                buttons.forEach(function (btn) {
                    btn.classList.remove('active');
                });
                button.classList.add('active');
            }

            function changeText(text) {
                document.getElementById('dropdownButton').innerText = text + ' \u25BC';
            }
        </script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="AD_js/ruang-admin.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>
        <script src="AD_js/demo/chart-area-demo.js"></script>  
    </body>

</html>