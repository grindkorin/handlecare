//parallax
let stars = document.getElementById('stars');
let moon = document.getElementById('moon');
let pine = document.getElementById('pine');
let mountain = document.getElementById('mountain');
let houses = document.getElementById('houses');
let building = document.getElementById('building');
let clouds = document.getElementById('clouds');
let text = document.getElementById('textH');
window.addEventListener('scroll', function () {
    let value = window.scrollY;
    stars.style.left = value * 0.25 + 'px';
    moon.style.top = value * 1.05 + 'px';
    pine.style.top = value * 0.2 + 'px';
    mountain.style.top = value * 0.4 + 'px';
    text.style.bottom = value * 1 + 'px';
    clouds.style.top = value * 1.05 + 'px';
    building.style.top = value * 0.4 + 'px';
})
//
//hidden
let field = document.getElementById("popU");
function hide() {
    if(field.style.display === "none") {
        field.style.display = "flex";
    }else{
        field.style.display = "none";
    }
}
let userPic = document.getElementById("userPic");
userPic.onclick.caller(hide());

//passResetCheck НЕ РАБОТАЕТ! Проверить TODO
function checkPasswordMatch(fieldConfirmPassword) {
    if (fieldConfirmPassword.value != $("#password").val()) {
        fieldConfirmPassword.setCustomValidity("Passwords do not match!");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }
}