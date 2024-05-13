<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage accounts</title>
    <link rel="stylesheet" type="text/css" href="/CSS/account-layout.css">
</head>
<body>
<ul>
    <!-- Add menu items specific to admin -->
    <li><a href="/admin">Home</a></li>
    <li><a href="/admin/manageaccounts">Accounts</a></li>

    <li style="float: right;"><a href="/logout" style="color: white; background-color: #f44336;">Logout</a></li>
</ul>
<div class="container">
<div class="box">
<h3>Accounts</h3>
</div>
    <div class="row">
<form action="/admin/manageaccounts" method="GET">
    <div class="col-25">
    <label for="firstName">First Name:</label>
    </div>
    <div class="col-75">
    <input type="text" id="firstName" name="firstName" value="${firstName}">
    </div>
    <div class="col-25">
    <label for="lastName">Last Name:</label>
    </div>
    <div class="col-75">
    <input type="text" id="lastName" name="lastName" value="${lastName}">
    </div>

    <div>
        <input type="submit" value="Search">
    </div>

</form>
    </div>
    <br/>

    <table id="tablestyle">
        <tr>

            <th>First Name</th>
            <th>Last Name</th>

            <th>Role</th>
            <th>Email</th>


        </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.role}</td>
            <td>${user.email}</td>


        </tr>
    </c:forEach>
</table>

</div>
</body>
</html>
