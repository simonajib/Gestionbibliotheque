<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Borrowed Books</title>
    <link rel="stylesheet" type="text/css" href="/CSS/account-layout.css">
</head>
<body>
<ul>
    <!-- Add menu items specific to student -->
    <li><a href="/student">Home</a></li>
    <li><a href="/student/books">Books</a></li>
    <li><a href="/student/reserved-books">Reservation</a></li>
    <li><a href="/student/borrowed-books">Borrowed Books</a></li>


    <li style="float: right;"><a href="/logout" style="color: white; background-color: #f44336;">Logout</a></li>
</ul>
<div class="container">
    <div class="box">
        <h3>Borrowed Books</h3>
    </div>

    <br/>

    <table id="tablestyle">
        <tr>

            <th>Name</th>
            <th>Author</th>
            <th>Return Date</th>





        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <c:if test="${!transaction.returned}">
                <tr>
                    <td>${transaction.book.title}</td>
                    <td>${transaction.book.author.name}</td>
                    <td>${transaction.returnDate}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

</div>
</body>
</html>
