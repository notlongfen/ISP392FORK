<%-- 
    Document   : Checkout
    Created on : May 29, 2024, 6:50:57 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.isp392.user.*"%>
<%@page import="com.mycompany.isp392.cart.*"%>
<%@page import="com.mycompany.isp392.product.*"%>
<%@page import="com.mycompany.isp392.promotion.*"%>
<%@ page import="java.util.List" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkout</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #fff;
            }

            .card img {
                max-height: 80px; /* Change this value to make the image smaller */
            }

            .card-body {
                padding: 0.5rem;
            }

            .list-group-item {
                background-color: #ffffff;
            }

            input.btn:hover {
                background: grey;
                color: white;
            }

            #coupon {
                width: 300px; /* Set the desired width */
            }

            #checkout-form input, #checkout-form textarea, #checkout-form input.btn {
                color: white;
                background-color: black;
                border-color: black;
            }

            input {
                color: black;
            }

            .form-check-input:checked {
                background-color: black;
                border-color: black;
            }

            hr {
                border: 0;
                height: 4px;
                background: black;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.5);
            }

            .form-control {
                box-shadow: 0 4px 8px -4px rgba(0, 0, 0, 0.3);
            }
            .no-borders .list-group-item {
                border: none;
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
        <form action="MainController" method="POST" id="checkout-form">
            <div class="container mt-5" style="margin-top: 20px; margin-bottom: 20px;">
                <h1 class="text-center mb-5 border-bottom " style="font-weight: bold">Checkout</h1>
                <div class="row">
                    <div class="col-md-6">

                        <h2>Billing Info</h2>
                        <hr>
                        <div class="mb-3">
                            <label for="name" class="form-label">Name*</label>
                            <%  
                                String name = (String) request.getAttribute("name");
                                if ( name !=null){  %>
                            <input type="text" style="color: black;" class="form-control" id="name" name="name" value="<%= name %>" required>
                            <% } else if  ( loginUser.getUserName() != null ){ %>
                            <input type="text" style="color: black;" class="form-control" id="name" name="name" value="<%= loginUser.getUserName() %>" required>
                            <% } else { %>
                            <input type="text" style="color: black;" class="form-control" id="name" name="name" value="" required>
                            <% } %>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone*</label>
                            <%  
                                String phoneNumber = (String)  request.getAttribute("phoneNumber");
                                if ( phoneNumber != null){  
                            %>
                            <input type="text" style="color: black;" class="form-control" id="phone" name="phone" value="<%= phoneNumber %>" required>
                            <% } else if (loginUser.getPhone() != 0){ %>
                            <input type="text" style="color: black;" class="form-control" id="phone" name="phone" value="<%= loginUser.getPhone() %>" required>
                            <% } else { %>
                            <input type="text" style="color: black;" class="form-control" id="phone" name="phone" value="" required>
                            <% } %>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address*</label>
                            <%  
                                String address = (String) request.getAttribute("address");
                                if( address !=null){ 
                            %>
                            <input type="text" style="color: black;" class="form-control" id="address" name="address" value="<%= address %>" required>
                            <% } else if  (loginUser.getAddress() != null){ %>
                            <input type="text" style="color: black;" class="form-control" id="address" name="address" value="<%= loginUser.getAddress() %>" required>
                            <% } else { %>
                            <input type="text" style="color: black;" class="form-control" id="address" name="address" value="" required>
                            <% } %>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="ward" class="form-label">Ward</label>
                                <% 
                                    String ward = (String) request.getAttribute("ward");
                                    if ( ward != null ){ 
                                %>
                                <input type="text" style="color: black;" class="form-control" id="ward" name="ward" value="<%= ward %>" required>
                                <% } else if (loginUser.getWard() != null ){ %>
                                <input type="text" style="color: black;" class="form-control" id="ward" name="ward" value="<%= loginUser.getWard() %>" required>
                                <% } else { %>
                                <input type="text" style="color: black;" class="form-control" id="ward" name="ward" value="" required>
                                <% } %>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="district" class="form-label">District</label>
                                <% 
                                    String district = (String) request.getAttribute("district");
                                    if ( district != null ){ 
                                %>
                                <input type="text" style="color: black;" class="form-control" id="district" name="district" value="<%= district %>" required>
                                <% } else if  (  loginUser.getDistrict() != null ){ %>
                                <input type="text" style="color: black;" class="form-control" id="district" name="district" value="<%= loginUser.getDistrict() %>" required>
                                <% } else { %>
                                <input type="text" style="color: black;" class="form-control" id="district" name="district" value="" required>
                                <% } %>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="city" class="form-label">City</label>
                                <% 
                                    String city = (String) request.getAttribute("city");
                                    if ( city != null ){ 
                                %>
                                <input type="text" style="color: black;" class="form-control" id="city" name="city" value="<%= city %>" required>
                                <% } else if  (loginUser.getCity() != null ){ %>
                                <input type="text" style="color: black;" class="form-control" id="city" name="city" value="<%= loginUser.getCity() %>" required>
                                <% } else { %>
                                <input type="text" style="color: black;" class="form-control" id="city" name="city" value="" required>
                                <% } %>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="note" class="form-label">Note</label>
                            <% 
                                   String note = (String) request.getAttribute("note");
                                   if ( note != null ){ 
                            %>
                            <input type="text" style="color: black;" class="form-control" id="note" name="note" value="<%= note %>">
                            <% } else { %>
                            <textarea class="form-control" id="note" style="color: black;" name="note"></textarea>
                            <% } %>
                        </div>

                    </div>
                    <div class="col-md-6 ">
                        <h2>Your Payment Detail</h2>
                        <%  
                            List<CartDetailsDTO> cartList = (List<CartDetailsDTO>) request.getAttribute("CART_CHECKOUT");
                            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                            for(int i=0; i<cartList.size(); i++){
                        %>
                        <div class="border p-3">

                            <div class="card mb-3" style="border: none">
                                <div class="row g-0">
                                    <div class="col-md-4">
                                        <% 
                                            String[] images = cartList.get(i).getImage().split(";"); // Assuming images are stored as a semicolon-separated string
                                            if (!images[0].trim().isEmpty()) { 
                                        %>
                                        <img src="<%= images[0] %>" class="img-fluid rounded-start" alt="imgage">
                                        <% 
                                                }
                                        %>
                                    </div>
                                    <div class="col-md-8" style="border: none">
                                        <div class="card-body">
                                            <h5 class="card-title"><%= cartList.get(i).getProductName() %> </h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text " style="color: red; font-weight: 700"><%= formatter.format(cartList.get(i).getPrice()) %></p>
                                                <p style="color: grey;">Size: <%= cartList.get(i).getSize() %></p>
                                                <p style="color: grey; padding-right: 20px;">Quantity: <%= cartList.get(i).getQuantity() %></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>    
                            <% } %>

                            <div class="mb-3" >
                                <label for="coupon" class="form-label" >You have a coupon? Click here to enter your code</label>
                                <div class="form-inline">
                                    <% if (request.getAttribute("PROMOTION_NAME") ==null){ %>
                                    <input type="text" class="form-control mb-2 mr-sm-2" id="coupon" name="promotionName" value="">
                                    <input type="submit" class="btn btn-primary mb-2" name="action" value="Check Promotion">
                                    <% }else{ %>
                                    <input type="text" class="form-control mb-2 mr-sm-2" id="coupon" name="promotionName" value="<%=request.getAttribute("PROMOTION_NAME")%>">
                                    <input type="submit" class="btn btn-primary mb-2" name="action" value="Check Promotion">
                                    <% }%>
                                </div>

                            </div>
                            <%  
                                CartDTO cart = (CartDTO) request.getAttribute("CART_INFO");
                                Double originalPrice = (Double) request.getAttribute("CART_TOTAL_PRICE");
                                Double finalPrice = (Double) request.getAttribute("CART_FINAL_PRICE");
                           
                            %>
                            <ul class="list-group mb-3 no-borders">
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Cart Subtotal:</span>
                                    <% if (cart != null){%>
                                    <strong><%= formatter.format(cart.getTotalPrice()) %></strong>
                                    <% }else{ %>
                                    <strong><%= formatter.format(originalPrice) %></strong>
                                    <% } %>
                                </li>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Shipping:</span>
                                    <strong style="color: yellow">40.000 VNĐ</strong>
                                </li>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Discount:</span>
                                    <% 
                                        if (originalPrice != null && finalPrice != null) {
                                            double discountMoney = originalPrice - (finalPrice - 40000) ;
                                    %>
                                    <strong style="color: grey"><%= formatter.format(discountMoney) %> </strong>
                                    <% }else{ %>
                                    <strong style="color: grey">0 VNĐ</strong>
                                    <% } %>
                                </li>
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>Order Total:</span>
                                    <% if (finalPrice == null && originalPrice ==null) {
                                        double price = cart.getTotalPrice() + 40000;
                                    %>
                                    <strong style="color: red; font-weight: bold;"><%= formatter.format(price) %> </strong>
                                    <% }else{ %>
                                    <strong style="color: red; font-weight: bold;"><%= formatter.format(finalPrice) %> </strong>
                                    <% } %>

                                </li>
                            </ul>

                            <div class="form-check mb-3">
                                <p style="color: red">Ensure that the above information is completely correct </p>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-auto">
                                    <input type="submit" class="btn mb-5" name="action" value="Place Order"></input>
                                </div>
                            </div>
                        </div> 
                    </div> 
                </div> 
            </div>
        </form>
        <!-- Modal -->
        <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title" id="errorModalLabel">Error</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">

                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul class="list-group list-group-flush">
                            <% PromotionError pe = (PromotionError) request.getAttribute("PROMOTION_ERROR");
                           if (pe != null && !pe.getConditionError().isEmpty()) { %>
                            <li class="list-group-item list-group-item-danger"><%= pe.getConditionError() %></li>
                                <% } %>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="US_footer.jsp" %>
        <%@include file="US_RequestSupport.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var hasError = <%= (request.getAttribute("PROMOTION_ERROR") != null ? "true" : "false") %>;
                if (hasError) {
                    var errorModal = new bootstrap.Modal(document.getElementById('errorModal'), {});
                    errorModal.show();
                }
            });
        </script>
    </body>
</html>
