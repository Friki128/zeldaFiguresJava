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
    <title>Draw</title>
</head>
<body>
    <%@include file="./nav-bar.jsp" %>
    <h1 class="nameText">${name}</h1>
    <div class="editDrawingBase">
        <div class="editDrawingInputs">
            <div class="editDiv">
                <label for="type">Type</label>
                <select name="type" id="type">
                    <option value="rectangle" selected>Rectangle</option>
                    <option value="oval">Oval</option>
                    <option value="triangle">Triangle</option>
                    <option value="star">Star</option>
                    <option value="line">Line</option>
                    <option value="pencil">Pencil</option>
                    <option value="select">Selec</option>
                </select>
            </div>
            <div class="editDiv">
                <label for="width">Width</label>
                <input type="range" name="width" id="width" min="5" max="50">
            </div>
            <div class="editDiv" id="heightDiv">
                <label for="height">Height</label>
                <input type="range" name="height" id="height" min="5" max="50">
            </div>
            <div class="editDiv" id="filledDiv">
                <label for="filled">Filled</label>
                <input type="checkbox" name="filled" id="filled">
            </div>
            <div class="editDiv">
                <label for="color">Color</label>
                <input type="color" name="color" id="color">
            </div>
            <div class="editDiv hidden" id="pointsDiv">
                <label for="points">Points</label>
                <input type="number" name="points" id="points" value="7">
            </div>
            <div class="editDiv hidden" id="updateDiv">
                <label for="edit">Update</label>
                <input type="button" value="Update" id="update">
            </div>
            
        </div>
        <div class="editDrawingCanvasBox">
            <canvas id="editDrawingCanvas" class="canvasVersion" width="600px" height="600px"></canvas>
            <div id="itemList">
            </div>
        </div>
        <div class="editForm">
            <button id="clearButton" class="generalButton">Clear</button>
            <form action="/addVersion" method="post">
                <input type="number" name="id" id="id" hidden value="${drawingId}">
                <input type="text" name="picture" id="picture" hidden>
                <input type="submit" value="Add Version" class="generalButton">
            </form>
        </div>
    </div>
   <script src="/js/editDrawing.js" type="module"></script>
</body>
</html>