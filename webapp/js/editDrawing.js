import { drawInCanvas } from "./loadDrawing.js";
import { getDrawing } from "./getDrawing.js";
import { updateVersion } from "./addVersion.js";
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
let id = document.getElementById("id");
let updateDiv = document.getElementById("updateDiv");
let update = document.getElementById("update");
let undo = document.getElementById("undo");
let redo = document.getElementById("redo");
let jsonList = "{}"
let currentFigureId = 0;
let deleteButtons;
let pencilStatus = false;
let lineStatus = false;
let dragging = false;
let lineInitialX = 0;
let lineInitialY = 0;
let positions = [];
let version = []
let versionId = 0;
let canvasBound = canvas.getBoundingClientRect();
let selectedFigure = null;
const Yoffset = canvasBound.top;
const Xoffset = canvasBound.left;

function getPercentage(value){
    return (value * 100) / canvas.width;
}

function updateDrawing(){
    saveEditor()
    picture.value = JSON.stringify(jsonList);
    updateVersion(id.value, JSON.stringify(jsonList));
    currentFigureId = drawInCanvas(canvas, jsonList);
    updateList();
}

function updateList(){
    let result = "";
    for(let figure in jsonList){
        result += "<div class=\"logItem\"><Button class=\"logDeleteButton\" id=\""+ figure +"\">X</Button><p class=\"logName\" id=\"" + figure +"\">" + jsonList[figure].type + "</p></div>"
    }
    itemList.innerHTML = result;
    deleteButtons = document.getElementsByClassName("logDeleteButton");
    for (let i = 0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener('click', function() {
            removeFigure(this.id);
        });
    }
    let logNames = document.getElementsByClassName("logName");
    for(let i=0; i<logNames.length;i++){
        logNames[i].addEventListener('click', function(){
            selectedFigure = jsonList[this.id]
            setInputsValue(selectedFigure);
            changeInputs(selectedFigure.type, true)
        })
    }
    }


function removeFigure(id){
    delete jsonList[id];
    updateDrawing();
}

drawingType.onchange = function(){
    selectedFigure = null
    changeInputs(drawingType.value, false);
}

function changeInputs(value, edit){
    switch(value){
        case "star":
        changeVisibility(false, true, true, edit);
        break;
        case "line":
        case "pencil":
            changeVisibility(false, false, false, edit);
            break;
        case "select":
            changeVisibility(true, true, true, edit)
        default:
            changeVisibility(true, true, false, edit);
            break;
    }
}

function setInputsValue(figure){
    color.value = figure.color
    width.value = figure.width
    filled.value = figure.filled
    switch(figure.type){
        case "star":
            points.value = figure.points
        default:
            height.value = figure.height
    }
}

function changeVisibility(heightVisible, filledVisible, pointsVisible, updateVisible){
    heightDiv.classList.add("hidden");
    filledDiv.classList.add("hidden");
    pointsDiv.classList.add("hidden");
    updateDiv.classList.add("hidden");
    if(heightVisible) heightDiv.classList.remove("hidden");
    if(filledVisible) filledDiv.classList.remove("hidden");
    if(pointsVisible) pointsDiv.classList.remove("hidden");
    if(updateVisible) updateDiv.classList.remove("hidden");
}

function addVersion(){
    clearVersions()
    versionId += 1
    version.push(JSON.stringify(jsonList))
}

function clearVersions(){
    for(let i = versionId; i<version.length-1 ;i++){
        version.pop()
    }
}

undo.onclick = function(){
    versionId -= 1
    if(versionId < 0) versionId = 0
    jsonList = JSON.parse(version[versionId])
    updateDrawing()
}

redo.onclick = function(){
    versionId += 1
    if(versionId >= version.length) versionId = version.length - 1
    jsonList = JSON.parse(version[versionId])
    updateDrawing()
}

clearButton.onclick = function () {
    jsonList = {}; 
    addVersion()
    updateDrawing();
}

document.onmousemove = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    if(pencilStatus == true){
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
    if(dragging && selectedFigure != null){
        selectedFigure.x = x
        selectedFigure.y = y
        jsonList[selectedFigure.id]= selectedFigure
        drawInCanvas(canvas, jsonList)
    }
}

document.onmouseup = function(){
    if(dragging == true){
        addVersion()
        updateDrawing()
        dragging = false
    }
    
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
        addVersion()
        updateDrawing()
    }
}

canvas.onmousedown = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    if(pencilStatus == false && drawingType.value == "pencil"){
        pencilStatus = true;
    }
    if(drawingType.value == "select"){
        selectedFigure = findSelected(x,y)
        console.log(selectedFigure)
        if(selectedFigure != null){
            setInputsValue(selectedFigure);
            changeInputs(selectedFigure.type, true)
            dragging = true;
        }
    }
}

canvas.onclick = function(event){
    let x = getPercentage(event.clientX - Xoffset);
    let y = getPercentage(event.clientY - Yoffset);
    updateFigureList(currentFigureId, x, y, drawingType.value);
}

update.onclick = function(){
    if(selectedFigure == null) return;
    updateFigureList(selectedFigure.id, selectedFigure.x, selectedFigure.y, selectedFigure.type);
}

function updateFigureList(id, x, y, type){
    switch(type){
        case "star":
            let star = {
                "id": id, 
                "type": type, 
                "x": x, 
                "y": y, 
                "width": width.value, 
                "filled": filled.checked, 
                "color": color.value,
                "points": points.value
            }
            jsonList[id] = star;
            break;
        case "line":
            if(lineStatus){
                lineStatus = false;
                let line = {
                    "id": id, 
                    "type":type, 
                    "x1": lineInitialX, 
                    "y1": lineInitialY, 
                    "x2": x, 
                    "y2": y
                }
                jsonList[id] = line;
            }else{
                lineStatus = true;
                lineInitialX = x;
                lineInitialY = y;
            }
            break;
        case "pencil":
            break;
        case "select":
            break    
        default:
            let figure = {
                "id": id, 
                "type": type, 
                "x": x, 
                "y": y, 
                "width": width.value, 
                "height": height.value, 
                "filled": filled.checked, 
                "color": color.value
            }
            jsonList[id] = figure;
            break;
    }
    addVersion()
    updateDrawing();

}

function findSelected(x, y){
    let result = null;
    for(let figure in jsonList){
        let object = jsonList[figure]
        if(object.type != "line" && object.type != "pencil"){
            let positions = [object.x, object.y]
            let figureWidth = object.width
            let figureHeight = figureWidth
            if(object.type != "star") figureHeight = object.height
            if(collided(positions, [x,y], figureWidth, figureHeight))result = object
        } 
    }
    return result;
}

function collided(position, mousePosition, width, height){
    return ((mousePosition[0] > position[0] - width/2) && (mousePosition[0] < position[0] + width/2)) && ((mousePosition[1] > position[1] - height/2) && (mousePosition[1] < position[1] + height/2))
}

function saveEditor(){
    localStorage.setItem("points", points.value)
    localStorage.setItem("width", width.value)
    localStorage.setItem("height", height.value)
    localStorage.setItem("color", color.value)
    localStorage.setItem("type", drawingType.value)
    localStorage.setItem("filled", filled.value)
    save
}

async function start(){
    let drawing = await getDrawing(id.value);
    jsonList = drawing;
    version[0] = JSON.stringify(jsonList)
    versionId = 0
    points.value = localStorage.getItem("points")
    width.value = localStorage.getItem("width")
    height.value = localStorage.getItem("height")
    filled.value = localStorage.getItem("filled")
    drawingType.value = localStorage.getItem("type")
    color.value = localStorage.getItem("color")
    updateDrawing();
    
}
start();