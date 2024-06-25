<%-- 
    Document   : shopStaff
    Created on : Jun 11, 2024, 10:58:44 AM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Staff Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || 3 != loginUser.getRoleID()) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        
        <h1>Welcome: <%= loginUser.getUserName()%></h1>
        <form action="MainController">
            <input type="submit" name="action" value="Manage products"/>
            <input type="submit" name="action" value="Manage supports"/>
    </body>
</html>
