'use strict'
// TODO: rewrite with JQuery
function createAdminAccount(){
    showInvoices();
}

function newCar(){
    var info = ""
        + "<div class=\"container\">"
        + "<form class=\"form-std\" name=\"car\">"
        + "<h2 class=\"form-std-heading\">Add new car</h2>"
        + "<label for=\"name\" class=\"sr-only\">Mark</label>"
        + "<input type=\"text\" id=\"name\" class=\"form-control\" placeholder=\"Mark and model\" required autofocus>"
        + "<label for=\"price\" class=\"sr-only\">Price</label>"
        + "<input type=\"number\" min=\"0\" id=\"price\" class=\"form-control\" placeholder=\"0.00\" required>"
        + "<label for=\"releaseDate\" class=\"sr-only\">Year</label>"
        + "<input type=\"date\" id=\"releaseDate\" class=\"form-control\" required>"
        + "<input class=\"btn btn-lg btn-primary btn-block\" value=\"save\" type=\"button\" onclick=\"registerNewCar()\">"
        + "</form></div>";
    document.getElementById("info_div").innerHTML = info;
}

function showInvoices(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<table class=\"table table-striped\"><h2 class=\"form-std-heading\">Invoices</h2>"
                +"<thead> <tr>"
                +"<th>Id</th> <th>User</th> <th>Car</th> <th>Starts</th>"
                +"<th>Expires</th> <th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";

            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].invoice_id+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].username+"</td>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].car_name+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].starts_at+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].expires_at+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].price+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showInvoiceById("
                    +json[i].invoice_id + ");return false;\">"+json[i].status+"</a></td>"
                    + "</tr>";
            }

            info += "</tbody></table>";
            document.getElementById("info_div").innerHTML = info;
        }
    }
    xhr.open("GET", '/admin/invoice/show_all', true);
    xhr.send();
}

function showUsers(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<table class=\"table table-striped\"><h2 class=\"form-std-heading\">Users</h2>"
                +"<thead> <tr>"
                +"<th>User name</th> <th>Roles</th> <th>First name</th> <th>Second name</th>"
                +"<th>Birthday</th> <th>Address</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].username+"</a></td>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].roles+"</a></td>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].firstname+"</a></td>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].secondname+"</a></td>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].birthday+"</a></td>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].address+"</a></td>"
                    + "<td><a href=\"/admin/user/"+json[i].username + "\">"+json[i].status+"</a></td>"
                    + "</tr></a>"
            }

            info += "</tbody>";
            document.getElementById("info_div").innerHTML = info;
        }
    }
    xhr.open("GET", '/admin/user/show_all', true);
    xhr.send();
}

function showCars(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info ="<table class=\"table table-striped\"><h2 class=\"form-std-heading\">Cars</h2>"
                +"<thead> <tr>"
                +"<th>Id</th> <th>Name</th> <th>Color</th> <th>Release date</th>"
                +"<th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"/admin/car/"+json[i].car_id + "\">"+json[i].car_id+"</a></td>"
                    + "<td><a href=\"/admin/car/"+json[i].car_id + "\">"+json[i].name+"</a></td>"
                    + "<td><a href=\"/admin/car/"+json[i].car_id + "\">"+json[i].color+"</a></td>"
                    + "<td><a href=\"/admin/car/"+json[i].car_id + "\">"+json[i].release_date+"</a></td>"
                    + "<td><a href=\"/admin/car/"+json[i].car_id + "\">"+json[i].price+"</a></td>"
                    + "<td><a href=\"/admin/car/"+json[i].car_id + "\">"+json[i].status+"</a></td>"
                    + "</tr>"
            }

            info += "</tbody>";
            document.getElementById("info_div").innerHTML = info;
        }
    }
    xhr.open("GET", '/admin/car/show_all', true);
    xhr.send();

}


function registerNewCar(){
    var xhr = new XMLHttpRequest();
	var i = 0, json = "", form = document.forms["car"], text = "no";
    json = "{";
    for(i=0; i<form.length; i++){
        var element = form.elements[i];
    	if(element.type != "button"){
    		json += "\"" + element.id + "\"" + ":" +"\""+ element.value +"\""+ ",";
        } else {
        	json = json.slice(0, -1);
        }
    }
    json += "}";

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            text = this.responseText;
            alert(text);
            if(text == "success"){
                registerNewCar();
            }
        }
    }
	xhr.open("POST", '/admin/car/save', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}

function showInvoiceById(id){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            var json = JSON.parse(this.responseText);

            var info = "<div class=\"container\">"
                    +"<h2>Rent car invoice</h2>"
                    +"<p>Invoice id: <span id=\"invoice_id\">"+json.invoice_id + "</span></p>"
                    +"<p>Birthday: <span id=\"birthday\">"+ json.birthday +"</span></p>"
                    +"<p>Car id: <span id=\"car_id\">>"+json.car_id+"</span></p>"
                    +"<p>Car name: <span id=\"car_name\">>"+json.car_name+"</span></p>"
                    +"<Username: <span id=\"username\">>"+json.username+"</span></p>"
                    +"<p>Car price: <span id=\"\">" + json.car_price+"</span></p>"
                    +"<p>Starts: <span id=\"\">"+ json.starts_at+"</span></p>"
                    +"<p>Expires: <span id=\"\">"+ json.expires_at+"</span></p>"
                    +"<p>Invoice price: <span id=\"\">" + json.invoice_price+"</span></p>"
                    +"<p>Description: <span  id=\"description\"" + json.description+"</span></p>"
                    +"<p>Status: <select id=\"statusValues\" onchange=\"checkInvoice()\">";
            var i;
            for(i=0; i<json.statusValues.length; i++){
                info+= "<option>" + json.statusValues[i].toString+ "</option>";
            }
            info+= "</select></p>"
                    +"<p><a id=\"button\" class=\"btn btn-lg btn-block btn-primary\" onclick=\"updateInvoice();return false;\">Confirm</a></p>";

            info += "</div>";
            document.getElementById("info_div").innerHTML = info;
            document.getElementById("statusValues").selectedIndex = json.status;
            }
        }

    xhr.open("GET", '/admin/invoice/'+id, true);
    xhr.send();
}

function updateInvoice(){
    var xhr = new XMLHttpRequest();
    var description = document.getElementById("description").innerHTML;
    var status = document.getElementById("statusValues").selectedIndex;
    var id = document.getElementById("invoice_id").innerHTML;

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status ==200){
            alert(this.responseText);
        }
    }
    if(description.length == 0){
        description = "empty";
    }
    xhr.open("UPDATE", '/admin/invoice/update/'+ id +'/description/'+description+'/status/'+status);
    xhr.send();
}

function checkInvoice(){
    var index = document.getElementById("statusValues").selectedIndex;
    if(index == 6){
        var button = document.getElementById("button");
            button.innerHTML = "Confirm and create";
            button.setAttribute("onclick", "updateAndCreate()");
    }
}

function updateAndCreate(){
    updateInvoice();
    basedInvoice();
}

function newInvoice(){
    var xhr = new XMLHttpRequest();
    var info = "<div class=\"container\">"
        + "<form class=\"form-std\" name=\"invoice\">"
        + "<h2 class=\"form-std-heading\">New invoice</h2>"
        + "<p><label for=\"username\" >Username: </label>"
        + "<input type=\"text\" id=\"username\" class=\"form-control\" placeholder=\"Username\" required autofocus></p>"
        + "<p><label for=\"car_id\">Car id: </label>"
        + "<input type=\"text\" id=\"car_id\" class=\"form-control\" placeholder=\"car id\" required></p>"
        + "<p><label for=\"car_name\">Car name: </label>"
        + "<input type=\"text\" id=\"car_name\" class=\"form-control\" placeholder=\"car name\" required></p>"
        + "<p><label for=\"on_date\">On date: </label>"
        + "<input type=\"date\" id=\"on_date\" class=\"form-control\" required></p>"
        + "<p><label for=\"invoice_price\">Price: </label>"
        + "<input type=\"number\" id=\"invoice_price\" class=\"form-control\" required></p>"
        + "<p><label for=\"description\">Description: </label>"
        + "<p>Status: <select id=\"statusValues\"></select></p>"
        + "<input type=\"text\" id=\"description\" class=\"form-control\" placeholder=\"Description\" required>"
        + "<input type=\"button\" class\"btn btn-block btn-primary\" value=\"Submit\" onclick=\"registerNewInvoice()\">"
        + "</div>";

    document.getElementById("info_div").innerHTML = info;
    document.getElementById("on_date").value = new Date();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var json = JSON.parse(this.responseText);
            var values = document.getElementById("statusValues").innerHTML;
            var i;
            for(i=0; i<json.length; i++){
                values+= "<option>" + json[i].toString+ "</option>";
            }

            document.getElementById("statusValues").innerHTML = values;
        }
    }

    xhr.open("GET", '/admin/status_values', true);
    xhr.send();
}
//TODO: debug it
// id username already bounded with login-block
function basedInvoice(){
        var car_name = String(document.getElementById("car_name").innerHTML);
        var car_id = String (document.getElementById("car_id").innerHTML);
        var username = String (document.getElementById("username").innerHTML);
        var description = "Based on " + document.getElementById("invoice_id").innerHTML + " invoice.";

        newInvoice();

        document.getElementById("username").value = username;
        document.getElementById("car_name").value = car_name;
        document.getElementById("car_id").value = car_id;
        document.getElementById("description").value = description;
}

//TODO: test it
function registerNewInvoice(){

    var i, json = "{", form = document.getElementById("invoice");

    for(i=0; i<form.length; i++){
        element = form.elements[i];
        if(element.type == "button"){
            continue;
        }
        if(element.type == "select"){
            json += "\"status\" : \"" + element.selectedIndex + "\",";
            continue;
        }

        json += "\"" + element.id + "\" : \"" + element.value + "\",";
    }

    json = json.slice(0, -1);

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4&& this.status == 200){
            showOrders();
        }
    }
//    TODO controller
    xhr.open("POST", '/admin/invoice/save', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}

//TODO: Write showUserByUsername(username) method

//TODO: Write showCarById