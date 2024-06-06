<%-- 
    Document   : signup
    Created on : Jun 5, 2024, 6:37:31 PM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign up page</title>
        <meta http-equiv="Content_Type" content="width=device-width; charset=UTF-8">
    </head>
    <body>
        <div>Input your information</div>
        <form action="SignUpController" method="POST">
            Full Name <input type="text" name="userName"/><br/>
            Email <input type="text" name="email"/><br/>
            Phone number <input type="number" name="phone"/><br/>
            Ward <input type="text" name="ward"/><br/>
            District <input type="text" name="district"/><br/>
            City <input type="text" name="city" /><br/>
            Address <input type="text" name="address" /><br/>
            Date of birth <input type="date" name="birthday" /><br/>
            Password <input type="password" name="password" /><br/>
            Confirm password <input type="password" name="password" /><br/>
            <input type="submit" name="action" value="Sign_In"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>