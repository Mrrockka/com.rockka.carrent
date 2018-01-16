'use strict'

function save(){
    var xhr = new XMLHttpRequest();
	var i = 0, json = "", doc = document.forms["user"], text = "no";
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
            text = this.responseText;
            if(text == "welcome"){
                window.open("/welcome")
            }
            alert(text);
        }
    }
	xhr.open("POST", '/register', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}
