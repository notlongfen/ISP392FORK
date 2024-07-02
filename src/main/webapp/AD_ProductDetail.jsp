<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Product Detail</title>
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
            .action-buttons .btn {
                margin-left: 5px;
                margin-right: 5px;
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
                    <!---Header --->
                    <%@include file="AD_header.jsp" %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-900">Product Details</h1>
                        </div>
                        <div class="d-flex justify-content-end mb-4">
                            <% 
                                ProductDTO parentProduct = (ProductDTO) request.getAttribute("PARENT_PRODUCT");
                                if (parentProduct != null) {
                            %>
                            <form action="MainController" method="post">
                                <input type="hidden" name="parentProductID" value="<%= parentProduct.getProductID() %>">
                                <input type="hidden" name="parentProductName" value="<%= parentProduct.getProductName() %>">
                                <button type="submit" class="btn btn-danger" name="action" value="Add_Product_Details_Page" style="background: #C43337;">
                                    Add Product Detail
                                </button>
                            </form>
                            <% } %>
                        </div>
                        <div class="row mb-3">
                            <div class="col-xl-12 mb-4">
                                <div class="card">
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <!--<h6 class="m-0 font-weight-bold text-primary">Product Details</h6>-->
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th class="text-center">Product Name</th>
                                                    <th class="text-center">Product Detail ID</th>
                                                    <th class="text-center">Color</th>
                                                    <th class="text-center">Size</th>
                                                    <th class="text-center">Price</th>
                                                    <th class="text-center">Stock Quantity</th>
                                                    <th class="text-center">Images</th>
                                                    <th class="text-center">Import Date</th>
                                                    <th class="text-center">Status</th>
                                                    <th class="text-center">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <% 
                                                List<ProductDetailsDTO> productDetailsList = (List<ProductDetailsDTO>) request.getAttribute("PRODUCT_DETAILS_LIST");
                                                if (productDetailsList != null) {
                                                    for (ProductDetailsDTO details : productDetailsList) {
                                                %>
                                                <tr>
                                                    <td class="text-center"><%= parentProduct.getProductName() %></td>
                                                    <td class="text-center"><%= details.getProductDetailsID() %></td>
                                                    <td class="text-center"><%= details.getColor() %></td>
                                                    <td class="text-center"><%= details.getSize() %></td>
                                                    <td class="text-center"><%= details.getPrice() %>$</td>
                                                    <td class="text-center"><%= details.getStockQuantity() %></td>
                                                    <td class="text-center">
                                                        <% 
                                                        String[] images = details.getImage().split(";"); // Assuming images are stored as a semicolon-separated string
                                                        for (String image : images) {
                                                            if (!image.trim().isEmpty()) { 
                                                        %>
                                                        <img src="<%= image %>" alt="Product Image" style="max-width: 100px; max-height: 100px;">
                                                        <% 
                                                            }
                                                        }
                                                        %>
                                                    </td>
                                                    <td class="text-center px-0"><%= details.getImportDate() %></td>
                                                    <td class="text-center"><span class="badge <%= details.getStatus() == 1 ? "badge-success" : "badge-warning" %>"><%= details.getStatus() == 1 ? "Available" : "Deleted" %></span></td>
                                                    <td class="text-center action-buttons px-0">
                                                        <% if (details.getStatus() == 1) { %>
                                                        <button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#confirmDeleteModal" data-id="<%= details.getProductDetailsID() %>" data-parentId = "<%= parentProduct.getProductID() %>"data-delete-type="productDetail">Delete</button>
                                                        <% } %>
                                                        <form action="MainController" method="post" style="display:inline;">
                                                            <input type="hidden" name="productDetailID" value="<%= details.getProductDetailsID() %>">
                                                            <button type="submit" class="btn btn-sm btn-dark" name="action" value="Edit_Product_Detail_Page">Edit</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% 
                                                    }
                                                } else {
                                                %>
                                                <tr>
                                                    <td colspan="10" class="text-center">No product details found</td>
                                                </tr>
                                                <% 
                                                }
                                                %>
                                            </tbody>
                                        </table>
                                        <hr>
                                        <!-- Pagination -->
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-center mt-3">
                                                <li class="page-item">
                                                    <a class="page-link" href="#" aria-label="Previous" style="color: #C43337">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                                <li class="page-item mx-1"><a class="page-link" href="#" style="color: #C43337">1</a></li>
                                                <li class="page-item mx-1"><a class="page-link" href="#" style="color: #C43337">2</a></li>
                                                <li class="page-item mx-1"><a class="page-link" href="#" style="color: #C43337">3</a></li>
                                                <li class="page-item">
                                                    <a class="page-link" href="#" aria-label="Next" style="color: #C43337">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                        <!-- End Pagination -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Footer -->
                <!-- Footer -->
            </div>
        </div>
        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        
          <!-- Include the Modals -->
                        <%@include file="logoutModal.jsp" %>
                        <%@include file="confirmDeleteModal.jsp" %>
                        <% if (request.getAttribute("SUCCESS_MESSAGE") != null) { %>
                        <%@include file="successModal.jsp" %>
                        <% } %>
        <script>
            // Sorting functionality
            let ascending = true;
            function sortTable() {
                const tableBody = document.getElementById('tableBody');
                const rows = Array.from(tableBody.querySelectorAll('tr'));
                rows.sort((a, b) => {
                    const cellA = a.querySelectorAll('td')[1].textContent.trim();
                    const cellB = b.querySelectorAll('td')[1].textContent.trim();
                    if (ascending) {
                        return cellA.localeCompare(cellB);
                    } else {
                        return cellB.localeCompare(cellA);
                    }
                });
                rows.forEach(row => tableBody.appendChild(row));
                ascending = !ascending;
                document.getElementById('sortIconProduct').textContent = ascending ? '▲' : '▼';
            }
            // Deleting functionality
            document.querySelectorAll('.btn-danger[data-toggle="modal"]').forEach(btn => {
                btn.addEventListener('click', function () {
                    const productDetailID = this.getAttribute('data-productdetailid');
                     const parentProductID = this.getAttribute('data-parentid');
                    document.getElementById('modalProductDetailID').value = productDetailID;
                     document.getElementById('modalParentProductID').value = parentProductID;
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
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDzwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
