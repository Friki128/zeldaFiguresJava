let nameButton = document.getElementById("nameButton");
let nameMenu = document.getElementById("nameMenu");
let nameInput = document.getElementById("name");
let nameText = document.getElementById("nameText");
nameButton.onclick = function(){
    nameInput.value = nameText.textContent;
    nameMenu.style.display = "block";
}
window.onclick = function(event) {
    if (event.target == nameMenu) {
      nameMenu.style.display = "none";
    }
  }