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

            var info = "<div class=\"col-md-5 col-sm-6\">"
                    + "<form class=\"form-horizontal\" id=\"user_info\">"
                    + "<h2 class=\"form-std-heading\">Account info</h2>"
                    + "<div class=\"col-sm-4\"><img src=\"/thumbs/users/" + username + ".jpg\" class=\"img-rounded thumb\" alt=\"user image\"></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"name\" class=\"control-label col-sm-4\">Username: </label>"
                    + "<span id=\"username\" class=\"col-sm-8 form-control\">" + json.username + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"color\" class=\"control-label col-sm-4\">Roles: </label>"
                    + "<span id=\"roles\" class=\"col-sm-8 form-control\">" + json.roles + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"firstname\" class=\"control-label col-sm-4\">First name: </label>"
                    + "<span id=\"firstname\" class=\"col-sm-8 form-control\">" + json.firstname + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"secondname\" class=\"control-label col-sm-4\">Second name: </label>"
                    + "<span id=\"secondname\" class=\"col-sm-8 form-control\">" + json.secondname + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"lastname\" class=\"control-label col-sm-4\">Last name: </label>"
                    + "<span id=\"lastname\" class=\"col-sm-8 form-control\">" + json.lastname + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"birthday\" class=\"control-label col-sm-4\">Birthday: </label>"
                    + "<span id=\"birthday\" class=\"col-sm-8 form-control\">" + json.birthday + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"address\" class=\"control-label col-sm-4\">Address: </label>"
                    + "<span id=\"address\" class=\"col-sm-8 form-control\">" + json.address + "</span></div>"
                    + "<div class=\"form-inline form-group\"><label for=\"about_me\" class=\"control-label col-sm-4\">About me: </label>"
                    + "<span id=\"about_me\" class=\"col-sm-8 form-control\">" + json.about_me + "</span></div>"
                    + "<input id=\"user_info_btn\" class=\"btn btn-lg btn-secondary\" type=\"button\" value=\"Edit\" onclick=\"setEnabled()\">"
                    + "</form></div>";

            document.getElementById("info_div").innerHTML = info;
        }
    }

    xhr.open("GET", '/user/info', true);
    xhr.send();
}

//TODO: test it
function setEnabled(){

    var form = document.getElementById("user_info");
    var i, info, element, labels = form.getElementsByClassName("control-label");

    info = "<div class=\"col-md-5 col-sm-6\">"
        + "<form class=\"form-horizontal\" id=\"user_info\">"
        + "<h2 class=\"form-std-heading\">Account info</h2>"
        + "<div class=\"col-sm-4\"><img src=\"/thumbs/users/" + username + ".jpg\" class=\"img-rounded thumb\" alt=\"user image\"></div>";


    for(i=0; i<form.length; i++){
        element = form[i];

        if(element.type == "button"){
            continue;
        }

        info += "<div class=\"form-inline form-group\"><label for=\"" + element.id + "\" class=\"control-label col-sm-4\">" + labels[i].innerHTML+ "</label>";

        if(element.id == "birthday"){
            info+= "<div class=\"col-sm-8\"><input type=\"text\" id=\"" + element.id +"\" class=\"col-sm-8 form-control\" value=\"" + element.innerHTML + "\" ></div></div>";
            continue;
        }

        info += "<div class=\"col-sm-8\"><input type=\"text\" id=\"" + element.id +"\" class=\"col-sm-8 form-control\" value=\"" + element.innerHTML + "\" ></div></div>";
    }

    info += "<div><input class=\"btn btn-lg btn-primary\" type=\"button\" value=\"Update\" onclick=\"updateUser()\">"
        + "<input class=\"btn btn-lg btn-secondary\" type=\"button\" value=\"Cancel\" onclick=\"showAccount()\"></div>"
        + "</form></div>";

    document.getElementById("info_div").innerHTML = info;
}

//TODO: change send type to JSON.
function updateUser(){
    var xhr = new XMLHttpRequest(), url = '/user/update/';

    var form = document.getElementById("user_info");

    var i, element;

    for(i = 0; i<form.length; i++){
        element = form.elements[i];
        if(element.type == "button"){
            continue;
        }
        if(element.id == "username"){
            continue;
        }
        url += element.id + "/" + element.value + "/";
    }

    url = url.slice(0, -1);

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var text = this.responseText;
            showAccount();
            alert(text);
        }
    }

    xhr.open("POST", url, true);
    xhr.send();
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

            var info = "<div class=\"col-md-5 col-sm-6\">"
                    +"<h2>Rent car order</h2>"
                    +"<p>Invoice id: "+json.invoice_id + "</p>"
                    +"<p>Birthday: "+ json.birthday +"</p>"
                    +"<pCar name: >"+json.car_name+"</p>"
                    +"<p>Starts: "+ json.starts_at+"</p>"
                    +"<p>Expires: "+ json.expires_at+"</p>"
                    +"<p>Invoice price: " + json.invoice_price+"</p>"
                    +"<p>Description: "+ json.description +"</p>"
                    +"<p>Status: "+json.invoiceStatus +"</p>";
//          2 - is CLOSED in OrderStatus enum, 0 - is new
            if(json.status >0 && json.status<3){
                if(json.status != 6){
                    info += "<input type=\"button\" value=\"close order?\" onclick=\"changeOrderStatus("+id +", 1)\">";
                }
            } else {
                if(json.status == 0){
                    info += "<input type=\"button\" value=\"open order?\" onclick=\"changeOrderStatus("+id +", 3)\">";
                }
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



