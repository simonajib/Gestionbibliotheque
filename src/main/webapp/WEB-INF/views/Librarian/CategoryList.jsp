<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authors</title>
    <link rel="stylesheet" type="text/css" href="/CSS/account-layout.css">
</head>
<body>

<ul>
    <!-- Add menu items specific to librarian -->
    <li><a href="/librarian">Home</a></li>
    <li><a href="/Authors">Authors</a></li>
    <li><a href="/Category">Category</a></li>

    <li><a href="/books">Books</a></li>
    <li><a href="/librarian/reserved-books">Reservation</a></li>
    <li><a href="/librarian/transactions">Transaction</a></li>

    <li style="float: right;"><a href="/logout" style="color: white; background-color: #f44336;">Logout</a></li>
</ul>
<div class="container">
    <div class="box">
        <h3>Category</h3>
    </div>
    <div class="row">
        <a href="/addCategory" style="float: right;" class="button-like-submit">Add</a>
    </div>
    <br/>

    <table id="tablestyle">
        <tr>

            <th>Name</th>
            <th>Action</th>

        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.category}</td>
                <td><a href="/deleteCategory/${category.id}" style="color: white; background-color: #f44336;" onclick="return confirm('Are you sure you want to delete this category?')">Delete</a></td>

            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
