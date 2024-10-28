<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <title>Login</title>
</head>
<body>
    <div class = "panel">
        <h1>Login</h1>
        <form action="" method="post">
            <label for="name">Name</label>
            <input type="text" name="name" id="name">
           <label for="password">Password</label>
            <input type="password" name="password" id="password">
            <input type="submit" value="Login" id="submit">
        </form>
        <p id="error">${error}</p>
        <a href="/register">Register</a>
    </div>
    
</body>
</html>