<!DOCTYPE html>
<html lang="en">
    <%@ page import="com.mycompany.isp392.user.*" %>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="img/logo/logo.png" rel="icon">
        <title>RuangAdmin - Dashboard</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="AD_css/ruang-admin.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .account
            {
                display: inline-block;
                position: relative;
                min-width: 180px;
                padding-left: 5px;
                margin-right: 30px;
                margin-top: 10px;
                /*text-align: center;*/
                vertical-align: middle;
                /*background: #1e1e27;*/
                color: #FAFDFB;
            }
            .account > a
            {
                display: block;
                /*color: #b5aec4;*/
                font-weight: 200;
                height: 50px;
                line-height: 50px;
                font-size: 17px;
            }
            .account > a > i
            {
                margin-left: 8px;
            }
            .account:hover .account_selection
            {
                visibility: visible;
                opacity: 1;
                top: 100%;
            }
            .account_selection
            {
                display: block;
                position: absolute;
                right: 0;
                top: 120%;
                margin: 0;
                width: 100%;
                /*background: #FFFFFF;*/
                visibility: hidden;
                opacity: 0;
                z-index: 1;
                /*box-shadow: 0 0 25px rgba(63, 78, 100, 0.15);*/
                -webkit-transition: opacity 0.3s ease;
                -moz-transition: opacity 0.3s ease;
                -ms-transition: opacity 0.3s ease;
                -o-transition: opacity 0.3s ease;
                transition: all 0.3s ease;
            }
            .account_selection li
            {
                padding-left: 5px;
                padding-right: 10px;
                line-height: 50px;
            }
            .account_selection li a
            {
                display: block;
                color: #232530;
                border-bottom: solid 1px #dddddd;
                font-size: 17px;
                -webkit-transition: all 0.3s ease;
                -moz-transition: all 0.3s ease;
                -ms-transition: all 0.3s ease;
                -o-transition: all 0.3s ease;
                transition: all 0.3s ease;
            }
            .account_selection li a:hover
            {
                color: #b5aec4;
            }
            .account_selection li:last-child a
            {
                border-bottom: none;
            }
            .account_selection li a i
            {
                margin-right: 10px;
            }
            
        </style>
    </head>

    <body id="page-top">
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                response.sendRedirect("US_SignIn.jsp");
                return;
            }
        %>

        <!-- TopBar -->
        <nav class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top" style="background: #C43337">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-search fa-fw"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                         aria-labelledby="searchDropdown">
                        <form class="navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-1 small" placeholder="What do you want to look for?"
                                       aria-label="Search" aria-describedby="basic-addon2" style="border-color: #3f51b5;">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </li>
                
                <div class="col-md-6 text-right">
                    <div class="top_nav_right">
                        <ul class="top_nav_menu">

                            <!-- Currency / Language / My Account -->

                            <li class="account">
                                <a href="#" style="color: #FFFFFF">
                                    <i class="fas fa-user-alt  " style="color: #fff; margin-right: 10px;"></i>
                                    <%= loginUser.getUserName() %>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                   
                                <ul class="account_selection">
                                    <li><a href="MainController?action=EditEmpProfilePage"><i class="fas fa-pencil-alt" style="color: #232530"></i>Edit Profile</a></li>
                                    <li><a href="MainController?action=Logout"><i class="fa fa-sign-out" style="color: #232530"></i>Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </ul>
        </nav>
        <!-- Topbar -->



        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="AD_js/ruang-admin.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>
        <script src="AD_js/demo/chart-area-demo.js"></script>  
    </body>

</html>