<%-- 
    Document   : editEmployee
    Created on : Jun 19, 2024, 10:22:32 AM
    Author     : TTNHAT
--%>

<%@page import="com.mycompany.isp392.user.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Employee Page</title>
    </head>
    <body>
        <%
            UserDAO dao = new UserDAO();
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDTO user = dao.getUserByID(userID);
        %>
        <h2>Edit Customer Information</h2>
        <form action="MainController" method="POST">
            <input type="hidden" name="userID" value="<%= user.getUserID()%>" readonly=""/>
            Customer Name: <input type="text" name="userName" value="<%=user.getUserName()%>" required=""/><br/>
            <input type="hidden" name="oldEmail" value="<%=user.getEmail()%>" readonly=""/></br>
            Email: <input type="text" name="email" value="<%=user.getEmail()%>" required=""/></br>
            <input type="hidden" name="oldPhone" value="<%= user.getPhone()%>" readonly=""/></br>
            Phone: <input type="number" name="phone" value="<%= user.getPhone()%>" required=""/></br>
            Role: <select name="roleID">
                <option value="1" <%= user.getRoleID() == 1 ? "selected" : "" %>>System Manager</option>
                <option value="2" <%= user.getRoleID() == 2 ? "selected" : "" %>>Shop Manager</option>
                <option value="3" <%= user.getRoleID() == 3 ? "selected" : "" %>>Shop Staff</option>
            </select>
            Status: <select name="status">
                <option value="0" <%= user.getStatus() == 0 ? "selected" : "" %>>Inactive</option>
                <option value="1" <%= user.getStatus() == 1 ? "selected" : "" %>>Active</option>
            </select></br>
            <input type="hidden" name="currentPassword" value="<%=user.getPassword()%>"/>
            Password: <input type="password" name="password" value=""/></br>
            Confirm Password: <input type="password" name="confirm" value=""/></br>
            <input type="submit" name="action" value="EditEmployee" required=""/>
            <input type="reset" value="Reset" required=""/>
        </form>
        <%
            UserError userError = (UserError) request.getAttribute("EDIT_ERROR");
            if (userError != null) {
        %>
        <div style="color: red;">
            <p><%= userError.getUserNameError()%></p>
            <p><%= userError.getEmailError()%></p>
            <p><%= userError.getPhoneError()%></p>
            <p><%= userError.getBirthdayError()%></p>
            <p><%= userError.getPasswordError()%></p>
            <p><%= userError.getConfirmError()%></p>
            <p><%= userError.getError()%></p>
        </div>
        <%
            }
        %>
    </body>
</html>
