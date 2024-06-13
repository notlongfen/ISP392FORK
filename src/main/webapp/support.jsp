<%-- 
    Document   : support
    Created on : Jun 10, 2024, 6:02:03 PM
    Author     : notlongfen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.mycompany.isp392.support.SupportDTO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="text" name="search" placeholder="Search for support request">
            <input type="submit"name="action" value="Search support">
        </form>
        <%
           List<SupportDTO> spdto = (List<SupportDTO>) session.getAttribute("SUPPORT_LIST");
           String test = "true";
        %>
        <%
        if(spdto.size() > 0){
        for(int i =0; i< spdto.size(); i++){
                System.out.println(spdto.get(i)); %>
        <%= spdto.get(i).getCustID()%>
        <%
    }
}else{
        %>
<%= test%>
        <%
        }
        %>
    </body>
</html>
