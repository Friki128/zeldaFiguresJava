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
    <title>${name}</title>
</head>
<body>
    <%@include file="./nav-bar.jsp" %>
    <div id="nameMenu">
        <div id="nameMenuContent">
            <form action="/updateDrawingName" method="post" id="nameForm">
                <input type="number" name="drawingId" id="drawingId" hidden value="${drawingId}">
                <label for="name">Name</label>
                <input class="normalInput" type="text" name="name" id="name">
                <input class="normalInput" type="submit" value="Create" id="submit">
            </form>
        </div>
    </div>

    <div class="versionViewDiv">
        <div class="versionViewDivInternal">
            <div class="versionNameMenu">
                <p id="nameText">${name}</p>
                <c:if test="${isOwner}">
                    <button id="nameButton" class="generalButton">Change Name</button>
                </c:if>    
            </div>
            <p class="versionText">Creator: ${creator}</p>
            <c:if test="${isOwner}">
            <form class="versionForm" action="/updateDrawingPublicStatus" method="post">
                <input type="number" name="id" id="id" hidden value="${drawingId}">
                <label for="status">Public status: </label>
                    <c:if test="${isPublic}">
                        <input type="checkbox" name="status" id="status" checked>
                    </c:if>    
                    <c:if test="${!isPublic}">
                        <input type="checkbox" name="status" id="status">
                    </c:if>
                <input type="submit" value="Update">  
            </form>
            </c:if>
            <p class="versionText">Number of Elements: ${elements}</p>
            <p class="versionText">Creation date: ${creationDate}</p>
            <p class="versionText">Version date: ${updateDate}</p>
            <a class="editButton" href="/clone?id=${drawingId}">Clone</a>
            <a class="editButton" href="/viewVersionsOfDrawing?id=${drawingId}">View Versions</a>
            <c:if test="${isOwner}">
                <a class="editButton" href="/deleteDrawing?id=${drawingId}">Delete</a>
            </c:if>
        </div>
        <div class="versionViewDivInternal">
            <canvas class="canvasVersion" picture="${picture}"></canvas>
            <c:if test="${isOwner}">
                <a class="editButton" href="/editDrawing?drawingId=${drawingId}">Edit</a>
            </c:if>
        </div>
    </div>
</body>
</html>