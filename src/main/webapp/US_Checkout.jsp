<%-- 
    Document   : Checkout
    Created on : May 29, 2024, 6:50:57 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            button.btn:hover {
                background: grey;
                color: white;
            }

            input, textarea, button.btn {
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
        <%@include file="US_header.jsp" %>
        <div class="container mt-5" style="margin-top: 20px; margin-bottom: 20px;">
            <h1 class="text-center mb-5 border-bottom " style="font-weight: bold">Checkout</h1>
            <div class="row">
                <div class="col-md-6">
                    <h2>Billing Info</h2>
                    <hr >
                    <form id="billing-form">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name*</label>
                            <input type="text" style="color: black;" class="form-control" id="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone*</label>
                            <input type="text" style="color: black;" class="form-control" id="phone" required>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address*</label>
                            <input type="text" style="color: black;" class="form-control" name="" id="address" required>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="ward" class="form-label">Ward</label>
                                <input type="text" style="color: black;" class="form-control" id="ward" value="Phường 13">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="district" class="form-label">District</label>
                                <input type="text" style="color: black;" class="form-control" id="district" value="Quận 1">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="city" class="form-label">City</label>
                                <input type="text" style="color: black;" class="form-control" id="city" value="Hồ Chí Minh">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="note" class="form-label">Note</label>
                            <textarea class="form-control" id="note" style="color: black;"></textarea>
                        </div>
                    </form>
                </div>
                <div class="col-md-6 ">
                    <h2>Your Payment Detail</h2>


                    <div class="border p-3">

                        <div class="card mb-3" style="border: none">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="images/product_6.png" class="img-fluid rounded-start" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'">
                                </div>
                                <div class="col-md-8" style="border: none">
                                    <div class="card-body">
                                        <h5 class="card-title">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                        <div class="d-flex justify-content-between">
                                            <p class="card-text " style="color: red; font-weight: 700">$5,000</p>
                                            <p style="color: grey;">Size: EU 42</p>
                                            <p style="color: grey; padding-right: 20px;">Quantity: 1</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card mb-3" style="border: none">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="images/product_8.png" class="img-fluid rounded-start" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                        <div class="d-flex justify-content-between">
                                            <p class="card-text " style="color: red; font-weight: 700">$5,000</p>
                                            <p style="color: grey;">Size: EU 42</p>
                                            <p style="color: grey; padding-right: 20px;">Quantity: 1</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="card mb-3" style="border: none">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="images/product_5.png" class="img-fluid rounded-start" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                        <div class="d-flex justify-content-between">
                                            <p class="card-text " style="color: red; font-weight: 700">$5,000</p>
                                            <p style="color: grey;">Size: EU 42</p>
                                            <p style="color: grey; padding-right: 20px;">Quantity: 1</p>
                                        </div>
                                    </div>                            
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3" >
                        <label for="coupon" class="form-label" >You have a coupon? Click here to enter your code</label>
                        <input type="text" class="form-control" id="coupon">
                    </div>
                    <ul class="list-group mb-3 no-borders">
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Cart Subtotal:</span>
                            <strong>$15,000</strong>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Shipping:</span>
                            <strong style="color: yellow">$2</strong>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Discount:</span>
                            <strong style="color: grey">-$3,000</strong>
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Order Total:</span>
                            <strong style="color: red; font-weight: bold;">$12,000</strong>
                        </li>
                    </ul>
                    <div class="form-check mb-3">
                        <input type="checkbox" class="form-check-input" id="confirm-info" name="" required>
                        <label class="form-check-label" for="confirm-info" >I confirm that delivery information is correct</label>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col-auto">
                            <button type="submit" form="billing-form" class="btn mb-5">Place Order</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="US_footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <script>
            document.getElementById('billing-form').addEventListener('submit', function (event) {
                event.preventDefault();
                if (document.getElementById('confirm-info').checked) {
                    alert('Order placed successfully!');
                } else {
                    alert('Please confirm that the delivery information is correct.');
                }
            });
        </script>
    </body>
</html>
