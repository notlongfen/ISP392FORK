<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.isp392.product.ProductDTO"%>
<%@page import="com.mycompany.isp392.product.ProductDetailsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <title>Search Products</title>
    <style>
        .form-container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #dee2e6;
            border-radius: 12px;
            background-color: #f8f9fa;
        }
        .form-group label {
            font-weight: bold;
        }
        .collapse-table td {
            padding: 0;
            border-top: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2 class="text-center" style="color: #000; font-weight: bold;">Search Products</h2>
            <form action="MainController" method="get">
                <div class="form-group">
                    <input type="text" class="form-control" name="searchText" placeholder="Enter product name or description to search">
                    <button type="submit" class="btn btn-info mt-2" name="action" value="Search_Product">Search</button>
                </div>
            </form>

            <c:if test="${not empty PRODUCT_LIST}">
                <table class="table table-bordered mt-3">
                    <thead class="thead-dark">
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Description</th>
                            <th>Brand ID</th>
                            <th>Number of Purchase</th>
                            <th>Details</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${PRODUCT_LIST}">
                            <tr data-toggle="collapse" data-target="#details-${entry.key.productID}" class="accordion-toggle">
                                <td>${entry.key.productID}</td>
                                <td>${entry.key.productName}</td>
                                <td>${entry.key.description}</td>
                                <td>${entry.key.brandID}</td>
                                <td>${entry.key.numberOfPurchase}</td>
                                <td>
                                    <button class="btn btn-info btn-sm" type="button">
                                        Toggle Details
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#editProduct-${entry.key.productID}">
                                        Edit
                                    </button>
                                </td>
                                <td>
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="productID" value="${entry.key.productID}">
                                        <button type="submit" class="btn btn-danger btn-sm" name="action" value="Delete_Product">Delete</button>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8" class="hiddenRow">
                                    <div class="accordian-body collapse" id="details-${entry.key.productID}">
                                        <table class="table table-sm collapse-table">
                                            <thead>
                                                <tr>
                                                    <th>Color</th>
                                                    <th>Size</th>
                                                    <th>Stock Quantity</th>
                                                    <th>Price</th>
                                                    <th>Import Date</th>
                                                    <th>Image</th>
                                                    <th>Status</th>
                                                    <th>Edit</th>
                                                    <th>Delete</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="detail" items="${entry.value}">
                                                    <tr>
                                                        <td>${detail.color}</td>
                                                        <td>${detail.size}</td>
                                                        <td>${detail.stockQuantity}</td>
                                                        <td>${detail.price}</td>
                                                        <td>${detail.importDate}</td>
                                                        <td><img src="${detail.image}" alt="Product Image" width="50"></td>
                                                        <td>${detail.status}</td>
                                                        <td>
                                                            <button class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="#editDetail-${detail.productID}-${detail.color}-${detail.size}">
                                                                Edit
                                                            </button>
                                                        </td>
                                                        <td>
                                                            <form action="MainController" method="post">
                                                                <input type="hidden" name="productID" value="${detail.productID}">
                                                                <input type="hidden" name="color" value="${detail.color}">
                                                                <input type="hidden" name="size" value="${detail.size}">
                                                                <button type="submit" class="btn btn-danger btn-sm" name="action" value="Delete_Detail">Delete</button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="9" class="hiddenRow">
                                                            <div class="accordian-body collapse" id="editDetail-${detail.productID}-${detail.color}-${detail.size}">
                                                                <form action="MainController" method="post">
                                                                    <input type="hidden" name="productID" value="${detail.productID}">
                                                                    <input type="hidden" name="originalColor" value="${detail.color}">
                                                                    <input type="hidden" name="originalSize" value="${detail.size}">
                                                                    <div class="form-group">
                                                                        <label for="editColor-${detail.productID}-${detail.color}-${detail.size}">Color</label>
                                                                        <input type="text" class="form-control" id="editColor-${detail.productID}-${detail.color}-${detail.size}" name="color" value="${detail.color}">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="editSize-${detail.productID}-${detail.color}-${detail.size}">Size</label>
                                                                        <input type="text" class="form-control" id="editSize-${detail.productID}-${detail.color}-${detail.size}" name="size" value="${detail.size}">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="editStockQuantity-${detail.productID}-${detail.color}-${detail.size}">Stock Quantity</label>
                                                                        <input type="number" class="form-control" id="editStockQuantity-${detail.productID}-${detail.color}-${detail.size}" name="stockQuantity" value="${detail.stockQuantity}">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="editPrice-${detail.productID}-${detail.color}-${detail.size}">Price</label>
                                                                        <input type="number" class="form-control" id="editPrice-${detail.productID}-${detail.color}-${detail.size}" name="price" value="${detail.price}">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="editImportDate-${detail.productID}-${detail.color}-${detail.size}">Import Date</label>
                                                                        <input type="date" class="form-control" id="editImportDate-${detail.productID}-${detail.color}-${detail.size}" name="importDate" value="${detail.importDate}">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="editImage-${detail.productID}-${detail.color}-${detail.size}">Image</label>
                                                                        <input type="text" class="form-control" id="editImage-${detail.productID}-${detail.color}-${detail.size}" name="image" value="${detail.image}">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="editDetailStatus-${detail.productID}-${detail.color}-${detail.size}">Status</label>
                                                                        <input type="number" class="form-control" id="editDetailStatus-${detail.productID}-${detail.color}-${detail.size}" name="detailStatus" value="${detail.status}">
                                                                    </div>
                                                                    <button type="submit" class="btn btn-success" name="action" value="Edit_Product_Details">Save Changes</button>
                                                                </form>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="8" class="hiddenRow">
                                    <div class="accordian-body collapse" id="editProduct-${entry.key.productID}">
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="productID" value="${entry.key.productID}">
                                            <div class="form-group">
                                                <label for="newName-${entry.key.productID}">Product Name</label>
                                                <input type="text" class="form-control" id="newName-${entry.key.productID}" name="newName" value="${entry.key.productName}">
                                            </div>
                                            <div class="form-group">
                                                <label for="newDescription-${entry.key.productID}">Description</label>
                                                <input type="text" class="form-control" id="newDescription-${entry.key.productID}" name="newDescription" value="${entry.key.description}">
                                            </div>
                                            <button type="submit" class="btn btn-success" name="action" value="Edit_Product">Save Changes</button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty PRODUCT_LIST}">
                <div class="alert alert-info text-center">No products found matching your search criteria.</div>
            </c:if>
        </div>
    </div>

    <!-- Include necessary scripts -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDzwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
