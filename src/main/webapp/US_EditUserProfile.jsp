<%-- 
    Document   : MyProfile
    Created on : May 27, 2024, 7:56:56 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <style>
            body {
                background-color: #f8f9fa;
                padding: 0;
                margin: 0;
            }
            .profile-container {
                padding: 50px;
                background-color: #fff;
                border-radius: 10px;
                /*                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
                padding-left: 100px;
            }
            .profile-sidebar {
                background-color: #fff;
                border: none;
                /*                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
                height: 500px;
            }
            /*.form-control{color: black;}*/
            .btn.middle {
                background-color: black;
                color: white;
                display: flex;
                margin: 20px auto 0; /* Centering the button */
            }
            .flex-container {
                display: flex;
            }
            .flex-item {
                flex: 1;
                margin: 10px;
            }
            .profile-sidebar{
                width: 300px;
                height: 500px;
            }

            .nav-link {
                transition: background-color 0.3s, color 0.3s;
                font-size: 18px;
                color: black;
            }

            .nav-link:hover,
            .nav-link.active {
                color: white;
                background-color: black;
            }

            .input-group{
                position: relative;
            }
            .btn-format{
                background-color: #fff;
                color:#000;
                background-color: transparent; /* Xóa nền màu xám */
                border: none; /* Xóa đường viền */
                color: inherit; /* Giữ nguyên màu chữ */
                padding: 0; /* Xóa khoảng đệm */
                cursor: pointer; /* Thay đổi con trỏ khi hover */
            }
            .btn-format h5{
                color: #ffffff;
                font-size: medium;
            }
            .info {
                background-color: transparent; /* Xóa nền màu xám */
                border: none; /* Xóa đường viền */
                color: inherit; /* Giữ nguyên màu chữ */
                padding: 0; /* Xóa khoảng đệm */
                cursor: pointer; /* Thay đổi con trỏ khi hover */
            }
            .right-element {
                position: inherit;
                right: 0;
            }
            .btn-format.right-element button {
                display: inline-block;
                margin-left: 5px; /* Điều chỉnh khoảng cách giữa hai nút */
            }

        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>

        <div class="container-fluid ">
            <div class="row ">
                <!<!-- Profile Sidebar -->
                <div class="col-lg-3 profile-sidebar " style="margin-top: 7rem;" >
                    <div class="card h-100">
                        <div>
                            <div class="user-side-bar" style="padding-left: 30px">
                                <h5 class="card-title" style="padding-top: 20px" >Hello 🌟</h5>
                                <h2 class="card-text" style="font-size: medium; font-weight: bold">Nguyen Van A</h2>
                            </div>
                            <hr>
                            <div >
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link active" aria-current="page" href="MyProfile.jsp"><div class="fas fa-user mr-2"></div> Personal Information</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link " href="MyOrder.jsp"><div class="fas fa-box mr-2"></div> My Order</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="MyWishlist.jsp"><div class="fas fa-heart mr-2"></div> ️My Wishlists</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8 profile-container" style="margin-top: 4rem;">
                    <div class="row">
                        <div class="col-sm-6">
                            <h1 class="mb-4" left-element>My Profile</h1>
                        </div>
                        <div class="btn-format right-element btn-format.right-element button" >
                            <a href="US_EditUserProfile.jsp"><button type="button" class="btn middle bg-white" style="border: 2px solid black;">
                                    <h5 style="color: black">Save</h5>
                                </button></a>

                        </div>
                        <div class="col-sm-6"></div>
                    </div>
                    <form>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">FullName</label>
                            <div class="input-group">
                                <input type="text" style="color: black; background-color: #fff" class="form-control" id="fullName" value="Nguyen Van A" disabled>
                                <span class="input-group-text edit-icon btn-sm" style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email Address</label>
                            <div class="input-group">
                                <input type="text" style="color: black; background-color: #fff" class="form-control" id="email" value="ABC@gmail.com" disabled>
                                <span class="input-group-text edit-icon btn-sm" style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <div class="input-group">
                                <input type="text" style="color: black; background-color: #fff"class="form-control" id="address" value="123 Đường DEF, Quận 1, Thành phố Hồ Chí Minh" disabled>
                                <span class="input-group-text edit-icon btn-sm"style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-sm-4">
                                <label for="ward" class="form-label">Ward</label>
                                <div style="display: flex; align-content: center;">
                                    <input type="text" style="color: black; background-color: #fff" class="form-control" id="ward" value="Phường 13" disabled="">
                                    <span class="input-group-text edit-icon btn-sm"style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <label for="district" class="form-label">District</label>
                                <div style="display: flex; align-content: center;">
                                    <input type="text"style="color: black; background-color: #fff" class="form-control" id="district" value="Quận 1" disabled="">
                                    <span class="input-group-text edit-icon btn-sm"style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <label for="city" class="form-label">City</label>
                                <div style="display: flex; align-content: center;">
                                    <input type="text"style="color: black;  background-color: #fff" class="form-control" id="city" value="Hồ Chí Minh" disabled="">
                                    <span class="input-group-text edit-icon btn-sm"style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="birthday" class="form-label">Birthday</label>
                            <div class="input-group">
                                <input type="text"style="color: black; background-color: #fff" class="form-control" id="birthday" value="04/11/2004" disabled="">
                                <span class="input-group-text edit-icon btn-sm"style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone Number</label>
                            <div class="input-group">
                                <input type="text" style="color: black; background-color: #fff"class="form-control" id="phoneNumber" value="0901234567" disabled=""> 
                                <span class="input-group-text edit-icon btn-sm"style="background-color: #fff"><i class="fas fa-pencil-alt"></i></span>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <%@include file="US_footer.jsp" %>
    <script>

        document.addEventListener('DOMContentLoaded', function () {
            var navLinks = document.querySelectorAll('.profile-sidebar .nav-link');
            navLinks.forEach(function (link) {
                link.addEventListener('click', function () {
                    navLinks.forEach(function (nav) {
                        nav.classList.remove('active');
                    });
                    link.classList.add('active');
                });
            });
        });

        document.addEventListener('DOMContentLoaded', function () {
            var editIcons = document.querySelectorAll('.edit-icon');
            editIcons.forEach(function (icon) {
                icon.addEventListener('click', function () {
                    var input = this.parentNode.querySelector('.form-control');
                    input.disabled = !input.disabled;
                    // Thay đổi mãu chữ
                    if (input.disabled) {
                        input.style.color = 'black';
                    } else {
                        input.style.color = 'red';
                    }
                });
            });
        });

        document.addEventListener('DOMContentLoaded', function () {
            var editProfileButton = document.querySelector('.btn.middle');
            var inputs = document.querySelectorAll('.form-control');
            editProfileButton.addEventListener('click', function () {
                inputs.forEach(function (input) {
                    input.disabled = true;
                    if (input.disabled) {
                        input.style.color = '#000';
                    } else {
                        input.style.color = 'red';
                    }
                })
            })
        });
    </script>
</body>
</html>
