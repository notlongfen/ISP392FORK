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
                <th>Update Status</th>
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
                        <td>
                            <select name="status" class="form-control">
                                <option value="0" <%= (order.getStatus() == 0) ? "selected" : "" %>>Cancelled</option>
                                <option value="1" <%= (order.getStatus() == 1) ? "selected" : "" %>>Confirming</option>
                                <option value="2" <%= (order.getStatus() == 2) ? "selected" : "" %>>Delivering</option>
                                <option value="3" <%= (order.getStatus() == 3) ? "selected" : "" %>>Completed</option>
                            </select>
                        </td>
                        <td><%= order.getTotal() %></td>
                        <td><%= order.getOrderDate() %></td>
                        <td><%= order.getCustID() %></td>
                        <td><%= order.getPromotionID() %></td>
                        <td><%= order.getCartID() %></td>
                        <td>
                            <input type="hidden" name="orderID" value="<%= order.getOrderID() %>">
                            <input type="hidden" name="action" value="Edit_Order">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                        </td>
                    </tr>
            <% 
                }
            } else {
            %>
                <tr>
                    <td colspan="8" class="text-center">No orders found</td>
                </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
