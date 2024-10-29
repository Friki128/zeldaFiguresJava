<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar">
    <div class="baseMenu">
        <a href="/">Main</a></li>
        <a href="/viewPublicDrawings">Public</a>
        <a href="/viewUserDrawings">User</a>
    </div>
    <div class="userMenu">
        <a href="/deleteUser">Delete Account</a>
        <a href="/logout">Log out</a>
    </div>
</div>