'user strict'
// TODO: rewrite with JQuery

function createUserAccount(){
    showAccount();
}

function showAccount(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var json = JSON.parse(this.responseText);

            var info = "<div class=\"col-md-9 col-sm-8\">"
                    + "<form class=\"form-horizontal\" id=\"user_info\">"
                    + "<h2 class=\"ml-sm-4 mt-sm-4 form-std-heading\">Account info</h2>"
                    + "<div class=\"col-sm-4\"><img src=\"/thumbs/users/" + json.username + ".jpg\" class=\"img-rounded thumb\" alt=\"user image\"></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"username\" class=\"control-label col-md-6 col-sm-6\">Username: </label>"
                    + "<span id=\"username\" class=\"col-md-6 col-sm-6\">" + json.username + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"firstname\" class=\"control-label col-md-6 col-sm-6\">First name: </label>"
                    + "<span id=\"firstname\" class=\"col-md-6 col-sm-6\">" + json.firstname + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"secondname\" class=\"control-label col-md-6 col-sm-6\">Second name: </label>"
                    + "<span id=\"secondname\" class=\"col-md-6 col-sm-6\">" + json.secondname + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"lastname\" class=\"control-label col-md-6 col-sm-6\">Last name: </label>"
                    + "<span id=\"lastname\" class=\"col-md-6 col-sm-6\">" + json.lastname + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"birthday\" class=\"control-label col-md-6 col-sm-6\">Birthday: </label>"
                    + "<span id=\"birthday\" class=\"col-md-6 col-sm-6\">" + json.birthday + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"address\" class=\"control-label col-md-6 col-sm-6\">Address: </label>"
                    + "<span id=\"address\" class=\"col-md-6 col-sm-6\">" + json.address + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"about_me\" class=\"control-label col-md-6 col-sm-6\">About me: </label>"
                    + "<span id=\"about_me\" class=\"col-md-6 col-sm-6\">" + json.about_me + "</span></div>"
                    + "<input id=\"user_info_btn\" class=\"btn btn-lg btn-secondary btn-secondary\" type=\"button\" value=\"Edit\" onclick=\"setEnabled()\">"
                    + "</form></div>";

            document.getElementById("info_div").innerHTML = info;
        }
    }

    xhr.open("GET", '/user/info', true);
    xhr.send();
}

//TODO: doesn't work
function setEnabled(){

    var form = document.getElementById("user_info");
    var i, info, element, labels = form.getElementsByClassName("control-label");

    info = "<div class=\"col-md-9 col-sm-8\">"
        + "<form class=\"form-horizontal\" id=\"user_info\">"
        + "<h2 class=\"ml-sm-4 mt-sm-4 form-std-heading\">Account info</h2>"
        + "<div class=\"col-sm-4\"><img src=\"/thumbs/users/" + username + ".jpg\" class=\"img-rounded thumb\" alt=\"user image\"></div>";


    for(i=0; i<form.length; i++){
        element = form[i];

        if(element.type == "button"){
            continue;
        }
        if(element.type == "username"){
            info += element;
        }
        if(element.type == "roles"){
            info += element;
        }

        info += "<div class=\"form-inline form-group\">" + element.id;

        if(element.id == "birthday"){
            info+= "<div class=\"col-md-6 col-sm-6\"><input type=\"text\" id=\"" + element.id +"\" class=\"form-control\" value=\"" + element.innerHTML + "\" ></div></div>";
            continue;
        }

        info += "<div class=\"col-md-6 col-sm-6\"><input type=\"text\" id=\"" + element.id +"\" class=\"form-control\" value=\"" + element.innerHTML + "\" ></div></div>";
    }

    info += "<div><input class=\"btn btn-lg btn-primary\" type=\"button\" value=\"Update\" onclick=\"updateUser()\">"
        + "<input class=\"btn btn-lg btn-secondary\" type=\"button\" value=\"Cancel\" onclick=\"showAccount()\"></div>"
        + "</form></div>";

    document.getElementById("info_div").innerHTML = info;
}

function updateUser(){
    var xhr = new XMLHttpRequest(), url = '/user/update/', json = "{";

    var form = document.getElementById("user_info");

    var i, element;

    for(i = 0; i<form.length; i++){
        element = form[i];
        if(element.type == "button"){
            continue;
        }
        if(element.type == "date"){
            json += "\"" + element.id + "\" : \" " + (new Date(element.value)).getTime() + "\" , ";
            continue;
        }
        json += "\"" + element.id + "\" : \" " + element.value + "\" , ";
    }

    json = json.slice(0, -1);
    json += "}";

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var text = this.responseText;
            showAccount();
            alert(text);
        }
    }

    xhr.open("POST", url, true);
    xhr.send(json);
}

function showOrders(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var json = JSON.parse(this.responseText);

            var info = "<table class=\"table table-striped\"><h2 class=\"form-std-heading\">Orders</h2>"
                    +"<thead> <tr>"
                    +"<th>Id</th> <th>Car</th> <th>Starts</th>"
                    +"<th>Expires</th> <th>Price</th> <th>Status</th>"
                    +"</tr> </thead>"
                    +"<tbody>";

            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("
                    +json[i].invoice_id + ");return false;\">"+json[i].invoice_id+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("
                    +json[i].invoice_id + ");return false;\">"+json[i].car_name+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("
                    +json[i].invoice_id + ");return false;\">"+json[i].starts_at+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("
                    +json[i].invoice_id + ");return false;\">"+json[i].expires_at+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("
                    +json[i].invoice_id + ");return false;\">"+json[i].invoice_price+"</a></td>"
                    + "<td><a href=\"#\" onclick=\"showOrderById("
                    +json[i].invoice_id + ");return false;\">"+json[i].invoiceStatus+"</a></td>"
                    + "</tr>";
            }

            info += "</tbody></table>";

            document.getElementById("info_div").innerHTML = info;
        }
    }

    xhr.open("GET", '/user/invoice/show_all', true);
    xhr.send();
}

function showOrderById(id){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState==4 && this.status==200){
            var i, json = JSON.parse(this.responseText);

            var info = "<div class=\"mt-sm-4 col-md-9 col-sm-8\">"
                    +"<h2 class=\"\">Rent car order</h2>"
                    +"<dl class=\"row\">"
                    +"<dt class=\"col-md-6 col-sm-6\">Invoice id: </dt><dd class=\"col-md-6 col-sm-6\">"+json.invoice_id + "</dd>"
                    +"<dt class=\"col-md-6 col-sm-6\">Car name: </dt><dd class=\"col-md-6 col-sm-6\">"+json.car_name+"</dd>"
                    +"<dt class=\"col-md-6 col-sm-6\">Starts: </dt><dd class=\"col-md-6 col-sm-6\">"+ json.starts_at+"</dd>"
                    +"<dt class=\"col-md-6 col-sm-6\">Expires: </dt><dd class=\"col-md-6 col-sm-6\">"+ json.expires_at+"</dd>"
                    +"<dt class=\"col-md-6 col-sm-6\">Invoice price: </dt><dd class=\"col-md-6 col-sm-6\">" + json.invoice_price+"</dd>"
                    +"<dt class=\"col-md-6 col-sm-6\">Description: </dt><dd class=\"col-md-6 col-sm-6\">"+ json.description +"</dd>"
                    +"<dt class=\"col-md-6 col-sm-6\">Status: </dt><dd class=\"col-md-6 col-sm-6\">"+json.invoiceStatus +"</dd>"
                    +"</dl>";
//          1 - is CLOSED in OrderStatus enum, 3 - is new
            if(json.status == 3){
                info += "<input type=\"button\" value=\"close order?\" onclick=\"changeOrderStatus("+ id +", 1)\">";
            }
            if(json.status == 1){
                info += "<input type=\"button\" value=\"open order?\" onclick=\"changeOrderStatus("+ id +", 3)\">";
            }

            info += "</div>";

            document.getElementById("info_div").innerHTML = info;
        }

    }
    xhr.open("GET", '/user/invoice/'+id, true);
    xhr.send();
}

function changeOrderStatus(id, status){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            alert(this.responseText);
            showOrderById(id);
        }
    }
//    2 - is CLOSED in OrderStatus enum, 0 - is new
    xhr.open("POST", '/user/invoice/update/'+id+'/status/'+status, true);
    xhr.send();


}