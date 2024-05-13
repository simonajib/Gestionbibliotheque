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
        <h3>Reservation</h3>
    </div>

    <br/>

    <table id="tablestyle">
        <tr>

            <th>Name</th>
            <th>Author</th>
            <th>Action</th>




        </tr>
        <c:forEach var="reservedBooks" items="${reservedBooks}">
            <c:if test="${!reservedBooks.fulfilled}">
            <tr>
                <td>${reservedBooks.book.title}</td>
                <td>${reservedBooks.book.author.name}</td>
                <td><form action="/librarian/reservation/update" method="post">
                    <input type="hidden" name="reservationId" value="${reservedBooks.id}">
                    <input type="hidden" name="isbn" value="${reservedBooks.book.isbn}">
                    <input type="hidden" name="uid" value="${reservedBooks.borrower.uid}">
                    <input type="checkbox" name="fulfilled" value="true">
                    <button type="submit">Update Reservation</button>
                </form>

                </td>

            </tr>
            </c:if>
        </c:forEach>
    </table>

</div>
</body>
</html>
