<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.product.ProductDTO" %>
<%@ page import="com.mycompany.isp392.brand.BrandDTO" %>
<%@ page import="com.mycompany.isp392.category.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.isp392.user.UserDTO" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Product List</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="AD_css/ruang-admin.min.css" rel="stylesheet">
        <style>
            .pagination .page-link {
                border-radius: 20px;
            }
            .pagination .page-item.active .page-link {
                background-color: #007bff;
                border-color: #007bff;
            }
            .pagination .page-link:focus,
            .pagination .page-link:hover {
                background-color: #007bff;
                border-color: #007bff;
            }
            .pagination .page-item:first-child .page-link {
                border-top-left-radius: 15px;
                border-bottom-left-radius: 15px;
            }
            .pagination .page-item:last-child .page-link {
                border-top-right-radius: 15px;
                border-bottom-right-radius: 15px;
            }
            .delete-btn {
                display: none;
            }
            .table-hover tbody tr:hover .delete-btn {
                display: inline-block;
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
                    UserDTO loginUser1 = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (loginUser == null || (loginUser.getRoleID() != 2 && loginUser.getRoleID() != 3)) {
                        response.sendRedirect("US_SignIn.jsp");
                        return;
                    }
                    %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-900">All Products</h1>
                        </div>
                        <form action="MainController" method="post">
                            <input type="hidden" name="ProductPage" value="AddProductPage">
                            <div class="d-flex justify-content-end mb-4">
                                <button type="submit" class="btn btn-danger" style="background: #C43337;" value="Add_Product_Page" name="action">Create Product</button>
                            </div>
                        </form>
                        <div class="row mb-3">
                            <div class="col-xl-12 mb-4">
                                <div class="card">
                                    <div class="py-3 d-flex flex-row align-items-center justify-content-between">
                                        <!-- Invoice -->
                                    </div>
                                    <div class="table-responsive">
                                        <form>
                                            <div class="row mb-4 mx-2 justify-content-between">
                                                <div class="col">
                                                    <div class="input-group">
                                                        <select id="brandSelect" class="custom-select">
                                                            <option value="Select Brand">Select Brand</option>
                                                            <% 
                                                                List<BrandDTO> brands = (List<BrandDTO>) request.getAttribute("BRAND_LIST");
                                                                if (brands != null) {
                                                                    for (BrandDTO brand : brands) {
                                                                        if (brand.getStatus() == 1) { 
                                                            %> 
                                                            <option value="<%= brand.getBrandName() %>"><%= brand.getBrandName() %></option>
                                                            <% 
                                                                        } // Closing if statement
                                                                    } // Closing for loop
                                                                } // Closing if statement
                                                            %>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <select id="statusSelect" class="custom-select">
                                                        <option value="Select Status">Select Status</option>
                                                        <option value="Available">Available</option>
                                                        <option value="Deleted">Deleted</option>
                                                    </select>
                                                </div>

                                                <div class="col">
                                                    <div class="input-group">
                                                        <input type="text" name="searchText" class="form-control" placeholder="Search...">
                                                        <div class="input-group-append">
                                                            <button class="btn btn-outline-secondary" type="submit" name="action" value="Search_Product">Search</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <table class="table table-hover align-items-center table-flush">
                                        <thead class="thead-light">
                                            <tr>
                                                <th class="text-center">ID</th>
                                                <th class="text-center">
                                                    <button type="button" class="btn p-0" onclick="sortTable()">Name <span id="sortIconProduct">▲</span></button>
                                                </th>
                                                <th class="text-center">
                                                    <button type="button" class="btn p-0" onclick="sortBrandTable()">Brand <span id="sortIconBrand">▲</span></button>
                                                </th>
                                                <th class="text-center">Description</th>
                                                <th class="text-center">Status</th>
                                                <th class="text-center">Number of purchasing</th>
                                                <th class="text-center">Categories</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tableBody">
                                            <% 
                                                List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
                                                if (products != null) {
                                                    for (ProductDTO product : products) {
                                                        String brandName = "";
                                                        for (BrandDTO brand : brands) {
                                                            if (brand.getBrandID() == product.getBrandID()) {  // Comparing primitive int values
                                                                brandName = brand.getBrandName();
                                                                break;
                                                            }
                                                        }
                                            %>
                                            <tr>
                                                <td class="text-center"><%= product.getProductID() %></td>
                                                <td class="text-center">
                                                    <form action="MainController" method="post" style="display:inline;">
                                                        <input type="hidden" name="action" value="Search_ProductDetail">
                                                        <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                                        <a href="javascript:void(0)" onclick="this.parentNode.submit();"><%= product.getProductName() %></a>
                                                    </form>
                                                </td>
                                                <td class="text-center"><%= brandName %></td>
                                                <td class="text-center"><%= product.getDescription() %></td>
                                                <td class="text-center"><span class="badge <%= product.getStatus() == 1 ? "badge-success" : "badge-warning" %>"><%= product.getStatus() == 1 ? "Available" : "Deleted" %></span></td>
                                                <td class="text-center"><%= product.getNumberOfPurchase() %></td>
                                                <td class="text-center">
                                                    <% 
                                                        List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("CATEGORY_LIST_" + product.getProductID());
                                                        if (categories != null) {
                                                            for (CategoryDTO category : categories) {
                                                    %>
                                                    <span class="badge badge-info"><%= category.getCategoryName() %></span>
                                                    <% 
                                                            }
                                                        } else {
                                                    %>
                                                    <span class="badge badge-secondary">No categories</span>
                                                    <% 
                                                        }
                                                    %>
                                                </td>
                                                <td class="text-center action-buttons">
                                                    <% if (product.getStatus() == 1) { %>
                                                    <button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#confirmDeleteModal" data-id="<%= product.getProductID() %>" data-delete-type="product">Delete</button>
                                                    <% } else { %>
                                                    <button type="button" class="btn btn-sm btn-danger delete-btn-disabled" disabled aria-disabled="true">Delete</button>
                                                    <% } %>
                                                    <form action="MainController" method="post" style="display:inline;">
                                                        <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                                        <button type="submit" class="btn btn-sm btn-dark" name="action" value="Edit_Product_Page">Edit</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% 
                                                    }
                                                } else {
                                            %>
                                            <tr>
                                                <td colspan="8" class="text-center">No products found</td>
                                            </tr>
                                            <% 
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                    <hr>
                                </div>
                                <div class="card-footer"></div>
                            </div>
                        </div>
                    </div>
                    <!--Row-->
                    <!-- Modal Logout -->
                    <%@include file="logoutModal.jsp" %>
                    <%@include file="confirmDeleteModal.jsp" %>
                    <% if (request.getAttribute("SUCCESS_MESSAGE") != null) { %>
                    <%@include file="successModal.jsp" %>
                    <% } %>
                    <!-- Scroll to top -->
                    <a class="scroll-to-top rounded" href="#page-top">
                        <i class="fas fa-angle-up"></i>
                    </a>
                    <!-- Include necessary scripts -->
                    <script>
                        let ascending = true;

                        function sortTable() {
                            const tableBody = document.getElementById('tableBody');
                            const rows = Array.from(tableBody.querySelectorAll('tr'));

                            rows.sort((a, b) => {
                                const cellA = a.querySelectorAll('td')[1].textContent.trim();
                                const cellB = b.querySelectorAll('td')[1].textContent.trim();

                                return ascending ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
                            });

                            rows.forEach(row => tableBody.appendChild(row));

                            ascending = !ascending;
                            document.getElementById('sortIconProduct').textContent = ascending ? '▲' : '▼';
                        }

                        function sortBrandTable() {
                            const tableBody = document.getElementById('tableBody');
                            const rows = Array.from(tableBody.querySelectorAll('tr'));

                            rows.sort((a, b) => {
                                const cellA = a.querySelectorAll('td')[2].textContent.trim();
                                const cellB = b.querySelectorAll('td')[2].textContent.trim();

                                return ascending ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
                            });

                            rows.forEach(row => tableBody.appendChild(row));

                            ascending = !ascending;
                            document.getElementById('sortIconBrand').textContent = ascending ? '▲' : '▼';
                        }

                        document.getElementById('brandSelect').addEventListener('change', function () {
                            const name = this.value;
                            const tableBody = document.getElementById('tableBody');
                            const rows = Array.from(tableBody.rows);

                            rows.forEach(row => {
                                const rowBrand = row.querySelector('td:nth-child(3)').textContent.trim();
                                row.style.display = name === "Select Brand" ? '' : (rowBrand === name ? '' : 'none');
                            });
                        });

                        document.getElementById('statusSelect').addEventListener('change', function () {
                            const status = this.value;
                            const tableBody = document.getElementById('tableBody');
                            const rows = Array.from(tableBody.rows);

                            rows.forEach(row => {
                                const rowStatus = row.querySelector('td:nth-child(5) .badge').textContent.trim();
                                row.style.display = status === "Select Status" ? '' : (rowStatus === status ? '' : 'none');
                            });
                        });

                        document.querySelectorAll('.btn-danger[data-toggle="modal"]').forEach(btn => {
                            btn.addEventListener('click', function () {
                                const id = this.getAttribute('data-id');
                                const deleteType = this.getAttribute('data-delete-type');
                                document.getElementById('modalID').value = id;
                                document.getElementById('modalDeleteType').value = deleteType;
                                document.getElementById('deleteForm').action = deleteType === 'product' ? 'DeleteProductController' : 'DeleteProductDetailsController';
                            });
                        });
                    </script>
                    <script src="vendor/jquery/jquery.min.js"></script>
                    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
                    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
                    <script src="AD_js/ruang-admin.min.js"></script>
                    <script src="vendor/chart.js/Chart.min.js"></script>
                    <script src="AD_js/demo/chart-area-demo.js"></script>
                    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDzwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
                </div>
            </div>
        </div>
    </body>
</html>
