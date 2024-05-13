<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="/CSS/homelibrarian.css">
    <title>Home</title>
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
<br>
<br>



<div class="container">
    <div class="center-text">
        <h2>Welcome, ${currentUser.firstName} ${currentUser.lastName}</h2>
    </div>
</div>
    <div class="box-container">


            <div class="box box1">
                <a href="/librarian/manageaccount">

                <div class="text">
                    <h2 class="topic-heading">${studentCount}</h2>
                    <h2 class="topic">Students</h2>
                </div>
                <img src="/Images/Student.png" alt="Student">
                </a>
            </div>

        <div class="box box2">
            <a href="/books">
            <div class="text">
                <h2 class="topic-heading">${bookCount}</h2>
                <h2 class="topic">Books</h2>
            </div>

            <img src=
                         "/Images/book.png"
                 alt="likes">
            </a>

        </div>

        <div class="box box3">
            <a href="/librarian/reserved-books">
            <div class="text">
                <h2 class="topic-heading">${fulfilledReservationCount}</h2>
                <h2 class="topic">Reservation</h2>
            </div>

            <img src=
                         "/Images/reserve.png"
                 alt="comments">
        </div>

        <div class="box box4">
            <div class="text">
                <h2 class="topic-heading">${unreturnedTransactionCount}</h2>
                <h2 class="topic">Borrowed</h2>
            </div>
            <img src=
                         "/Images/borrowed.png" alt="borrowed">
        </div>
    </div>
</div>

</body>
</html>
