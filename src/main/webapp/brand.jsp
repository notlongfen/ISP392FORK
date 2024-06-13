<%@ page import="com.mycompany.isp392.brand.BrandDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand Management</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-5">Brand Management</h1>
            <h2>Add New Brand</h2>
            <form action="MainController" method="POST">
                <div class="form-group">
                    <label for="brandName">Brand Name:</label>
                    <input type="text" class="form-control" name="brandName" required>
                    <button type="submit" class="btn btn-info mt-2" name="action" value="Add_Brand">Add</button>
                    <% if (request.getAttribute("MESSAGE") != null) { %>
                        <div class="alert alert-info"><%= request.getAttribute("MESSAGE") %></div>
                    <% } %>
                </div>
            </form>
            
            <!-- Search Brands -->
            <h2>Search Brands</h2>
            <form action="MainController" method="GET">
                <div class="form-group">
                    <input type="text" class="form-control" name="brandName" placeholder="Enter brand name to search" >
                    <button type="submit" class="btn btn-info mt-2" name="action" value="Search_Brand">Search</button>
                </div>
            </form>

            <!-- Brands Table -->
            <table class="table table-bordered mt-3">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Brand Name</th>
                        <th>Status</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<BrandDTO> brands = (List<BrandDTO>) request.getAttribute("brands");
                    if (brands != null) {
                        for (BrandDTO brand : brands) {
                    %>
                        <tr>
                            <form action="MainController" method="POST">
                            <td><%= brand.getBrandID() %></td>
                            <td><input type="text" name="brandName" value="<%= brand.getBrandName() %>"></td>
                            <td><%= brand.getStatus() == 1 ? "Available" : "Deleted" %></td>
                            <td>                               
                                    <input type="hidden" name="brandID" value="<%= brand.getBrandID() %>">
                                    <input type="hidden" name="action" value="Edit_Brand">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                            </td>
                            <td>
                                <% if (brand.getStatus() == 1) { %>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="brandID" value="<%= brand.getBrandID() %>">
                                        <input type="hidden" name="action" value="Delete_Brand">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                <% } else { %>
                                    <button class="btn btn-secondary disabled">Not Available</button>
                                <% } %>
                            </td>
                        </tr>
                    <% 
                        }
                    } else {
                    %>
                        <tr>
                            <td colspan="5" class="text-center">No brands found</td>
                        </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
