<%-- 
    Document   : addCategory
    Created on : Jun 11, 2024, 12:57:04 PM
    Author     : TTNHAT
--%>

<%@page import="com.mycompany.isp392.category.CategoryError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category Page</title>
    </head>
    <body>
        <%
            CategoryError categoryError = (CategoryError) request.getAttribute("CATEGORY_ERROR");
            if(categoryError==null) categoryError = new CategoryError();
        %>
        <h1>Add Category Information</h1>
        <form action="MainController" method="POST">
            Category Name: <input type="text" name="categoryName" required=""/><%= categoryError.getCategoryNameError()%><br/> 
            Description: <textarea name="description" required=""></textarea><%= categoryError.getDescriptionError()%><br/> 
            <input type="submit" name="action" value="Add_Category" required=""/>
            <input type="reset" value="Reset" required=""/>
        </form>
    </body>
</html>
