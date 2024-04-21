<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 17/04/2024
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<form action="<%request.getContextPath();%>/Controller?action=updateProduct" method="post">

   <label for="productId">ProductId:</label>
   <input type="text" name="productId" id="productId" value="${product.productId}" readonly/><br>

   <label for="productName">ProductName:</label>
   <input type="text" name="productName" id="productName" value="${product.productName}"/><br>

   <label for="price">Price:</label>
   <input type="number" name="price" id="price" value="${product.price}" /><br>

   <label for="title">Title:</label>
   <input type="text" name="title" id="title" value="${product.title}" /><br>

   <label for="catalogId">CatalogId:</label>
   <input type="number" name="catalogId" id="catalogId" value="${product.catalogId}" /><br>

   <label for="active">Status:</label>
   <input type="radio" name="productStatus" id="active" value="true" ${product.productStatus ? "checked":""}/><label>Còn hàng</label>
   <input type="radio" name="productStatus" id="inActive" value="false" ${product.productStatus ? "":"checked"}/><label>Hết hàng</label>


  <input type="submit" value="Update"/>
</form>
<br>

</body>
</html>
