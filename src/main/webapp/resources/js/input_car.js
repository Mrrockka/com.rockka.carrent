'use strict'

function save(){
    var xhr = new XMLHttpRequest();
	var i = 0, json = "", doc = document.forms["car"], text = "no";
    json = "{";
    for(i=0; i<document.forms["car"].length; i++){
    	if(doc[i].type != "button"){
    		json += "\"" + doc[i].id + "\"" + ":" +"\""+ doc[i].value +"\""+ ",";
        } else {
        	json = json.slice(0, -1);
        }
    }
    json += "\"}";

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            text = this.responseText;
            alert(text);
        }
    }
	xhr.open("POST", '/car/save', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}