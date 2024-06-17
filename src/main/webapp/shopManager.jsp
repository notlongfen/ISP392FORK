<%-- 
    Document   : admin
    Created on : Jun 5, 2024, 8:46:37 AM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.user.UserDTO"%>
<%@page import="com.mycompany.isp392.promotion.PromotionDTO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || 2 != loginUser.getRoleID()) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Welcome: <%= loginUser.getUserName()%></h1>
        </br>

        <form action="MainController">
            Search: <input type="text" name="search" value="<%= search%>"/>
            <input type="submit" name="action" value="Search promotion"/>
        </form>
        <%
            List<PromotionDTO> promotionList = (List<PromotionDTO>) request.getAttribute("LIST_PROMOTION");
            if (promotionList != null) {
                if (promotionList.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Promotion ID</th>
                    <th>Promotion Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Discount (%)</th>
                    <th>Condition</th>
                    <th>Action</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (PromotionDTO promotion : promotionList) {
                %>
                <tr>
                    <td><%= count++ %></td>
                    <td><input type="text" name="promotionID" value="<%= promotion.getPromotionID() %>" readonly="" /></td>
                    <td><input type="text" name="promotionName" value="<%= promotion.getPromotionName() %>" readonly=""/></td>
                    <td><input type="text" name="startDate" value="<%= promotion.getStartDate() %>" readonly=""/></td>
                    <td><input type="text" name="endDate" value="<%= promotion.getEndDate() %>" readonly=""/></td>
                    <td><input type="text" name="discountPer" value="<%= promotion.getDiscountPer() %>" readonly=""/></td>
                    <td><input type="text" name="condition" value="Points >= <%= promotion.getCondition() %>" readonly=""/></td>
                    <td><input type="text" name="status" value="<%= promotion.getStatus()%>" readonly=""/></td>
                    <td>
                        
                            <input type="hidden" name="promotionID" value="<%= promotion.getPromotionID() %>"/>
                            <input type="submit" name="action" value="EditPromotion"/>
                    
                    </td>
                    <td>
                        <a href="MainController?action=DeletePromotion&promotionID=<%= promotion.getPromotionID()%>"> Delete</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
                }
            }
        %>

    </body>
</html>
