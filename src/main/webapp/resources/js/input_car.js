'use strict'

function save(){
    var xhr = new XMLHttpRequest();
	var i = 0, json = "", doc = document.forms["car"];
    json = "{";
    for(i=0; i<document.forms["car"].length; i++){
    	if(doc[i].type != "button"){
    		json += "\"" + doc[i].id + "\"" + ":" +"\""+ doc[i].value +"\""+ ",";
        } else {
        	json = json.substring(0, json.length-1);
        }
    }
    json += "}";

	xhr.open("POST", '/car/info', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}