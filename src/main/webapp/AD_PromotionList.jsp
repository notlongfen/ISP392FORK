<%-- 
    Document   : ProductList
    Created on : Jun 2, 2024, 4:31:06 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Promotion List</title>
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

            .container {
                display: flex;
                align-items: center;
                justify-content: center;
                margin-top: 10px;      
            }
            .container input[type="date"] {
                padding: 10px;
                margin: 30px 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                width: 400px;
                
            }
            .container button:hover {
                background-color: #0056b3;
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
                            <h1 class="h3 mb-0 text-gray-900"><b>Promotions</b></h1>
                        </div>
                        <div class="d-flex justify-content-end mb-4">
                            <a href="AD_CreatePromotion.jsp" class="btn btn-danger" style="background: #C43337;">Create Promotion</a>
                        </div>
                        <div class="row mb-3">
                            <!-- Invoice Example -->
                            <div class="col-xl-12 mb-4">
                                <div class="card">

                                    <div class="table-responsive">
                                        <div class="container">
                                            <input type="date" id="ngayBatDau" placeholder="Start Date">
                                            <input type="date" id="ngayKetThuc" placeholder="End Date">
                                            <button class="btn btn-outline-secondary" onclick="locNgay()" style="padding: 10px 20px;">Search</button>
                                        </div>

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>
                                                        <button class="btn p-0" onclick="sortTable()">Code Name <span id="sortIconProduct">▲</span></button>
                                                    </th>
                                                    <th>
                                                        <button class="btn p-0" onclick="sortBrandTable()">Percentage <span id="sortIconProduct">▲</span></button>
                                                    </th>

                                                    <th>Start Date</th>
                                                    <th>End Date</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <tr>
                                                    <td>1</td>
                                                    <td>Save50</td>
                                                    <td>50%</td>
                                                    <td>2024-06-01</td>
                                                    <td>2024-07-01</td>
                                                    <td>
                                                        <a href="#" class="btn btn-sm btn-danger">Delete</a>
                                                        <a href="AD_EditPromotion.jsp" class="btn btn-sm btn-dark">Edit</a>
                                                    </td>
                                                </tr>                                        

                                                <tr>
                                                    <td>2</td>
                                                    <td>Save50</td>
                                                    <td>50%</td>
                                                    <td>2024-06-01</td>
                                                    <td>2024-07-01</td>
                                                    <td>
                                                        <a href="#" class="btn btn-sm btn-danger">Delete</a>
                                                        <a href="AD_EditPromotion.jsp" class="btn btn-sm btn-dark">Edit</a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>3</td>
                                                    <td>Save50</td>
                                                    <td>50%</td>
                                                    <td>2024-06-01</td>
                                                    <td>2024-07-01</td>
                                                    <td>
                                                        <a href="#" class="btn btn-sm btn-danger">Delete</a>
                                                        <a href="AD_EditPromotion.jsp" class="btn btn-sm btn-dark">Edit</a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>4</td>
                                                    <td>Save50</td>
                                                    <td>50%</td>
                                                    <td>2024-01-22</td>
                                                    <td>2024-04-11</td>
                                                    <td>
                                                        <a href="#" class="btn btn-sm btn-danger">Delete</a>
                                                        <a href="AD_EditPromotion.jsp" class="btn btn-sm btn-dark">Edit</a>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>5</td>
                                                    <td>Save50</td>
                                                    <td>50%</td>
                                                    <td>2024-06-01</td>
                                                    <td>2024-07-01</td>
                                                    <td>
                                                        <a href="#" class="btn btn-sm btn-danger">Delete</a>
                                                        <a href="AD_EditPromotion.jsp" class="btn btn-sm btn-dark">Edit</a>
                                                    </td>
                                                </tr>

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
            //Sort Date
            function locNgay() {
                const ngayBatDau = document.getElementById('ngayBatDau').value;
                const ngayKetThuc = document.getElementById('ngayKetThuc').value;
                const tableBody = document.getElementById('tableBody');
                const rows = tableBody.getElementsByTagName('tr');

                if (ngayBatDau && ngayKetThuc) {
                    const startDate = new Date(ngayBatDau);
                    const endDate = new Date(ngayKetThuc);

                    for (let i = 0; i < rows.length; i++) {
                        const cells = rows[i].getElementsByTagName('td');
                        const ngayBatDauRow = new Date(cells[3].textContent.trim());
                        const ngayKetThucRow = new Date(cells[4].textContent.trim());

                        if (ngayBatDauRow >= startDate && ngayKetThucRow <= endDate) {
                            rows[i].style.display = '';
                        } else {
                            rows[i].style.display = 'none';
                        }
                    }
                } else {
                    for (let i = 0; i < rows.length; i++) {
                        rows[i].style.display = '';
                    }
                }
            }

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
