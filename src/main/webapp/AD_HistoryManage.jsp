
<%-- 
    Document   : ProductList
    Created on : Jun 2, 2024, 4:31:06 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.mycompany.isp392.brand.ManageBrandDTO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>History List</title>
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
                            <h1 class="h3 mb-0 text-gray-900"><b>History of Brand</b></h1>
                        </div>
                        <div class="row mb-3">
                            <!-- Invoice Example -->
                            <div class="col-xl-12 mb-4">
                                <div class="card">

                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>BrandID</th>
                                                    <th>EmployeeID</th>
                                                    <th>FieldOld</th>
                                                    <th>FieldNew</th>
                                                    <th>Action</th>
                                                    <th>Change Date</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">

                                                <tr>
                                                    <%
 List<ManageBrandDTO> manage = (List<ManageBrandDTO>) session.getAttribute("manageBrand");
 if (manage != null && manage.size() > 0) {
     for (ManageBrandDTO list : manage) {
         String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
         String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");
        
         String oldName = "", oldStatus = "", oldImage = "";
         String newName = "", newStatus = "", newImage = "";

         for (String field : oldFields) {
             if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                 oldImage = field;
             } else if (field.matches("\\d+")) {
                 oldStatus = field;
             } else {
                 oldName = field;
             }
         }

         for (String field : newFields) {
             if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                 newImage = field;
             } else if (field.matches("\\d+")) {
                 newStatus = field;
             } else {
                 newName = field;
             }
         }
                                                    %>
                                                <tr>
                                                    <td><%= list.getBrandID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldName.isEmpty()) { %>
                                                        Name: <%= oldName %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                        <% if (!oldImage.isEmpty()) { %>
                                                        <img src="<%= oldImage %>" alt="Old Image" width="100" height="100"><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newName.isEmpty()) { %>
                                                        Name: <%= newName %><br>
                                                        <% } %>
                                                        <% if (!newStatus.isEmpty()) { %>
                                                        Status: <%= newStatus %><br>
                                                        <% } %>
                                                        <% if (!newImage.isEmpty()) { %>
                                                        <img src="<%= newImage %>" alt="New Image" width="100" height="100"><br>
                                                        <% } %>
                                                    </td>
                                                    <td><%= list.getAction() %></td>
                                                    <td><%= list.getChangeDate() %></td>
                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr>
                                                    <td colspan="8" class="text-center">No Update !!!</td>
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
                                                <li class="page-item" >
                                                    <a class="page-link" href="#" aria-label="Next" style="color: #C43337">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                        <!-- End Pagination -->
                                    </div>
                                </div>

                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Employee</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>UserID</th>
                                                    <th>EmployeeID</th>
                                                    <th>FieldOld</th>
                                                    <th>FieldNew</th>
                                                    <th>Action</th>
                                                    <th>Change Date</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <tr>
                                                    <%
 List<ManageUserDTO> employee = (List<ManageUserDTO>) session.getAttribute("manageEmployee");
 if (employee != null && employee.size() > 0) {
     for (ManageUserDTO list : employee) {
         String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
         String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");
        

            String oldRole = "";
            String oldStatus = "";
            String newRole = "";
            String newStatus = "";

            // Assign role and status for old fields
            if (oldFields.length > 0) {
                if (oldFields.length == 1) {
                    oldStatus = oldFields[0];
                } else {
                    oldRole = oldFields[0];
                    oldStatus = oldFields[1];
                }
            }

            // Assign role and status for new fields
            if (newFields.length > 0) {
                if (newFields.length == 1) {
                    newStatus = newFields[0];
                } else {
                    newRole = newFields[0];
                    newStatus = newFields[1];
                }
            }
            
               // Mapping role IDs to role names
            String oldRoleName = "";
            switch (oldRole) {
                case "1": oldRoleName = "System Manager"; break;
                case "2": oldRoleName = "Shop Manager"; break;
                case "3": oldRoleName = "Staff"; break;
                
            }

            String newRoleName = "";
            switch (newRole) {
                case "1": newRoleName = "System Manager"; break;
                case "2": newRoleName = "Shop Manager"; break;
                case "3": newRoleName = "Staff"; break;
                
            }
                                                    %>
                                                <tr>
                                                    <td><%= list.getUserID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldRole.isEmpty()) { %>
                                                        Role: <%= oldRoleName %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newRole.isEmpty()) { %>
                                                        Role: <%= newRoleName %><br>
                                                        <% } %>
                                                        <% if (!newStatus.isEmpty()) { %>
                                                        Status: <%= newStatus %><br>
                                                        <% } %>

                                                    </td>
                                                    <td><%= list.getAction() %></td>
                                                    <td><%= list.getChangeDate() %></td>
                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr>
                                                    <td colspan="8" class="text-center">No Update !!!</td>
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
                                                <li class="page-item" >
                                                    <a class="page-link" href="#" aria-label="Next" style="color: #C43337">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                        <!-- End Pagination -->
                                    </div>
                                </div>

                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Customer</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>UserID</th>
                                                    <th>EmployeeID</th>
                                                    <th>FieldOld</th>
                                                    <th>FieldNew</th>
                                                    <th>Action</th>
                                                    <th>Change Date</th>
                                                </tr>
                                            </thead>
                                            <tbody id="tableBody">
                                                <tr>
                                                    <%
 List<ManageUserDTO> customer = (List<ManageUserDTO>) session.getAttribute("manageCustomer");
 if (customer != null && customer.size() > 0) {
     for (ManageUserDTO list : customer) {
     
            
                                                    %>
                                                <tr>
                                                    <td><%= list.getUserID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        Status: <%= list.getLoadOldField().replace("[", "").replace("]", "")  %>
                                                    </td>
                                                    <td>
                                                        Status: <%= list.getLoadNewField().replace("[", "").replace("]", "")  %>
                                                    </td>
                                                    <td><%= list.getAction() %></td>
                                                    <td><%= list.getChangeDate() %></td>
                                                </tr>
                                                <%
                                                    }
                                                } else {
                                                %>
                                                <tr>
                                                    <td colspan="8" class="text-center">No Update !!!</td>
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
                                                <li class="page-item" >
                                                    <a class="page-link" href="#" aria-label="Next" style="color: #C43337">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </nav>
                                        <!-- End Pagination -->
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
