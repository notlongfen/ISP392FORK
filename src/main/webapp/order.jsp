<%@ page import="com.mycompany.isp392.order.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Order Management</h1>
    
    <h2>Search Orders</h2>
    <form action="MainController" method="GET">
        <div class="form-group">
            <input type="text" class="form-control" name="searchText" placeholder="Enter order ID, date, total, customer ID, or cart ID to search">
            <button type="submit" class="btn btn-info mt-2" name="action" value="Search_Order">Search</button>
        </div>
    </form>

    <% if (request.getAttribute("MESSAGE") != null) { %>
        <div class="alert alert-info"><%= request.getAttribute("MESSAGE") %></div>
    <% } %>

    <!-- Orders Table -->
    <table class="table table-bordered mt-3">
        <thead class="thead-dark">
            <tr>
                <th>Order ID</th>
                <th>Status</th>
                <th>Total</th>
                <th>Order Date</th>
                <th>Customer ID</th>
                <th>Promotion ID</th>
                <th>Cart ID</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
            if (orders != null && !orders.isEmpty()) {
                for (OrderDTO order : orders) {
            %>
                    <tr>
                        <form action="MainController" method="POST">
                            <td><%= order.getOrderID() %></td>
                            <td><%= order.getStatus() == 1 ? "Active" : "Inactive" %></td>
                            <td><%= order.getTotal() %></td>
                            <td><%= order.getOrderDate() %></td>
                            <td><%= order.getCustID() %></td>
                            <td><%= order.getPromotionID() %></td>
                            <td><%= order.getCartID() %></td>
                            <td>
                                <input type="hidden" name="orderID" value="<%= order.getOrderID() %>">
                                <input type="hidden" name="action" value="Edit_Order">
                                <button type="submit" class="btn btn-primary">Edit</button>
                            </td>
                            <td>
                                <% if (order.getStatus() == 1) { %>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="orderID" value="<%= order.getOrderID() %>">
                                        <input type="hidden" name="action" value="Delete_Order">
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                <% } else { %>
                                    <button class="btn btn-secondary disabled">Not Available</button>
                                <% } %>
                            </td>
                        </form>
                    </tr>
            <% 
                }
            } else {
            %>
                <tr>
                    <td colspan="9" class="text-center">No orders found</td>
                </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
