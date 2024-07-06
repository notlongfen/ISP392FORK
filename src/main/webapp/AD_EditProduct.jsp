<%@page import="com.mycompany.isp392.brand.BrandDTO" %>
<%@page import="com.mycompany.isp392.product.ProductDTO" %>
<%@page import="com.mycompany.isp392.product.ProductError" %>
<%@page import="com.mycompany.isp392.category.ChildrenCategoryDTO" %>
<%@page import="com.mycompany.isp392.category.CategoryUtils" %>
<%@page import="com.mycompany.isp392.category.CategoryDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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
                   if ((loginUser == null || 2!=loginUser.getRoleID()) && (loginUser == null || 3!=loginUser.getRoleID()) ) {
                       response.sendRedirect("US_SignIn.jsp");
                       return;
                   }
                    %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="form-container">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Edit Product</h2>
                            <%
                                ProductDTO product = (ProductDTO) request.getAttribute("PRODUCT");
                                List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute("BRAND_LIST");
                                List<ChildrenCategoryDTO> categoryList = (List<ChildrenCategoryDTO>) request.getAttribute("CATEGORY_LIST");
                                List<CategoryDTO> productCategories = (List<CategoryDTO>) request.getAttribute("PRODUCT_CATEGORIES");
                            %>
                            <form action="EditProductController" method="post">
                                <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="productName">Product Name</label>
                                        <input type="text" class="form-control" id="productName" name="productName" value="<%= product.getProductName() %>">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="brandID">Brand</label>
                                        <select class="form-control" id="brandID" name="brandID">
                                            <option value="">Choose...</option>
                                            <%
                                                for (BrandDTO brand : brandList) {
                                            %>
                                            <option value="<%= brand.getBrandID() %>" <%= brand.getBrandID() == product.getBrandID() ? "selected" : "" %>><%= brand.getBrandName() %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="description">Description</label>
                                        <textarea class="form-control" id="description" name="description" rows="3"><%= product.getDescription() %></textarea>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="status">Status</label>
                                        <select class="form-control" id="status" name="status">
                                            <option value="1" <%= product.getStatus() == 1 ? "selected" : "" %>>Available</option>
                                            <option value="0" <%= product.getStatus() == 0 ? "selected" : "" %>>Deleted</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-8">
                                        <label for="numberOfPurchasing">Number of Purchasing</label>
                                        <input type="number" class="form-control" id="numberOfPurchasing" name="numberOfPurchasing" value="<%= product.getNumberOfPurchase() %>">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="categoryIDs">Categories</label>
                                        <div id="categories">
                                            <%
                                                for (ChildrenCategoryDTO category : categoryList) {
                                                    boolean isSelected = CategoryUtils.isCategorySelected(productCategories, category.getCdCategoryID());
                                            %>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="category<%= category.getCdCategoryID() %>" name="categoryIDs" value="<%= category.getCdCategoryID() %>" <%= isSelected ? "checked" : "" %>>
                                                <label class="form-check-label" for="category<%= category.getCdCategoryID() %>"><%= category.getCategoryName() %></label>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group text-center">
                                    <button type="submit" class="btn btn-danger btn-custom" name="action" value="Edit_Product">Submit</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
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

        <!-- Error Modal -->
        <% if (request.getAttribute("PRODUCT_ERROR") != null) { %>
        <%@include file="errorModal.jsp" %>
        <% } %>
        <% if (request.getAttribute("PRODUCT_ERROR") != null) { %>
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
    </body>
</html>
