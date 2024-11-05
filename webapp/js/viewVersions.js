import { drawInCanvas } from "./loadDrawing.js";
let canvases = document.getElementsByClassName("drawingCanvas")
for(let i=0;i<canvases.length;i++){
    let picture = document.getElementById("pic" + canvases[i].id).value;
    drawInCanvas(canvases[i], JSON.parse(picture))
  }
  