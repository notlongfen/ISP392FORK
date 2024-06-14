<%@ page import="com.mycompany.isp392.category.CategoryDTO, com.mycompany.isp392.category.ChildrenCategoryDTO" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Category Management</h1>
    <h2>Search Categories</h2>
    <form action="MainController" method="GET">
        <div class="form-group">
            <input type="text" class="form-control" name="searchText" placeholder="Enter category name or description to search">
            <button type="submit" class="btn btn-info mt-2" name="action" value="Search_Category">Search
        </div>
    </form>

    <!-- Categories Table -->
    <table class="table table-bordered mt-3">
        <thead class="thead-dark">
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Child Categories</th>
            </tr>
        </thead>
        <tbody>
            <% 
            Map<CategoryDTO, List<ChildrenCategoryDTO>> categoryChildrenMap = (Map<CategoryDTO, List<ChildrenCategoryDTO>>) request.getAttribute("categoryChildrenMap");
            if (categoryChildrenMap != null && !categoryChildrenMap.isEmpty()) {
                for (Map.Entry<CategoryDTO, List<ChildrenCategoryDTO>> entry : categoryChildrenMap.entrySet()) {
                    CategoryDTO category = entry.getKey();
                    List<ChildrenCategoryDTO> children = entry.getValue();
            %>
                    <tr>
                        <td><%= category.getCategoryID() %></td>
                        <td><%= category.getCategoryName() %></td>
                        <td><%= category.getDescription() %></td>
                        <td><%= category.getStatus() == 1 ? "Active" : "Inactive" %></td>
                        <td>
                            <% for (ChildrenCategoryDTO child : children) { %>
                                <div><%= child.getCategoryName() %> (Status: <%= child.getStatus() == 1 ? "Active" : "Inactive" %>)</div>
                            <% } %>
                        </td>
                    </tr>
            <% 
                }
            } else {
            %>
                <tr>
                    <td colspan="5" class="text-center">No categories found</td>
                </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
