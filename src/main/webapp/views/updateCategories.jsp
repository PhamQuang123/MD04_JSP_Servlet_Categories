<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 14/04/2024
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Categories</title>
</head>
<body>
<form action="<%request.getContextPath();%>/Controller?action=update" method="post">
  <label for="catalogId">ID:</label>
  <input type="number" name="catalogId" id="catalogId"  value="${categories.catalogId}" readonly/><br>

  <label for="catalogName">Name:</label>
  <input type="text" name="catalogName" id="catalogName"  value="${categories.catalogName}"/><br>

  <label for="descriptions">Descriptions:</label>
  <input type="text" name="descriptions" id="descriptions" value="${categories.descriptions}"/><br>

  <label for="active">Status:</label>
  <input type="radio" name="catalogStatus" value="true" id="active" ${categories.catalogStatus?"checked":""}/><label>Hoạt động</label>
  <input type="radio" name="catalogStatus" value="false" id="inActive" ${categories.catalogStatus?"":"checked"}/><label>Không hoạt động</label><br>

  <input type="submit" value="Update">
</form>
</body>
</html>
