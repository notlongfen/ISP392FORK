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
            <button type="submit" class="btn btn-info mt-2" name="action" value="Search_Category">Search</button>
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
                <th>Edit</th>
                <th>Delete</th>
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
                        <form action="MainController" method="POST">
                            <td><%= category.getCategoryID() %></td>
                            <td><input type="text" name="categoryName" value="<%= category.getCategoryName() %>"></td>
                            <td><input type="text" name="description" value="<%= category.getDescription() %>"></td>
                            <td><%= category.getStatus() == 1 ? "Active" : "Inactive" %></td>
                            <td>
                                <input type="hidden" name="categoryID" value="<%= category.getCategoryID() %>">
                                <input type="hidden" name="action" value="Edit_Category">
                                <button type="submit" class="btn btn-primary">Edit</button>
                            </td>
                            <td>
                                <% if (category.getStatus() == 1) { %>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="categoryID" value="<%= category.getCategoryID() %>">
                                        <input type="hidden" name="action" value="Delete_Category">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                <% } else { %>
                                    <button class="btn btn-secondary disabled">Not Available</button>
                                <% } %>
                            </td>
                        </form>
                        <td>
                            <% for (ChildrenCategoryDTO child : children) { %>
                                <div>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="cdCategoryID" value="<%= child.getCdCategoryID() %>">
                                        <input type="text" name="categoryName" value="<%= child.getCategoryName() %>">
                                        <input type="hidden" name="action" value="Edit_ChildrenCategory">
                                        <button type="submit" class="btn btn-primary">Edit</button>
                                        <% if (child.getStatus() == 1) { %>
                                            <input type="hidden" name="action" value="Delete_ChildrenCategory">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        <% } else { %>
                                            <button class="btn btn-secondary disabled">Not Available</button>
                                        <% } %>
                                    </form>
                                </div>
                            <% } %>
                        </td>
                    </tr>
            <% 
                }
            } else {
            %>
                <tr>
                    <td colspan="7" class="text-center">No categories found</td>
                </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
