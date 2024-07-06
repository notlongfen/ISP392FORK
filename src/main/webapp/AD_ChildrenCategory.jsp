<%-- 
    Document   : ChildrenCategory
    Created on : Jun 17, 2024, 2:54:52 PM
    Author     : jojo
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.user.*"%>
<%@page import="com.mycompany.isp392.category.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Children Categories</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/ruang-admin.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                                int parentID = (int) request.getAttribute("PARENT_CATEGORY_ID");
                                int parentStatus = (int) request.getAttribute("PARENT_CATEGORY_STATUS");
                                session.setAttribute("PARENT_CATEGORY_ID", parentID);
                    %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-900">Children Categories</h1>
                        </div>
                        <div class="d-flex justify-content-end mb-4">
                            <%
                                if(parentStatus == 1){
                            %>
                            <a href="AD_CreateChildrenCategory.jsp" class="btn btn-danger" style="background: #C43337;">Add new Children Category</a>
                            <%
                                } else {
                            %>
                            <a href="#" class="btn btn-danger disabled" aria-disabled="true" style="background: #C43337;">Add new Children Category</a>
                            <%
                               }
                            %>
                        </div>
                        <div class="row mb-3">
                            <!-- Invoice Example -->
                            <div class="col-xl-12 mb-4">
                                <div class="card">
                                    <div class=" py-3 d-flex flex-row align-items-center justify-content-between">
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
                                                        <input type="hidden" name="parentID" value="<%= parentID%>" readonly=""/>
                                                        <input type="text" name="searchText" value="<%= search%>"class="form-control" placeholder="Search...">
                                                        <div class="input-group-append">
                                                            <button class="btn btn-outline-secondary" type="submit" name="action" value="Search_Children_Category">Search</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <%
                                                List<ChildrenCategoryDTO> listChildCategory = (List<ChildrenCategoryDTO>) request.getAttribute("CHILD_CATEGORY_LIST");
                                                if (listChildCategory != null) {
                                                    if (listChildCategory.size() > 0){
                                        %>   
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>
                                                        <button class="btn p-0"  onclick="sortTable()">Name<span id="sortIconProduct">▲</span></button>
                                                    </th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>

                                            </thead>
                                            <tbody id="tableBody">
                                                <%
                                                    for (ChildrenCategoryDTO cdCategory : listChildCategory) {
                                                %>
                                                <tr>
                                                    <td><%= cdCategory.getCdCategoryID()%></td>
                                                    <td><%= cdCategory.getCategoryName()%></td>
                                                    <td>
                                                        <span class="badge <%= (cdCategory.getStatus() == 1) ? "badge-success" : "badge-danger" %>">
                                                            <%= (cdCategory.getStatus() == 1) ? "Active" : "Inactive" %>
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <%
                                                            if(cdCategory.getStatus() == 0){
                                                        %>
                                                        <a href="#" class="btn btn-sm btn-danger disabled" aria-disabled="true">Delete</a>
                                                        <%
                                                            } else {
                                                        %>
                                                        <a href="#" class="btn btn-sm btn-danger" onclick="showConfirmDeleteModal(<%= cdCategory.getCdCategoryID() %>)">Delete</a>
                                                        <%
                                                            }
                                                             if(parentStatus == 1){
                                                        %>
                                                        <form action="MainController" method="POST" style="display:inline;">
                                                            <input type="hidden" name="cdCategoryID" value="<%= cdCategory.getCdCategoryID()%>"/>
                                                            <input type="hidden" name="action" value="GetChildrenCategoryInfo"/>
                                                            <button type="submit" class="btn btn-sm btn-dark">Edit</button>
                                                        </form>
                                                        <%
                                                            } else {
                                                        %>
                                                        <button type="" class="btn btn-sm btn-dark disabled" aria-disabled="true" style="pointer-events: none;">Edit</button>
                                                        <%
                                                            }
                                                        %>
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
                                                <li class="page-item" >
                                                    <a class="page-link" href="#" aria-label="Next" style="color: #C43337">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
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
                                        <!--<button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>-->
                                    </div>
                                    <div class="modal-body">
                                        Are you sure you want to delete this child category?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-danger" id="confirmDeleteButton">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!---Container Fluid-->
                </div>
            </div>
        </div>

        <!-- Success Modal -->
        <% if (request.getAttribute("SUCCESS_MESSAGE") != null) { %>
        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="successModalLabel">Success</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
                    const rowStatus = row.querySelector('td:nth-child(3) .badge').textContent.trim();
                    if (status === "Select Status") {
                        row.style.display = '';
                    } else if (rowStatus === status) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
//            document.querySelectorAll('.btn-danger').forEach(btn => {
//                btn.addEventListener('click', function () {
//                    // Hiển thị modal xác nhận xóa
//                    $('#confirmDeleteModal').modal('show');
//
//                    // Lưu trữ thông tin dòng cần xóa vào một thuộc tính data để sử dụng sau này
//                    const rowToDelete = this.closest('tr');
//                    $('#confirmDeleteButton').data('rowToDelete', rowToDelete);
//                });
//            });
//
//            document.getElementById('confirmDeleteButton').addEventListener('click', function () {
//                // Ẩn modal xác nhận xóa
//                $('#confirmDeleteModal').modal('hide');
//
//                // Lấy thông tin dòng cần xóa từ thuộc tính data đã lưu trữ
//                const rowToDelete = $('#confirmDeleteButton').data('rowToDelete');
//
//                // Xóa dòng
//                rowToDelete.remove();
//            });
            function showConfirmDeleteModal(cdCategoryID) {
                // Store the user ID in a global variable or data attribute
                document.getElementById('confirmDeleteButton').setAttribute('data-cdcategory-id', cdCategoryID);
                // Show the modal
                var confirmDeleteModal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
                confirmDeleteModal.show();
            }

            document.getElementById('confirmDeleteButton').addEventListener('click', function () {
                var cdCategoryID = this.getAttribute('data-cdcategory-id');
                var url = "MainController?action=Delete_ChildrenCategory&cdCategoryID=" + cdCategoryID;
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
