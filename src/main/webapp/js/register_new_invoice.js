'use strict';
// TODO: rewrite with JQuery
function registerNewInvoice(){
	if(areDatesValid()){
		var xhr = new XMLHttpRequest();
		var url = '/invoice/register/' + document.getElementById("carid").innerHTML;
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
				window.location.href = '/payment/get_form/' + this.responseText;
			}
		}
		xhr.open("POST", url, true);
		xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
		xhr.send(json);
	}
}


function setPrice(){
    if(document.getElementById("startsAt").value != null && document.getElementById("expiresAt").value != null){
        document.getElementById("price").value = 200;
    }
}

function areDatesValid(){
	var first = $("startsAt").val(), second = $("expiresAt").val();
	if(first ==null && second == null){
		alert("Invalid dates");
		return false;
	}

	first = new Date(first).getTime();
	second = new Date(second).getTime();

	if(first>= second){
		alert("Invalid dates");
		return false;
	}

	return true;
}