'use strict'

function createAdminAccount(){
    showOrders();
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

function showOrders(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<table class=\"table table-striped\"><h2 class=\"form-std-heading\">Orders</h2>"
                +"<thead> <tr>"
                +"<th>Id</th> <th>User</th> <th>Car</th> <th>Starts</th>"
                +"<th>Expires</th> <th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].order_id+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].username+"</td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].car_name+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].starts_at+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].expires_at+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].price+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("+json[i].order_id + ");return false;\">"+json[i].status+"</a></td>"
                    + "</tr>"
            }

            info += "</tbody></table>";
            document.getElementById("info_div").innerHTML = info;
        }
    }
    xhr.open("GET", '/admin/order/show_all', true);
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
	var i = 0, json = "", doc = document.forms["car"], text = "no";
    json = "{";
    for(i=0; i<doc.length; i++){
    	if(doc[i].type != "button"){
    		json += "\"" + doc[i].id + "\"" + ":" +"\""+ doc[i].value +"\""+ ",";
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
                window.location.href = '#';
            }
        }
    }
	xhr.open("POST", '/admin/car/save', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}

function showOrderById(id){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            var i, json = JSON.parse(this.responseText);
//            TODO: write switch for status
            var status = json.status;
            switch(status){
                case 0:
                    status = "new";
                    break;
                case 1:
                    status = "confirmed";
                    break;
                case -1:
                    status = "denied";
                    break;
                case 2:
                    status = "active";
                    break;
                default:
                    status = "error";
            }

            var info = "<div class=\"container\">"
                    +"<h2>Rent car order</h2>"
                    +"<p>Order id: "+json.order_id + "</p>"
                    +"<p>Birthday: "+ json.birthday +"</p>"
                    +"<pCar name: >"+json.car_name+"</p>"
                    +"<p>Car price: " + json.car_price+"</p>"
                    +"<p>Starts: "+ json.starts_at+"</p>"
                    +"<p>Expires: "+ json.expires_at+"</p>"
                    +"<p>Order price: " + json.order_price+"</p>"
                    +"<p>Description: <input value=\""+ json.description+"\" type=\"text\" name=\"description\"></p>"
                    +"<p>Status: "+ status+"</p>"
                    +"<p><a class=\"btn btn-lg btn-block btn-primary\" onclick=\"updateOrder("+json.order_id+");return false;\">Confirm</a></p>";
            }

            info += "</div>";
            document.getElementById("info_div").innerHTML = info;
        }


    xhr.open("GET", '/admin/order/'+id, true);
    xhr.send();
}