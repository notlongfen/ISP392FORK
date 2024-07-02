<%-- 
    Document   : CreateProduct
    Created on : Jun 2, 2024, 4:10:09 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.user.UserDTO"%>
<%@page import="com.mycompany.isp392.promotion.PromotionDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
        <title>Edit Promotion</title>
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
        <%
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (loginUser == null || 2 != loginUser.getRoleID()) {
                        response.sendRedirect("US_SignIn.jsp");
                        return;
                    }
                    PromotionDTO promotion = (PromotionDTO) request.getAttribute("PROMOTION");
                    if (promotion == null) {
                        response.sendRedirect("AD_PromotionList.jsp");
                        return;
                    }
        %>
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="AD_sidebar.jsp" %>

            <!-- Sidebar -->
            <div id="content-wrapper" class="d-flex flex-column">

                <div id="content">
                    <!-- Header -->
                    <%@include file="AD_header.jsp" %>

                    <div class="container-fluid" id="container-wrapper">

                        <div class="form-container">
                            <h2 class="text-center" style="color: #000; font-weight: bold;">Edit Promotion</h2>
                            <form action="MainController">
                                <div class="form-row">
                                    <input type="hidden" name="promotionID" value="<%= promotion.getPromotionID() %>" readonly=""/>
                                    <div class="form-group col-md-6">
                                        <label for="productName">Code Name</label>
                                        <input type="text" class="form-control" id="productName" name="promotionName" value="<%= promotion.getPromotionName() %>" required=""/>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="productName">Percentage</label>
                                        <input type="text" class="form-control" id="percentage"  name="discountPer" value="<%= promotion.getDiscountPer() %>" required=""/>
                                    </div>

                                </div>
                                <div class="form-row">

                                    <div class="form-group col-sm-4">
                                        <label for="importDate">Start Date</label>
                                        <input type="date" class="form-control" id="startDate" name="startDate" value="<%= promotion.getStartDate() %>" required=""/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="importDate">End Date</label>
                                        <input type="date" class="form-control" id="endDate" name="endDate" value="<%= promotion.getEndDate() %>" required=""/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="status">Status</label>
                                        <input type="int" class="form-control" id="status" name="status" value="<%= promotion.getStatus() %>" required=""/>
                                    </div>
                                </div > 

                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="description">Description</label>
                                        <input type="text" class="form-control" id="description" name="description" value="<%= promotion.getDescription() %>" required=""/>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="condition">Condition</label>
                                        <input type="int" class="form-control" id="condition" name="condition" value="<%= promotion.getCondition() %>" required=""/>
                                    </div>
                                </div>

                                <div class="form-group text-center">
                                    <button type="submit" class="btn btn-danger btn-custom" name="action" value="SaveEditPromotion">Submit</button>
                                    <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Include necessary scripts -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
            <script src="js/ruang-admin.min.js"></script>
            <script src="vendor/chart.js/Chart.min.js"></script>
            <script src="js/demo/chart-area-demo.js"></script>
    </body>
</html>
