<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.mycompany.isp392.cart.CartError"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Single Product</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <style>
            #add_to_wishlist:hover, #add_to_cart:hover {
                background-color: #f0f0f0;
                color: black;
                transform: scale(1.05);
            }

            .quantity .btn {
                width: 40px;
                height: 40px;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .quantity .border {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 40px;
                font-size: 1rem;
            }

            .sizes, .colors {
                display: flex;
                flex-wrap: wrap;
            }

            .size, .color {
                flex: 1 1 30%;
                margin: 5px;
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
                cursor: pointer;
            }

            .size.disabled, .color.disabled {
                opacity: 0.5;
                cursor: not-allowed;
            }

            .single_product_image img {
                width: 100%;
                height: auto;
            }

            .carousel-item img {
                max-height: 500px;
            }

            .carousel-control-prev-icon, .carousel-control-next-icon {
                background-color: black;
            }

            .selected {
                border: 2px solid #c53337;
            }
        </style>
    </head>
    <body>
        <%
            Map<String, Map<String, Map<String, Object>>> colorSizeMap = (Map<String, Map<String, Map<String, Object>>>) request.getAttribute("colorSizeMap");
            ProductDTO product = (ProductDTO) request.getAttribute("product");
            String selectedColor = request.getParameter("color");
            if (selectedColor == null && colorSizeMap != null && !colorSizeMap.isEmpty()) {
                selectedColor = colorSizeMap.keySet().iterator().next();
            }
            String selectedSize = request.getParameter("size");
            if (selectedSize == null && selectedColor != null && colorSizeMap.get(selectedColor) != null && !colorSizeMap.get(selectedColor).isEmpty()) {
                selectedSize = colorSizeMap.get(selectedColor).keySet().iterator().next();
            }

            Gson gson = new Gson();
            String colorSizeMapJson = gson.toJson(colorSizeMap);

//            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//            int price = (int) colorSizeMap.get(selectedColor).get(selectedSize).get("price");
//            String formattedPrice = formatter.format(price);
            
        %>
        <div class="super_container">           
            <%@include file="US_header.jsp" %>

            <div class="fs_menu_overlay"></div>

            <div class="container single_product_container">
                <div class="row">
                    <div class="col">
                        <div class="breadcrumbs d-flex flex-row align-items-center">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="categories.html"><i class="fa fa-angle-right" aria-hidden="true"></i>Men's</a></li>
                                <li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><%= product.getProductName()%></a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-7">
                        <div id="productCarousel" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <%
                                    boolean isActive = true;
                                    if (selectedColor != null && selectedSize != null) {
                                        String[] images = (String[]) colorSizeMap.get(selectedColor).get(selectedSize).get("images");
                                        for (String image : images) {
                                            if (!image.trim().isEmpty()) {
                                %>
                                <div class="carousel-item <%= isActive ? "active" : ""%>">
                                    <img src="<%= image.trim()%>" alt="Product Image">
                                </div>
                                <%
                                                isActive = false;
                                            }
                                        }
                                    }
                                %>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="product_details">
                            <div class="product_details_title">
                                <h2><%= product.getProductName()%></h2>
                                <p><%= product.getDescription()%></p>
                            </div>
                            <form action="MainController" method="POST">
                                <div class="free_delivery d-flex flex-row align-items-center justify-content-center">
                                    <span class="ti-truck"></span><span>free delivery</span>
                                </div>
                                <div class="product_price mt-5" style="color: #C53337"><span id="productPrice">
                                        <%= colorSizeMap.get(selectedColor).get(selectedSize).get("price")%>
                                    </span>
                                </div>

                                <div style="margin-top: 30px;">
                                    <label style="font-size: 20px;">Select size:</label>
                                    <div class="sizes">
                                        <%
                                            for (int size = 36; size <= 45; size++) {
                                                String sizeStr = String.valueOf(size);
                                                boolean available = colorSizeMap.get(selectedColor).containsKey(sizeStr);
                                        %>
                                        <div class="size <%= available ? "" : "disabled"%>" data-size="<%= sizeStr%>">
                                            US <%= size%>
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>

                                <div style="margin-top: 30px;">
                                    <label style="font-size: 20px;">Select color:</label>
                                    <div class="colors">
                                        <%
                                            for (String colorOption : colorSizeMap.keySet()) {
                                        %>
                                        <div class="color <%= colorOption.equals(selectedColor) ? "selected" : ""%>" data-color="<%= colorOption%>">
                                            <%= colorOption%>
                                        </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>

                                <div class="quantity d-flex flex-column flex-sm-row align-items-sm-center mb-5">
                                    <div class="col-md-4 text-sm-right text-center">
                                        <span style="font-size: 20px;">Quantity: </span>
                                    </div>
                                    <div class="col-md-8 d-flex justify-content-center align-items-center">
                                        <button type="button" id="decrease" class="btn btn-dark me-2">-</button>
                                        <span class="border px-3 py-2" id="quantity">1</span>
                                        <button type="button" id="increase" class="btn btn-dark ms-2">+</button>
                                    </div>
                                </div>

                                <input type="hidden" name="productID" value="<%= product.getProductID()%>">
                                <input type="hidden" name="quantity" id="quantityInput" value="1">
                                <input type="hidden" name="price" id="priceInput" value="">
                                <input type="hidden" name="selectedSize" id="selectedSize" value="">
                                <input type="hidden" name="selectedColor" id="selectedColor" value="">

                                <div class="row mt-2 justify-content-center">
                                    <div class="col">
                                        <div class="d-grid gap-2">
                                            <button type="submit" name="action" value="Add_To_Cart" id="add_to_cart" class="btn btn-dark" style="background: black; border: 3px solid black;"><b>Add to Cart</b></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-2 justify-content-center">
                                    <div class="col">
                                        <div class="d-grid gap-2">
                                            <button id="add_to_wishlist" class="btn btn-outline-dark" style="background: white; border: 2px solid black;"><b>Favorite</b></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
            <!-- Error Modal -->
            <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header bg-danger text-white">
                            <h5 class="modal-title" id="errorModalLabel">Error</h5>
                            <button type="button" class="close text-white" data-bs-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <ul class="list-group list-group-flush">
                                <%
                                    CartError error = (CartError) request.getAttribute("CART_ERROR");
                                    if (error != null) {
                                        if (error.getError() != null && !error.getError().isEmpty()) {
                                %>
                                <li class="list-group-item list-group-item-danger"><%= error.getError()%></li>
                                    <%
                                        }
                                        if (error.getProductError() != null && !error.getProductError().isEmpty()) {
                                    %>
                                <li class="list-group-item list-group-item-danger"><%= error.getProductError()%></li> 
                                    <%
                                        }
                                        if (error.getQuantityError() != null && !error.getQuantityError().isEmpty()) {
                                    %>
                                <li class="list-group-item list-group-item-danger"><%= error.getQuantityError()%></li> 
                                    <%
                                            }
                                        }
                                    %>
                            </ul>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>


            <%@include file="US_footer.jsp" %>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
            <script>
                $(document).ready(function () {
                    var selectedColor = '<%= selectedColor%>'; // Initial selected color from server-side
                    if (!selectedColor && $('.color').length > 0) {
                        selectedColor = $('.color:first').data('color'); // Default to the first available color
                    }
                    $('#selectedColor').val(selectedColor); // Update hidden input field
                });

                $(document).ready(function () {
                    console.log("Document ready");

                    // Handle color selection
                    $('.color').click(function () {
                        console.log("Color clicked:", $(this).data('color'));

                        if ($(this).hasClass('disabled'))
                            return;
                        var selectedColor = $(this).data('color');
                        $('.color').removeClass('selected');
                        $(this).addClass('selected');
                        $('#selectedColor').val(selectedColor); // Update hidden input field

                        // Update sizes based on selected color
                        updateSizes(selectedColor);
                        updateImagesAndPrice();
                    });

                    // Handle size selection
                    $(document).on('click', '.size', function () {
                        console.log("Size clicked:", $(this).data('size'));

                        if ($(this).hasClass('disabled'))
                            return;
                        var selectedSize = $(this).data('size');
                        $('.size').removeClass('selected');
                        $(this).addClass('selected');
                        $('#selectedSize').val(selectedSize); // Update hidden input field

                        // Update images and price based on selected size
                        updateImagesAndPrice();
                    });

                    $('#increase').click(function () {
                        console.log("Increase quantity clicked");
                        var quantity = parseInt($('#quantity').text());
                        $('#quantity').text(quantity + 1);
                        $('#quantityInput').val(quantity + 1);
                    });

                    $('#decrease').click(function () {
                        console.log("Decrease quantity clicked");
                        var quantity = parseInt($('#quantity').text());
                        if (quantity > 1) {
                            $('#quantity').text(quantity - 1);
                            $('#quantityInput').val(quantity - 1);
                        }
                    });

                    // Initialize sizes based on the initial selected color
                    updateSizes('<%= selectedColor%>');
                });

                function updateSizes(selectedColor) {
                    console.log("Updating sizes for color:", selectedColor);
                    var sizes = <%= colorSizeMapJson%>;
                    $('.size').each(function () {
                        var size = $(this).data('size');
                        if (sizes[selectedColor].hasOwnProperty(size)) {
                            $(this).removeClass('disabled');
                        } else {
                            $(this).addClass('disabled');
                        }
                    });

                    // Update images and price for the first available size
                    var firstAvailableSize = $('.size:not(.disabled)').first();
                    if (firstAvailableSize.length > 0) {
                        firstAvailableSize.click();
                    }
                }

                function updateImagesAndPrice() {
                    var selectedColor = $('.color.selected').data('color');
                    var selectedSize = $('.size.selected').data('size');
                    console.log("Updating images and price for color:", selectedColor, "and size:", selectedSize);

                    var sizes = <%= colorSizeMapJson%>;
                    var imageGallery = $('.carousel-inner');
                    imageGallery.empty(); // Clear existing images

                    if (sizes[selectedColor] && sizes[selectedColor][selectedSize]) {
                        var images = sizes[selectedColor][selectedSize]["images"];
                        var isActive = true;
                        for (var i = 0; i < images.length; i++) {
                            if (images[i].trim() !== "") {
                                var item = $('<div class="carousel-item"></div>');
                                if (isActive) {
                                    item.addClass('active');
                                    isActive = false;
                                }
                                var img = $('<img>').attr("src", images[i].trim()).attr("alt", "Product Image");
                                item.append(img);
                                imageGallery.append(item);
                            }
                        }
                        var price = sizes[selectedColor][selectedSize]["price"];
                        $('#productPrice').text(price);
                        $('#priceInput').val(price); // Update hidden input field

                    }
                }

                // Display success modal if message exists
                $(document).ready(function () {
                    const successMessage = '<%= request.getAttribute("SUCCESS_MESSAGE")%>';
                    if (successMessage) {
                        document.getElementById('successMessage').innerText = successMessage;
                        $('#successModal').modal('show');
                    }

                <% if (request.getAttribute("CART_ERROR") != null) { %>
                    $('#errorModal').modal('show');
                <% }%>

                });
            </script>.
        </div>
    </body>
</html>

