<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <title>Account Created</title>
    <link rel="stylesheet" type="text/css" href="css/accountcreate.css">
</head>
<body>
        <div class="wrapperRegister">
            <div class="containerRegister">
                <p>Account created!</p>
                <form action="/login" method="GET">
                    <input class="accountCreatedButton" type="submit" value="Go back to login page"/>
                </form>
            </div>
        </div>


</body>
</html>
