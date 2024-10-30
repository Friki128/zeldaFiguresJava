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
    <c:if test="${mode =='user'}">
    <div class="createDrawingMenu">
        <a href="/addDrawing">New</a>
        <a href="/fuseDrawings">Fuse</a>
    </div>
</c:if>

    <div class="drawingsDiv">
        <div class="drawingDiv">
            <div class="canvasBox">
                <canvas class="drawingCanvas"></canvas>
                <label>
                    <input type="checkbox" name="selector" id="selector" class="selector">
                </label>
            </div>
            <a href="/" class="drawingTitle">Name</a>
        </div>
        <div class="drawingDiv">
            <div class="canvasBox">
                <canvas class="drawingCanvas"></canvas>
                <label>
                    <input type="checkbox" name="selector" id="selector" class="selector">
                </label>
            </div>
            <a href="/" class="drawingTitle">Name</a>
        </div>
        <div class="drawingDiv">
            <div class="canvasBox">
                <canvas class="drawingCanvas"></canvas>
                <label>
                    <input type="checkbox" name="selector" id="selector" class="selector">
                </label>
            </div>
            <a href="/" class="drawingTitle">Name</a>
        </div>
        <div class="drawingDiv">
            <div class="canvasBox">
                <canvas class="drawingCanvas"></canvas>
                <label>
                    <input type="checkbox" name="selector" id="selector" class="selector">
                </label>
            </div>
            <a href="/" class="drawingTitle">Name</a>
            
        </div>
        <div class="drawingDiv">
            <div class="canvasBox">
                <canvas class="drawingCanvas"></canvas>
                <label>
                    <input type="checkbox" name="selector" id="selector" class="selector">
                </label>
            </div>
            <a href="/" class="drawingTitle">Name</a>
            
        </div>
        <div class="drawingDiv">
            <div class="canvasBox">
                <canvas class="drawingCanvas"></canvas>
                <label>
                    <input type="checkbox" name="selector" id="selector" class="selector">
                </label>
            </div>
            <a href="/" class="drawingTitle">Name</a>
            
        </div>
    </div>
</body>
</html>