<%-- 
    Document   : login
    Created on : Jun 5, 2024, 8:43:43 AM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta http-equiv="Content_Type" content="width=device-width; charset=UTF-8">
    </head>
    <body>
        <div>Input your information</div>
        <form action="MainController" method="POST">
            Email <input type="text" name="email"/><br/>
            Password <input type="password" name="password" /><br/>
            <input type="submit" name="action" value="Login"/>
            <input type="submit" name="action" value="Sign_Up"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
