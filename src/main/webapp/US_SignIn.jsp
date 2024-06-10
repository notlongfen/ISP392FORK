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
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">       
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
            .social-login-buttons button {
                width: 100%;
                margin-bottom: 10px;
                border: 1px solid #555555;
                border-radius: 0;
            }
            .social-login-buttons button {
                display: flex;
                align-items: center;

                gap: 8px; 
            }
            .mb-3.mb-3 text-end{
                text-decoration: none;
            }
            .social-login-buttons img {
                margin-right: 8px; /* Add space between the icon and text */
            }

            .social-login-buttons {
                flex: 1;  
                /*Ensure buttons take up equal space*/ 
                text-align: center; /* Center the buttons */
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
            .social-icons {
                display: space-around;
                gap: 5px;
                margin-left: 10px;
                margin-right: 10px;
            }

            .icon {
                color: #ffffff;
                font-size: 24px;
                text-decoration: none;
            }
            .icon:hover {
                color: #555;
            }
            .a.social-icons {
                color: white;
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
        <div class="container my-5" style="width: 500px">
            <div class="card">
                <div class="card-body">

                    <div class="mt-5">
                        <ul class="nav nav-tabs mb-3 justify-content-center">
                            <li class="nav-item" style="font-weight: bold">
                                <a class="nav-link" href="US_SignUp.jsp">Sign up</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="SignIn.jsp.jsp">Log in</a>
                            </li>
                        </ul>
                    </div>

                    <form>
                        <p style="font-weight: bold; font-size: medium">Log in</p>
                        <div class="mb-3">
                            <input type="email" class="form-control" style="border-radius: 0" id="email" placeholder="Email Address">
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" id="password" style="border-radius: 0" placeholder="Password">
                        </div>
                        <div class="mb-3 text-end">
                            <a href="#" style="text-decoration: none">Forgot Password?</a>
                        </div>
                        <button type="submit" class="btn btn-dark w-100" style="border-radius: 0;">Log in</button>
                        <div class="row d-flex justify-content-between">
                            <div class="social-login-buttons mt-3">
                                <button type="button" class="btn btn-light border mb-2" style="">
                                    <img src="https://img.icons8.com/color/16/000000/google-logo.png" alt="Google Logo"/> Google
                                </button>
                            </div>
                            <div class="social-login-buttons mt-3">
                                <button type="button" class="btn btn-light border">
                                    <img src="https://img.icons8.com/color/16/000000/facebook.png" alt="Facebook Logo"/> Facebook
                                </button>
                            </div>
                        </div>
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
