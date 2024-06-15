<%-- 
    Document   : addProduct
    Created on : Jun 4, 2024, 9:15:30 AM
    Author     : TTNHAT
--%>

<%@page import="com.mycompany.isp392.promotion.PromotionError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Promotion</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
     <%
            PromotionError promotionError = (PromotionError) request.getAttribute("PROMOTION_ERROR");
            if(promotionError==null) promotionError = new PromotionError();
        %>
    <h2>Add Promotion Information</h2>
    <form action="MainController" method="POST">
        Promotion Name: <input type="text" name="promotionName" required=""/><%=promotionError.getPromotionNameError()%><br/>
        Start Date: <input type="date" name="startDate" required=""/><br/>
        End Date: <input type="date" name="endDate" required=""/><%= promotionError.getEndDateError()%><br/>
        Discount Percentage: <input type="number" name="discountPer" required="" min="1" max="100"/><%= promotionError.getDiscountPerError()%><br/>
        Condition: <input type="number" name="condition" required=""><%= promotionError.getConditionError()%><br/>
        <input type="submit" name="action" value="Add_Promotion" required=""/>
        <input type="reset" value="Reset" required=""/>
    </form>
</body>
</html>
