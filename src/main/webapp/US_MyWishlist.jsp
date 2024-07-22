<%-- 
    Document   : MyWishlist
    Created on : May 30, 2024, 6:57:42 PM
    Author     : jojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Wishlist</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .custom-margin {
                margin-top: 7rem;
            }
            .profile-sidebar {
                background-color: #fff;
                border-radius: 5px;
                /*box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);*/
                margin-bottom: 20px;
                width: 300px;
                height: 500px;
                border: none;
            }

            .no-border {
                border: none;
            }

            .custom-border {
                border: 2px solid black; /* Đặt độ rộng của border thành 2px */
                padding: 20px; /* Thêm padding để tăng diện tích của border */
            }

            .inline {
                display: inline; /* Sử dụng display: inline để các phần tử hiển thị trên cùng một dòng */
                margin-right: 20px; /* Tạo một khoảng cách ngang giữa các phần tử */
                vertical-align: bottom;
            }

            .resized-image {
                width: 1280px;
                height: 1280px;
                object-fit: contain;
            }

            .same-size {
                width: 100px; /* Thiết lập kích thước width của các phần tử cùng một giá trị */
                margin-right: 5px;
            }

            .no-wrap {
                white-space: nowrap;
                margin-left: -80px;
            }

            .wishlist-item {
                border-bottom: 1px solid #e0e0e0;
                padding-bottom: 1rem;
                margin-bottom: 1rem;
            }

            .wishlist-item:last-child {
                border-bottom: none;
            }

            h1 {
                margin-bottom: 2rem;
            }

            .img-fluid {
                max-width: 100%;
                height: auto;
            }

            .text-end .text-decoration-line-through {
                color: #888;
                font-size: 1rem;
            }

            .text-end .fs-4 {
                font-size: 1.5rem;
            }

            .text-muted.small {
                font-size: 0.875rem;
            }
            .nav-link {
                transition: background-color 0.3s, color 0.3s;
                font-size: 18px;
                color: black;
            }

            .nav-link:hover,
            .nav-link.active {
                color: white;
                background-color: black;
            }

            .remove i {
                color: #696969; /* Màu mặc định của biểu tượng trái tim */
                transition: color 0.3s ease; /* Hiệu ứng chuyển màu mượt */
                font-size: 30px;
                margin-top: 20px;
            }

            .remove i:hover {
                color: #C53337; /* Màu khi hover */
            }
        </style>
    </head>
    <body>
        <%@include file="US_header.jsp" %>
        <div class="container-fluid custom-margin">
            <div class="row">

                <!-- Wishlist Section -->
                <div class="col-lg-11 ml-5">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h1>My Wishlist</h1>
                    </div>
                    <%
                                               NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                                List<ProductDetailsDTO> listProduct = (List<ProductDetailsDTO>) session.getAttribute("wishlist");
                                if (listProduct != null && listProduct.size() > 0) {
                                for (ProductDetailsDTO list : listProduct) {
                    %>
                    <div class="wishlist-item row mb-4 d-flex">
                        <div class="col-md-2 text-center">
                            <img src="<%= list.getImage().split(";")[0] %>" alt="Image" class="img-fluid resized-image">
                        </div>
                        <div class="col-md-7">
                            <h5><%= list.getProductName() %></h5>
                            <p style="margin-top: 20px;"><%= list.getBrandName() %></p>
                            <div class="text-left">
                                <a  onclick="showConfirmDeleteModal(<%=list.getProductID()%>, <%=list.getProductDetailsID()%>)" class="remove">
                                    <i class="remove-btn fas fa-trash"></i>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-3 text-end">
                            <p class="inline text-danger fs-4 same-size" ><%= formatter.format(list.getPrice()) %></p>
                            <br>
                            <form action="MainController" method="POST">
                                <input type="hidden" name="productDetailsID" value="<%=list.getProductDetailsID()%>"/>
                                <input type="hidden" name="productID" value="<%= list.getProductID()%>"/>
                                <input type="hidden" name="size" value="<%= list.getSize()%>"/>
                                <input type="hidden" name="color" value="<%= list.getColor()%>"/>
                                <input type="hidden" name="price" value="<%= list.getPrice()%>"/>
                                <input type="hidden" name="quantity" value="1"/>
                                <input type="hidden" name="page" value="Wishlist"/>
                                <button type="submit" name="action" value="Add_To_Cart" class="btn btn-dark" style="width: 150px; margin-top: 90px;">Add to Cart</button>
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <h1>No products found</h1>

                    <%
                    }
                    %>

                </div>
            </div>

            <!-- Modal Xác nhận Xóa -->
            <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete this product?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-danger" id="confirmDeleteButton">Confirm</button>
                        </div>
                    </div>
                </div>
            </div>
            
             <!-- Success Modal -->
            <% if (request.getAttribute("SUCCESS_MESSAGE") != null) { %>
            <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header bg-success text-white">
                            <h5 class="modal-title" id="successModalLabel">Success</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <span id="successMessage"></span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <%@include file="US_footer.jsp" %>
        <%@include file="US_RequestSupport.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
        <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            var navLinks = document.querySelectorAll('.profile-sidebar .nav-link');
                                            navLinks.forEach(function (link) {
                                                link.addEventListener('click', function () {
                                                    navLinks.forEach(function (nav) {
                                                        nav.classList.remove('active');
                                                    });
                                                    link.classList.add('active');
                                                });
                                            });
                                        });


//            document.getElementById('deleteBtn').addEventListener('click', function (event) {
//                event.preventDefault(); // Prevent the default action (navigation)
//
//                // Show confirmation dialog
//                const userConfirmed = confirm("Do you want to delete this item?");
//
//                if (userConfirmed) {
//                    // If user clicked "Yes", proceed with the delete action
//                    window.location.href = this.href;
//                }
//                // If user clicked "No", do nothing
//            });

                                        function showConfirmDeleteModal(productID, productDetailID) {
                                            // Store the product ID and product detail ID in the data attributes of the button
                                            document.getElementById('confirmDeleteButton').setAttribute('data-product-id', productID);
                                            document.getElementById('confirmDeleteButton').setAttribute('data-product-detail-id', productDetailID);
                                            // Show the modal
                                            var confirmDeleteModal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
                                            confirmDeleteModal.show();
                                        }

                                        document.addEventListener('DOMContentLoaded', function () {
                                            document.getElementById('confirmDeleteButton').addEventListener('click', function () {
                                                var productID = this.getAttribute('data-product-id');
                                                var productDetailID = this.getAttribute('data-product-detail-id');
                                                var url = "MainController?action=deleteWishlist&productID=" + productID + "&productDetailID=" + productDetailID;
                                                window.location.href = url;
                                            });
                                        });
                                        
                                         $(document).ready(function () {
                                            const successMessage = '<%= request.getAttribute("SUCCESS_MESSAGE")%>';
                                            if (successMessage) {
                                                document.getElementById('successMessage').innerText = successMessage;
                                                $('#successModal').modal('show');
                                            }
                                        });
        </script>
    </body>
</html>
