'use strict';
// TODO: rewrite with JQuery
function registerNewOrder(){
    var xhr = new XMLHttpRequest();
    var url = '/invoice/save/' + document.getElementById("carid").innerHTML;
	var i = 0, json = "", doc = document.forms["invoice"];
    json = "{";
    for(i=0; i<doc.length; i++){
    	if(doc[i].type == "button"){
    		continue;
        }
        json += "\"" + doc[i].id + "\"" + ":" +"\""+ doc[i].value +"\""+ ",";
    }

    json = json.slice(0, -1);
    json += "}";

    xhr.onreadystatechange= function(){
        if(this.readyState==4 && this.status==200){
            var text =this.responseText;
            if(text == "success"){
                window.location.href = '/user/account';
            }
        }
    }
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}


function setPrice(){
    if(document.getElementById("startsAt").value != null && document.getElementById("expiresAt").value != null){
        document.getElementById("price").value = 200;
    }
}