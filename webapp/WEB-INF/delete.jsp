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
    <title>Delete</title>
</head>
<body>
    <div class = "panel">
        <h1>Are you sure you want to proceed with deletion? This action cannot be reverted.</h1>
            <form action="" method="post" class="divPanel">
                <a href="/viewPublicDrawings" class="yesButton">No</a>
                <c:if test="${mode == 'drawing'}">
                    <input type="number" name="id" id="id" hidden value="${drawingId}">
                </c:if>    
                <c:if test="${mode == 'version'}">
                    <input type="number" name="drawingId" id="drawingId" hidden value="${drawingId}">
                    <input type="number" name="versionId" id="versionId" hidden value="${versionId}">
                </c:if>    
                <input type="submit" value="Yes" class="noButton">
            </form>
        
        
    </div>
    
</body>
</html>