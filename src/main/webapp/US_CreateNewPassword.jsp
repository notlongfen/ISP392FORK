<%-- 
    Document   : SignIn
    Created on : May 31, 2024, 9:40:29 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create New Password</title>
        <style>
            a {
                color: black;
                text-decoration: none; /* Bỏ gạch chân của liên kết nếu muốn */
            }
            a:active {
                color: black;
                font-weight: bold;
            }
            a:visited {
                color: gray;
            }
            
            .header {
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                padding: 20px;
                background-color: #ffffff;
            }
            .logo h1 {
                margin: 0;
                font-size: 3em;
                color: #333;
                position: relative;
                display: inline-block;
            }
            .logo h1::after {
                content: "";
                position: absolute;
                bottom: -10px;
                left: 0;
                width: 100%;
                height: 2px;
                background-color: white;
            }
            .logo span {
                color: #b21f2d; /* Updated color to red */
            }
            .bg-custom1 {
                background-color: #FFFFFF; /* Màu tùy chỉnh */
                color: white;
            }
        </style>
    </head>
    <body style="background-color: rgba(250, 250, 250, 1);">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="header text-center bg-custom1">
                        <div class="logo">
                            <h1><span>ISP392</span>SHOP</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container my-5" style="width: 500px; ">
            <div class="card" style="margin-bottom: 250px;">
                <div class="card-body" >
                    <form>
                        <p style="font-weight: bold; font-size: 23px;">Create New Password</p>
                        <p style="color: #838383">Your new password must be different from your previous password</p>
                        <p class="mb-0" style="font-size: 18px;">New password</p>
                        <div class="mb-0">
                            <input type="password" class="form-control" style="border-radius: 0" id="password" placeholder="Enter password">
                        </div>
                        <p class="mt-3 mb-0" style="font-size: 18px;">Confirm password</p>
                        <div class="mb-3 mt-0">
                            <input type="password" class="form-control" style="border-radius: 0" id="confirmPassword" placeholder="Enter password">
                        </div>
                        <button type="submit" class="btn btn-dark w-100" style="border-radius: 0; font-size: 20px;">Reset Password</button>
                    </form>
                </div>
            </div>
        </div>
        
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="styles.css">
        <jsp:include page="US_footer.jsp" />
    </body>
    
</html>