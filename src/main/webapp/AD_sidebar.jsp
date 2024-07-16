<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.user.UserDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sidebar</title>
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
        <%
            UserDTO loggedInUser = (UserDTO) session.getAttribute("LOGIN_USER");
            int roleID = loggedInUser != null ? loggedInUser.getRoleID() : -1;
        %>
        <form action="MainController" method="post">
            <ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar" style="background: #C43337">
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="HomePageController" style="background: #FFF">
                    <div class="sidebar-brand-text" style="color: #C43337">KRO<span style="color: #000">NO</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <div class="sidebar-heading pt-3" style="font-size: 15px">
                    Features
                </div>

                <% if (roleID == 2 || roleID == 3) { %> <!-- Shop Manager, Shop Staff -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Manage_Products_Page">
                        <i class="fas fa-box"></i>
                        <span>Products</span>
                    </button>
                </li>
                <% } %>

                <% if (roleID == 2) { %> <!-- Shop Manager -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Search_Category">
                        <i class="fa fa-pie-chart"></i>
                        <span>Categories</span>
                    </button>
                </li>
                <% } %>

                <% if (roleID == 2) { %> <!-- Shop Manager -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Manage_Brands_Page">
                        <i class="fas fa-tshirt"></i>
                        <span>Brands</span>
                    </button>
                </li>
                <% } %>

                <% if (roleID == 2 || roleID == 3) { %> <!-- Shop Manager, Shop Staff -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Manage_Support">
                        <i class="fas fa-comment"></i>
                        <span>Supports</span>
                    </button>
                </li>
                <% } %>

                <% if (roleID == 2) { %> <!-- Shop Manager -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Manage_Order">
                        <i class="fas fa-cash-register"></i>
                        <span>Orders</span>
                    </button>
                </li>
                <% } %>

                <% if (roleID == 2) { %> <!-- Shop Manager -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Manage promotions">
                        <i class="fas fa-tags"></i>
                        <span>Promotions</span>
                    </button>
                </li>
                <% } %>

                <% if (roleID == 1) { %> <!-- System Manager -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="Search_User">
                        <i class="fas fa-users"></i>
                        <span>Users</span>
                    </button>
                </li>
                <% } %>


                <% if (roleID == 2) { %> <!-- Shop Manager -->
                <li class="nav-item mt-3">
                    <button type="submit" class="nav-link btn btn-link" name="action" value="History">
                        <i class="fas fa-tags"></i>
                        <span>View Update History</span>
                    </button>
                </li>
                <% } %>

                <li class="nav-item mt-3">
                    <a class="nav-link" href="AD_Statistic.jsp">
                        <i class="fas fa-tshirt"></i>
                        <span>AD_Statistic</span>
                    </a>
                </li>
                
                 <li class="nav-item mt-3">
                    <a class="nav-link" href="AD_StatisticCustomer.jsp">
                        <i class="fas fa-tshirt"></i>
                        <span>AD_StatisticCustomer</span>
                    </a>
                </li>

                <hr class="sidebar-divider">

                <button type="submit" class="logout-btn btn btn-link" name="action" value="Logout">
                    <i class="fas fa-sign-out-alt"></i>
                    Log out
                </button>
            </ul>
        </form>

        <!-- jQuery and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
