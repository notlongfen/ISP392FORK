<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.brand.BrandDTO"%>
<%@page import="com.mycompany.isp392.brand.BrandError"%>
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
                    <%
                                                                        if (loginUser == null || 2 != loginUser.getRoleID()) {
                                                                            response.sendRedirect("US_SignIn.jsp");
                                                                            return;
                                                                        }
                    %>
                    <div class="container-fluid" id="container-wrapper">

                        <div class="form-container">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Edit Brand</h2>
                            <form action="EditBrandController" method="post" enctype="multipart/form-data">
                                <div class="form-row">
                                    <div class="form-group col-md-8 mt-xl-5" style="margin-left: 100px;">
                                        <label for="brand">Brand Name</label>
                                        <% 
    BrandDTO brand = (BrandDTO) request.getAttribute("BRAND");
    if (brand != null) {
                                        %>

                                        

                                        <input type="text" class="form-control" name="newBrandName" value="<%= brand.getBrandName() %>" 
                                               style="width: 550px">
                                        <input type="hidden" class="form-control" name="oldBrandName" value="<%= brand.getBrandName() %>">
                                        <input type="hidden" name="brandID" value="<%= brand.getBrandID() %>">
                                        <input type="hidden" name="edit" value="Edit">
                                        <input type="hidden" name="oldStatus" value="<%= brand.getStatus() %>">
                                    </div>
                                    <div class="form-group col-md-8 mt-xl-5" style="margin-left: 100px;">
                                        <label for="brandImage">Brand Image</label>
                                        <div class="col-md-3">
                                            <img src="<%= brand.getImage() %>" alt="Brand Image" class="img-thumbnail" style="max-width: 100px; max-height: 100px; margin-right: 10px;">
                                        </div>
                                        <div class="mt-3">
                                            <input type="file" class="form-control-file" id="brandImage" name="brandImage" style="width: 550px">
                                        </div>
                                    </div>
                                    <div class="form-group col-md-8 mt-xl-5" style="margin-left: 100px;">
                                        <label for="status">Status</label>
                                        <select class="form-control" id="status" name="status" style="width: 550px">
                                            <option value="1" <%= brand.getStatus() == 1 ? "selected" : "" %>>Available</option>
                                            <option value="0" <%= brand.getStatus() == 0 ? "selected" : "" %>>Deleted</option>
                                        </select>
                                    </div>
                                    <% } %>
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
                                    BrandError brandError = (BrandError) request.getAttribute("BRAND_ERROR");
                                    if (brandError != null) {
                                        if (brandError.getBrandNameError() != null && !brandError.getBrandNameError().isEmpty()) {
                                %>
                                <li class="list-group-item list-group-item-danger"><%= brandError.getBrandNameError() %></li>
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

            <% if (request.getAttribute("BRAND_ERROR") != null) { %>
            <script>
                $(document).ready(function () {
                    $('#errorModal').modal('show');
                });
            </script>
            <% } %>

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
        </div>
    </body>
</html>

