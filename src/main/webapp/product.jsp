<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Product</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h2>Add Product Information</h2>
    <form action="AddProductController" method="POST">
        Product Name: <input type="text" name="productName" required="required"/><br/>
        Description: <textarea name="description" required="required"></textarea><br/>
        <!-- Hidden fields for numberOfPurchase and status -->
        <input type="hidden" name="numberOfPurchase" value="0"/>
        <input type="hidden" name="status" value="0"/>
        Brand ID: <input type="number" name="brandID" required="required"/><br/>
        <input type="submit" value="Add_Product"/>
        <input type="reset" value="Reset"/>
    </form>
</body>
</html>
