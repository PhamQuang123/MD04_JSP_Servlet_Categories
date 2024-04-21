<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14/04/2024
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Product</title>
</head>
<body>
<form action="<%request.getContextPath();%>/Controller?action=createProduct" method="post">
    <label for="productId">ProductId:</label>
    <input type="text" name="productId" id="productId"/><br>

    <label for="productName">ProductName:</label>
    <input type="text" name="productName" id="productName"/><br>

    <label for="price">Price:</label>
    <input type="number" name="price" id="price"/><br>

    <label for="title">Title:</label>
    <input type="text" name="title" id="title"/><br>

    <label for="catalogId">CatalogId:</label>
    <input type="number" name="catalogId" id="catalogId"/><br>

    <label for="active">Status:</label>
    <input type="radio" name="productStatus" value="true" id="active"/><label>Còn hàng</label>
    <input type="radio" name="productStatus" value="false" id="inActive"/><label>Đã hết</label><br>

    <input type="submit" value="Create">
</form>
</body>
</html>
