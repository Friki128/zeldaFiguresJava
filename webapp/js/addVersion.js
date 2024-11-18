export function updateVersion(id, picture){
    let data = new URLSearchParams();
    data.append("id", id);
    data.append("picture", picture);
    fetch("/updateVersion", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: data
    })
}