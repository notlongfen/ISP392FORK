<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<%@ page import="com.mycompany.isp392.brand.BrandDTO"%>
<%@ page import="com.mycompany.isp392.category.CategoryDTO"%>
<%@ page import="java.util.List"%>
<%@page import="com.mycompany.isp392.product.ProductError"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product Detail</title>
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
            .btn-custom {
                margin: 0 5px;
            }
            .text-center {
                text-align: center;
            }
            .mb-3 {
                margin-bottom: 1rem;
            }
            .mb-5 {
                margin-bottom: 3rem;
            }
        </style>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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
                                       if ((loginUser == null || 2!=loginUser.getRoleID()) && (loginUser == null || 3!=loginUser.getRoleID()) ) {
                                           response.sendRedirect("US_SignIn.jsp");
                                           return;
                                       }
                    %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="form-container">
                            <h2 class="text-center mb-5" style="color: #000; font-weight: bold;">Edit Product Detail</h2>

                            <%
                                ProductDetailsDTO productDetail = (ProductDetailsDTO) request.getAttribute("PRODUCT_DETAIL");
                            %>

                            <form action="EditProductDetailsController" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="productDetailID" value="<%= productDetail.getProductDetailsID() %>">
                                <input type="hidden" name="oldStockQuantity" value="<%= productDetail.getStockQuantity() %>">
                                <input type="hidden" name="oldPrice" value="<%= productDetail.getPrice() %>">
                                <input type="hidden" name="oldImportDate" value="<%= productDetail.getImportDate() %>">
                                <input type="hidden" name="oldDetailStatus" value="<%= productDetail.getStatus() %>">
                                <input type="hidden" name="edit" value="Edit">
                                <div class="form-row mb-3">
                                    <div class="form-group col-md-4">
                                        <label for="color">Color</label>
                                        <input type="text" class="form-control" id="color" name="color" value="<%= productDetail.getColor() %>" readonly>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="size">Size</label>
                                        <input type="text" class="form-control" id="size" name="size" value="<%= productDetail.getSize() %>" readonly>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="stockQuantity">Stock Quantity</label>
                                        <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" value="<%= productDetail.getStockQuantity() %>">
                                    </div>
                                </div>

                                <div class="form-row mb-3">
                                    <div class="form-group col-md-6">
                                        <label for="price">Price</label>
                                        <input type="number" class="form-control" id="price" name="price" value="<%= productDetail.getPrice() %>">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="importDate">Import Date</label>
                                        <input type="date" class="form-control" id="importDate" name="importDate" value="<%= productDetail.getImportDate() %>">
                                    </div>
                                </div>

                                <div class="form-group mb-5">
                                    <label for="imageGallery">Image Gallery</label>
                                    <div class="row">
                                        <%
                                            String[] images = productDetail.getImage().split(";");
                                            for (String image : images) {
                                                if (!image.trim().isEmpty()) {
                                        %>
                                        <div class="col-md-3">
                                            <img src="<%= image %>" alt="Product Image" class="img-thumbnail" style="width: 100px; height: 100px; margin-right: 10px;">
                                        </div>
                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                    <div class="mt-3">
                                        <input type="file" class="form-control-file" id="imageUpload" name="imageUpload" multiple>
                                    </div>
                                </div>

                                <div class="form-group mb-3">
                                    <label for="status">Status</label>
                                    <select class="form-control" id="status" name="detailStatus">
                                        <option value="1" <%= productDetail.getStatus() == 1 ? "selected" : "" %>>Available</option>
                                        <option value="0" <%= productDetail.getStatus() == 0 ? "selected" : "" %>>Deleted</option>
                                    </select>
                                </div>

                                <div class="form-group text-center">
                                    <button type="submit" class="btn btn-danger btn-custom">Submit</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer -->
            <%-- Include your footer file here if available --%>
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
