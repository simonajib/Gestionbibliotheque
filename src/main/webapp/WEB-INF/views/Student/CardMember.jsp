<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Membership Duration Selection</title>
</head>
<body>
<h1>Choose Membership Duration</h1>
<form action="submitMembershipForm" method="post">
    <label for="duration">Select Duration:</label>
    <select name="duration" id="duration">
        <option value="1">1 Month</option>
        <option value="3">3 Months</option>
        <option value="6">6 Months</option>
        <option value="12">12 Months</option>
    </select>
    <br>
    <input type="hidden" name="userId" value="${user.id}">

    <input type="submit" value="Submit">
</form>
</body>
</html>
