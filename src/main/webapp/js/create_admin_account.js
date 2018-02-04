'use strict'
// TODO: rewrite with JQuery
function createAdminAccount(){
    $(showInvoices());
}

function showUsers(){

	$.get('/admin/user/show_all', function(data, status){

        var h2 = $("<h2></h2>").text("Users");

        var thead = $("<thead></thead>")
			.append(
				$("<tr></tr>")
					.append(
						$("<th></th>").text("User name"),
						$("<th></th>").text("Roles"),
						$("<th></th>").text("First name"),
						$("<th></th>").text("Second name"),
						$("<th></th>").text("Birthday"),
						$("<th></th>").text("Address"),
						$("<th></th>").text("Status")
					)
			);

        var tbody = $("<tbody></tbody>"), tr, i, y;

        for(i = 0; i<data.length; i++){
            tr = $("<tr></tr>")
				.append(
					$("<td></td>").text(data[i].username),
					$("<td></td>").text(data[i].roles),
					$("<td></td>").text(data[i].firstname),
					$("<td></td>").text(data[i].secondname),
					$("<td></td>").text(data[i].birthday),
					$("<td></td>").text(data[i].address),
					$("<td></td>").text(data[i].status)
				);

			tr.click(function(){
				showUserByUsername($(this).find("td:first").text());
			});
			tr.mouseenter(function(){
				$(this).addClass("chosen");
			});
			tr.mouseleave(function(){
				$(this).removeClass("chosen");
			});
			tbody.append(tr);
        }

		var table = $("<table></table>").append(thead, tbody).addClass("table table-striped");
        $("#info_div").html(h2).append(table);
	});
}

function showUserByUsername(username) {

	$.get('/admin/user/' + username, function(data, status){
		alert(status);
		var col = "col-md-6 col-sm-6";
		var div = $("<div></div>").attr({"class" : col}),
			form = $("<form></form>").append($("<h2></h2>").text("User info").addClass("form-std-heading")).addClass("form-horizontal mt-md-4");

		form.append(
			$("<div></div>").addClass("col-sm-4")
				.append(
					$("<img>").attr({"src" : "/thumbs/users/" + username + ".jpg"}).addClass("img-rounded thumb")
				)
		);

		form.append(
			$("<dl></dl>").addClass("row")
				.append(
					$("<dt></dt>").addClass(col).text("Username: "), $("<dd></dd>").addClass(col).text(data.username),
					$("<dt></dt>").addClass(col).text("Roles: "), $("<dd></dd>").addClass(col).text(data.roles),
					$("<dt></dt>").addClass(col).text("First name: "), $("<dd></dd>").addClass(col).text(data.firstname),
					$("<dt></dt>").addClass(col).text("Second name: "), $("<dd></dd>").addClass(col).text(data.secondname),
					$("<dt></dt>").addClass(col).text("Last name: "), $("<dd></dd>").addClass(col).text(data.lastname),
					$("<dt></dt>").addClass(col).text("Birthday: "), $("<dd></dd>").addClass(col).text(data.birthday),
					$("<dt></dt>").addClass(col).text("Address: "), $("<dd></dd>").addClass(col).text(data.address),
					$("<dt></dt>").addClass(col).text("About me: "), $("<dd></dd>").addClass(col).text(data.about_me),
					$("<dt></dt>").addClass(col).text("Status: "), $("<dd></dd>").addClass(col).text(data.status)
				)
		);

		div.append(form);
		$("#info_div").html(div);

	});
}

function showCars(){

	$.get('/admin/car/show_all',
		function(data, status){
			var h2 = $("<h2></h2>").text("Cars");
			var thead = $("<thead></thead>")
						.append(
							$("<tr></tr>")
								.append(
									$("<th></th>").text("Id"),
									$("<th></th>").text("Car name"),
									$("<th></th>").text("Color"),
									$("<th></th>").text("Release date"),
									$("<th></th>").text("Price"),
									$("<th></th>").text("Status")
								)
						), tbody = $("<tbody></tbody>");
			var i, tr;
			for(i=0; i<data.length; i++){
				tr = $("<tr></tr>")
						.append(
							$("<td></td>").text(data[i].car_id),
							$("<td></td>").text(data[i].name),
							$("<td></td>").text(data[i].color),
							$("<td></td>").text(data[i].release_date),
							$("<td></td>").text(data[i].price),
							$("<td></td>").text(data[i].status)
						);
				tr.click(function(){
						showCarById($(this).find("td:first").text());
				});
				tr.mouseenter(function(){
						$(this).addClass("chosen");
				});
				tr.mouseleave(function(){
						$(this).removeClass("chosen");
				});
				tbody.append(tr);
			}
			var table = $("<table></table>").append(thead, tbody).addClass("table table-striped");
			$("#info_div").html(h2).append(table);
		}
	);
}

function newCar(){

	var form = $("<form></form>").addClass("form-horizontal").attr({"name" : "car"});
	var classLabel = "control-label col-md-6 col-sm-6", classDiv = "col-md-6 col-sm-6";
	form.append(
		$("<h2></h2>").text("New car").attr({"id":"form_head"}),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("Car name: ").addClass(classLabel).attr({"for" : "name"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "text", "id" : "name", "placeholder" : "Car name","class" : "form-control"})
					)
			),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("Color: ").addClass(classLabel).attr({"for" : "color"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "text", "id" : "color", "placeholder" : "Color","class" : "form-control"})
					)
			),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("Country: ").addClass(classLabel).attr({"for" : "country"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "text", "id" : "country", "placeholder" : "Country","class" : "form-control"})
					)
			),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("Description: ").addClass(classLabel).attr({"for" : "description"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "text", "id" : "description", "placeholder" : "Description","class" : "form-control"})
					)
			),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("price").addClass(classLabel).attr({"for" : "price"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "number", "id" : "price", "placeholder" : "0","class" : "form-control"})
					)
			),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("Year: ").addClass(classLabel).attr({"for" : "releaseDate"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "date", "id" : "releaseDate", "placeholder" : "Year","class" : "form-control"})
					)
			),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("Status: ").addClass(classLabel).attr({"for" : "statusValues"}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<select></select>").attr({"id" : "statusValues"})
					)
			),
		$("<input>").attr({"type" : "button", "id" : "carBtn", "value" : "save","class" : "btn btn-secondary btn-block btn-lg", "onclick" : "registerNewCar()"})
	);

	$("#info_div").html(form);

	$.get('/admin/car/status_values', function(data, status){
		var i, statusValues = $("#statusValues");
		for(i=0; i<data.length; i++){
			statusValues.append( $("<option></option>").text(data[i].toString));
		}
		statusValues[0].selectedIndex = 1;
	});

}
/*
	var form = $("<form></form>").addClass("form-horizontal").attr({"name" : "car"});
	var classLabel = "control-label col-md-6 col-sm-6", classDiv = "col-md-6 col-sm-6";
	form.append(
		$("<h2></h2>").text("New car"),
		$("<div></div>").addClass("form-inline form-group")
			.append(
				$("<label></label>").text("").addClass(classLabel).attr({"for" : ""}),
				$("<div><div>").addClass(classDiv)
					.append(
						$("<input>").attr({"type" : "text", "id" : "", "placeholder" : "","class" : "form-control"})
					)
			),
	);
*/


function showCarById(id){
    newCar();

	$.get('/admin/car/' + id, function(data, status){
		$("#form_head").val("Car info");
		$("#name").val(data.name);
		$("#color").val(data.color);
		$("#country").val(data.country);
		$("#description").val(data.description);
		$("#price").val(data.price);
		$("#releaseDate").val(data.release_date);
		$("#statusValues")[0].selectedIndex = data.status;
		$("#carBtn").val("Update").attr({"onclick":"updateCar('/admin/car/update/" + id +"')"});


	});
}

function updateCar(url){
	var i = 0, json = "", form = document.forms["car"];
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

	$.ajax({
		url: url,
		type: 'post',
		data: json,
		headers: {
			'Content-type' : 'application/json; charset=utf-8'
		},
		dataType: 'text',
		success: function () {
			showCars();
		}
	});
}

function registerNewCar(){
    updateCar('/admin/car/save');
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

    xhr.open("UPDATE", '/admin/invoice/update', true);
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

    xhr.open("POST", '/admin/invoice/save', true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}


