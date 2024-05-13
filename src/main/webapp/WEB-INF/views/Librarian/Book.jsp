<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books</title>
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
        <h3>Books</h3>
    </div>
    <div class="row">


            <a href="/books/add" style="float: right;" class="button-like-submit">Add</a>


    </div>
    <br/>

    <table id="tablestyle">
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Edition</th>
            <th>Category</th>
            <th>Image</th> <!-- Add a new column for the image -->
            <th>Action</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author.name}</td>
                <td>${book.edition}</td>
                <td>${book.category.category}</td>
                <td><img src="${book.imagePath}" alt="Book Image" style="width: 100px;"></td> <!-- Display the image -->
                <td><a href="/deleteBook/${book.isbn}" style="color: white; background-color: #f44336;" onclick="return confirm('Are you sure you want to delete this Book?')">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
