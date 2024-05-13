<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation</title>
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
        <h3>Reservation</h3>
    </div>

    <br/>

    <table id="tablestyle">
        <tr>
            <th></th>
            <th>Name</th>
            <th>Author</th>
            <th>Cancel Reservation</th>




        </tr>
        <c:forEach var="reservation" items="${reservations}">
            <c:if test="${!reservation.fulfilled}">
                <tr>
                    <td><img src="${reservation.book.imagePath}" alt="Book Image" style="width: 100px;"></td>
                    <td>${reservation.book.title}</td>
                    <td>${reservation.book.author.name}</td>
                    <td>
                        <form action="/reservations/cancel" method="post">
                            <input type="hidden" name="reservationId" value="${reservation.id}">
                            <input type="submit" value="Cancel">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

</div>
</body>
</html>
<style>


    #tablestyle th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;

    }

    /* Centering the image */
    #tablestyle td img {
        display: block;
        margin: 0 auto; /* Center horizontally */
    }

</style>