import { drawInCanvas } from "./loadDrawing.js";
let picture = document.getElementById("picture");
let drawingType = document.getElementById("type");
let color = document.getElementById("color");
let filled = document.getElementById("filled");
let width = document.getElementById("width");
let height = document.getElementById("height");
let itemList = document.getElementById("itemList");
let canvas = document.getElementById("editDrawingCanvas");
let clearButton = document.getElementById("clearButton");
let points = document.getElementById("points");
let pointsDiv = document.getElementById("pointsDiv");
let heightDiv = document.getElementById("heightDiv");
let filledDiv = document.getElementById("filledDiv");
let jsonList = JSON.parse(picture.value)
let currentFigureId = 0;
let deleteButtons;
let pencilStatus = false;
let lineStatus = false;
let lineInitialX = 0;
let lineInitialY = 0;
let positions = [];
let canvasBound = canvas.getBoundingClientRect();
const Yoffset = canvasBound.top;
const Xoffset = canvasBound.left;

function getPercentage(value){
    return (value * 100) / canvas.width;
}

function updateDrawing(){
    picture.value = JSON.stringify(jsonList);
    currentFigureId = drawInCanvas(canvas, jsonList);
    updateList();
}

function updateList(){
    let result = "";
    for(let figure in jsonList){
        result += "<div class=\"logItem\"><Button class=\"logDeleteButton\" id=\""+ figure +"\">X</Button><p>" + jsonList[figure].type + "</p></div>"
    }
    itemList.innerHTML = result;
    deleteButtons = document.getElementsByClassName("logDeleteButton");
    for (let i = 0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener('click', function() {
            removeFigure(this.id);
        });
    }
    }


function removeFigure(id){
    delete jsonList[id];
    updateDrawing();
}

drawingType.onchange = function(){
    switch(drawingType.value){
        case "star":
        changeVisibility(false, true, true);
        break;
        case "line":
        case "pencil":
            changeVisibility(false, false, false);
            break;
        default:
            changeVisibility(true, true, false);
            break;
    }
}

function changeVisibility(heightVisible, filledVisible, pointsVisible){
    heightDiv.classList.add("hidden");
    filledDiv.classList.add("hidden");
    pointsDiv.classList.add("hidden");
    if(heightVisible) heightDiv.classList.remove("hidden");
    if(filledVisible) filledDiv.classList.remove("hidden");
    if(pointsVisible) pointsDiv.classList.remove("hidden");
}

clearButton.onclick = function () {
    jsonList = {}; 
    updateDrawing();
}

document.onmousemove = function(event){
    if(pencilStatus == true){
        let x = getPercentage(event.clientX - Xoffset);
        let y = getPercentage(event.clientY - Yoffset);
        positions.push([x,y]);
        let pencil = {
            "id": currentFigureId,
            "type": drawingType.value,
            "positions": positions,
            "color": color.value
        }
        jsonList[currentFigureId] = pencil
        drawInCanvas(canvas, jsonList)
    }
}

document.onmouseup = function(){
    if(pencilStatus == true && positions != []){
        pencilStatus = false;
        let pencil = {
            "id": currentFigureId,
            "type": drawingType.value,
            "positions": positions,
            "color": color.value
        }
        positions = [];
        jsonList[currentFigureId] = pencil
        updateDrawing()
    }
}

canvas.onmousedown = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    if(pencilStatus == false && drawingType.value == "pencil"){
        pencilStatus = true;
    }
}

canvas.onclick = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    switch(drawingType.value){
        case "star":
            let star = {
                "id": currentFigureId, 
                "type": drawingType.value, 
                "x": x, 
                "y": y, 
                "width": width.value, 
                "filled": filled.checked, 
                "color": color.value,
                "points": points.value
            }
            jsonList[currentFigureId] = star;
            break;
        case "line":
            if(lineStatus){
                lineStatus = false;
                let line = {
                    "id": currentFigureId, 
                    "type":drawingType.value, 
                    "x1": lineInitialX, 
                    "y1": lineInitialY, 
                    "x2": x, 
                    "y2": y
                }
                jsonList[currentFigureId] = line;
            }else{
                lineStatus = true;
                lineInitialX = x;
                lineInitialY = y;
            }
            break;
        case "pencil":
            break;
        default:
            let figure = {
                "id": currentFigureId, 
                "type": drawingType.value, 
                "x": x, 
                "y": y, 
                "width": width.value, 
                "height": height.value, 
                "filled": filled.checked, 
                "color": color.value
            }
            jsonList[currentFigureId] = figure;
            break;
    }
    updateDrawing();
}

updateDrawing();