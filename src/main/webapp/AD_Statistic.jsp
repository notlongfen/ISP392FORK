<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="vendor/fontawesome-free/css/all.min.css">
        <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="AD_css/ruang-admin.min.css">
        <style>
            .status-card {
                height: 70px;
                width: 330px;
                margin-bottom: 1rem;
                margin-right: 1rem;
                padding: 0;
            }
            .status-icon {
                font-size: 2rem;
            }
            .product {
                padding: 10px 0;
                overflow: hidden;
                white-space: nowrap;
                border-bottom: 1px solid #ddd;
                background-color: #f9f9f9;
                color: #333;
            }


            .product:nth-child(odd) {
                background-color: #e9ecef;
            }
            .product:nth-child(even) {
                background-color: #f8f9fa;
            }


            .product:last-child {
                border-bottom: none;
            }

            .product span:first-child {
                display: inline-block;
                max-width: 80%;
                overflow: hidden;
                text-overflow: ellipsis;
                vertical-align: top;
                color: #007bff;
                font-weight: bold;
            }

            .product span:last-child {
                display: inline-block;
                font-size: 13px;
                color: #888;
                margin-left: 10px;
            }
        </style>
    </head>
    <body id="page-top">
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="AD_sidebar.jsp" %>
            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">
                <div id="content">
                    <!---Header --->
                    <%@include file="AD_header.jsp" %>
                    <!-- Container Fluid-->
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0" style="color:black;">Dashboard</h1>
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="./">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                            </ol>
                        </div>

                        <div class="mb-3">
                            <div class="mb-4 row justify-content-center">
                                <!-- Total Incomes Card Example -->
                                <div class="col-lg-5" style="margin-left: 15px;">
                                    <div class="card" ">
                                        <div class="card-body">
                                            <div class="row align-items-center">
                                                <div class="col mr-2">
                                                    <div class="mb-0 font-weight-bold text-gray mb-2" style="font-size: 17px;color: #000;font-weight: bold;">TOTAL INCOMES</div>
                                                    <div class="mb-0 font-weight-bold text-gray mb-2" style="font-size: 15px; color: black;">Today: $<c:out value="${totalIncomeToday}" /></div>
                                                    <div class="mb-0 font-weight-bold text-gray" style="font-size: 15px; color: black">Yesterday: $<c:out value="${totalIncomeYesterday}" /></div>                            
                                                </div>
                                                <div class="col-auto">
                                                    <i class="far fa-file-alt fa-2x text-warning"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Number of Orders Card Example -->
                                <div class="col-lg-5">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row align-items-center">
                                                <div class="col mr-2">
                                                    <div class="mb-0 font-weight-bold text-gray mb-2" style="font-size: 15px;color: #000;font-weight: bold;">NUMBER OF ORDERS</div>
                                                    <div class="mb-0 font-weight-bold text-gray mb-2" style="font-size: 15px; color: black;">Today: <c:out value="${numberOfOrdersToday}" /></div>
                                                    <div class="mb-0 font-weight-bold text-gray" style="font-size: 15px; color: black;">Yesterday: <c:out value="${numberOfOrdersYesterday}" /></div>                            
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-calendar fa-2x text-primary"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="container">
                                <div class="mb-4 row justify-content-center">

                                    <div class="card status-card col-md-4">
                                        <div class="card-body">
                                            <div class="row align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">PENDING ORDERS: <c:out value="${pendingOrders}" /></div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-hourglass-start text-warning status-icon"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card status-card col-md-4">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">IN PROGRESS ORDERS: <c:out value="${inProgressOrders}" /></div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-spinner text-primary status-icon"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card status-card col-md-4">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">DELIVERING ORDERS: <c:out value="${deliveringOrders}" /></div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-shipping-fast text-info status-icon"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card status-card col-md-4">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">COMPLETED ORDERS: <c:out value="${completedOrders}" /></div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-check text-success status-icon"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card status-card col-md-4">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-uppercase mb-1" style="font-size: 17px;">CANCELED ORDERS: <c:out value="${canceledOrders}" /></div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-times text-danger status-icon"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Order Status Cards -->

                            <div class="row">
                                <div class="col-xl-8 col-lg-7">
                                    <div class="card mb-4">
                                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                            <h6 class="m-0 font-weight-bold text-primary">Revenue Report</h6>
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
                                                <c:forEach var="product" items="${top5Products}">
                                                    <div class="product">
                                                        <span>${product.productName}</span>
                                                        <span>${product.numberOfPurchase} purchases</span>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Invoice Example -->
                            <!-- Message From Customer -->
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
            var monthlyIncomes = ${monthlyIncomes}; // Make sure this is a list
            var monthlyOrders = ${monthlyOrders}; // Make sure this is a list

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

            // Set new default font family and font color to mimic Bootstrap's default styling
            Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
            Chart.defaults.global.defaultFontColor = '#858796';

            function number_format(number, decimals, dec_point, thousands_sep) {
                number = (number + '').replace(',', '').replace(' ', '');
                var n = !isFinite(+number) ? 0 : +number,
                        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                        s = '',
                        toFixedFix = function (n, prec) {
                            var k = Math.pow(10, prec);
                            return '' + Math.round(n * k) / k;
                        };
                s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
                if (s[0].length > 3) {
                    s[0] = s[0].replace(/\B(?=(?:d{3})+(?!\d))/g, sep);
                }
                if ((s[1] || '').length < prec) {
                    s[1] = s[1] || '';
                    s[1] += new Array(prec - s[1].length + 1).join('0');
                }
                return s.join(dec);
            }

            // Area Chart Example
            var ctx = document.getElementById("myAreaChart");
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                    datasets: [{
                            label: "Earnings",
                            lineTension: 0.3,
                            backgroundColor: "rgba(78, 115, 223, 0.5)",
                            borderColor: "rgba(78, 115, 223, 1)",
                            pointRadius: 3,
                            pointBackgroundColor: "rgba(78, 115, 223, 1)",
                            pointBorderColor: "rgba(78, 115, 223, 1)",
                            pointHoverRadius: 3,
                            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                            pointHitRadius: 10,
                            pointBorderWidth: 2,
                            data: monthlyIncomes,
                        },
                        {
                            label: "Orders",
                            lineTension: 0.3,
                            backgroundColor: "rgba(28, 200, 138, 0.5)",
                            borderColor: "rgba(28, 200, 138, 1)",
                            pointRadius: 3,
                            pointBackgroundColor: "rgba(28, 200, 138, 1)",
                            pointBorderColor: "rgba(28, 200, 138, 1)",
                            pointHoverRadius: 3,
                            pointHoverBackgroundColor: "rgba(28, 200, 138, 1)",
                            pointHoverBorderColor: "rgba(28, 200, 138, 1)",
                            pointHitRadius: 10,
                            pointBorderWidth: 2,
                            data: monthlyOrders,
                        }],
                },
                options: {
                    maintainAspectRatio: false,
                    layout: {
                        padding: {
                            left: 10,
                            right: 25,
                            top: 25,
                            bottom: 0
                        }
                    },
                    scales: {
                        xAxes: [{
                                time: {
                                    unit: 'date'
                                },
                                gridLines: {
                                    display: false,
                                    drawBorder: false
                                },
                                ticks: {
                                    maxTicksLimit: 7
                                }
                            }],
                        yAxes: [{
                                ticks: {
                                    maxTicksLimit: 5,
                                    padding: 10,
                                    callback: function (value, index, values) {
                                        return '$' + number_format(value);
                                    }
                                },
                                gridLines: {
                                    color: "rgb(234, 236, 244)",
                                    zeroLineColor: "rgb(234, 236, 244)",
                                    drawBorder: false,
                                    borderDash: [2],
                                    zeroLineBorderDash: [2]
                                }
                            }],
                    },
                    legend: {
                        display: false
                    },
                    tooltips: {
                        backgroundColor: "rgb(255,255,255)",
                        bodyFontColor: "#858796",
                        titleMarginBottom: 10,
                        titleFontColor: '#6e707e',
                        titleFontSize: 14,
                        borderColor: '#dddfeb',
                        borderWidth: 1,
                        xPadding: 15,
                        yPadding: 15,
                        displayColors: false,
                        intersect: false,
                        mode: 'index',
                        caretPadding: 10,
                        callbacks: {
                            label: function (tooltipItem, chart) {
                                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                                return datasetLabel + ': ' + number_format(tooltipItem.yLabel);
                            }
                        }
                    }
                }
            });
        </script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="AD_js/ruang-admin.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>
    </body>
</html>
