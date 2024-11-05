import { drawInCanvas } from "./loadDrawing.js";
let addButton = document.getElementById("addButton");
let fuseButton = document.getElementById("fuseButton");
let nameMenu = document.getElementById("nameMenu");
let nameForm = document.getElementById("nameForm");
let nameInput = document.getElementById("name");
let canvases = document.getElementsByClassName("drawingCanvas")
let selectors = document.getElementsByClassName("selector");
let drawingIds = document.getElementById("drawingIds");
let selected = {};

if(addButton != null){
  addButton.onclick = function(){
    nameInput.value = "New_Drawing";
    nameForm.setAttribute("action", "/addDrawing");
    nameMenu.style.display = "block";
}
}

if(fuseButton != null){
  fuseButton.onclick = function(){
    drawingIds.value = JSON.stringify(selected);
     nameInput.value = "New_Drawing";
     nameForm.setAttribute("action", "/fuse");
     nameMenu.style.display = "block";
 }
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

for(let i=0;i<selectors.length;i++){
  selectors[i].addEventListener("click", function(){
      if(selected[selectors[i].id] != null){
        delete selected[selectors[i].id]
      }else{
        selected[selectors[i].id] = selectors[i].id;
      }
  })
}