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
    <!-- Add menu items specific to student -->
    <li><a href="/student">Home</a></li>
    <li><a href="/student/books">Books</a></li>
    <li><a href="/student/reserved-books">Reservation</a></li>
    <li><a href="/student/borrowed-books">Borrowed Books</a></li>


    <li style="float: right;"><a href="/logout" style="color: white; background-color: #f44336;">Logout</a></li>
</ul>
<div class="container">
    <div class="box">
        <h3>Books</h3>
    </div>
    <div class="row">

    </div>

    <br/>
    <table id="tablestyle">
        <tr>
            <th></th>

            <th>Title</th>
            <th>Author</th>
            <th>Edition</th>
            <th>Category</th>
            <th>Langue</th>
            <th>Availability</th>



        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><img src="${book.imagePath}" alt="Book Image" style="width: 100px;"></td>

                <td>${book.title}</td>
                <td>${book.author.name}</td>
                <td>${book.edition}</td>
                <td>${book.category.category}</td>
                <td>${book.language}</td>
                <c:choose>
                    <c:when test="${book.available == true}">
                        <td>
                            <form action="/reservation" method="post">
                            <!-- Additional fields as needed -->
                            <input type="hidden" name="book.isbn" value="${book.isbn}">
                            <input type="hidden" name="borrower.uid" value="${user.uid}">

                            <div class="row">
                                <div class="col-25">
                                    <input type="submit" value="Reserve">
                                </div>
                            </div>
                        </form></td>
                    </c:when>
                    <c:otherwise>
                        <td> <a class="indisponible">Indisponible</a></td>
                    </c:otherwise>
                </c:choose></td>


            </tr>
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