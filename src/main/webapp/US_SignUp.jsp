<%-- 
    Document   : SignUp
    Created on : May 31, 2024, 9:42:35 PM
    Author     : jojo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.user.UserError" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">        
        <link rel="stylesheet" href="styles/header_ISP.css">
    </head>
    <body id="page-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="header text-center ">
                        <div class="logo">
                            <h1><span>KRO</span>NO</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container mb-5" style="width: 500px">
            <div class="card">
                <div class="card-body">
                    <div class="mt-1">
                        <ul class="nav nav-tabs mb-3 justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="SignUp.jsp">Sign up</a>
                            </li>
                            <li class="nav-item" style="font-weight: bold">
                                <a class="nav-link" href="US_SignIn.jsp" style="color: grey">Log in</a>
                            </li>
                        </ul>
                    </div>

                    <!--Form--->
                    <form action="RegisterController" method="POST" accept-charset="UTF-8">
                        <div class="mb-3">
                            <label for="fullName" class="form-label" >Enter your full name</label>
                            <input type="text" class="form-control" id="fullName" placeholder="Enter your full name" name="userName" required="">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email Address</label>
                            <input type="email" class="form-control" id="email" placeholder="Email Address" name="email" required="">
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone number</label>
                            <input type="number" class="form-control" id="phoneNumber" placeholder="Phone number" name="phone" required="">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">City</label>
                            <select class="form-select" id="city" name="city" aria-label = ".form-select-sm">
                                <option value="" >Select your city</option>  
                            </select>
                        </div>
                        <div class="row mb-3 d-flex justify-content-between">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">District</label>
                                <select class="form-select" id="district" name="district" aria-label=".form-select-sm">
                                    <option value="" >Select District</option>
                                </select>
                            </div>  
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Ward</label>
                                <select class="form-select" id="ward" name="ward" aria-label=".form-select-sm">
                                    <option value="" >Select Ward</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="address" placeholder="Detail Address" name="address" required="">
                        </div>
                        <div class="mb-3">
                            <label for="dob" class="form-label">Date of birth</label>
                            <input type="date" class="form-control" id="dob" name="birthday" required="">
                        </div>
                        <div class="mb-3 position-relative">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password" required="">
                            <i class="far fa-eye" id="togglePassword" style="cursor: pointer; position: absolute; right: 10px; top: 35px;"></i>
                        </div>
                        <div class="mb-3 position-relative">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword" required="">
                            <i class="far fa-eye" id="toggleConfirmPassword" style="cursor: pointer; position: absolute; right: 10px; top: 35px;"></i>
                        </div>

                        <input type="hidden" id="cityName" name="cityName">
                        <input type="hidden" id="districtName" name="districtName">
                        <input type="hidden" id="wardName" name="wardName">

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="terms" required="">
                            <label class="form-check-label" for="terms">
                                I confirm that I have read and accept the <a href="MainController?action=View_term_and_condition">Terms and Conditions</a> and the <a href="MainController?action=View_privacy">Privacy Policy</a>.
                            </label>
                        </div>
                        <button type="submit" class="btn btn-dark w-100" name="action" value="Sign Up">Sign Up</button>
                    </form>
                </div>
            </div>
        </div>
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <jsp:include page="US_footer.jsp" />

        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <script>
            var citis = document.getElementById("city");
            var districts = document.getElementById("district");
            var wards = document.getElementById("ward");
            var cityNameField = document.getElementById("cityName");
            var districtNameField = document.getElementById("districtName");
            var wardNameField = document.getElementById("wardName");

            var Parameter = {
                url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                method: "GET",
                responseType: "application/json",
            };
            var promise = axios(Parameter);
            promise.then(function (result) {
                renderCity(result.data);
            });

            function renderCity(data) {
                for (const x of data) {
                    citis.options[citis.options.length] = new Option(x.Name, x.Id);
                }
                citis.onchange = function () {
                    districts.length = 1;
                    wards.length = 1;
                    cityNameField.value = citis.options[citis.selectedIndex].text;
                    districtNameField.value = '';
                    wardNameField.value = '';
                    if (this.value != "") {
                        const result = data.filter(n => n.Id === this.value);
                        for (const k of result[0].Districts) {
                            districts.options[districts.options.length] = new Option(k.Name, k.Id);
                        }
                    }
                };
                districts.onchange = function () {
                    wards.length = 1;
                    districtNameField.value = districts.options[districts.selectedIndex].text;
                    wardNameField.value = '';
                    const dataCity = data.filter((n) => n.Id === citis.value);
                    if (this.value != "") {
                        const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;
                        for (const w of dataWards) {
                            wards.options[wards.options.length] = new Option(w.Name, w.Id);
                        }
                    }
                };
                wards.onchange = function () {
                    wardNameField.value = wards.options[wards.selectedIndex].text;
                };
            }

            const togglePassword = document.querySelector("#togglePassword");
            const password = document.querySelector("#password");
            const toggleConfirmPassword = document.querySelector("#toggleConfirmPassword");
            const confirmPassword = document.querySelector("#confirmPassword");

            togglePassword.addEventListener("click", function () {
                const type = password.getAttribute("type") === "password" ? "text" : "password";
                password.setAttribute("type", type);
                this.classList.toggle("fa-eye-slash");
            });

            toggleConfirmPassword.addEventListener("click", function () {
                const type = confirmPassword.getAttribute("type") === "password" ? "text" : "password";
                confirmPassword.setAttribute("type", type);
                this.classList.toggle("fa-eye-slash");
            });

        </script>
    </body>
</html>
