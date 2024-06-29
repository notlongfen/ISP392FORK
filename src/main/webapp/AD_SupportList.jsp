<%-- 
    Document   : ProductList
    Created on : Jun 2, 2024, 4:31:06 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.isp392.support.SupportDTO" %>
<%@ page import="com.mycompany.isp392.user.UserDTO" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Support List</title>
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

                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-900">All Support </h1>
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
                                                        <option value="Done">Done</option>
                                                        <option value="Not yet">Not yet</option>
                                                    </select>
                                                </div>
                                                <%
                                                String search = request.getParameter("search");
                                                if (search == null) {
                                                    search = "";
                                                    }
                                                %>
                                                <div class="col-md-3">

                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="Search..." name="search" value="<%= search%>">
                                                        <div class="input-group-append">
                                                            <!-- <button class="btn btn-outline-secondary" type="submit" name="action" value="Search Support">Search</button> -->
                                                            <input type="submit" name="action" value="Search Support">
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </form>
                                        <%
                                            List<SupportDTO> supportList = (List<SupportDTO>) request.getAttribute("SUPPORT_LIST");
                                            int count = 1;
                                            if (supportList != null && !supportList.isEmpty()) {
                                        %>
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>#</th>
                                                    <th>
                                                        <button class="btn p-0" onclick="sortTable()">Name <span id="sortIconProduct">▲</span></button>
                                                    </th>
                                                    <th>
                                                        <button class="btn p-0">Email</button>
                                                    </th>
                                                    <th>
                                                        <button class="btn p-0">Contact</button>
                                                    </th>
                                                    <th>
                                                        <button class="btn p-0">Import Date</button>
                                                    </th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <%
                for (SupportDTO support : supportList) {
                    UserDTO user = (UserDTO) request.getAttribute("user_" + support.getSupportID());
                    if (user != null) {
                                                %>

                                                <tr>
                                                    <td><%= count++ %></td>
                                                    <td><%= user.getUserName() %></td> 
                                                    <td><%= user.getEmail() %></td>
                                                    <td><%= user.getPhone() %></td>
                                                    <td><%= support.getRequestDate() %></td>
                                                    <!--<td><%= support.getStatus() %></td>-->
                                                    <td><span class="badge badge-success">Done</span></td>
                                                    <td><a href="MainController?action=ViewSupport&email=<%= user.getEmail() %>&supportID=<%=support.getSupportID()%>" class="btn btn-sm " style="background: green ; color: #FFF">View</a></td>
                                                    <td>
                                                        <% if (support.getStatus() == 1) { %>
                                                        <form action="ViewSupportDetailsController" method="GET">
                                                            <input type="hidden" name="supportID" value="<%= support.getSupportID() %>"/>
                                                            <input type="submit" value="ViewSupport" name="action"/>
                                                        </form>
                                                        <% } else { %>
                                                        <form action="AD_ReplySupport.jsp" method="POST">
                                                            <input type="hidden" name="supportID" value="<%= support.getSupportID() %>"/>
                                                            <input type="submit" value="ReplySupport" name="action"/>
                                                        </form>
                                                        <% } %>
                                                    </td>
                                                </tr>
                                                <%
                    }
                }
                                                %>
                                            </tbody>
                                        </table>
                                        <%
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
                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận Xóa</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Bạn có chắc chắn muốn xóa mục này không?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <button type="button" class="btn btn-danger" id="confirmDeleteButton">Xóa</button>
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
                    const rowStatus = row.querySelector('td:nth-child(6) .badge').textContent.trim();
                    if (status === "Select Status") {
                        row.style.display = '';
                    } else if (rowStatus === status) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
            // Thêm sự kiện click vào nút xóa trong mỗi dòng
            document.querySelectorAll('.btn-danger').forEach(btn => {
                btn.addEventListener('click', function () {
                    // Hiển thị modal xác nhận xóa
                    $('#confirmDeleteModal').modal('show');

                    // Lưu trữ thông tin dòng cần xóa vào một thuộc tính data để sử dụng sau này
                    const rowToDelete = this.closest('tr');
                    $('#confirmDeleteButton').data('rowToDelete', rowToDelete);
                });
            });

// Thêm sự kiện click vào nút xác nhận xóa trong modal
            document.getElementById('confirmDeleteButton').addEventListener('click', function () {
                // Ẩn modal xác nhận xóa
                $('#confirmDeleteModal').modal('hide');

                // Lấy thông tin dòng cần xóa từ thuộc tính data đã lưu trữ
                const rowToDelete = $('#confirmDeleteButton').data('rowToDelete');

                // Xóa dòng
                rowToDelete.remove();
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
    </body>
</html>
