
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
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
        <h3>Transactions</h3>
    </div>

    <br/>

    <table id="tablestyle">
        <tr>

        <tr>

            <th>Date of Issue</th>
            <th>Return Date</th>
            <th>Book</th>
            <th>Borrower</th>
            <th>Returned</th>
        <th>Action</th>
        </tr>



        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <tr>

                <td>${transaction.dateOfIssue}</td>
                <td>${transaction.returnDate}</td>
                <td>${transaction.book.title}</td>
                <td>${transaction.borrower.firstName} ${transaction.borrower.lastName}</td>
                <td>${transaction.returned ? 'Yes' : 'No'}</td>
                <td>
                    <c:if test="${not transaction.returned}">
                        <form action="/librarian/transaction/return" method="post">
                            <input type="hidden" name="transactionId" value="${transaction.id}">
                            <button type="submit">Return</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
