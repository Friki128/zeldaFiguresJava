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
    <title>Versions</title>
</head>
<body>
    <%@include file="./nav-bar.jsp" %>
    <div class="versionsListDiv">
        <h1>${name}</h1>
        <table>
            <thead>
                <th>Make current</th>
                <th>Date</th>
                <th>Picture</th>
            </thead>
            <tbody>
                <c:forEach var="version" items="${versions}">
                <td><a href="/makeCurrent?drawingId=${drawingId}&versionId=${version.id}" class="editButton">Make current</a></td>
                <td><a href="/viewDrawingVersion?drawingId=${drawingId}&versionId=${version.id}" class="editButton">${version.date}</a></td>
                <td><canvas class="drawingCanvas" drawing="${drawing.picture}"></canvas></td>
                <td><a href="/deleteVersion?id=${drawingId}&versionId=${version.id}" class="editButton">Delete</a></td>
           
        </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>