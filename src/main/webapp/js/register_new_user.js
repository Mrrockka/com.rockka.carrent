'use strict';
// TODO: rewrite with JQuery
function registerNewUser(){
    var xhr = new XMLHttpRequest();
	var i = 0, json = "", doc = document.forms["user"];
    json = "{";
    for(i=0; i<doc.length; i++){
    	if(doc[i].type == "button"){
    		continue;
        }
        if(doc[i].id == "password_check"){
    		continue;
        }
        json += "\"" + doc[i].id + "\"" + ":" +"\""+ doc[i].value +"\""+ ",";
    }

    json = json.slice(0, -1);
    json += "}";

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            var text = this.responseText;
            alert(text);
            if(text == "success"){
                window.location.href = '/login';
            }
        }
    }
	xhr.open("POST", '/register_new_user', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}
