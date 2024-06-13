<%-- 
    Document   : addChildrenCategory
    Created on : Jun 11, 2024, 5:11:46 PM
    Author     : TTNHAT
--%>

<%@page import="com.mycompany.isp392.category.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Children Category Page</title>
    </head>
    <body>
        <%
            CategoryError cdCategoryError = (CategoryError) request.getAttribute("CHILDREN_CATEGORY_ERROR");
            if (cdCategoryError == null)
                cdCategoryError = new CategoryError();
        %>
        <h1>Add Children Category Information</h1>
        <form action="MainController" method="POST">
            Category Name: <input type="text" name="cdCategoryName" required=""/><%= cdCategoryError.getCdCategoryNameError()%><br/> 
            Parent Category: <select name="parentID" required=""> <%= cdCategoryError.getParentIDError()%>
                <%
                    CategoryDAO dao = new CategoryDAO();
                    List<CategoryDTO> list = (List<CategoryDTO>) dao.getListCategory();
                    if (list != null) {
                        if (list.size() > 0) {
                            for (CategoryDTO category : list) {

                %>
                <option value = <%=category.getCategoryID()%>><%=category.getCategoryName()%></option>
                <%                                
                            }
                        }
                    }
                %>
            </select></br>
        <input type="submit" name="action" value="Add_Children_Category" required=""/>
        <input type="reset" value="Reset" required=""/>
    </form>
</body>
</html>
