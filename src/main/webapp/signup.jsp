<%-- 
    Document   : signup
    Created on : Jun 5, 2024, 6:37:31 PM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.user.UserError" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Sign up page</title>
        <meta http-equiv="Content_Type" content="width=device-width; charset=UTF-8">
    </head>
    <body>
        <div>Input your information</div>
        <form action="RegisterController" method="POST">
            Full Name <input type="text" name="userName"/><br/>
            Email <input type="text" name="email"/><br/>
            Phone number <input type="number" name="phone"/><br/>
            Ward <input type="text" name="ward"/><br/>
            District <input type="text" name="district"/><br/>
            City <input type="text" name="city" /><br/>
            Address <input type="text" name="address" /><br/>
            Date of birth <input type="date" name="birthday" /><br/>
            Password <input type="password" name="password" /><br/>
            Confirm password <input type="password" name="confirmPassword" /><br/>
            <input type="submit" name="action" value="Sign_In"/>
            <input type="reset" value="Reset"/>
        </form>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError != null) {
        %>
            <div style="color: red;">
                <p><%= userError.getUserNameError() %></p>
                <p><%= userError.getEmailError() %></p>
                <p><%= userError.getPhoneError() %></p>
                <p><%= userError.getBirthdayError() %></p>
                <p><%= userError.getPasswordError() %></p>
                <p><%= userError.getConfirmError() %></p>
                <p><%= userError.getError() %></p>
            </div>
        <%
            }
        %>
    </body>
</html>


