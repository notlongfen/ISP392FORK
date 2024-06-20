<%-- 
    Document   : sidebar
    Created on : May 30, 2024, 4:07:54 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SiderBar</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .logout-btn {
                position: absolute;
                bottom: 20px;
                left: 20px;
                display: flex;
                align-items: center;
                color: #000;
                text-decoration: none;
            }
            .logout-btn i {
                margin-right: 18px;
            }
        </style>
    </head>
    <body>
        <ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar" style="background: #C43337">
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp" style="background: #FFF">
                <div class="sidebar-brand-text" style="color: #C43337">ISP392<span style="color: #000">SHOP</span></div>
            </a>
            <hr class="sidebar-divider my-0">
            <hr class="sidebar-divider">
            
            <div class="sidebar-heading" style="font-size: 15px">
                Features
            </div>
<!--            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBootstrap" aria-expanded="true" aria-controls="collapseBootstrap">
                    <i class="far fa-fw fa-window-maximize"></i>
                    <span>Product</span>
                </a>
                <div id="collapseBootstrap" class="collapse" aria-labelledby="headingBootstrap" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">vbnfvhn</h6>
                        <a class="collapse-item" href="alerts.html">Product List</a>
                        <a class="collapse-item" href="buttons.html">Categories</a>
                        <a class="collapse-item" href="dropdowns.html">Brand</a>
                    </div>
                </div>
            </li>-->
            <li class="nav-item mt-3">
                <a class="nav-link" href="AD_ProductList.jsp">
                    <i class="fas fa-box"></i>
                    <span>Product List</span>
                </a>
            </li>
            
            <li class="nav-item mt-3">
                <a class="nav-link" href="AD_CategoriesList.jsp">
                    <i class="fa fa-pie-chart"></i>
                    <span>Categories</span>
                </a>
            </li>
            
            <li class="nav-item mt-3">
                <a class="nav-link" href="AD_ManageBrands.jsp">
                    <i class="fas fa-tshirt"></i>
                    <span>Brand</span>
                </a>
            </li>

            <li class="nav-item mt-3">
                <a class="nav-link" href="AD_SupportList.jsp">
                    <i class="fas fa-comment"></i>
                    <span>Support</span>
                </a>
            </li>
            
            <li class="nav-item mt-3">
                <a class="nav-link" href="AD_OrderList.jsp">
                    <i class="fas fa-cash-register"></i>
                    <span>Order</span>
                </a>
            </li>
            
            <li class="nav-item mt-3">
                <a class="nav-link" href="AD_PromotionList.jsp">
                    <i class="fas fa-tags"></i>
                    <span>Discount</span>
                </a>
            </li>
            
            <li class="nav-item mt-3">
                <a class="nav-link" href="ProductList.jsp">
                    <i class="fa fa-bar-chart"></i>
                    <span>Statistic Report</span>
                </a>
            </li>
            
            <hr class="sidebar-divider">

            <a href="#" class="logout-btn">
                <i class="fas fa-sign-out-alt"></i>
                Log out
            </a>
        </ul>

        <!-- jQuery and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
