
<%@page import="com.mycompany.isp392.user.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <title>Add New Employee</title>
        <style>
            .form-container {
                max-width: 800px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #dee2e6;
                border-radius: 12px;
                background-color: #f8f9fa;
            }
            .form-group label {
                font-weight: bold;
            }
        </style>
    </head>
    <body id="page-top">

        <div id="wrapper">
            <!-- Sidebar -->

            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">

                <div id="content">
                    <!-- Header -->
            

                    <div class="container-fluid" id="container-wrapper">

                        <div class="form-container" style="height: 600px;">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Add A New Employee</h2>
                            <form action="MainController" method="POST">
                                <div class="form-row">
                                    <div class="form-group col-md-8 mt-xl-5" style="margin-left: 100px;">
                                        <label for="brand" style="margin-top: 10px">Employee Name</label>
                                        <input type="text" class="form-control" id="empName" placeholder="Enter employee name" 
                                               style="width: 550px" name="userName">
                                        <label for="roleID" style="margin-top: 10px">Role</label>
                                        <div style="color: #dee2e6">
                                            <select class="form-select form-control" id="roleID" aria-label=".form-select-sm" placeholder="Employee role" 
                                                    style="width: 550px" name="roleID">
                                                <option value="1">1 - System Manager</option>
                                                <option value="2">2 - Shop Manager</option>
                                                <option value="3">3 - Shop Staff</option>
                                            </select>
                                        </div>
                                        <label for="email" style="margin-top: 10px">Email</label>
                                        <input type="email" class="form-control" id="empEmail" placeholder="Enter email" 
                                               style="width: 550px" name="email">
                                        <label for="phone" style="margin-top: 10px">Phone</label>
                                        <input type="number" class="form-control" id="empPhone" placeholder="Enter phone number" 
                                               style="width: 550px" name="phone">
                                        <label for="password" style="margin-top: 10px">Password</label>
                                        <input type="password" class="form-control" id="empPassword" placeholder="Enter password" 
                                               style="width: 550px" name="password">
                                    </div>
                                </div>
                                <div class="form-group text-center" style="margin-top: 10px">
                                    <button type="submit" class="btn btn-danger btn-custom" name="action" value="Add_Employee">Submit</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                </div>
                            </form>
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
                            <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <ul class="list-group list-group-flush">
                                <%
                                    UserError error = (UserError) request.getAttribute("USER_ERROR");
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
                                    }
                                %>
                            </ul>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <% if (request.getAttribute("USER_ERROR") != null) { %>
            <script>
                $(document).ready(function () {
                    $('#errorModal').modal('show');
                });
            </script>
            <% } %>

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
            <script src="AD_js/ruang-admin.min.js"></script>
            <script src="vendor/chart.js/Chart.min.js"></script>
            <script src="AD_js/demo/chart-area-demo.js"></script>
    </body>
</html>
