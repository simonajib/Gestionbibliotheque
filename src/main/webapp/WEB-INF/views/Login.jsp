<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="CSS/security-layout.css">
</head>
<body>
<div class="wrapper">
    <div class="container">

        <form action="/login" method="POST">

            <div class="row">
                <div class="col-25">
                    <label for="login">Username</label>
                </div>
                <div class="col-75">
                    <input type="text" id="login" name="login"/>
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="password">Password</label>
                </div>
                <div class="col-75">
                    <input type="password" id="password" name="password"/>
                </div>
            </div>
            <div class="form-actions">
                <input type="submit" value="Login">
            </div>

        </form>
        <form action="/register" method="GET">
            <div class="row">
                <input type="submit" value="Register">
            </div>
        </form>
    </div>
</div>
</body>
</html>
