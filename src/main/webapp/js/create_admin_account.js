'use strict'
// TODO: rewrite with JQuery
function createAdminAccount(){
    showInvoices();
    checkBadges();
}

function showUsers(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<table class=\"table table-striped\"><h2 class=\"\">Users</h2>"
                +"<thead> <tr>"
                +"<th>User name</th> <th>Roles</th> <th>First name</th> <th>Second name</th>"
                +"<th>Birthday</th> <th>Address</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].username+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].roles+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].firstname+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].secondname+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].birthday+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].address+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showUserByUsername('"+json[i].username + "'); return false;\">"+json[i].status+"</a></td>"
                    + "</tr></a>"
            }

            info += "</tbody>";
            document.getElementById("info_div").innerHTML = info;
        }
    }
    xhr.open("GET", '/admin/user/show_all', true);
    xhr.send();
}

function showUserByUsername(username) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function (){
        if(this.readyState == 4 && this.status ==200){
            var json = JSON.parse(this.responseText);
            var info = "<div class=\"col-md-6 col-sm-6\">"
                    + "<form class=\"form-horizontal\" id=\"user_info\">"
                    + "<h2 class=\"form-std-heading ml-sm-4 mt-sm-4\">User info</h2>"
                    + "<div class=\"col-sm-6\"><img src=\"/thumbs/users/" + username + ".jpg\" class=\"img-rounded thumb\" alt=\"user image\"></div>"
                    + "<div><dl class=\"row\">"
                    + "<dt class=\"col-sm-6\">Username: </dt><dd class=\"col-sm-6\">" + json.username + "</dd>"
                    + "<dt class=\"col-sm-6\">Roles: </dt><dd class=\"col-sm-6\">" + json.roles + "</dd>"
                    + "<dt class=\"col-sm-6\">First name: </dt><dd class=\"col-sm-6\">" + json.firstname + "</dd>"
                    + "<dt class=\"col-sm-6\">Second name: </dt><dd class=\"col-sm-6\">" + json.secondname + "</dd>"
                    + "<dt class=\"col-sm-6\">Last name: </dt><dd class=\"col-sm-6\">" + json.lastname + "</dd>"
                    + "<dt class=\"col-sm-6\">Birthday: </dt><dd class=\"col-sm-6\">" + json.birthday + "</dd>"
                    + "<dt class=\"col-sm-6\">Address: </dt><dd class=\"col-sm-6\">" + json.address + "</dd>"
                    + "<dt class=\"col-sm-6\">About me: </dt><dd class=\"col-sm-6\">" + json.about_me + "</dd>"
                    + "<dt class=\"col-sm-6\">Status: </dt><dd class=\"col-sm-6\">" + json.status + "</dd>"
                    + "</dl></div>";

            document.getElementById("info_div").innerHTML = info;
        }
    }

    xhr.open("GET", '/admin/user/' + username, true);
    xhr.send();
}

function showCars(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info ="<table class=\"table table-striped\"><h2 class=\"\">Cars</h2>"
                +"<thead> <tr>"
                +"<th>Id</th> <th>Name</th> <th>Color</th> <th>Release date</th>"
                +"<th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"#\" onclick=\"showCarById("+json[i].car_id + "); return false;\">"+json[i].car_id+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showCarById("+json[i].car_id + "); return false;\">"+json[i].name+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showCarById("+json[i].car_id + "); return false;\">"+json[i].color+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showCarById("+json[i].car_id + "); return false;\">"+json[i].release_date+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showCarById("+json[i].car_id + "); return false;\">"+json[i].price+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showCarById("+json[i].car_id + "); return false;\">"+json[i].status+"</a></td>"
                    + "</tr>"
            }

            info += "</tbody>";
            document.getElementById("info_div").innerHTML = info;
        }
    }
    xhr.open("GET", '/admin/car/show_all', true);
    xhr.send();

}

//TODO: upload images function
function newCar(){
    var info = ""
        + "<div class=\"col-md-6 col-sm-6\">"
        + "<form class=\"form-horizontal\" name=\"car\">"
        + "<h2 id=\"form_head\" class=\"ml-sm-4 mt-sm-4\">New car</h2>"
        + "<div class=\"form-inline form-group\"><label for=\"name\" class=\"control-label col-md-6 col-sm-6\">Car name: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><input type=\"text\" id=\"name\" class=\"form-control\" placeholder=\"Car name\" required autofocus></div></div>"
        + "<div class=\"form-inline form-group\"><label for=\"color\" class=\"control-label col-md-6 col-sm-6\">Color: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><input type=\"text\" id=\"color\" class=\"form-control\" placeholder=\"Color\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label for=\"country\" class=\"control-label col-md-6 col-sm-6\">Country: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><input type=\"text\" id=\"country\" class=\"form-control\" placeholder=\"Country\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label for=\"description\" class=\"control-label col-md-6 col-sm-6\">Description: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><input type=\"text\" id=\"description\" class=\"form-control\" placeholder=\"Description\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label for=\"price\" class=\"control-label col-md-6 col-sm-6\">Price: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><input type=\"number\" min=\"0\" id=\"price\" class=\"form-control\" placeholder=\"0.00\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label for=\"releaseDate\" class=\"control-label col-md-6 col-sm-6\">Year: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><input type=\"date\" id=\"releaseDate\" class=\"form-control\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label for=\"statusValues\" class=\"control-label col-md-6 col-sm-6\">Status: </label>"
        + "<div class=\"col-md-6 col-sm-6\"><select id=\"statusValues\"></select></div></div>"
        + "<input id=\"carBtn\" class=\"btn btn-lg btn-secondary btn-block ml-sm-4\" value=\"save\" type=\"button\" onclick=\"registerNewCar()\">"
        + "</form></div>";

    document.getElementById("info_div").innerHTML = info;

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var values = document.getElementById("statusValues").innerHTML;
            var i, json = JSON.parse(this.responseText);
            for(i =0; i<json.length; i++){
                values += "<option>" + json[i].toString + "</option>";
            }
            document.getElementById("statusValues").innerHTML = values;
            document.getElementById("statusValues").selectedIndex = 1;
        }
    }

    xhr.open("GET", '/admin/car/status_values', true);
    xhr.send();
}

function showCarById(id){
    newCar();

    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var json = JSON.parse(this.responseText);
            document.getElementById("form_head").innerHTML = "Car info";
            document.getElementById("name").value = json.name;
            document.getElementById("color").value = json.color;
            document.getElementById("country").value = json.country;
            document.getElementById("description").value = json.description;
            document.getElementById("price").value = json.price;
            document.getElementById("releaseDate").value = json.release_date;
            document.getElementById("statusValues").selectedIndex = json.status;
            document.getElementById("carBtn").value = "Update";
            var btnFun = "updateCar('/admin/car/update/" + id + "')";
            document.getElementById("carBtn").setAttribute("onclick", btnFun);
        }
    }

    xhr.open("GET", '/admin/car/' + id, true);
    xhr.send();
}

function updateCar(url){

    var xhr = new XMLHttpRequest();
	var i = 0, json = "", form = document.forms["car"], text = "no";
    json = "{";
    for(i=0; i<form.length; i++){
        var element = form.elements[i];
    	if(element.type == "button"){
    		continue;
        }
        if(element.id == "statusValues"){
            json += "\"status\"" + ":" +"\""+ element.selectedIndex +"\""+ ",";
            continue;
        }

        json += "\"" + element.id + "\"" + ":" +"\""+ element.value +"\""+ ",";
    }

    json = json.slice(0, -1);
    json += "}";

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            text = this.responseText;
            alert(text);
            if(text == "success"){
                showCars();
            }
        }
    }
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}

function registerNewCar(){
    updateCar('/admin/car/register');
}

function showInvoices(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<table class=\"table table-striped\"><h2 class=\"\">Invoices</h2>"
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

function showInvoiceById(id){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            var json = JSON.parse(this.responseText);

            var info = "<div class=\"col-md-5 col-sm-6\">"
                    +"<h2 class=\"ml-sm-4 mt-sm-4\">Rent car invoice</h2>"
                    +"<div><dl class=\"row\">"
                    +"<dt class=\"col-sm-6\">Invoice id: </dt><dd id=\"invoice_id\" class=\"col-sm-6\">"+json.invoice_id + "</dd>"
                    +"<dt class=\"col-sm-6\">Car id: </dt><dd id=\"car_id\" class=\"col-sm-6\">"+json.car_id+"</dd>"
                    +"<dt class=\"col-sm-6\">Car name: </dt><dd id=\"car_name\" class=\"col-sm-6\">"+json.car_name+"</dd>"
                    +"<dt class=\"col-sm-6\">Username: </dt><dd id=\"username\" class=\"col-sm-6\">"+json.username+"</dd>"
                    +"<dt class=\"col-sm-6\">Car price: </dt><dd class=\"col-sm-6\">" + json.car_price+"</dd>"
                    +"<dt class=\"col-sm-6\">Starts: </dt><dd class=\"col-sm-6\">"+ json.starts_at+"</dd>"
                    +"<dt class=\"col-sm-6\">Expires: </dt><dd class=\"col-sm-6\">"+ json.expires_at+"</dd>"
                    +"<dt class=\"col-sm-6\">Invoice price: </dt><dd class=\"col-sm-6\">" + json.invoice_price+"</dd>";
            var y;
            if(json.bounded_with.length>0){
                info+="<dt class=\"col-sm-6\">Bounded with: </dt><dd class=\"col-sm-6\">";
                for(y=0; y<json.bounded_with.length; y++){
                    info += "<a href=\"#\" onclick=\"showInvoiceById(" + json.bounded_with[y]+")\">" + json.bounded_with[y]+ " </a>";
                }
                info += "</dd>";
            }

            info+= "<dt class=\"col-sm-6\">Status: </dt><dd class=\"col-sm-6\"><select id=\"statusValues\">";

            var i;
            for(i=0; i<json.statusValues.length; i++){
                info+= "<option>" + json.statusValues[i].toString+ "</option>";
            }
            info+= "</select></dd>"
                    +"<dt class=\"col-sm-6\">Description: </dt><input type=\"text\" value=\"" + json.description+ "\" id=\"description\" class=\"col-sm-6\">"
                    +"<a id=\"invoiceBtn\" class=\"btn btn-lg btn-block btn-primary\" onclick=\"updateInvoice();return false;\" href=\"#\">Update this invoice</a>"
                    +"<a id=\"boundBtn\" class=\"btn btn-lg btn-block btn-secondary\" onclick=\"basedInvoice();return false;\" href=\"#\">Bound new with this</a>";

            info += "</dl></div>";
            document.getElementById("info_div").innerHTML = info;
            document.getElementById("statusValues").selectedIndex = json.status;
        }
    }

    xhr.open("GET", '/admin/invoice/'+id, true);
    xhr.send();
}

function updateInvoice(){
    var xhr = new XMLHttpRequest();
    var json;

    json = "{\"description\" : \""+document.getElementById("description").value
            + "\", \"status\" : \"" +document.getElementById("statusValues").selectedIndex
            + "\", \"invoice_id\" : \"" + document.getElementById("invoice_id").innerHTML
            + "\"}";

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status ==200){
            alert(this.responseText);
        }
    }

    xhr.open("POST", '/admin/invoice/update', true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}

//INFO: used only with basedInvoice() method.
function newInvoice(){
    var xhr = new XMLHttpRequest();
    var info = "<div class=\"col-md-5    col-sm-6\">"
        + "<form class=\"ml-sm-4 form-horizontal\" id=\"invoice\">"
        + "<h2 class=\"ml-sm-4 mt-sm-4\">New invoice</h2>"
        + "<div class=\"form-inline form-group\"><label class=\"label-control col-md-6 col-sm-6\" for=\"username\" >Username: </label>"
        + "<div class=\"col-sm-6\"><input type=\"text\" id=\"username\" class=\"form-control\" placeholder=\"Username\" required autofocus></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"car_id\">Car id:</label>"
        + "<div class=\"col-sm-6\"><input type=\"text\" id=\"car_id\" class=\"form-control\" placeholder=\"car id\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"car_name\">Car name: </label>"
        + "<div class=\"col-sm-6\"><input type=\"text\" id=\"car_name\" class=\"form-control\" placeholder=\"car name\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"on_date\">On date: </label>"
        + "<div class=\"col-sm-6\"><input type=\"datetime-local\" id=\"on_date\" class=\"form-control\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"invoice_price\">Price: </label>"
        + "<div class=\"col-sm-6\"><input type=\"number\" id=\"invoice_price\" class=\"form-control\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"statusValues\">Status: </label>"
        + "<div class=\"col-sm-6\"><select id=\"statusValues\"></select></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"description\">Description: </label>"
        + "<div class=\"col-sm-6\"><input type=\"text\" id=\"description\" class=\"form-control\" placeholder=\"Description\" required></div></div>"
        + "<div class=\"form-inline form-group\"><label class=\"control-label col-md-6 col-sm-6\" for=\"based_on\">Based on: </label>"
        + "<div class=\"col-sm-6\"><input type=\"number\" id=\"based_on\" class=\"form-control\" placeholder=\"--\" required></div></div>"
        + "<input type=\"button\" class=\"btn btn-block btn-primary\" value=\"Submit\" onclick=\"registerNewInvoice()\">"
        + "</form></div>";

    document.getElementById("info_div").innerHTML = info;
    document.getElementById("on_date").value = (new Date()).toJSON().slice(0, 10);

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var json = JSON.parse(this.responseText);
            var values = document.getElementById("statusValues");
            var i;
            for(i=0; i<json.length; i++){
                values+= "<option>" + json[i].toString+ "</option>";
            }

            document.getElementById("statusValues").innerHTML = values;
            document.getElementById("statusValues").selectedIndex = 3;
        }
    }

    xhr.open("GET", '/admin/invoice/status_values', true);
    xhr.send();
}


function basedInvoice(){
        var car_name = String(document.getElementById("car_name").innerHTML);
        var car_id = String (document.getElementById("car_id").innerHTML);
        var username = String (document.getElementById("username").innerHTML);
        var invoice_id = document.getElementById("invoice_id").innerHTML;
        var description = "Based on " + invoice_id + " invoice.";

        newInvoice();

        document.getElementById("username").value = username;
        document.getElementById("car_name").value = car_name;
        document.getElementById("car_id").value = car_id;
        document.getElementById("description").value = description;
        document.getElementById("based_on").value = invoice_id;
}

function registerNewInvoice(){

    var i, json = "{";
    var form = document.forms["invoice"], element;

    for(i=0; i<form.length; i++){
        element = form[i];
        if(element.type == "button"){
            continue;
        }
        if(element.id == "statusValues"){
            json += "\"status\" : \"" + element.selectedIndex + "\",";
            continue;
        }

        json += "\"" + element.id + "\" : \"" + element.value + "\",";
    }

    json = json.slice(0, -1);
    json += "}";
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4&& this.status == 200){
            var text = this.responseText;
            alert(text);
            if(text == "success"){
                showInvoices();

            }
        }
    }

    xhr.open("POST", '/admin/invoice/register', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}


function checkBadges(){

}