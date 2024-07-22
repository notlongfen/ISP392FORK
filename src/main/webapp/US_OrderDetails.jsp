<%-- 
    Document   : OrderDetail
    Created on : Jun 2, 2024, 9:37:46 AM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.user.*"%>
<%@page import="com.mycompany.isp392.order.*"%>
<%@ page import="java.util.List" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Detail</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .order-item {
                display: flex;
                justify-content: space-between;
                padding: 10px 0;
            }
            .order-item img {
                max-width: 150px;
                height: 150px;
            }
            .order-status {
                /*border-left: 2px solid #ddd;*/
                padding-left: 20px;
                background-color: #fff;

            }

            .order-status-container {
                max-height: 600px; /* Increased height */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
                padding: 20px;
                background-color: #fff;
                overflow-y: auto;
                margin-top: 30px;
            }

            .order-status .status-item {
                position: relative;
                padding: 10px 20px;
                margin-bottom: 40px; /* Adjust this value to increase or decrease spacing */
                background-color: #f9f9f9;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .order-status .status-item::before {
                content: '';
                position: absolute;
                left: -11px;
                top: 0;
                width: 20px;
                height: 20px;
                border-radius: 50%;
                background-color: #28a745;
                border: 2px solid #fff;
            }
            .order-status .status-item::after {
                content: '';
                position: absolute;
                left: -1px;
                top: 20px;
                width: 2px;
                height: calc(100% + 50px);
                background-color: #ddd;
            }
            .order-status .status-item:last-child::after {
                display: none;
            }
            .order-status .status-item.cancel::before {
                background-color: #666666;
            }
            .order-status .status-item.pending::before {
                background-color: #cc0000;
            }
            .order-status .status-item.in-process::before {
                background-color: #ffc107;
            }
            .order-status .status-item.delivering::before {
                background-color: #3399ff;
            }
            .order-status .status-item.completed::before {
                background-color: #009900;
            }

            .order-info {
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); /* Điều chỉnh độ mờ và độ đậm của shadow tại đây */
            }

            .infor_day{
                color: grey;
                font-style: italic;
                margin: 0;
            }
            p{
                padding: 0;
                margin: 0;
            }
        </style>
    </head>
    <body>
        <%
            CustomerDTO loginUser = (CustomerDTO) session.getAttribute("CUST");  
            if (loginUser == null) {
                return;
            }           
        %>

        <%@include file="US_header.jsp" %>
        <div class="container mt-5" >
            <div class="row">

                <div class="col-md-7"  style="margin-top: 30px;">
                    <%
                        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                        OrderDTO order = (OrderDTO) request.getAttribute("DETAILS_OF_ORDER");
                        List<OrderDetailsDTO> orderItems = (List<OrderDetailsDTO>) request.getAttribute("ITEMS_OF_ORDER");
                        List<OrderDetailsDTO> orderDetails = (List<OrderDetailsDTO>) request.getAttribute("DETAILS_OF_ORDER_DETAILS");
                        List<ManageOrderDTO> orderStatus = (List<ManageOrderDTO>) request.getAttribute("STATUS_OF_ORDER");
                        double cartTotalPrice = (double) request.getAttribute("CART_TOTAL_PRICE");
                    %>
                    <div style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px; padding: 20px;">
                        <h3 style="padding: 15px 15px 10px;">Order items</h3>
                        <hr>
                        <% 
                                if (order !=null){
                                    for (int i=0;i < orderDetails.size(); i++ ){
                        %>
                        <div class="order-item border-bottom align-items-center" style="padding: 15px 0;">
                            <div class="d-flex align-items-center">
                                <%
                                    if(orderItems.get(i).getImage().contains(";")){
                                    String[] images = orderItems.get(i).getImage().split(";");     
                                %>
                                <img src="<%= images[1]%>" alt="Item image" style="margin-right: 20px;">
                                <% } %>
                                <div>
                                    <h6 style="margin-bottom: 5px;"> <%= orderItems.get(i).getProductName() %></h6>
                                    <p style="margin-bottom: 0;"><%= orderItems.get(i).getSize() %>  Size: <%= orderItems.get(i).getSize() %> Quantity: <%= orderDetails.get(i).getQuantity() %></p>
                                </div>
                            </div>
                        </div>
                        <% } } %>
                        <div class="d-flex justify-content-between" style="padding: 15px 0;">
                            <div>
                                <h4>Order note</h4>
                                <% if (order.getNote() !=null) { %>
                                <p><%= order.getNote()%></p>
                                <% }else{ %>
                                <p></p>
                                <% } %>
                            </div>
                            <div class="mt-3">
                                <p style="margin: 5px; ">Cart Subtotal: <span style="color: #000; font-weight: bold;"><%= formatter.format(cartTotalPrice) %></span></p>
                                <p style="margin: 5px; ">Shipping: <span style="color: #cccc00; font-weight: bold;">40.000 đ</span></p>
                                <% if ((cartTotalPrice + 40000) == order.getTotal()){ %>
                                <p style="margin: 5px;">Discount: <span style="color: grey; font-weight: bold;">0 đ</span></p>
                                <% } else { %>
                                <p style="margin: 5px;">Discount: <span style="color: grey; font-weight: bold;"><%= formatter.format(order.getTotal() - cartTotalPrice - 40000) %></span></p>
                                <% } %>
                                <hr style="margin: 10px 0;">
                                <h5>Order Total: <%= formatter.format(order.getTotal()) %></h5>
                            </div>
                        </div>
                    </div>

                    <div class="mt-3">
                        <div class="row mb-5">
                            <div class="order-info">
                                <h5>Customer Information</h5>
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <p><i class="fas fa-user"></i> <%= order.getUserName() %></p>
                                        <p><i class="fas fa-envelope"></i> <%= loginUser.getEmail() %></p>
                                    </div>
                                    <div>
                                        <p><i class="fas fa-phone"></i><%= order.getPhone() %></p>
                                        <p><i class="fas fa-map-marker-alt"></i>
                                            <%=order.getAddress()%>,<%=order.getWard()%>,<%=order.getDistrict()%>,<%=order.getCity()%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-md-4 ms-3 order-status-container">
                    <h3>Order Status</h3>
                    <hr>

                    <div class="order-status">
                        <div class="status-item pending">
                            <p>Pending</p>
                            <p class="infor_day"><%=order.getOrderDate()%></p>
                        </div>
                        <% 
                        if (orderStatus !=null){
                            for(int i =0; i < orderStatus.size(); i++ ){
                        %>
                        <% if (orderStatus.get(i).getLoadNewField().equals("[0]")) { %>
                        <div class="status-item cancel  ">
                            <p>Canceled</p>
                            <p class="infor_day"><%= orderStatus.get(i).getChangeDate() %></p>
                        </div>
                        <% } if (orderStatus.get(i).getLoadNewField().equals("[2]")) { %>
                        <div class="status-item in-process">
                            <p>In Processing</p>
                            <p class="infor_day"><%= orderStatus.get(i).getChangeDate() %></p>
                        </div>
                        <% } if (orderStatus.get(i).getLoadNewField().equals("[3]")) { %>
                        <div class="status-item delivering ">
                            <p>Delivering</p>
                            <p class="infor_day"><%= orderStatus.get(i).getChangeDate() %></p>
                        </div>
                        <% } if (orderStatus.get(i).getLoadNewField().equals("[4]")){ %>
                        <div class="status-item completed  ">
                            <p>Completed</p>
                            <p class="infor_day"><%= orderStatus.get(i).getChangeDate() %></p>
                        </div>
                        <% } %>
                        <% } }  %>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="US_footer.jsp" %>
    </body>
</html>
