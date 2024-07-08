<%-- 
    Document   : OrderDetail
    Created on : Jun 2, 2024, 9:37:46 AM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Interface</title>
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
            .order-status .status-item.in-process::before {
                background-color: #17a2b8;
            }
            .order-status .status-item.in-delivery::before {
                background-color: #ffc107;
            }
            .order-status .status-item.delivered::before {
                background-color: #28a745;
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
         <%@include file="US_header.jsp" %>
        <div class="container mt-5" >
            <div class="row">

                <div class="col-md-7"  style="margin-top: 30px;">

                    <div style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px; padding: 20px;">
                        <h3 style="padding: 15px 15px 10px;">Order items</h3>
                        <hr>
                        <div class="order-item border-bottom align-items-center" style="padding: 15px 0;">
                            <div class="d-flex align-items-center">
                                <img src="images/product_7.png" alt="Item image" style="margin-right: 20px;">
                                <div>
                                    <h6 style="margin-bottom: 5px;">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h6>
                                    <p style="margin-bottom: 0;">Red Size: EU 42 Quantity: 1</p>
                                </div>
                            </div>
                            <div class="text-danger" style="margin-left: auto; font-weight: bold;">$5,000</div>
                        </div>
                        <div class="order-item border-bottom align-items-center" style="padding: 15px 0;">
                            <div class="d-flex align-items-center">
                                <img src="images/product_8.png" alt="Item image" style="margin-right: 20px;">
                                <div>
                                    <h6 style="margin-bottom: 5px;">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h6>
                                    <p style="margin-bottom: 0;">Red Size: EU 42 Quantity: 1</p>
                                </div>
                            </div>
                            <div class="text-danger" style="margin-left: auto; font-weight: bold;">$5,000</div>
                        </div>
                        <div class="order-item border-bottom align-items-center" style="padding: 15px 0;">
                            <div class="d-flex align-items-center">
                                <img src="images/product_5.png" alt="Item image" style="margin-right: 20px;">
                                <div>
                                    <h6 style="margin-bottom: 5px;">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h6>
                                    <p style="margin-bottom: 0;">Red Size: EU 42 Quantity: 1</p>
                                </div>
                            </div>
                            <div class="text-danger" style="margin-left: auto; font-weight: bold;">$5,000</div>
                        </div>
                        <div class="d-flex justify-content-between" style="padding: 15px 0;">
                            <div>
                                <h4>Order note</h4>
                                <p></p>
                            </div>
                            <div class="mt-3">
                                <p style="margin: 5px; ">Cart Subtotal: <span style="color: #000; font-weight: bold;">$5000</span></p>
                                <p style="margin: 5px; ">Shipping: <span style="color: #cccc00; font-weight: bold;">$2</span></p>
                                <p style="margin: 5px;">Discount: <span style="color: grey; font-weight: bold;">-$3,000</span></p>
                                <hr style="margin: 10px 0;">
                                <h5>Order Total: $12,000</h5>
                            </div>
                        </div>
                    </div>


                    <div class="mt-3">
                        <div class="row mb-5">
                            <div class="order-info">
                                <h5>Customer Information</h5>
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <p><i class="fas fa-user"></i> Nguyen Van A</p>
                                        <p><i class="fas fa-envelope"></i> abcde@gmail.com</p>
                                    </div>
                                    <div>
                                        <p><i class="fas fa-phone"></i> 09012345678</p>
                                        <p><i class="fas fa-map-marker-alt"></i> 111 Duong Canh La, Phuong Canh Cay, Quan Go May, TP HCM</p>
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
                        <div class="status-item in-process ">
                            <p>In Process</p>
                            <p class="infor_day">29/05/2024 13:20:11</p>
                        </div>
                        <div class="status-item in-delivery">
                            <p>In Delivery</p>
                            <p class="infor_day" >30/05/2024 09:31:11</p>
                        </div>
                        <div class="status-item delivered">
                            <p>Successfully Delivered</p>
                            <p class="infor_day" >31/05/2024 12:20:11</p>
                        </div>
                    </div>
                </div>


            </div>
        </div>
         <%@include file="US_footer.jsp" %>
    </body>
</html>
