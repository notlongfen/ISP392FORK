<%-- 
    Document   : ProductList
    Created on : Jun 2, 2024, 4:31:06 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.isp392.order.*" %>
<%@ page import="com.mycompany.isp392.user.*" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>View Order</title>
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
                    <%
                                        if (loginUser == null || 2!=loginUser.getRoleID() ) {
                                            response.sendRedirect("US_SignIn.jsp");
                                            return;
                                        }
                    %>
                    <div class="container-fluid" id="container-wrapper">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-900"><b>Orders</b></h1>
                        </div>

                        <div class="row mb-" style="margin-left: 50px; margin-top: 100px;">
                            <!-- Invoice Example -->
                            <div class="mt-5">
                                <div class="card">
                                    <div class=" py-3 d-flex flex-row align-items-center justify-content-between">
                                        <!--<h6 class="m-0 font-weight-bold text-primary">Invoice</h6>-->
                                    </div>
                                    <form action="MainController">
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th class="text-center">Order ID</th>
                                                    <th class="text-center">Consignee</th>
                                                    <th class="text-center">Delivery address</th>
                                                    <th class="text-center">Ward</th>
                                                    <th class="text-center">District</th>
                                                    <th class="text-left">City</th>
                                                    <th class="text-center">Phone</th>
                                                    <th class="text-center">Note</th>
                                                    <th class="text-center">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <!-- GetOrderInforController -->
                                                <%
                                                    OrderDTO order = (OrderDTO) request.getAttribute("ORDER_INFOR");
                                                    if (order != null) {
                                                %>
                                                <tr>
                                                    <td class="text-center"><%= order.getOrderID() %></td>
                                                    <td class="text-center"><%= order.getUserName() %></td>
                                                    <td class="text-center"><%= order.getAddress() %></td>
                                                    <td class="text-center"><%= order.getWard() %></td>
                                                    <td class="text-center"><%= order.getDistrict() %></td>
                                                    <td class="text-center"><%= order.getCity() %></td>
                                                    <td class="text-center"><%= order.getPhone() %></td>
                                                    <td class="text-center"><%= order.getNote() %></td>
                                                    <td class="text-center">
                                                        <input type="hidden" name="orderID" value="<%= order.getOrderID() %>">
                                                        <select name="status" class="form-control">
                                                            <option value="0" <%= order.getStatus() == 0 ? "selected" : "" %> >Canceled</option>
                                                            <option value="1" <%= order.getStatus() == 1 ? "selected" : "" %>>In processing</option>
                                                            <option value="2" <%= order.getStatus() == 2 ? "selected" : "" %>>Delivering</option>
                                                            <option value="3" <%= order.getStatus() == 3 ? "selected" : "" %>>Completed</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                            <input type="submit" name="action" value="Update_Order_Status">
                                        </table>
                                    </form>

                                    <table class="table align-items-center table-flush mt-4">
                                        <thead class="thead-light">
                                            <tr>
                                                <th class="text-center">Product Detail ID</th>
                                                <th class="text-center">Quantity</th>
                                                <th class="text-center">Unit Price</th>
                                            </tr>
                                        </thead>
                                        <tbody id="tableBodyDetails">
                                            <%
                                                List<OrderDetailsDTO> orderDetailsList = (List<OrderDetailsDTO>) request.getAttribute("ORDER_DETAIL_LIST");
                                                if (orderDetailsList != null) {
                                                    for (OrderDetailsDTO orderDetails : orderDetailsList) {
                                            %>
                                            <tr>
                                                <td class="text-center"><%= orderDetails.getProductDetailsID() %></td>
                                                <td class="text-center"><%= orderDetails.getQuantity() %></td>
                                                <td class="text-center"><%= orderDetails.getUnitPrice() %></td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                            <%--                                            <a href="MainController?action=Update_Order_Status&orderID=<%= order.getOrderID()%>&status=<%= order.getStatus() %>" class="btn btn-primary btn-danger" style="">Save</a> --%>

                                <!--<div class="card-footer"></div>-->
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
                                    Are you sure you want to delete this user?
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

    <!-- Scroll to top -->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <script>
        let ascending = true;

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
