<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="/CSS/account-layout.css">
    <title>Admin Home</title>
</head>
<body>

<ul>
    <!-- Add menu items specific to admin -->
    <li><a href="/admin">Home</a></li>
    <li><a href="/admin/manageaccounts">Accounts</a></li>

    <li style="float: right;"><a href="/logout" style="color: white; background-color: #f44336;">Logout</a></li>
</ul>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="container">
    <h2>Welcome, ${currentUser.firstName} ${currentUser.lastName}</h2>

</div>

</body>
</html>
