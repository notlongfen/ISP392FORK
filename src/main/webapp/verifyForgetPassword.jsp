<%-- 
    Document   : verifyForgetPassword
    Created on : Jun 17, 2024, 9:50:39 PM
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
            <label>Your Submit Code</label>
            <input type="text" name="token" placeholder="<%= request.getParameter("token")%>">
            <input type="password" name="newPassword" placeholder="New Password">
            <input type="password" name="confirmPassword" placeholder="Confirm New Password">
            <input type="submit" name="action" value="Verify_Token">
        </form>
            <%= request.getParameter("token")%>
    </body>
</html>
