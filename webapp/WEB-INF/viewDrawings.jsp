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
    <title>Drawings</title>
</head>
<body>
    <%@include file="./nav-bar.jsp" %>
    <div id="nameMenu">
        <div id="nameMenuContent">
            <form action="/addDrawing" method="post" id="nameForm">
                <label for="name">Name</label>
                <input class="normalInput" type="text" name="name" id="name">
                <input class="normalInput" type="submit" value="Create" id="submit">
            </form>
        </div>
    </div>
    <c:if test="${mode =='user'}">
    <div class="createDrawingMenu">
        <button id="addButton">New</button>
        <button id="fuseButton">Fuse</button>
    </div>
</c:if>

    <div class="drawingsDiv">


        <c:forEach var="drawing" items="${drawings}" >
            <div class="drawingDiv">
                <div class="canvasBox">
                    <canvas class="drawingCanvas" drawing="${drawing.picture}"></canvas>
                    <label>
                        <input type="checkbox" name="selector" id="selector" drawingId="${drawing.drawingId}" class="selector">
                    </label>
                </div>
                <a href="/viewDrawing?id=${drawing.drawingId}" class="drawingTitle">${drawing.name}</a>
            </div>
        </c:forEach>
    </div>
    <script src="/js/viewDrawings.js"></script>
</body>
</html>