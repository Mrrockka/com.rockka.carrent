'use strict'

function getUserName(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            changeDiv(this.responseText);
        }
    }

    xhr.open("GET", '/username', true);
    xhr.send();
}

function changeDiv(user){

    var text = "<li class=\"nav-item dropdown\">";
    if(user != "unknown"){
        user = JSON.parse(user);
        text += "<span class=\"nav-link dropdown-toggle\""
                + "id=\"userdrop\" data-toggle=\"dropdown\""
                + "aria-haspopup=\"true\" aria-expanded=\"false\">"
                + user.nickname + "</span>"
                + "<div class=\"dropdown-menu\" aria-labelledBy=\"userdrop\">"
                + "<a class=\"dropdown-item\" href=\"/account\">Account</a>"
                + "<a class=\"dropdown-item\" href=\"/settings\">Settings</a>"
                + "<a class=\"dropdown-item\" href=\"/logout\">Log out</a>"
                + "</div>";
    } else {
        text += "<a class=\"nav-link\" href=\"/login\">Log in</a>";
    }

    text += "</li>";

    document.getElementById("username").innerHTML = text;

}
