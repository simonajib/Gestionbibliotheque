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
        <h3>Authors</h3>
    </div>
    <div class="row">
        <form action="/Authors" method="GET">
            <div class="col-25">
                <label for="name">Name:</label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" value="${name}">
            </div>


            <div>
                <input type="submit" value="Search">
            </div>
            <div>
                <a href="/addAuthor" class="button-like-submit">Add</a>

            </div>


        </form>
    </div>
    <br/>

    <table id="tablestyle">
        <tr>

            <th>Name</th>
            <th>Action</th>

        </tr>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.name}</td>
               <td><a href="/deleteAuthor/${author.id}" style="color: white; background-color: #f44336;" onclick="return confirm('Are you sure you want to delete this author?')">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
