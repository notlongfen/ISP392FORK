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


            .container {
                display: flex;
                justify-content: space-between;
            }
            .card {
                width: 800px;
                margin: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #f8f9fc;
                padding: 15px;
                border-bottom: 1px solid #e3e6f0;
            }
            .card-body {
                padding: 15px;
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

            .card-body1 {
                padding: 15px;
            }
            .card-body1 div {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 0;
            }
            .card-body1 div span:nth-child(1) {
                flex: 1;
            }
            .card-body1 div span:nth-child(2) {
                flex: 0 0 auto;
                width: 50px;
                text-align: right;
                margin-right: 40px;
            }
            .card-body1 div span:nth-child(3) {
                flex: 0 0 auto;
                width: 30px;
                text-align: right;
            }
            .card-body1 div span i.text-red {
                color: red;
            }
            .card-body1 div span i.text-green {
                color: green;
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


                        <!-- Pie Chart -->
                        <div class="container">
                            <div class="card">
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Total customer</h6>
                                </div>
                                <div class="card-body1">
                                    <div>
                                        <span>Customers never ordered:</span>
                                        <span>20</span>
                                        <span><i class="fas fa-users fa-2x text-red"></i></span>
                                    </div>
                                    <div>
                                        <span>Customers who have ordered:</span>
                                        <span>34</span>
                                        <span><i class="fas fa-users fa-2x text-green"></i></span>
                                    </div>
                                </div>
                            </div>

                            <div class="card" >
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Top 5 customers have highest point</h6>
                                </div>
                                <div class="card-body">
                                    <div class="product top1">
                                        <span>TOP 1:</span>
                                        <span>Nguyen Van A</span>
                                        <span>800 ponts</span>
                                    </div>
                                    <div class="product top2">
                                        <span>TOP 2:</span>
                                        <span>Nguyen Van A</span>
                                        <span>800 ponts</span>
                                    </div>
                                    <div class="product top3">
                                        <span>TOP 3:</span>
                                        <span>Nguyen Van A</span>
                                        <span>800 ponts</span>
                                    </div>
                                    <div class="product top4">
                                        <span>TOP 4:</span>
                                        <span>Nguyen Van A</span>
                                        <span>800 ponts</span>
                                    </div>
                                    <div class="product top5">
                                        <span>TOP 5:</span>
                                        <span>Nguyen Van A</span>
                                        <span>800 ponts</span>
                                    </div>
                                </div>
                            </div>
                        </div>




                        <!-- Invoice Example -->

                        <!-- Message From Customer-->




                    </div>
                    <!--Row-->




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