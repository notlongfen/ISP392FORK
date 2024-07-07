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
        <title>Dashboard</title>
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


            /* Định dạng cho nút dropdown ở month, today,... */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-button {
                background-color: #6c63ff;
                color: white;
                padding: 10px 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
                border-radius: 5px;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                border-radius: 5px;
            }

            .dropdown-content a:hover {
                background-color: #ddd;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropdown-button {
                background-color: #5a54d6;
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
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Earnings (Today)</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-calendar fa-2x text-primary"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Earnings (Annual) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Sales</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">650</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-shopping-cart fa-2x text-success"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- New User Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">All User</div>
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">366</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-users fa-2x text-info"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Pending Requests Card Example -->

                            <!-- All Product Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">All Product</div>
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">20</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-cubes fa-2x text-success"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- All invoice Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Order</div>
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="far fa-file-alt fa-2x text-warning"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Pending Requests Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-uppercase mb-1">Pending Requests</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                                                <div class="mt-2 mb-0 text-muted text-xs">
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-comments fa-2x text-warning"></i>
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
                                        <button class="btn" onclick="activateButton(this)">7 days</button>
                                        <button class="btn" onclick="activateButton(this)">2 weeks</button>
                                        <button class="btn active" onclick="activateButton(this)">1 month</button>
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
                                        <div class="dropdown">
                                            <button class="dropdown-button" id="dropdownButton">Month &#x25BC;</button>
                                            <div class="dropdown-content">
                                                <a href="#" onclick="changeText('Today')">Today</a>
                                                <a href="#" onclick="changeText('Week')">Week</a>
                                                <a href="#" onclick="changeText('Month')">Month</a>
                                                <a href="#" onclick="changeText('This year')">This year</a>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="card-body">
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Air force 1
                                                <div class="small float-right"><b>600 of 800 Items</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-warning" role="progressbar" style="width: 80%" aria-valuenow="80"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <div class="small text-gray-500">T-shirt
                                                <div class="small float-right"><b>500 of 800 Items</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: 70%" aria-valuenow="70"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Ultra boost 5
                                                <div class="small float-right"><b>455 of 800 Items</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-danger" role="progressbar" style="width: 55%" aria-valuenow="55"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Jordan 1
                                                <div class="small float-right"><b>400 of 800 Items</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50"
                                                     aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <div class="small text-gray-500">Sun glasses
                                                <div class="small float-right"><b>200 of 800 Items</b></div>
                                            </div>
                                            <div class="progress" style="height: 12px;">
                                                <div class="progress-bar " role="progressbar" style="width: 30%; background-color: #FF964B;" aria-valuenow="30"
                                                     aria-valuemin="0" aria-valuemax="100" ></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div style="display: flex; justify-content: space-around;">
                                            <div>
                                                <div style="color: #6366F1; font-size: 14px;">Remaining Products</div>
                                                <div style="display: flex; align-items: center; justify-content: center; margin-top: 5px;">
                                                    <span style="font-size: 24px; color: #6B7280;">110</span>
                                                    <i class='fas fa-box' style="margin-left: 20px; color: #218838"></i>
                                                </div>
                                            </div>
                                            <div>
                                                <div style="color: #6366F1; font-size: 14px;">Out of stock Products</div>
                                                <div style="display: flex; align-items: center; justify-content: center; margin-top: 5px;">
                                                    <span style="font-size: 24px; color: #6B7280;">10</span>
                                                    <i class='fas fa-box-open' style="margin-left: 20px; color: #FF6347"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Invoice Example -->
                            <div class="col-xl-8 col-lg-7 mb-4">
                                <div class="card">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Invoice</h6>
                                        <a class="m-0 float-right btn btn-danger btn-sm" href="AD_OrderList.jsp">Order<i class="fas fa-chevron-right" style="margin-left: 5px;"></i></a>
                                    </div>
                                    <div class="table-responsive">
                                        <div class="dashboard1 formatDashboard1">
                                            <div class="card1 in-progress" style="text-align: left">
                                                <div class="count">113<span class="arrow-down"><i class="fas fa-arrow-down"></i></span></div>
                                                <div>In progress</div>
                                                <div class="icon1"><i class="fas fa-spinner"></i></div>                                                      
                                            </div>
                                            <div class="card1 in-delivery" style="text-align: left">
                                                <div class="count">52<span class="arrow-down"><i class="fas fa-arrow-down"></i></span></div>
                                                <div>In Delivery</div>
                                                <div class="icon1"><i class="fas fa-truck"></i></div>                                                      
                                            </div>
                                            <div class="card1 items-delivered" style="text-align: left">
                                                <div class="count">250<span class="arrow-up"><i class="fas fa-arrow-up"></i></span></div>
                                                <div>Items Delivered</div>
                                                <div class="icon1"><i class="fas fa-box"></i></div>
                                            </div>
                                            <div class="card1 items-canceled" style="text-align: left">
                                                <div class="count">13<span class="arrow-up"><i class="fas fa-arrow-up"></i></span></div>
                                                <div>Items Canceled</div>
                                                <div class="icon1"><i class="fas fa-ban"></i></div>      
                                            </div>
                                        </div>
                                </div>
                            </div>
                                </div>
                            <!-- Message From Customer-->
                            <div class="col-xl-4 col-lg-5">
                                <div class="card mb-4">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <h6 class="m-0 font-weight-bold text-primary">Customers</h6>
                                        <div class="dropdown">
                                            <button class="dropdown-button" id="dropdownButton">Month &#x25BC;</button>
                                            <div class="dropdown-content">
                                                <a href="#" onclick="changeText('Today')">Today</a>
                                                <a href="#" onclick="changeText('Week')">Week</a>
                                                <a href="#" onclick="changeText('Month')">Month</a>
                                                <a href="#" onclick="changeText('This year')">This year</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="customers">
                                        <div class="customer-row">
                                            <div>New Customers</div>
                                            <div class="icon">
                                                <span>20</span>
                                                <i class="bi bi-people"></i>
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-people" viewBox="0 0 16 16" style="color: #218838">
                                                <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1zm-7.978-1L7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002-.014.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0M6.936 9.28a6 6 0 0 0-1.23-.247A7 7 0 0 0 5 9c-4 0-5 3-5 4q0 1 1 1h4.216A2.24 2.24 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816M4.92 10A5.5 5.5 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0m3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4"/>
                                                </svg> 
                                            </div>
                                        </div>     
                                        <div class="customer-row">
                                            <div>Returning Customers</div>
                                            <div class="icon">
                                                <span>30</span>
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-people" viewBox="0 0 16 16" style="color: #FF6347">
                                                <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1zm-7.978-1L7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002-.014.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0M6.936 9.28a6 6 0 0 0-1.23-.247A7 7 0 0 0 5 9c-4 0-5 3-5 4q0 1 1 1h4.216A2.24 2.24 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816M4.92 10A5.5 5.5 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0m3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4"/>
                                                </svg>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>



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