
export function getDrawing(id){
   return new Promise((resolve, effect) =>{ 
    fetch("/getDrawing?id="+ id, {
        method: "GET"
    }).then(res=>{
        return res.json();
    }).then(data=>{
        resolve(data);
    })
});}