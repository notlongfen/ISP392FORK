<%@page import="com.mycompany.isp392.brand.BrandDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <title>Add Product</title>
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
                <h2 class="text-center" style="color: #000; font-weight: bold;">Add New Product</h2>
                <form action="MainController" method="post">
                    <div class="form-group">
                        <label for="productName">Product Name</label>
                        <input type="text" class="form-control" id="productName" name="productName">
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" ></textarea>
                    </div>
                    <div class="form-group">
                        <label for="brandID">Brand</label>
                        <select class="form-control" id="brandID" name="brandID">
                            <option value="">Choose...</option>
                            <%
                                List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute("BRAND_LIST");
                                String message = (String) request.getAttribute("MESSAGE");
                                if (brandList != null) {
                                    for (BrandDTO brand : brandList) {
                            %>
                            <option value="<%= brand.getBrandID() %>"><%= brand.getBrandName() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-danger" name="action" value="Add_Product">Submit</button>
                    </div>
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-danger" name="action" value="Add_Product_Details_Page">Details</button>
                    </div>
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-danger" name="action" value="Search_Product_Page">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
