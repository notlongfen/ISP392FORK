<%-- 
    Document   : viewSupport
    Created on : Jun 27, 2024, 6:02:11 PM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.isp392.support.SupportDTO" %>
<%@ page import="com.mycompany.isp392.user.UserDTO" %>
<%@ page import="com.mycompany.isp392.support.ProcessSupportDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Support</title>
    </head>
    <body>
        <%
//            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
//            if (loginUser == null || 3 != loginUser.getRoleID()) {
//                response.sendRedirect("US_SignIn.jsp");
//                return;
//            }
            SupportDTO support = (SupportDTO) request.getAttribute("SUPPORT");
            UserDTO user = (UserDTO) request.getAttribute("USER");
            ProcessSupportDTO process = (ProcessSupportDTO) request.getAttribute("PROCESS_SUPPORT");
            if (support == null) {
                response.sendRedirect("shopStaff.jsp");
                return;
            }
        %>
        From Email: 
        <input type="email" name="email" value="<%= user.getEmail()%>"></br>
        Content: 
        <input type="text" name="content" value="<%= support.getRequestMessage()%>"></br>
        Reply Message:
        <input type="text" name="responseMessage" value="<%= process.getResponseMessage()%>"></br>

    </body>
</html>
