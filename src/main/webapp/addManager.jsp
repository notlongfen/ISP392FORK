<%-- 
    Document   : addManager
    Created on : Jun 14, 2024, 2:13:47 PM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.user.UserError" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <div>Input your information</div>
    <form action="AddManagerController" method="POST">
        Full Name <input type="text" name="userName"/><br/>
        Email <input type="text" name="email"/><br/>
        Phone number <input type="number" name="phone"/><br/>
        Role ID <select name ="roleID">
            <option value="1">1 - Admin</option>
            <option value="2">2 - Manager</option>
            <option value="3">3 - Staff</option>
        </select>
        Position <select name ="position">
            <option value="Admin">1 - Admin</option>
            <option value="Manager">2 - Manager</option>
            <option value="Staff">3 - Staff</option>
        </select><br/>
        Password <input type="password" name="password" /><br/>
        Confirm password <input type="password" name="confirmPassword" /><br/>
        <input type="submit" name="action" value="Add_Manager"/>
        <input type="reset" value="Reset"/>
    </form>
    
</body>
</html>
