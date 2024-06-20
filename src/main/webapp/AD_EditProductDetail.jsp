<%-- 
    Document   : EditProduct
    Created on : Jun 3, 2024, 4:15:02 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                    <div class="container-fluid" id="container-wrapper">
                        <div class="form-container">
                            <h2 class="text-center mb-5" style="color: #000; font-weight: bold;">Edit Product Detail</h2>
                                <form>
                                <div class="form-row mb-3">
                                <div class="form-group col-md-8">
                                    <label for="productName">Product Name</label>
                                    <input type="text" class="form-control" id="productName" value="Air force 1">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="brand">Brand</label>
                                    <select class="form-control" id="brand" >
                                        <option>Nike</option>
                                        <option>Adidas</option>
                                        <option>Puma</option>
                                        <option>Vans</option>
                                    </select>
                                </div>
                        </div>

                        <div class="form-row mb-3">
                            <div class="form-group col-md-4">
                                <label for="stockQuantity">Stock Quantity</label>
                                <input type="text" class="form-control" id="stockQuantity" value="100">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="price">Price</label>
                                <input type="text" class="form-control" id="price" value="2,000">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="importDate">Import Date</label>
                                <input type="text" class="form-control" id="importDate" value="01/06/2024">
                            </div>
                        </div>


                        <div class="form-row mb-3">

                            <div class="form-group col-md-4">
                                <label for="size">Size</label>
                                <select class="form-control" id="brand" >
                                    <option>39</option>
                                    <option>40</option>
                                    <option>41</option>
                                    <option>42</option>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="color">Color</label>
                                <input type="text" class="form-control" id="color" value="White">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="category">Category</label>
                                <input type="text" class="form-control" id="category" value="Shoes">
                            </div>
                        </div>

                        <div class="form-group mb-3">
                            <label for="description">Description</label>
                            <textarea class="form-control" id="description" rows="3">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</textarea>
                        </div>

                        <div class="form-group mb-5">
                            <label for="imageGallery">Image Gallery</label>
                            <div class="row">
                                <div>
                                    <img src="images/product_8.png" alt="Image 1" class="img-thumbnail" style="width: 100px; height: 100px; margin-right: 10px;">
                                    <img src="images/product_4.png" alt="Image 2" class="img-thumbnail" style="width: 100px; height: 100px; margin-right: 10px;">
                                    <img src="images/product_6.png" alt="Image 3" class="img-thumbnail" style="width: 100px; height: 100px; margin-right: 10px;">
                                </div>
                                <div class="mt-5">
                                    <input type="file" class="form-control-file" id="imageUpload">
                                </div>
                            </div>
                        </div>

                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-danger btn-custom">Submit</button>
                            <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                            <!--<a href="previousPage.jsp" class="btn btn-secondary btn-custom">Back</a>-->
                        </div>
                        </form>
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
