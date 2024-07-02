<%-- 
    Document   : ReplySupport
    Created on : Jun 5, 2024, 10:25:49 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.support.SupportDTO" %>
<%@ page import="com.mycompany.isp392.user.UserDTO" %>
<%@ page import="com.mycompany.isp392.support.ProcessSupportDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <title>Reply Support</title>
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

        </style>
    </head>
    <body id="page-top">
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if ((loginUser == null || 2!=loginUser.getRoleID()) && (loginUser == null || 3!=loginUser.getRoleID()) ) {
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
                                <h2 class="text-center" style="color: #000; font-weight: bold">Reply Support</h2>
                                <form action="MainController" method="POST" >
                                    <div class="form-group">
                                        <label for="brandName">To</label>
                                        <input type="text" class="form-control readonly-input" id="from" name="toEmail" value="<%= user.getEmail()%>" readonly="">
                                    </div>
                                    <div class="form-group">
                                        <label for="brandName">Title</label>
                                        <input class="form-control" id="textDescriptipn" rows="5" name="subject" placeholder="Enter Title Here">
                                    </div>
                                    <div class="form-group">
                                        <label for="brandName" class="form-label">Content</label>
                                        <input type="text" class="form-control" id="content" rows="5" name="content" value="<%= support.getRequestMessage()%>" readonly="">
                                    </div>
                                    <div class="form-group">
                                        <label for="brandName" class="form-label">Reply Message</label>
                                        <input class="form-control" id="textDescriptipn" rows="5" name="replyMessage" placeholder="Enter Reply Message Here">
                                    </div>
                                    <div class="form-group text-center">
                                        <button type="submit" class="btn btn-primary btn-danger" name="action" value="Reply_Support">Submit</button>
                                        <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
                                    </div>
                                    <input type="hidden" name="supportID" value="<%= support.getSupportID() %>">
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
