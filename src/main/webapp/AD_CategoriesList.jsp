<%-- 
    Document   : CategoriesList
    Created on : Jun 13, 2024, 9:14:34 PM
    Author     : jojo
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.user.*"%>
<%@page import="com.mycompany.isp392.category.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Categories List</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/ruang-admin.min.css" rel="stylesheet">
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
            .description {
                max-width: 200px; /* Adjust this value to control the truncation length */
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
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

                    <!-- Container Fluid-->
                    <%@include file="AD_header.jsp" %>
                    <%
                               if (loginUser == null || 2 != loginUser.getRoleID() || loginUser.getStatus() == 0) {
                                    response.sendRedirect("US_SignIn.jsp");
                                    return;
                                }
                                String search = request.getParameter("searchText");
                                if (search == null) {
                                    search = "";
                                }
                    %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-900"><b>Categories</b></h1>
                        </div>
                        <div class="d-flex justify-content-end mb-4">
                            <a href="AD_CreateCategories.jsp" class="btn btn-danger" style="background: #C43337;">Add new Category</a>
                            <!--<a href="CreateProduct.jsp" class="btn btn-danger" style="margin-left: 20px; background: #C43337; ">View products detail</a>-->
                        </div>
                        <div class="row mb-3">
                            <!-- Invoice Example -->
                            <div class="col-xl-12 mb-4">
                                <div class="card">
                                    <div class="py-3 d-flex flex-row align-items-center justify-content-between">
                                        <!--<h6 class="m-0 font-weight-bold text-primary">Invoice</h6>-->
                                    </div>
                                    <div class="table-responsive">
                                        <form action="MainController" method="POST">
                                            <div class="row mb-4 mx-2 justify-content-between">
                                                <div class="col-md-3">
                                                    <div class="input-group">
                                                        <select id="entriesSelect" class="custom-select">
                                                            <option value="Select Entries">Select Entries</option>
                                                            <option value="5">5</option>
                                                            <option value="10">10</option>
                                                            <option value="15">15</option>
                                                            <option value="20">20</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <select id="statusSelect" class="custom-select">
                                                        <option value="Select Status">Select Status</option>
                                                        <option value="Active">Active</option>
                                                        <option value="Inactive">Inactive</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" name="searchText" value="<%= search%>" placeholder="Search...">
                                                        <div class="input-group-append">
                                                            <button class="btn btn-outline-secondary" type="submit" name="action" value="Search_Category">Search</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <%
                                                List<CategoryDTO> listCategory = (List<CategoryDTO>) request.getAttribute("CATEGORY_LIST");
                                                if (listCategory != null) {
                                                    if (listCategory.size() > 0){
                                        %>    
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>
                                                        <button class="btn p-0" onclick="sortTable()">Name <span id="sortIconProduct">▲</span></button>
                                                    </th>
                                                    <th>
                                                        <button class="btn p-0">Description</button>
                                                    </th>
                                                    <th>
                                                        Image
                                                    </th>
                                                    <th>
                                                        Status
                                                    </th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <%
                                                    for (CategoryDTO category : listCategory) {
                                                %>
                                                <tr>
                                                    <td><%= category.getCategoryID()%></td>
                                                    <td>
                                                        <form action="MainController" method="POST" style="display:inline;">
                                                            <input type="hidden" name="action" value="Search_Children_Category">
                                                            <input type="hidden" name="parentID" value="<%= category.getCategoryID()%>"/>
                                                            <a href="javascript:void(0)" onclick="this.parentNode.submit();"><%= category.getCategoryName() %></a>
                                                        </form>
                                                    </td>
                                                    <td class="description"><%= category.getDescription()%></td>
                                                    <td><img src="<%= category.getImage() %>" alt="Category Image" style="max-width: 100px; max-height: 100px;"></td>
                                                    <td>
                                                        <span class="badge <%= (category.getStatus() == 1) ? "badge-success" : "badge-danger" %>">
                                                            <%= (category.getStatus() == 1) ? "Active" : "Inactive" %>
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <%
                                                            if(category.getStatus() == 0){
                                                        %>
                                                        <a href="#" class="btn btn-sm btn-danger disabled" aria-disabled="true">Delete</a>
                                                        <%
                                                            } else {
                                                        %>
                                                        <a href="#" class="btn btn-sm btn-danger" onclick="showConfirmDeleteModal(<%= category.getCategoryID() %>)">Delete</a>
                                                        <%
                                                            }
                                                        %>
                                                        <form action="MainController" method="POST" style="display:inline;">
                                                            <input type="hidden" name="categoryID" value="<%= category.getCategoryID()%>"/>
                                                            <input type="hidden" name="action" value="GetCategoryInfo"/>
                                                            <button type="submit" class="btn btn-sm btn-dark">Edit</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                        <%
                                                }
                                            }
                                        %>
                                        <hr>
                                        <!-- Pagination -->
                                       
                                        <!-- End Pagination -->
                                    </div>
                                    <div class="card-footer"></div>
                                </div>
                            </div>
                        </div>
                        <!--Row-->

                        <!-- Modal Logout -->
                        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelLogout" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabelLogout">Ohh No!</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure you want to logout?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                        <a href="login.html" class="btn btn-primary">Logout</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!---Mode up delete item in voice -->
                        <!-- Modal Xác nhận Xóa -->
                        <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to delete this category?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-danger" id="confirmDeleteButton">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    <!---Container Fluid-->
                </div>
                <!-- Footer -->
                <!-- Footer -->
            </div>
        </div>

        <!-- Success Modal -->
        <% if (request.getAttribute("SUCCESS_MESSAGE") != null) { %>
        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="successModalLabel">Success</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <span id="successMessage"></span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>

        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <script>
            function sortBrandTable() {
                const tableBody = document.getElementById('tableBody');
                const rows = Array.from(tableBody.querySelectorAll('tr'));

                rows.sort((a, b) => {
                    const cellA = a.querySelectorAll('td')[2].textContent.trim();
                    const cellB = b.querySelectorAll('td')[2].textContent.trim();

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

            document.getElementById('entriesSelect').addEventListener('change', function () {
                const numEntries = parseInt(this.value);
                const tableBody = document.getElementById('tableBody');
                const rows = Array.from(tableBody.rows);

                rows.forEach((row, index) => {
                    if (isNaN(numEntries) || this.value === "Select Entries") {
                        row.style.display = '';
                    } else if (index < numEntries) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });


//Select theo status
            document.getElementById('statusSelect').addEventListener('change', function () {
                const status = this.value;
                const tableBody = document.getElementById('tableBody');
                const rows = Array.from(tableBody.rows);

                rows.forEach(row => {
                    const rowStatus = row.querySelector('td:nth-child(4) .badge').textContent.trim();
                    if (status === "Select Status") {
                        row.style.display = '';
                    } else if (rowStatus === status) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });


            function showConfirmDeleteModal(categoryID) {
                // Store the user ID in a global variable or data attribute
                document.getElementById('confirmDeleteButton').setAttribute('data-category-id', categoryID);
                // Show the modal
                var confirmDeleteModal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
                confirmDeleteModal.show();
            }

            document.getElementById('confirmDeleteButton').addEventListener('click', function () {
                var categoryID = this.getAttribute('data-category-id');
                var url = "MainController?action=Delete_Category&categoryID=" + categoryID;
                window.location.href = url;
            });

            // Display success modal if message exists
            $(document).ready(function () {
                const successMessage = '<%= request.getAttribute("SUCCESS_MESSAGE") %>';
                if (successMessage) {
                    document.getElementById('successMessage').innerText = successMessage;
                    $('#successModal').modal('show');
                }
            });
        </script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="js/ruang-admin.min.js"></script>
        <script src="vendor/chart.js/Chart.min.js"></script>
        <script src="js/demo/chart-area-demo.js"></script>  
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
