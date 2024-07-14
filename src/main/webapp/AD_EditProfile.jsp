<%-- 
    Document   : AD_EditProfile
    Created on : Jul 13, 2024, 8:04:07 AM
    Author     : TTNHAT
--%>

<%@page import="com.mycompany.isp392.user.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        
        <style>
            body {
                background-color: #f8f9fa;
                padding: 0;
                margin: 0;
            }
            .profile-container {
                padding: 50px;
                background-color: #fff;
                border-radius: 10px;
                /*                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
                padding-left: 100px;
            }
            .profile-sidebar {
                background-color: #fff;
                border: none;
                /*                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
                height: 500px;
            }
            /*.form-control{color: black;}*/
            .btn.middle {
                background-color: black;
                color: white;
                display: flex;
                margin: 20px auto 0; /* Centering the button */
            }
            .flex-container {
                display: flex;
            }
            .flex-item {
                flex: 1;
                margin: 10px;
            }
            .profile-sidebar{
                width: 300px;
                height: 500px;
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

            .input-group{
                position: relative;
            }
            .btn-format{
                background-color: #fff;
                color:#000;
                background-color: transparent; /* Xóa nền màu xám */
                border: none; /* Xóa đường viền */
                color: inherit; /* Giữ nguyên màu chữ */
                padding: 0; /* Xóa khoảng đệm */
                cursor: pointer; /* Thay đổi con trỏ khi hover */
            }
            .btn-format h5{
                color: #ffffff;
                font-size: medium;
            }
            .info {
                background-color: transparent; /* Xóa nền màu xám */
                border: none; /* Xóa đường viền */
                color: inherit; /* Giữ nguyên màu chữ */
                padding: 0; /* Xóa khoảng đệm */
                cursor: pointer; /* Thay đổi con trỏ khi hover */
            }
            .right-element {
                position: inherit;
                right: 0;
            }
            .btn-format.right-element button {
                display: inline-block;
                margin-left: 5px; /* Điều chỉnh khoảng cách giữa hai nút */
            }

        </style>
    </head>
    <body>


        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if(user == null){
                response.sendRedirect("US_SignIn.jsp");
                return;
            }
        %>

        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="AD_sidebar.jsp" %>

            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">

                <div id="content">
                    <!-- Header -->
                    <%@include file="AD_header.jsp" %>
                    <div class="container-fluid" id="container-wrapper">


                        <div class="col-lg-8 profile-container">
                            <div class="text-left">
                                <div class="col-sm-6">
                                    <h1 class="mb-9" left-element>Edit Profile</h1>
                                </div>
                            </div>
                            <form action="MainController" method="POST">
                                <input type="hidden" name="userID" value="<%= user.getUserID()%>" readonly=""/>
                                <input type="hidden" name="oldEmail" value="<%= user.getEmail()%>" readonly=""/>  
                                <input type="hidden" name="oldPhone" value="<%= user.getPhone()%>" readonly=""/>
                                <div class="form-row mb-3">
                                    <div class="form-group col-md-6">
                                        <label for="fullName" class="form-label">Username</label>   
                                        <input type="text" style="color: black; background-color: #fff" class="form-control" id="fullName" name="userName" value="<%= user.getUserName()%>" required=""/>
                                    </div>
                                </div>

                                <div class="form-row mb-3">
                                    <div class="form-group col-md-6 mb-3">
                                        <label for="email" class="form-label">Email Address</label>
                                        <input type="text" style="color: black; background-color: #fff" class="form-control" id="email" name="email" value="<%= user.getEmail()%>" required="">
                                    </div>
                                    <div class="form-group col-md-6 mb-3">
                                        <label for="phoneNumber" class="form-label">Phone Number</label>
                                        <input type="number" style="color: black; background-color: #fff"class="form-control" id="phoneNumber" name="phone" value="<%= user.getPhone()%>" required=""> 
                                    </div>
                                </div>

                                <div class="form-row mb-3">
                                    <div class="form-group col-md-6">
                                        <label for="password">Password</label>
                                        <input type="password" class="form-control" id="password" name="password" value=""/>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="confirm">Confirm Password</label>
                                        <input type="password" class="form-control" id="confirm" name="confirm" value=""/>
                                    </div>
                                </div>

                                <div class="form-group" style="margin-top: 10px">
                                    <button type="submit" class="btn btn-danger btn-custom" name="action" value="EditEmpProfile">Save</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                </div>

                            </form>                          
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Error Modal -->
        <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title" id="errorModalLabel">Error</h5>
                        <button type="button" class="close text-white" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul class="list-group list-group-flush">
                            <%
                                UserError error = (UserError) request.getAttribute("EDIT_ERROR");
                                if (error != null) {
                                    if (error.getEmailError() != null && !error.getEmailError().isEmpty()) {
                            %>
                            <li class="list-group-item list-group-item-danger"><%= error.getEmailError() %></li>
                                <%
                                        }
                                        if (error.getPhoneError() != null && !error.getPhoneError().isEmpty()) {
                                %>
                            <li class="list-group-item list-group-item-danger"><%= error.getPhoneError() %></li>
                                <%
                                        }
                                        if(error.getPasswordError() != null && !error.getPasswordError().isEmpty()) {
                                %>
                            <li class="list-group-item list-group-item-danger"><%= error.getPasswordError() %></li>
                                <%     
                                         }
                                     }
                                %>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <script>
            <% if (request.getAttribute("EDIT_ERROR") != null) { %>
                $(document).ready(function () {
                    $('#errorModal').modal('show');
                });
            <% } %>
                
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

            document.addEventListener('DOMContentLoaded', function () {
                var editIcons = document.querySelectorAll('.edit-icon');
                editIcons.forEach(function (icon) {
                    icon.addEventListener('click', function () {
                        var input = this.parentNode.querySelector('.form-control');
                        input.disabled = !input.disabled;
                        // Thay đổi mãu chữ
                        if (input.disabled) {
                            input.style.color = 'black';
                        } else {
                            input.style.color = 'red';
                        }
                    });
                });
            });

            document.addEventListener('DOMContentLoaded', function () {
                var editProfileButton = document.querySelector('.btn.middle');
                var inputs = document.querySelectorAll('.form-control');
                editProfileButton.addEventListener('click', function () {
                    inputs.forEach(function (input) {
                        input.disabled = true;
                        if (input.disabled) {
                            input.style.color = '#000';
                        } else {
                            input.style.color = 'red';
                        }
                    });
                });
            });
        </script>

        <!-- Include necessary scripts -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>
        <script src="AD_js/demo/chart-area-demo.js"></script>
        <script src="AD_js/ruang-admin.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
</body>