<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation Book</title>

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
        <h1>Reservation Book</h1>
    </div>
    <form action="/reservation" method="post">
        <div class="row">
            <div class="col-25">
                <label for="title"> Title:</label>
            </div>
            <div class="col-75">
                <select id="title" name="book.isbn">
                    <option value="">Select a title</option> <!-- Default option -->
                    <c:forEach var="book" items="${books}">
                        <c:if test="${book.available == true}">
                            <option value="${book.isbn}">${book.title}</option>
                        </c:if>
                    </c:forEach>
                </select><br>
            </div>
            <!-- Additional fields as needed -->
            <input type="hidden" name="borrower.uid" value="${user.uid}">

            <div class="row">
                <div class="col-25">
                    <input type="submit" value="Reserve">
                </div>
            </div>
        </div>


    </form>


</div>
</body>
</html>
