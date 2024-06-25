<%@page import="com.mycompany.isp392.brand.BrandDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <title>Edit Brands</title>
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

                        <div class="form-container" style="height: 300px">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Edit Brand</h2>
                            <form action="MainController" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-8 mt-xl-5" style="margin-left: 100px;">
                                        <label for="brand">Brand Name</label>
                                        <% 
                                            BrandDTO brand = (BrandDTO) request.getAttribute("BRAND");
                                            if (brand != null) {
                                        %>
                                        <input type="text" class="form-control" name="brandName" value="<%= brand.getBrandName() %>" 
                                               style="width: 550px">
                                        <input type="hidden" name="brandID" value="<%= brand.getBrandID() %>">
                                        <% } %>
                                    </div>

                                </div>
                                <div class="form-group text-center">
                                    <button type="submit" class="btn btn-danger btn-custom" name="action" value="Edit_Brand">Submit</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Include necessary scripts -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDzwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="AD_js/ruang-admin.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>
        <script src="AD_js/demo/chart-area-demo.js"></script>
    </body>
</html>
