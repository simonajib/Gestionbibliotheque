<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>

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
<h1>Add Book</h1>
    </div>

<form action="/books/add" method="post" enctype="multipart/form-data">
    <div class="row">
        <div class="col-50">
            <label for="isbn"> ISBN:</label>
        </div>
        <div class="col-75">
            <input type="text" id="isbn" name="isbn" field="${book.isbn}" required="required">
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="title"> Title:</label>
        </div>
        <div class="col-75">
            <input type="text" id="title" name="title" field="${book.title}" required="required">
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="description"> Description:</label>
        </div>
        <div class="col-75">
            <input type="text" id="description" name="description" field="${book.description}" required="required">
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="edition"> Edition:</label>
        </div>
        <div class="col-75">
            <input type="text" id="edition" name="edition" field="${book.edition}" required="required">
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="publisher"> Publisher:</label>
        </div>
        <div class="col-75">
            <input type="text" id="publisher" name="publisher" field="${book.publisher}" required="required">
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="numberOfPages"> Number of Pages:</label>
        </div>
        <div class="col-75">
            <input type="number" id="numberOfPages" name="numberOfPages" field="${book.numberOfPages}" required="required">
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="language"> Language:</label>
        </div>
        <div class="col-75">
            <input type="text" id="language" name="language" field="${book.language}" required="required">
        </div>
    </div>

    <!-- Additional fields as needed -->
    <div class="row">
        <div class="col-50">
            <label for="author"> Author:</label>

        </div>

        <div class="col-75">
    <select id="author"name="author.id">
        <option value="">Select an Author</option> <!-- Default option -->
        <c:forEach var="author" items="${authors}">
            <option value="${author.id}">${author.name}</option>
        </c:forEach>
    </select><br>
    </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="category">Category:</label>

        </div>

        <div class="col-75">
            <select id="category"name="category.id">
                <option value="">Select an Category</option> <!-- Default option -->
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.category}</option>
                </c:forEach>
            </select><br>
        </div>
    </div>
    <div class="row">
        <div class="col-50">
    Availability:
        </div>
        <div class="col-75">
            <input type="checkbox" name="available"><br>
        </div>
    </div>
    <div class="row">
        <div class="col-50">
            <label for="image">Image:</label>
        </div>
        <div class="col-75">
            <input type="file" id="image" name="image" accept="image/*">
        </div>
    </div>

    <!-- Additional fields as needed -->
    <div class="row">
        <div class="col-50">
    <input type="submit" value="Add Book">
        </div>
    </div>
</form>
</div>
</body>
</html>
