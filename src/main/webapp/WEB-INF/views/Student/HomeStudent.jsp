<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="/CSS/account-layout.css">
    <title>Home</title>
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
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<<div class="container">
    <div style="text-align: center;">
        <h2>Welcome, ${currentUser.firstName} ${currentUser.lastName}</h2>
    </div>    <div class="notifications">
        <h3>Notifications</h3>
        <table id="tablestyle">
            <tr>
                <th>Message</th>
                <th>Actions</th> <!-- Add Actions column -->
            </tr>
            <c:forEach var="notification" items="${notifications}">
                <c:if test="${notification.recipient.uid == currentUser.uid}">
                    <tr>

                        <td>${notification.message}</td>
                        <td>
                            <form action="/deleteNotification" method="post">
                                <input type="hidden" name="notificationId" value="${notification.id}">
                                <button type="submit">Mark as Seen</button>
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
