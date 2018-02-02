'use strict'
// TODO: rewrite with JQuery
function getUserName(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var info = "<li class=\"nav-item dropdown\">", text = this.responseText;
                if(text != "unknown"){
                    text = JSON.parse(text);
                    info += "<span class=\"nav-link dropdown-toggle\""
                            + "id=\"userdrop\" data-toggle=\"dropdown\""
                            + "aria-haspopup=\"true\" aria-expanded=\"false\">"
                            + text.username + "</span>"
                            + "<div class=\"dropdown-menu\" aria-labelledBy=\"userdrop\">"
                            + "<a class=\"dropdown-item\" href=\"/account\">Account</a>"
                            + "<a class=\"dropdown-item\" href=\"/logout\">Log out</a>"
                            + "</div>";
                } else {
                    info += "<a class=\"nav-link\" href=\"/login\">Log in</a>";
                }

                info += "</li>";

                document.getElementById("name_block").innerHTML = info;
        }
    }

    xhr.open("GET", '/username', true);
    xhr.send();
}
