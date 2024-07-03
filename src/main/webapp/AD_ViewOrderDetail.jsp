<%-- 
    Document   : ReplySupport
    Created on : Jun 5, 2024, 10:25:49 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <title>View Support</title>
        <style>
            .form-container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #dee2e6;
                border-radius: 12px;
                background-color: #f8f9fa;
            }
            .form-group label {
                font-weight: bold;
            }
            .readonly-input {
                border: none;
                background-color: transparent;
                color: #000;
            }
        </style>
    </head>
    <body id="page-top">
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || 2!=loginUser.getRoleID()) {
                response.sendRedirect("US_SignIn.jsp");
                return;
            }
            SupportDTO support = (SupportDTO) request.getAttribute("SUPPORT_DETAIL");
            UserDTO user = (UserDTO) request.getAttribute("USER_SUPPORT");
            ProcessSupportDTO process = (ProcessSupportDTO) request.getAttribute("PROCESS_SUPPORT");
            if (support == null) {
                response.sendRedirect("AD_SupportList.jsp");
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
                        <div class="container mt-4">
                            <div class="form-container">
                                <h2 class="text-center" style="color: #000; font-weight: bold">View Support</h2>
                                <div class="form-group">
                                    <label for="to">From:</label>
                                    <input type="text" class="form-control readonly-input" id="from" name="email" value="<%= user.getEmail()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label for="textDescriptipn" class="form-label">Title:</label>
                                    <input type="text" class="form-control readonly-input" id="title" rows="5" name="title" value="<%= support.getTitle()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label for="textDescriptipn" class="form-label">Content:</label>
                                    <input type="text" class="form-control readonly-input" id="content" rows="5" name="content" value="<%= support.getRequestMessage()%>" readonly="">
                                </div>
                                <div class="form-group">
                                    <label for="textDescriptipn" class="form-label">Reply Message:</label>
                                    <input type="text" class="form-control readonly-input" id="responseMessage" rows="5" name="responseMessage" value="<%= process.getResponseMessage()%>" readonly="">
                                </div>
                                <form action="MainController">
                                    <div class="d-flex justify-content-end" >
                                    <button type="submit" class="btn btn-primary btn-danger" name="action" value="Back_To_SupportList">Back</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <!-- Bootstrap JS and dependencies -->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
