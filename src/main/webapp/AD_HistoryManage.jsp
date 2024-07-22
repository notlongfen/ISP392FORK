
<%-- 
    Document   : ProductList
    Created on : Jun 2, 2024, 4:31:06 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.mycompany.isp392.brand.ManageBrandDTO"%>
<%@page import="com.mycompany.isp392.product.ManageProductDTO"%>
<%@page import="com.mycompany.isp392.promotion.ManagePromotionDTO"%>
<%@page import="com.mycompany.isp392.category.ManageCategoryDTO"%>
<%@page import="com.mycompany.isp392.order.ManageOrderDTO"%>
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
    // Fetch the list of ManageBrandDTO objects from the session
    List<ManageBrandDTO> manage = (List<ManageBrandDTO>) session.getAttribute("manageBrand");
    if (manage != null && !manage.isEmpty()) {
        // Iterate over each ManageBrandDTO object in the list
        for (ManageBrandDTO list : manage) {
            // Retrieve and process the old fields
            String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
            String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

            String oldName = "", oldStatus = "", oldImage = "", oldBrandID = "";
            String newName = "", newStatus = "", newImage = "", newBrandID = "";

            // Determine old fields (name, status, image, brandID)
            for (String field : oldFields) {
                if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                    oldImage = field;
                } else if (field.matches("Status:.*")) {
                    oldStatus = field.replace("Status:", "").trim();
                } else if (field.matches("BrandID:.*")) {
                    oldBrandID = field.replace("BrandID:", "").trim();
                } else if(field.matches("Name:.*")) {
                    oldName = field.replace("Name:", "").trim();
                }
            }

            // Determine new fields (name, status, image, brandID)
            for (String field : newFields) {
                if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                    newImage = field;
                } else if (field.matches("Status:.*")) {
                    newStatus = field.replace("Status:", "").trim();
                } else if (field.matches("BrandID:.*")) {
                    newBrandID = field.replace("BrandID:", "").trim();
                } else if(field.matches("Name:.*")){
                    newName = field.replace("Name:", "").trim();
                }
            }
                                                    %>
                                                <tr>
                                                    <td><%= list.getBrandID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldBrandID.isEmpty()) { %>
                                                        BrandID: <%= oldBrandID %><br>
                                                        <% } %>
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
                                                        <% if (!newBrandID.isEmpty()) { %>
                                                        BrandID: <%= newBrandID %><br>
                                                        <% } %>
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

                                                                String oldName = "", oldStatus = "", oldEmail = "", oldPassword = "", oldPhone = "", oldRoleID = "";
                                                                String newName = "", newStatus = "", newEmail = "", newPassword = "", newPhone = "", newRoleID = "";

                                                                // Determine old fields (name, status, email, password, phone, roleID)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Email:.*")) {
                                                                        oldEmail = field.replace("Email:", "").trim();
                                                                    } else if (field.matches("Name:.*")) {
                                                                        oldName = field.replace("Name:", "").trim();
                                                                    } else if (field.matches("Password:.*")) {
                                                                        oldPassword = field.replace("Password:", "").trim();
                                                                    } else if (field.matches("Phone:.*")) {
                                                                        oldPhone = field.replace("Phone:", "").trim();
                                                                    } else if (field.matches("RoleID:.*")) {
                                                                        oldRoleID = field.replace("RoleID:", "").trim();
                                                                    }
                                                                }

                                                                // Determine new fields (name, status, email, password, phone, roleID)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Email:.*")) {
                                                                        newEmail = field.replace("Email:", "").trim();
                                                                    } else if (field.matches("Name:.*")) {
                                                                        newName = field.replace("Name:", "").trim();
                                                                    } else if (field.matches("Password:.*")) {
                                                                        newPassword = field.replace("Password:", "").trim();
                                                                    } else if (field.matches("Phone:.*")) {
                                                                        newPhone = field.replace("Phone:", "").trim();
                                                                    } else if (field.matches("RoleID:.*")) {
                                                                        newRoleID = field.replace("RoleID:", "").trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getUserID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldName.isEmpty()) { %>
                                                        Name: <%= oldName %><br>
                                                        <% } %>
                                                        <% if (!oldEmail.isEmpty()) { %>
                                                        Email: <%= oldEmail %><br>
                                                        <% } %>
                                                        <% if (!oldPassword.isEmpty()) { %>
                                                        Password: <%= oldPassword %><br>
                                                        <% } %>
                                                        <% if (!oldPhone.isEmpty()) { %>
                                                        Phone: <%= oldPhone %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                        <% if (!oldRoleID.isEmpty()) { %>
                                                        RoleID: <%= oldRoleID %><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newName.isEmpty()) { %>
                                                        Name: <%= newName %><br>
                                                        <% } %>
                                                        <% if (!newEmail.isEmpty()) { %>
                                                        Email: <%= newEmail %><br>
                                                        <% } %>
                                                        <% if (!newPassword.isEmpty()) { %>
                                                        Password: <%= newPassword %><br>
                                                        <% } %>
                                                        <% if (!newPhone.isEmpty()) { %>
                                                        Phone: <%= newPhone %><br>
                                                        <% } %>
                                                        <% if (!newStatus.isEmpty()) { %>
                                                        Status: <%= newStatus %><br>
                                                        <% } %>
                                                        <% if (!newRoleID.isEmpty()) { %>
                                                        RoleID: <%= newRoleID %><br>
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
                                                        Status: <%= list.getLoadOldField().replace("[Status:", "").replace("]", "")  %>
                                                    </td>
                                                    <td>
                                                        Status: <%= list.getLoadNewField().replace("[Status:", "").replace("]", "")  %>
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
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Product</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">
                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>ProductID</th>
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
                                                        List<ManageProductDTO> product = (List<ManageProductDTO>) session.getAttribute("manageProduct");
                                                        if (product != null && product.size() > 0) {
                                                            for (ManageProductDTO list : product) {
                                                                String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
                                                                String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

                                                                String oldName = "", oldStatus = "", oldDescription = "", oldNumber= "", oldBrandID = "", oldCategories = "";
                                                                String newName = "", newStatus = "", newDescription = "", newNumber= "", newBrandID = "", newCategories = "";

                                                                // Determine old fields (name, status, description, number of purchasing, brandID, categories)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Description:.*")) {
                                                                        oldDescription = field.replace("Description:", "").trim();
                                                                    } else if (field.matches("Name:.*")) {
                                                                        oldName = field.replace("Name:", "").trim();
                                                                    } else if (field.matches("Number of purchasing:.*")) {
                                                                        oldNumber = field.replace("Number of purchasing:", "").trim();
                                                                    } else if (field.matches("BrandID:.*")) {
                                                                        oldBrandID = field.replace("BrandID:", "").trim();
                                                                    } else if (field.matches("Categories:.*")) {
                                                                        oldCategories = field.replace("Categories:", "").trim();
                                                                    }
                                                                }

                                                                // Determine new fields (name, status, description, number of purchasing, brandID, categories)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Description:.*")) {
                                                                        newDescription = field.replace("Description:", "").trim();
                                                                    } else if (field.matches("Name:.*")) {
                                                                        newName = field.replace("Name:", "").trim();
                                                                    } else if (field.matches("Number of purchasing:.*")) {
                                                                        newNumber = field.replace("Number of purchasing:", "").trim();
                                                                    } else if (field.matches("BrandID:.*")) {
                                                                        newBrandID = field.replace("BrandID:", "").trim();
                                                                    } else if (field.matches("Categories:.*")) {
                                                                        newCategories = field.replace("Categories:", "").trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getProductID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldName.isEmpty()) { %>
                                                        Name: <%= oldName %><br>
                                                        <% } %>
                                                        <% if (!oldDescription.isEmpty()) { %>
                                                        Description: <%= oldDescription %><br>
                                                        <% } %>
                                                        <% if (!oldNumber.isEmpty()) { %>
                                                        Number of purchasing: <%= oldNumber %><br>
                                                        <% } %>
                                                        <% if (!oldBrandID.isEmpty()) { %>
                                                        BrandID: <%= oldBrandID %><br>
                                                        <% } %>
                                                        <% if (!oldCategories.isEmpty()) { %>
                                                        Categories: <%= oldCategories %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newName.isEmpty()) { %>
                                                        Name: <%= newName %><br>
                                                        <% } %>
                                                        <% if (!newDescription.isEmpty()) { %>
                                                        Description: <%= newDescription %><br>
                                                        <% } %>
                                                        <% if (!newNumber.isEmpty()) { %>
                                                        Number of purchasing: <%= newNumber %><br>
                                                        <% } %>
                                                        <% if (!newBrandID.isEmpty()) { %>
                                                        BrandID: <%= newBrandID %><br>
                                                        <% } %>
                                                        <% if (!newCategories.isEmpty()) { %>
                                                        Categories: <%= newCategories %><br>
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
                                </div>


                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Product Detail</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>ProductDetailID</th>
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
                                                        List<ManageProductDTO> productDetail = (List<ManageProductDTO>) session.getAttribute("manageProductDetails");
                                                        if (productDetail != null && productDetail.size() > 0) {
                                                            for (ManageProductDTO list : productDetail) {
                                                                String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
                                                                String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

                                                                String oldColor = "", oldStatus = "", oldSize = "", oldStock = "", oldPrice = "", oldDate = "", oldImage = "";
                                                                String newColor = "", newStatus = "", newSize = "", newStock = "", newPrice = "", newDate = "", newImage = "";

                                                                // Determine old fields (color, status, size, stock quantity, price, import date, image)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Color:.*")) {
                                                                        oldColor = field.replace("Color:", "").trim();
                                                                    } else if (field.matches("Size:.*")) {
                                                                        oldSize = field.replace("Size:", "").trim();
                                                                    } else if (field.matches("Stock Quantity:.*")) {
                                                                        oldStock = field.replace("Stock Quantity:", "").trim();
                                                                    } else if (field.matches("Price:.*")) {
                                                                        oldPrice = field.replace("Price:", "").trim();
                                                                    } else if (field.matches("Import Date:.*")) {
                                                                        oldDate = field.replace("Import Date:", "").trim();
                                                                    } else if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                                                                        oldImage = field.trim();
                                                                    }
                                                                }

                                                                // Determine new fields (color, status, size, stock quantity, price, import date, image)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Color:.*")) {
                                                                        newColor = field.replace("Color:", "").trim();
                                                                    } else if (field.matches("Size:.*")) {
                                                                        newSize = field.replace("Size:", "").trim();
                                                                    } else if (field.matches("Stock Quantity:.*")) {
                                                                        newStock = field.replace("Stock Quantity:", "").trim();
                                                                    } else if (field.matches("Price:.*")) {
                                                                        newPrice = field.replace("Price:", "").trim();
                                                                    } else if (field.matches("Import Date:.*")) {
                                                                        newDate = field.replace("Import Date:", "").trim();
                                                                    } else if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                                                                        newImage = field.trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getProductDetailsID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldColor.isEmpty()) { %>
                                                        Color: <%= oldColor %><br>
                                                        <% } %>
                                                        <% if (!oldSize.isEmpty()) { %>
                                                        Size: <%= oldSize %><br>
                                                        <% } %>
                                                        <% if (!oldStock.isEmpty()) { %>
                                                        Stock Quantity: <%= oldStock %><br>
                                                        <% } %>
                                                        <% if (!oldPrice.isEmpty()) { %>
                                                        Price: <%= oldPrice %><br>
                                                        <% } %>
                                                        <% if (!oldDate.isEmpty()) { %>
                                                        Import Date: <%= oldDate %><br>
                                                        <% } %>
                                                        <% if (!oldImage.isEmpty()) { %>
                                                        <img src="<%= oldImage %>" alt="Old Image" width="100" height="100"><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newColor.isEmpty()) { %>
                                                        Color: <%= newColor %><br>
                                                        <% } %>
                                                        <% if (!newSize.isEmpty()) { %>
                                                        Size: <%= newSize %><br>
                                                        <% } %>
                                                        <% if (!newStock.isEmpty()) { %>
                                                        Stock Quantity: <%= newStock %><br>
                                                        <% } %>
                                                        <% if (!newPrice.isEmpty()) { %>
                                                        Price: <%= newPrice %><br>
                                                        <% } %>
                                                        <% if (!newDate.isEmpty()) { %>
                                                        Import Date: <%= newDate %><br>
                                                        <% } %>
                                                        <% if (!newImage.isEmpty()) { %>
                                                        <img src="<%= newImage %>" alt="Old Image" width="100" height="100"><br>
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
                                </div>


                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Promotion</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>PromotionID</th>
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
                                                        List<ManagePromotionDTO> promotion = (List<ManagePromotionDTO>) session.getAttribute("managePromotion");
                                                        if (promotion != null && promotion.size() > 0) {
                                                            for (ManagePromotionDTO list : promotion) {
                                                                String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
                                                                String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

                                                                String oldPromotion = "", oldStatus = "", oldDescription = "", oldStartDate = "", oldEndDate = "", oldPer = "", oldImage = "", oldCondition = "";
                                                                String newPromotion = "", newStatus = "", newDescription = "", newStartDate = "", newEndDate = "", newPer = "", newImage = "", newCondition = "";

                                                                // Determine old fields (promotion, status, description, start date, end date, discount per, image, condition)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Promotion:.*")) {
                                                                        oldPromotion = field.replace("Promotion:", "").trim();
                                                                    } else if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Description:.*")) {
                                                                        oldDescription = field.replace("Description:", "").trim();
                                                                    } else if (field.matches("Start Date:.*")) {
                                                                        oldStartDate = field.replace("Start Date:", "").trim();
                                                                    } else if (field.matches("End Date:.*")) {
                                                                        oldEndDate = field.replace("End Date:", "").trim();
                                                                    } else if (field.matches("Discount Per:.*")) {
                                                                        oldPer = field.replace("Discount Per:", "").trim();
                                                                    } else if (field.matches("Condition:.*")) {
                                                                        oldCondition = field.replace("Condition:", "").trim();
                                                                    } else if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                                                                        oldImage = field.trim();
                                                                    }
                                                                }

                                                                // Determine new fields (promotion, status, description, start date, end date, discount per, image, condition)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Promotion:.*")) {
                                                                        newPromotion = field.replace("Promotion:", "").trim();
                                                                    } else if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Description:.*")) {
                                                                        newDescription = field.replace("Description:", "").trim();
                                                                    } else if (field.matches("Start Date:.*")) {
                                                                        newStartDate = field.replace("Start Date:", "").trim();
                                                                    } else if (field.matches("End Date:.*")) {
                                                                        newEndDate = field.replace("End Date:", "").trim();
                                                                    } else if (field.matches("Discount Per:.*")) {
                                                                        newPer = field.replace("Discount Per:", "").trim();
                                                                    } else if (field.matches("Condition:.*")) {
                                                                        newCondition = field.replace("Condition:", "").trim();
                                                                    } else if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                                                                        newImage = field.trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getPromotionID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldPromotion.isEmpty()) { %>
                                                        Promotion: <%= oldPromotion %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                        <% if (!oldDescription.isEmpty()) { %>
                                                        Description: <%= oldDescription %><br>
                                                        <% } %>
                                                        <% if (!oldStartDate.isEmpty()) { %>
                                                        Start Date: <%= oldStartDate %><br>
                                                        <% } %>
                                                        <% if (!oldEndDate.isEmpty()) { %>
                                                        End Date: <%= oldEndDate %><br>
                                                        <% } %>
                                                        <% if (!oldPer.isEmpty()) { %>
                                                        Discount Per: <%= oldPer %><br>
                                                        <% } %>
                                                        <% if (!oldCondition.isEmpty()) { %>
                                                        Condition: <%= oldCondition %><br>
                                                        <% } %>
                                                        <% if (!oldImage.isEmpty()) { %>
                                                        <img src="<%= oldImage %>" alt="Old Image" width="100" height="100"><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newPromotion.isEmpty()) { %>
                                                        Promotion: <%= newPromotion %><br>
                                                        <% } %>
                                                        <% if (!newStatus.isEmpty()) { %>
                                                        Status: <%= newStatus %><br>
                                                        <% } %>
                                                        <% if (!newDescription.isEmpty()) { %>
                                                        Description: <%= newDescription %><br>
                                                        <% } %>
                                                        <% if (!newStartDate.isEmpty()) { %>
                                                        Start Date: <%= newStartDate %><br>
                                                        <% } %>
                                                        <% if (!newEndDate.isEmpty()) { %>
                                                        End Date: <%= newEndDate %><br>
                                                        <% } %>
                                                        <% if (!newPer.isEmpty()) { %>
                                                        Discount Per: <%= newPer %><br>
                                                        <% } %>
                                                        <% if (!newCondition.isEmpty()) { %>
                                                        Condition: <%= newCondition %><br>
                                                        <% } %>
                                                        <% if (!newImage.isEmpty()) { %>
                                                        <img src="<%= newImage %>" alt="Old Image" width="100" height="100"><br>
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
                                </div>

                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Category</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>PromotionID</th>
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
                                                        List<ManageCategoryDTO> category = (List<ManageCategoryDTO>) session.getAttribute("manageCategory");
                                                        if (category != null && category.size() > 0) {
                                                            for (ManageCategoryDTO list : category) {
                                                                String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
                                                                String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

                                                                String oldName = "", oldStatus = "", oldDescription = "", oldImage = "";
                                                                String newName = "", newStatus = "", newDescription = "", newImage = "";

                                                                // Determine old fields (name, status, description, image)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Name:.*")) {
                                                                        oldName = field.replace("Name:", "").trim();
                                                                    } else if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Description:.*")) {
                                                                        oldDescription = field.replace("Description:", "").trim();
                                                                    } else if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                                                                        oldImage = field.trim();
                                                                    }
                                                                }

                                                                // Determine new fields (name, status, description, image)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Name:.*")) {
                                                                        newName = field.replace("Name:", "").trim();
                                                                    } else if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Description:.*")) {
                                                                        newDescription = field.replace("Description:", "").trim();
                                                                    } else if (field.matches(".*\\.(png|jpg|jpeg|gif)$")) {
                                                                        newImage = field.trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getCategories() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldName.isEmpty()) { %>
                                                        Name: <%= oldName %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                        <% if (!oldDescription.isEmpty()) { %>
                                                        Description: <%= oldDescription %><br>
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
                                                        <% if (!newDescription.isEmpty()) { %>
                                                        Description: <%= newDescription %><br>
                                                        <% } %>
                                                        <% if (!newImage.isEmpty()) { %>
                                                        <img src="<%= newImage %>" alt="Old Image" width="100" height="100"><br>
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
                                                    <td colspan="6" class="text-center">No Update !!!</td>
                                                </tr>
                                                <%
                                                    }
                                                %>
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
                                </div>

                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Product Detail</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>Children Category ID</th>
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
                                                        List<ManageCategoryDTO> cdCategory = (List<ManageCategoryDTO>) session.getAttribute("manageCDCategory");
                                                        if (cdCategory != null && cdCategory.size() > 0) {
                                                            for (ManageCategoryDTO list : cdCategory) {
                                                                String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
                                                                String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

                                                                String oldName = "", oldStatus = "";
                                                                String newName = "", newStatus = "";

                                                                // Determine old fields (status, name)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Name:.*")) {
                                                                        oldName = field.replace("Name:", "").trim();
                                                                    }
                                                                }

                                                                // Determine new fields (status, name)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    } else if (field.matches("Name:.*")) {
                                                                        newName = field.replace("Name:", "").trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getCDCategoryID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldName.isEmpty()) { %>
                                                        Name: <%= oldName %><br>
                                                        <% } %>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
                                                        <% if (!newName.isEmpty()) { %>
                                                        Name: <%= newName %><br>
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
                                                    <td colspan="6" class="text-center">No Update !!!</td>
                                                </tr>
                                                <%
                                                    }
                                                %>
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
                                </div>

                                <div class="d-sm-flex align-items-center justify-content-between mb-4 " style="margin-top: 20px;">
                                    <h1 class="h3 mb-0 text-gray-900"><b>History of Order</b></h1>
                                </div>
                                <div class="card" style="margin-top: 20px;">
                                    <div class="table-responsive">

                                        <table class="table align-items-center table-flush">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th>OrderID</th>
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
                                                        List<ManageOrderDTO> order = (List<ManageOrderDTO>) session.getAttribute("manageOrder");
                                                        if (order != null && order.size() > 0) {
                                                            for (ManageOrderDTO list : order) {
                                                                String[] oldFields = list.getLoadOldField().replace("[", "").replace("]", "").split(", ");
                                                                String[] newFields = list.getLoadNewField().replace("[", "").replace("]", "").split(", ");

                                                                String oldStatus = "";
                                                                String newStatus = "";

                                                                // Determine old fields (status)
                                                                for (String field : oldFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        oldStatus = field.replace("Status:", "").trim();
                                                                    }
                                                                }

                                                                // Determine new fields (status)
                                                                for (String field : newFields) {
                                                                    if (field.matches("Status:.*")) {
                                                                        newStatus = field.replace("Status:", "").trim();
                                                                    }
                                                                }
                                                    %>
                                                <tr>
                                                    <td><%= list.getOrderID() %></td>
                                                    <td><%= list.getEmpID() %></td>
                                                    <td>
                                                        <% if (!oldStatus.isEmpty()) { %>
                                                        Status: <%= oldStatus %><br>
                                                        <% } %>
                                                    </td>
                                                    <td>
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
                                                    <td colspan="6" class="text-center">No Update !!!</td>
                                                </tr>
                                                <%
                                                    }
                                                %>
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
