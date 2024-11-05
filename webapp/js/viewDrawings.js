import { drawInCanvas } from "./loadDrawing.js";
let addButton = document.getElementById("addButton");
let fuseButton = document.getElementById("fuseButton");
let nameMenu = document.getElementById("nameMenu");
let nameForm = document.getElementById("nameForm");
let nameInput = document.getElementById("name");
let canvases = document.getElementsByClassName("drawingCanvas")
addButton.onclick = function(){
    nameInput.value = "New_Drawing";
    nameForm.setAttribute("action", "/addDrawing");
    nameMenu.style.display = "block";
}

fuseButton.onclick = function(){
    nameInput.value = "New_Drawing";
    nameForm.setAttribute("action", "/fuse");
    nameMenu.style.display = "block";
}


window.onclick = function(event) {
    if (event.target == nameMenu) {
      nameMenu.style.display = "none";
    }
  }

for(let i=0;i<canvases.length;i++){
  let picture = document.getElementById("pic" + canvases[i].id).value;
  drawInCanvas(canvases[i], JSON.parse(picture))
}
