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
                                <form>
                                    <div class="form-group">
                                        <label for="brandName">To</label>
                                        <input type="text" class="form-control" id="to" placeholder="Enter">
                                    </div>
                                    <div class="form-group">
                                        <label for="brandName">Title</label>
                                        <input type="text" class="form-control" id="title" placeholder="Enter ">
                                    </div>
                                    <div class="form-group">
                                        <label for="brandName" class="form-label">Content</label>
                                        <textarea class="form-control" id="textDescriptipn" rows="5">
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                        </textarea>
                                    </div>

                                    <div class="form-group text-center">
                                        <button type="submit" class="btn btn-primary btn-danger">Submit</button>
                                        <button type="reset" class="btn btn-secondary btn-custom">Reset</button>
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
