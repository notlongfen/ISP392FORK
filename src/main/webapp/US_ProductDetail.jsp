<%-- 
    Document   : ProductDetail2
    Created on : May 21, 2024, 8:07:29 PM
    Author     : jojo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <title>Single Product</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Colo Shop Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" href="plugins/themify-icons/themify-icons.css">
    <link rel="stylesheet" type="text/css" href="plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="styles/single_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/single_responsive.css">    

    <style>
        #add_to_wishlist:hover {
            background-color: #f0f0f0; /* Màu n?n màu nâu nh?t khi nút ???c hover */
            color: black; /* Màu ch? */
            transform: scale(1.05); /* Scale nút lên 1.05 khi hover */
        }

        #add_to_cart:hover {
            /*color: black;  Màu ch? */
            transform: scale(1.05); /* Scale nút lên 1.05 khi hover */
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

        .sizes {
            display: flex;
            flex-wrap: wrap;
        }
        .size {
            flex: 1 1 30%;
        }
    </style>

    <body>
        <%
            List<ProductDetailsDTO> productDetails = (List<ProductDetailsDTO>) request.getAttribute("productDetails");
            ProductDTO product = (ProductDTO) request.getAttribute("product");
        %>
        <div class="super_container">           

            <%@include file="US_header.jsp" %>

            <div class="fs_menu_overlay"></div>

            <!-- Hamburger Menu -->
            <div class="container single_product_container">
                <div class="row">
                    <div class="col">

                        <!-- Breadcrumbs -->

                        <div class="breadcrumbs d-flex flex-row align-items-center">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="categories.html"><i class="fa fa-angle-right" aria-hidden="true"></i>Men's</a></li>
                                <li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><%=product.getProductName()%></a></li>
                            </ul>
                        </div>

                    </div>

                </div>

                <div class="row">


                    <div class="col-lg-7">
                        <div class="single_product_pics">
                            <div class="row">

                                <div class="col-lg-3 thumbnails_col order-lg-1 order-2">
                                    <div class="single_product_thumbnails">
                                        <ul>
                                            <%
                                                List<String> imageUrls = new ArrayList<>();
                                                Set<String> seenImages = new HashSet<>();
                                                int maxImages = 3; // Số lượng tối đa ảnh được lưu trong seenImages
                                                for (ProductDetailsDTO productDetail : productDetails) {
                                                    String imageSrc = productDetail.getImage();
                                                    // Kiểm tra xem ảnh đã được hiển thị chưa và chưa vượt quá số lượng tối đa
                                                    if (!seenImages.contains(imageSrc) && seenImages.size() < maxImages) {
                                                        seenImages.add(imageSrc);
                                                        imageUrls.add(imageSrc);
                                            %>
                                            <li><img src="images/newImage/<%= imageSrc%>" alt="" data-image="images/newImage/<%= imageSrc%>"></li>
                                                <%
                                                        }
                                                    }
                                                %>
                                        </ul>
                                    </div>
                                </div>


                                <div class="col-lg-9 image_col order-lg-2 order-1">
                                    <div class="single_product_image">
                                        <div class="single_product_image_background"
                                             style="background-image:url(images/newImage/<%= productDetails.get(0).getImage()%>)"></div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="product_details">
                            <div class="product_details_title">
                                <h2><%= product.getProductName()%></h2>
                                <p><%= product.getDescription()%></p>
                            </div>
                            <div class="free_delivery d-flex flex-row align-items-center justify-content-center">
                                <span class="ti-truck"></span><span>free delivery</span>
                            </div>
                            <div class="product_price mt-5" style="color: #C53337"><span>USD <%= productDetails.get(0).getPrice()%></span></div>

                            <div style="margin-top: 30px;">
                                <label style="font-size: 20px;">Select size:</label>
                                <select class="sizes form-control" id="sizeSelect">
                                    <option value="#"> Select Size</option>
                                    <%
                                        Set<String> seenSizes = new HashSet<>();
                                        for (ProductDetailsDTO productDetail : productDetails) {
                                            String size = productDetail.getSize();
                                            if (!seenSizes.contains(size)) {
                                                seenSizes.add(size);
                                    %>
                                    <option value="<%= size%>">US <%= size%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div style="margin-top: 30px;">
                                <label style="font-size: 20px;">Select color:</label>
                                <select class="colors form-control" id="colorSelect">
                                    <option value="#"> Select Color</option>
                                    <%
                                        Set<String> seenColors = new HashSet<>();
                                        for (ProductDetailsDTO productDetail : productDetails) {
                                            String color = productDetail.getColor();
                                            if (!seenColors.contains(color)) {
                                                seenColors.add(color);
                                    %>
                                    <option value="<%= color%>"><%= color%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="quantity d-flex flex-column flex-sm-row align-items-sm-center mb-5">
                                <div class="col-md-4 text-sm-right text-center ">
                                    <span style="font-size: 20px;">Quantity: </span>
                                </div>
                                <div class="col-md-8 d-flex justify-content-center align-items-center">
                                    <button id="decrease" class="btn btn-dark me-2">-</button>
                                    <span class="border px-3 py-2" style="width: 80px; text-align: center; width: 100%"
                                          id="quantity">1</span>
                                    <button id="increase" class="btn btn-dark ms-2">+</button>
                                </div>
                            </div>

                            <div class="row justify-content-center">
                                <div class="col">
                                    <div class="d-grid gap-2">
                                        <button id="add_to_cart" class="btn btn-dark"
                                                style="background: black; border: 3px solid black;"><b>Add to Cart</b></button>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-2 justify-content-center">
                                <div class="col">
                                    <div class="d-grid gap-2">
                                        <button id="add_to_wishlist" class="btn btn-outline-dark"
                                                style="background: white; border: 2px solid black;">
                                            <b>Favorite</b></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <!-- Tabs -->
        <div class="tabs_section_container">

            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="tabs_container"> <hr>                              
                            <ul class="tabs d-flex flex-sm-row flex-column align-items-left align-items-md-center justify-content-center">
                                <li ><span style="font-size: 50px; color: #C53337"><b>Description</b></span></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">

                        <!-- Tab Description -->

                        <div id="tab_1" class="tab_container active">
                            <div class="row">
                                <%
                                    List<String> firstThreeImages = new ArrayList<>();
                                    int count = 0;
                                    for (String image : seenImages) {
                                        firstThreeImages.add(image);
                                        count++;
                                        if (count == 3) {
                                            break;
                                        }
                                    }
                                %>

                                <% if (firstThreeImages.size() == 1) {%>
                                <div class="col-lg-5 desc_col">
                                    <div class="tab_text_block">
                                        <h2><%= product.getProductName()%></h2>
                                        <p><%= product.getDescription()%></p>
                                    </div>
                                    <div class="tab_image">
                                        <img src="images/newImage/<%= firstThreeImages.get(0)%>" alt="<%= product.getProductName()%>">
                                    </div>
                                </div>
                                <% } else if (firstThreeImages.size() == 2) {%>
                                <div class="col-lg-5 desc_col">
                                    <div class="tab_text_block">
                                        <h2><%= product.getProductName()%></h2>
                                        <p><%= product.getDescription()%></p>
                                    </div>
                                    <div class="tab_image">
                                        <img src="images/newImage/<%= firstThreeImages.get(0)%>" alt="<%= product.getProductName()%>">
                                    </div>
                                </div>
                                <div class="col-lg-5 offset-lg-2 desc_col">
                                    <div class="tab_image">
                                        <img src="images/newImage/<%= firstThreeImages.get(1)%>" alt="<%= product.getProductName()%>">
                                    </div>
                                </div>
                                <% } else if (firstThreeImages.size() == 3) {%>
                                <div class="col-lg-5 desc_col">
                                    <div class="tab_text_block">
                                        <h2><%= product.getProductName()%></h2>
                                        <p><%= product.getDescription()%></p>
                                    </div>
                                    <div class="tab_image">
                                        <img src="images/newImage/<%= firstThreeImages.get(0)%>" alt="<%= product.getProductName()%>">
                                    </div>
                                    <div class="tab_text_block">
                                        <h2><%= product.getProductName()%></h2>
                                        <p><%= product.getDescription()%></p>
                                    </div>
                                </div>
                                <div class="col-lg-5 offset-lg-2 desc_col">
                                    <div class="tab_image">
                                        <img src="images/newImage/<%= firstThreeImages.get(1)%>" alt="<%= product.getProductName()%>">
                                    </div>
                                    <div class="tab_text_block">
                                        <h2><%= product.getProductName()%></h2>
                                        <p><%= product.getDescription()%></p>
                                    </div>
                                    <div class="tab_image desc_last">
                                        <img src="images/newImage/<%= firstThreeImages.get(2)%>" alt="<%= product.getProductName()%>">
                                    </div>
                                </div>
                                <% }%>

                            </div>
                        </div>

                        <!-- Tab Additional Info -->
                    </div>
                </div>
            </div>
        </div>



        <!-- Footer -->
        <%@include file="US_footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#colorSelect').change(function () {
                    var productID = <%= product.getProductID()%>; // Thay product.getProductID() bằng giá trị thực
                    var color = $(this).val();

                    $.ajax({
                        url: 'GetSizeByColorAndID',
                        type: 'GET',
                        data: {
                            productID: productID,
                            color: color
                        },
                        dataType: 'json',
                        success: function (sizes) {
                            // Xóa tất cả các option hiện có trong select size
                            $('#sizeSelect').empty();

                            // Thêm option mặc định "Select Size"
                            $('#sizeSelect').append('<option value="#"> Select Size</option>');

                            // Thêm các option mới từ dữ liệu sizes trả về
                            $.each(sizes, function (index, size) {
                                $('#sizeSelect').append('<option value="' + size + '">US ' + size + '</option>');
                            });
                        },
                        error: function () {
                            alert('Error fetching sizes');
                        }
                    });
                });
            });



        </script>


    </script>
    <script src="javascript/jquery-3.2.1.min.js"></script>
    <script src="styles/bootstrap4/popper.js"></script>
    <script src="styles/bootstrap4/bootstrap.min.js"></script>
    <script src="plugins/Isotope/isotope.pkgd.min.js"></script>
    <script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="plugins/easing/easing.js"></script>
    <script src="plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    <script src="javascript/single_custom.js"></script>
    <script src="javascript/single_product.js"></script>
</body>

</html>
