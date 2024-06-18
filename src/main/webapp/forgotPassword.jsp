<%-- 
    Document   : forgotPassword
    Created on : Jun 17, 2024, 9:46:15 PM
    Author     : notlongfen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="MainController" method="POST">
            <label></label>
            <input type="email" name="email" placeholder="Email to reset password">
            <input type="submit" name="action" value="Forgot_Password">
        </form>
    </body>
</html>
