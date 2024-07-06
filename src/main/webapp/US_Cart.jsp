<%-- 
    Document   : Cart
    Created on : May 29, 2024, 9:33:24 AM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .cart-item img {
                width: 200px;
                height: 200px;
            }
            .cart-item h5 {
                margin-bottom: 0.5rem;
            }
            .cart-item p {
                margin-bottom: 0.25rem;
            }
            .cart-item p {
                border-bottom: none!important;
            }
            
            
            .styled-hr {
                border: 0;
                height: 1px;
                background-color: #999;
                margin: 20px 0;
            }
            .container.mt-5 {
                margin-top: 5rem !important; /* Add more space if needed */
            }
            .no-border {
                border: none;
            }
            .icons {
                display: flex;
                flex-direction: column; /* Hiển thị các biểu tượng theo cột */
                align-items: flex-start; /* Căn trái các biểu tượng */
            }
            .wishlist i {
                color: #696969; /* Màu mặc định của biểu tượng trái tim */
                transition: color 0.3s ease; /* Hiệu ứng chuyển màu mượt */
            }

            .wishlist i:hover {
                color: #C53337; /* Màu khi hover */
            }
            .remove i {
                color: #696969; /* Màu mặc định của biểu tượng trái tim */
                transition: color 0.3s ease; /* Hiệu ứng chuyển màu mượt */
            }

            .remove i:hover {
                color: #C53337; /* Màu khi hover */
            }
            .left {
                margin-left: -15px;
            }
            label {
                font-size: 20px;
            }

        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>
        <div class="container mt-5">
            <div class="row">
                <!-- Cart Items Section -->
                <div class="col-md-8">
                    <h2>Cart</h2>
                    <div class="cart-item d-flex justify-content-between align-items-center py-3">
                        
                        <div class="d-flex align-items-center" >
                            
                            <img src="images/s5.png" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'" class="img-fluid me-3">
                            
                            <div >
                                
                                <h5>Air Jordan 1 x Off-White Retro High OG 'Chicago'</h5>
                                
                                
                                
                                
                               
                                <p class="text-muted mb-0">Shoes</p>
                                <div class="d-flex align-items-center left">
                                    <div class="me-3">
                                        <label class ="col-auto no-border" for="size" class="form-label">Size</label>
                                        <select class="col-auto no-border" id="size" class="form-select">
                                            <option selected>41.5</option>
                                            <option>41</option>
                                            <option>40.5</option>
                                            <option>40</option>
                                            <option>39.5</option>
                                            <option>39</option>
                                            <!-- Add more options if needed -->
                                        </select>
                                    </div>
                                    <div class="me-3">
                                        <label class ="col-auto no-border" for="quantity" class="form-label">Quantity</label>
                                        <select class="col-auto no-border" id="quantity" class="form-select">
                                            <option selected>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>

                                            <!-- Add more options if needed -->
                                        </select>
                                    </div>
                                </div>
                                <div class="text-left">
                                    <a href="#" class="wishlist"><i class="fa fa-heart mr-2"></i></a>
                                    <a href="#"class="remove"><i class="remove-btn fas fa-trash"></i></a>
                                </div>  
                            </div>
                        </div>
                        <p class="text-danger fw-bold text-right" style="font-size: 20px;">$5,000</p>
                    </div>
                    
                    
                     
                    
                    <div class="cart-item d-flex justify-content-between align-items-center py-3 mt-5">
                        <div class="d-flex align-items-center">
                            <img src="images/s5.png" alt="Air Jordan 1 x Off-White Retro High OG 'Chicago'" class="img-fluid me-3">
                            <div>
                                <h5>Jordan 1 Retro High Light Smoke Grey</h5>

                                <p class="text-muted mb-0">Shoes</p>
                                <div class="d-flex align-items-centerleft left" >
                                    <div class="me-3">
                                        <label class ="col-auto no-border" for="size" class="form-label">Size</label>
                                        <select class="col-auto no-border" id="size" class="form-select">
                                            <option selected>41.5</option>
                                            <option>41</option>
                                            <option>40.5</option>
                                            <option>40</option>
                                            <option>39.5</option>
                                            <option>39</option>

                                            <!-- Add more options if needed -->
                                        </select>
                                    </div>
                                    <div class="me-3">
                                        <label class ="col-auto no-border" for="quantity" class="form-label">Quantity</label>
                                        <select class="col-auto no-border" id="quantity" class="form-select">
                                            <option selected>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>

                                            <!-- Add more options if needed -->
                                        </select>
                                    </div>
                                </div>
                                <div class="text-left">
                                    <a href="#" class="wishlist"><i class="fa fa-heart mr-2"></i></a>
                                    <a href="#"class="remove"><i class="remove-btn fas fa-trash"></i></a>
                                </div>  
                            </div>
                        </div>


                        <div class="row text-right" >
                            <p class="text-muted text-decoration-line-through col" style="font-size: 20px;">$1,000</p>
                            <p class="text-danger fw-bold col" style="font-size: 20px;">$500</p>
                        </div>                       

                    </div>
                    <i class="fas fa-clock mb-5 mt-5" style="color: #A5821D; font-weight: normal"></i><span style="color: #A5821D; font-weight: normal;"> Just a few left. Order soon.</span>
                </div>
                <!-- Summary Section -->
                <div class="col-lg-4">
                    <h3>Summary</h3>
                    <div>
                        <div class="row mt-4">
                            <div class="col">
                                <h5 style="font-weight: normal">Total</h5>
                            </div>
                            <div class="col text-right">
                                <h5 style="font-weight: normal">$5.500</h5>
                            </div>
                        </div>
                        <hr class="styled-hr">          
                        <button class="btn btn-dark w-100 mt-1" style="border-radius: 10px; font-size: 20px">Checkout</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Confirmation Modal -->
        <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmModalLabel">Confirm Removal</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to remove this item from the cart?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                        <button type="button" class="btn btn-danger" id="confirmRemove">Yes</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="US_footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', (event) => {
                let targetCartItem;

                document.querySelectorAll('.remove-btn').forEach((button) => {
                    button.addEventListener('click', (event) => {
                        event.preventDefault();
                        targetCartItem = event.target.closest('.cart-item');// Để xác định phần tử mà sự kiện click đã được kích hoạt
                        let confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));
                        confirmModal.show();
                    });
                });

                document.getElementById('confirmRemove').addEventListener('click', () => {
                    if (targetCartItem) {
                        targetCartItem.remove();
                        targetCartItem = null;
                        let confirmModal = bootstrap.Modal.getInstance(document.getElementById('confirmModal'));
                        confirmModal.hide();
                    }
                });
            });
        </script>
    </body>
</html>
