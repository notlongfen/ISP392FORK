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
        <title>Create New Product</title>
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

                        <div class="form-container">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Create New Product</h2>
                            <form>
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="productName">Product Name</label>
                                        <input type="text" class="form-control" id="productName" placeholder="Enter Product Name">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="brand">Brand</label>
                                        <select class="form-control" id="brand">
                                            <option selected>Choose...</option>
                                            <option>Brand A</option>
                                            <option>Brand B</option>
                                            <option>Brand C</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="stockQuantity">Stock Quantity</label>
                                        <input type="number" class="form-control" id="stockQuantity" placeholder="Enter Stock Quantity">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="price">Price</label>
                                        <input type="number" class="form-control" id="price" placeholder="Enter Price">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="importDate">Import Date</label>
                                        <input type="date" class="form-control" id="importDate">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="size">Size</label>
                                        <input type="text" class="form-control" id="size" placeholder="Enter Size">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="color">Color</label>
                                        <input type="text" class="form-control" id="color" placeholder="Enter Color">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="category">Category</label>
                                        <select class="form-control" id="category">
                                            <option selected>Choose...</option>
                                            <option>Category 1</option>
                                            <option>Category 2</option>
                                            <option>Category 3</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <textarea class="form-control" id="description" rows="3" placeholder="Enter Description"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="uploadImage">Upload Image</label>
                                    <input type="file" class="form-control-file" id="uploadImage">
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
