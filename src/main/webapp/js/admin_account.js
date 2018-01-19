'use strict'

function addInfoTable(){
//    id = workspace
// /admin/order/show_all
// /admin/user/show_all
// /admin/car/show_all
    showOrders();
}

function showOrders(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", '/admin/order/show_all', true);
    xhr.send();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<thead> <tr>"
                +"<th>Id</th> <th>User</th> <th>Car</th> <th>Start at</th>"
                +"<th>Expires at</th> <th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr><a href=\"/admin/order/"+json[i].order_id + "\">"
                    + "<td>"+json[i].id+"</td>"
                    + "<td>"+json[i].username+"</td>"
                    + "<td>"+json[i].car_name+"</td>"
                    + "<td>"+json[i].start_at+"</td>"
                    + "<td>"+json[i].expires_at+"</td>"
                    + "<td>"+json[i].price+"</td>"
                    + "<td>"+json[i].status+"</td>"
                    + "</a></tr>"
            }

            info += "</tbody>";
            document.getElementById("info_table").innerHTML = info;
        }
    }
}

function showUsers(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", '/admin/order/show_all', true);
    xhr.send();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<thead> <tr>"
                +"<th>Id</th> <th>User</th> <th>Car</th> <th>Start at</th>"
                +"<th>Expires at</th> <th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr><a href=\"/admin/order/"+json[i].order_id + "\">"
                    + "<td>"+json[i].order_id+"</td>"
                    + "<td>"+json[i].username+"</td>"
                    + "<td>"+json[i].car_name+"</td>"
                    + "<td>"+json[i].start_at+"</td>"
                    + "<td>"+json[i].expires_at+"</td>"
                    + "<td>"+json[i].price+"</td>"
                    + "<td>"+json[i].status+"</td>"
                    + "</a></tr>"
            }

            info += "</tbody>";
            document.getElementById("info_table").innerHTML = info;
        }
    }
}

function showCars(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", '/admin/order/show_all', true);
    xhr.send();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var i;
            var info = "<thead> <tr>"
                +"<th>Id</th> <th>User</th> <th>Car</th> <th>Start at</th>"
                +"<th>Expires at</th> <th>Price</th> <th>Status</th>"
                +"</tr> </thead>"
                +"<tbody>";
            var json = JSON.parse(this.responseText);

            for(i = 0; i<json.length; i++){
                info += "<tr><a href=\"/admin/order/"+json[i].order_id + "\">"
                    + "<td>"+json[i].order_id+"</td>"
                    + "<td>"+json[i].username+"</td>"
                    + "<td>"+json[i].car_name+"</td>"
                    + "<td>"+json[i].start_at+"</td>"
                    + "<td>"+json[i].expires_at+"</td>"
                    + "<td>"+json[i].price+"</td>"
                    + "<td>"+json[i].status+"</td>"
                    + "</a></tr>"
            }

            info += "</tbody>";
            document.getElementById("info_table").innerHTML = info;
        }
    }
}




