'use strict'

function addInfoTable(){
    showOrders();
}

function addCar(){
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
        + "<input class=\"btn btn-lg btn-primary btn-block\" value=\"save\" type=\"button\" onclick=\"saveCar()\">"
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
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].order_id+"</a></td>"
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].username+"</td>"
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].car_name+"</a></td>"
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].starts_at+"</a></td>"
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].expires_at+"</a></td>"
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].price+"</a></td>"
                    + "<td><a href=\"/admin/order/"+json[i].order_id + "\">"+json[i].status+"</a></td>"
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




