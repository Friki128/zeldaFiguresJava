let canvasContext;
let size;
export function drawInCanvas(canvas, json){
    canvasContext = canvas.getContext("2d");
    size = canvas.width;
    let id = 0;
    canvasContext.clearRect(0, 0, canvas.width, canvas.height)
    for(let figure in json){
        if(figure >= id) id = figure + 1;
        let object = json[figure]
        switch(object.type){
            case "oval":
                drawOval(adaptSize(object.x), adaptSize(object.y), adaptSize(object.width), adaptSize(object.height), object.color, object.filled);
                break;
            case "rectangle":
                drawRectangle(adaptSize(object.x), adaptSize(object.y), adaptSize(object.width), adaptSize(object.height), object.color, object.filled);
                break;
            case "triangle":
                drawTriangle(adaptSize(object.x), adaptSize(object.y), adaptSize(object.width), adaptSize(object.height), object.color, object.filled);
                break;
            case "star":
                drawStar(adaptSize(object.x), adaptSize(object.y), adaptSize(object.width), object.color, object.filled, object.points);
                break;
            case "line":
                drawLine(object.color, adaptSize(object.x1), adaptSize(object.y1), adaptSize(object.x2), adaptSize(object.y2));
                break;
            case "pencil":
                drawPencil(object.positions, object.color);
                break;
        }
    }
    return id;
}

function adaptSize(value){
    return (size*value)/100
}

function drawLine(color, x1, y1, x2, y2){
    canvasContext.strokeStyle = color;
    canvasContext.beginPath();
    canvasContext.moveTo(x1 , y1);
    canvasContext.lineTo(x2, y2);
    canvasContext.stroke();
}

function drawPencil(positions, color){
    canvasContext.strokeStyle = color;
    canvasContext.beginPath();
    canvasContext.moveTo(adaptSize(positions[0][0]) , adaptSize(positions[0][1]));
    positions.forEach(element => {
        canvasContext.lineTo(adaptSize(element[0]), adaptSize(element[1]));
    });
    canvasContext.stroke();
}

function drawTriangle(x,y,width,height,color,filled){
    canvasContext.fillStyle = color;
    canvasContext.strokeStyle = color;
    canvasContext.beginPath();
    canvasContext.moveTo(x - width/2 , y + height/2);
    canvasContext.lineTo(x + width/2, y + height/2);
    canvasContext.lineTo(x, y - height/2);
    canvasContext.closePath();
    canvasContext.stroke();
    if(filled){
        canvasContext.fill();
    }
}

function drawOval(x,y,width,height,color,filled){
    canvasContext.fillStyle = color;
    canvasContext.strokeStyle = color;
    canvasContext.beginPath();
    canvasContext.ellipse(x, y, width/2, height/2, 0, 0, 2 * Math.PI)
    canvasContext.stroke();
    if(filled){
        canvasContext.fill();
    }
}

function getStarPositions(x, y, size, rotation, points){
    let turn = 360/points;
    let result = [];
    for(let i=0; i<points; i++){
        let angle = rotation + turn*i;
        let Xchange = (size/2) * Math.sin(Math.PI * 2 * angle / 360);
        let Ychange = (size/2) * Math.cos(Math.PI * 2 * angle / 360);
        let newX = x + Xchange;
        let newY = y + Ychange;

        result.push([newX, newY]);


    }
    return result;
}

function drawStar(x,y,width,color,filled,points){
    let turn = 360/points;
    let OuterPositions = getStarPositions(x, y, width, turn/2, points);
    let InnerPositions = getStarPositions(x, y, width/2, turn, points);
    canvasContext.fillStyle = color;
    canvasContext.strokeStyle = color;
    canvasContext.beginPath();
    canvasContext.moveTo(x, y + width/4);
    for(let i=0;i<points;i++){
        canvasContext.lineTo(OuterPositions[i][0], OuterPositions[i][1]);
        canvasContext.lineTo(InnerPositions[i][0], InnerPositions[i][1]);
    }
    canvasContext.closePath();
    canvasContext.stroke();
    if(filled){
        canvasContext.fill();
    }
}

function drawRectangle(x, y, width, height, color, filled){
    let actual_x = x - width/2;
    let actual_y = y - height/2
    canvasContext.fillStyle = color;
    canvasContext.strokeStyle = color;
    if(filled){
        canvasContext.fillRect(actual_x, actual_y, width, height);
    }else{
        canvasContext.strokeRect(actual_x, actual_y, width, height);
    }

}