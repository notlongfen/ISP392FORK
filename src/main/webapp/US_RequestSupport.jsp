<%-- 
    Document   : US_RequestSupport
    Created on : Jul 5, 2024, 11:23:07 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
        <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
        <link rel="stylesheet" type="text/css" href="styles/responsive.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <style>
        #openFormButton {
                background-color: #C53337;
                color: white;
                padding: 16px;
                border: none;
                cursor: pointer;
                /*opacity: 0.8;*/
                position: fixed;
                bottom: 23px;
                right: 28px;
                width: 56px;
                height: 56px;
                border-radius: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 16px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .form-popup {
                display: none;
                position: fixed;
                bottom: 0;
                right: 15px;
                border: 3px solid #f1f1f1;
                z-index: 9;
                background-color: white;
                width: 300px;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 400px;
                height: 700px;
            }

            .form-container {
                display: flex;
                flex-direction: column;
            }

            .form-container input[type="text"],
            .form-container textarea {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                border: none;
                background: #f1f1f1;
            }

            .form-container input[type="text"]:focus,
            .form-container textarea:focus {
                background-color: #ddd;
                outline: none;
            }

            .form-container .btn {
                background-color: #C53337;
                color: white;
                padding: 16px 20px;
                border: none;
                cursor: pointer;
                width: 100%;
                margin-bottom:10px;
                /*opacity: 0.8;*/
            }

            .form-container .cancel {
                background-color: #33363F;
            }

            .form-container .btn:hover,
            .open-button:hover {
                opacity: 10;
            }

            a.btn {
                padding: 10px 15px;
                font-size: 15px;
                border: 1px solid;
                border-radius: 5px;
                cursor: pointer;
                margin-right: 10px;
                width: 150px;
                height: 50px;
                text-align: center;
                /* C?n gi?a n?i dung ngang */
                justify-content: center;
                /* C?n gi?a n?i dung d?c */
                align-items: center;
                display: flex;
            }
            a.btn:hover{
                background-color: #FFF;
                color: #c53337;
            }
            a.btn:active{
                background-color: #c53337;
                color: #fff;
            }


        
    </style>
        
    <body>
        <button id="openFormButton"><i class='fas fa-pen'></i></button>

            <div id="popupForm" class="form-popup">
                <form class="form-container" action="MainController" method="POST">
                    <h4 style="text-align: center">PLEASE FILL IN THE BLANKS TO REQUEST SUPPORT</h4>
                    <label for="email"><b>Email</b></label>
                    <input type="text" id="email" name="email" required>

                    <label for="title"><b>Title</b></label>
                    <input type="text" id="title" name="title" required>

                    <label for="content"><b>Content</b></label>
                    <textarea id="content" name="content" required></textarea>

                    <input type="submit" class="btn" value="Request For Support" name="action">
                    <button type="button" class="btn cancel" id="closeFormButton">Close</button>
                </form>
            </div>
        <script>
             document.getElementById('openFormButton').addEventListener('click', function () {
                document.getElementById('popupForm').style.display = 'block';
            });

            document.getElementById('closeFormButton').addEventListener('click', function () {
                document.getElementById('popupForm').style.display = 'none';
            });
        </script>
        
        <script src="US_js/jquery-3.2.1.min.js"></script>
        <script src="styles/bootstrap4/popper.js"></script>
        <script src="styles/bootstrap4/bootstrap.min.js"></script>
        <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
        <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
        <script src="plugins/easing/easing.js"></script>
        <script src="US_js/custom.js"></script>
        <script src="US_js/brand.js"></script>
    </body>
</html>
