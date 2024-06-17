<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.isp392.support.SupportDTO" %>
<%@ page import="com.mycompany.isp392.user.UserDTO" %>
<%@ page import="java.sql.Date" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Support Requests</title>
    </head>
    <body>
        <%
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Support Requests</h1>
        <form action="SearchSupportController" method="POST">
            Search: <input type="text" name="search" value="<%= search %>"/>
            <input type="submit" name="action" value="Search support">
        </form>

        <%
            List<SupportDTO> supportList = (List<SupportDTO>) request.getAttribute("SUPPORT_LIST");
            if (supportList != null && !supportList.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Contact</th>
                <th>Send Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <%
                for (SupportDTO support : supportList) {
                    UserDTO user = (UserDTO) request.getAttribute("user_" + support.getSupportID());
                    if (user != null) {
            %>
            <tr>
                <td><%= user.getUserName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPhone() %></td>
                <td><%= support.getRequestDate() %></td>
                <td><%= support.getStatus() == 1 ? "View" : "Reply" %></td>
                <td>
                    <% if (support.getStatus() == 1) { %>
                        <form action="ViewSupportDetailsController" method="GET">
                            <input type="hidden" name="supportID" value="<%= support.getSupportID() %>"/>
                            <input type="submit" value="ViewSupport"/>
                        </form>
                    <% } else { %>
                        <form action="ReplySupportController" method="POST">
                            <input type="hidden" name="supportID" value="<%= support.getSupportID() %>"/>
                            <input type="submit" value="ReplySupport"/>
                        </form>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <%
            }
        %>
    </body>
</html>
