<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14/04/2024
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Categories</title>
</head>
<body>
<form action="<%request.getContextPath();%>/Controller?action=create" method="post">
    <label for="catalogName">Name:</label>
    <input type="text" name="catalogName" id="catalogName"/><br>

    <label for="descriptions">Descriptions:</label>
    <input type="text" name="descriptions" id="descriptions"/><br>

    <label for="active">Status:</label>
    <input type="radio" name="catalogStatus" value="true" id="active"/><label>Hoạt động</label>
    <input type="radio" name="catalogStatus" value="false" id="inActive"/><label>Không hoạt động</label><br>

    <input type="submit" value="Create">
</form>
</body>
</html>
