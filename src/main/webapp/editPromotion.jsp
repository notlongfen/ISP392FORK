<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.user.UserDTO"%>
<%@page import="com.mycompany.isp392.promotion.PromotionDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Promotion</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || 2 != loginUser.getRoleID()) {
                response.sendRedirect("login.jsp");
                return;
            }
            PromotionDTO promotion = (PromotionDTO) request.getAttribute("PROMOTION");
            if (promotion == null) {
                response.sendRedirect("shopManager.jsp");
                return;
            }
        %>
        <h1>Edit Promotion</h1>
        <form action="MainController" method="post">
            Promotion ID:
            <input type="hidden" name="promotionID" value="<%= promotion.getPromotionID() %>" readonly=""/>
            Promotion Name:
            <input type="text" name="promotionName" value="<%= promotion.getPromotionName() %>" required=""/><br/>
            Start Date:
            <input type="date" name="startDate" value="<%= promotion.getStartDate() %>" required=""/><br/>
            End Date:
            <input type="date" name="endDate" value="<%= promotion.getEndDate() %>" required=""/><br/>
            Discount (%):
            <input type="number" name="discountPer" value="<%= promotion.getDiscountPer() %>" required=""/><br/>
            Condition:
            <input type="number" name="condition" value="<%= promotion.getCondition() %>" required=""/><br/>
            Status: 
            <input type="number" name="status" value="<%= promotion.getStatus() %>" required=""/><br/>
            <input type="submit" name="action" value="SaveEditPromotion"/>

        </form>
    </body>
</html>
