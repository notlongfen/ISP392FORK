<%-- 
    Document   : footer
    Created on : Jun 3, 2024, 4:17:10 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ví dụ Footer</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="styles/footer.css">
        <style>
            .shop-name {
                text-decoration: none; /* Loại bỏ gạch chân của liên kết */
                font-size: 50px; /* Kích thước chữ */
                color: #b21f2d !important;/* Màu chữ mặc định cho phần đầu tiên */
                margin: 0;
                font-size: 3em;
                color: #333;
                position: relative;
                display: inline-block;
                font-weight: normal;
            }
            .shop-name span {
                color: #ffffff; /* Màu đỏ cho phần chữ "no" */
                background-color: #1c1c1e; /* Màu nền đen cho phần chữ "Kro" */
                border-radius: 3px; /* Bo tròn các góc của nền */
            }
            .shop-name:hover {
                text-decoration: underline; /* Gạch chân liên kết khi di chuột qua */
            }

            .contact-info {
                display: flex; /* Sử dụng Flexbox để căn chỉnh các phần tử con */
                align-items: center; /* Căn chỉnh các phần tử con theo chiều dọc */
                color: #fff; /* Màu chữ trắng */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới mỗi thông tin liên lạc */
            }

            .contact-info i {
                margin-right: 10px; /* Khoảng cách giữa biểu tượng và văn bản */
                color: #fff; /* Màu của biểu tượng */
            }

            .contact-info p {
                margin: 0; /* Loại bỏ khoảng cách mặc định trên và dưới của thẻ p */
                color: #fff;
            }
        </style>
    </head>
    <body>

        <!-- Footer -->
        <div class="myapp-footer">
            <div class="container footer">
                <div class="container">
                    <div class="row justify-content-between">
                        <div class="col-md-5">
                            <!-- Tên shop -->
                            <a href="HomePageController" class="shop-name">KRO<span>NO</span></a>
                        </div>
                        <div class="col-md-5">
                            <div class="contact-info">
                                <i class="fas fa-map-marker-alt"></i>
                                <p>Lô E2a-7, Đường D1 Khu Công nghệ cao, P. Long Thạnh Mỹ, TP. Thủ Đức, TP. Hồ Chí Minh</p>
                            </div>
                            <div class="contact-info">
                                <i class="fas fa-phone"></i>
                                <p>Phone: (123) 456-7890</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="copyright">
                                &copy; 2024 ISP392SHOP
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Bootstrap và jQuery JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- Font Awesome cho biểu tượng -->
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>

    </body>
</html>

