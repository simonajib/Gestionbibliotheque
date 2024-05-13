<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Author</title>
    <link rel="stylesheet" type="text/css" href="css/author.css">

    <style>
        /* Add specific CSS styles here if needed */
    </style>
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
<br>

<div class="container">
    <form action="/addAuthor" object="${author}" method="POST">
        <div class="box">
            <h3>Add Author</h3>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="name">Name:</label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" required>
            </div>
            <div class="row">
                <input type="submit" value="Save">
            </div>
        </div>

    </form>

</div>

</body>
</html>
