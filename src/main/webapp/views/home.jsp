<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14/04/2024
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
<h1>******** CATEGORIES ********</h1>

<div>
    <form action="<%request.getContextPath();%>/Controller?action=search" method="post">
        <label for="catalogName">Tìm kiếm</label>
        <input type="text" name="catalogName" id="catalogName">
        <input type="submit" value="Search">
    </form>
</div>

<table border="1">
    <thead>
    <tr>
        <th>Mã danh mục</th>
        <th>Tên danh mục</th>
        <th>Mô tả</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listCategories}" var="categories">
        <tr>
            <td>${categories.catalogId}</td>
            <td>${categories.catalogName}</td>
            <td>${categories.descriptions}</td>
            <td>${categories.catalogStatus?"hoạt động":"không hoạt động"}</td>
            <td>
                <a href="<%request.getContextPath();%>/Controller?catalogId=${categories.catalogId}&action=initUpdate">
                    <button>Update</button>
                </a>
                <a href="<%request.getContextPath();%>/Controller?catalogId=${categories.catalogId}&action=delete">
                    <button>Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<%request.getContextPath();%>/views/createCategories.jsp">
    <button>Add New Categories</button>
</a>
<br>
<br>
<br>
<h1>******* PRODUCT ********</h1>
<div>
    <form action="<%request.getContextPath();%>/Controller?action=searchProduct" method="post">
        <label>Tìm kiếm</label>
        <label>fromPrice</label><input type="number" name="fromPrice" id="fromPrice">
        <label>toPrice</label><input type="number" name="toPrice" id="toPrice">
        <input type="submit" value="Search">
    </form>
</div>
<table border="1">
    <thead>
    <tr>
        <th>Mã sản phẩm</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Tiêu đề</th>
        <th>Mã danh mục</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listProducts}" var="product">
        <tr>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.price}</td>
            <td>${product.title}</td>
            <td>${product.catalogId}</td>
            <td>${product.productStatus?"Còn hàng":"Đã hết"}</td>
            <td>
                <a href="<%request.getContextPath();%>/Controller?productId=${product.productId}&action=initUpdateProduct">
                    <button>Update</button>
                </a>
                <a href="<%request.getContextPath();%>/Controller?productId=${product.productId}&action=deleteProduct">
                    <button>Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<%request.getContextPath();%>/views/product/createProduct.jsp">
    <button>Add New Product</button>
</a>

</body>
</html>
