<%-- 
    Document   : CreateProduct
    Created on : Jun 2, 2024, 4:10:09 PM
    Author     : jojo
--%>

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
            <%@include file="AD_sidebar.jsp" %>

            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">

                <div id="content">
                    <!-- Header -->
                    <%@include file="AD_header.jsp" %>

                    <div class="container-fluid" id="container-wrapper">

                        <div class="form-container" style="height: 600px;">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Add A New Employee</h2>
                            <form>
                                <div class="form-row">
                                    <div class="form-group col-md-8 mt-xl-5" style="margin-left: 100px;">
                                        <label for="brand" style="margin-top: 10px">Employee Name</label>
                                        <input type="text" class="form-control" id="empName" placeholder="Enter employee name" 
                                               style="width: 550px">
                                        <label for="position" style="margin-top: 10px">Position</label>
                                        <input type="text" class="form-control" id="empPos" placeholder="Enter employee position" 
                                               style="width: 550px">
                                        <label for="email" style="margin-top: 10px">Email</label>
                                        <input type="text" class="form-control" id="empEmail" placeholder="Enter email" 
                                               style="width: 550px">
                                        <label for="phone" style="margin-top: 10px">Phone</label>
                                        <input type="text" class="form-control" id="empPhone" placeholder="Enter phone number" 
                                               style="width: 550px">
                                    </div>

                                </div>
                                <!--                                <div class="form-row">-->
                                <div class="form-group text-center" style="margin-top: 10px">
                                    <button type="submit" class="btn btn-danger btn-custom">Submit</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                </div>
                                <!--                                </div>-->
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Include necessary scripts -->
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
