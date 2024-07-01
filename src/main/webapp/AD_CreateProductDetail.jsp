<%@page import="com.mycompany.isp392.product.ProductDTO"%>
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
            <% 
                int parentProductID = (int) request.getAttribute("PARENT_PRODUCT_ID");
                String parentProductName = (String) request.getAttribute("PARENT_PRODUCT_NAME");
            %>
            <h2 class="text-center" style="color: #000; font-weight: bold;">Add Product Details for <%= parentProductName %></h2>
            <form action="AddProductDetailsController" method="post" enctype="multipart/form-data">
                <input type="hidden" name="parentProductID" value="<%= parentProductID %>">
                <input type="hidden" name="parentProductName" value="<%= parentProductName %>">
                <div class="form-group">
                    <label for="color">Color</label>
                    <input type="text" class="form-control" id="color" name="color" required>
                </div>
                <div class="form-group">
                    <label for="sizes">Size</label>
                    <div id="sizes">
                        <% for (int i = 36; i <= 45; i++) { %>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" id="size<%= i %>" name="sizes" value="<%= i %>">
                            <label class="form-check-label" for="size<%= i %>"><%= i %></label>
                        </div>
                        <% } %>
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
                    <label for="uploadImages">Upload Images</label>
                    <input type="file" class="form-control-file" id="uploadImages" name="images" multiple>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-danger" name="action" value="Add_Product_Details">Submit</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
