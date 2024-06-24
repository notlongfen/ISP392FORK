<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <title>Add Product Details</title>
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
<body>
    <div class="container">
        <div class="form-container">
            <h2 class="text-center" style="color: #000; font-weight: bold;">Add Product Details</h2>
            <form action="MainController" method="post" >
                <div class="form-group">
                    <label for="productID">Product</label>
                    <select class="form-control" id="productID" name="productID" required>
                        <option value="">Choose...</option>
                        <%
                            List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
                            if (productList != null) {
                                for (ProductDTO product : productList) {
                        %>
                        <option value="<%= product.getProductID() %>"><%= product.getProductName() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="color">Color</label>
                    <input type="text" class="form-control" id="color" name="color" required>
                </div>
                <div class="form-group">
                    <label for="sizes">Size</label>
                    <div id="sizes">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="size40" name="sizes" value="40">
                            <label class="form-check-label" for="size40">40</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="size41" name="sizes" value="41">
                            <label class="form-check-label" for="size41">41</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="size42" name="sizes" value="42">
                            <label class="form-check-label" for="size42">42</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="size43" name="sizes" value="43">
                            <label class="form-check-label" for="size43">43</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="size44" name="sizes" value="44">
                            <label class="form-check-label" for="size44">44</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="stockQuantity">Stock Quantity</label>
                    <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" required>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" class="form-control" id="price" name="price" required>
                </div>
                <div class="form-group">
                    <label for="importDate">Import Date</label>
                    <input type="date" class="form-control" id="importDate" name="importDate" required>
                </div>
                <div class="form-group">
                    <label for="uploadImage">Upload Image</label>
                    <input type="file" class="form-control-file" id="uploadImage" name="image">
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-danger" name="action" value="Add_Product_Details">Submit</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
