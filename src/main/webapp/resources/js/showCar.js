"use strict"

function showCars(){
    var xhr = new XMLHttpRequest();
    var text;

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status ==200){
            text = this.responseText;
            parseCars(text);
        }
    }

    xhr.open('GET', '/car/getall', true);
    xhr.send();
}

function parseCars(text){
    var out = "", i;

    text = JSON.parse(text);

    if(text != null){
        for(i=0; i<text.length; i++){
            out += "<h2>Car</h2><p>id:" + text[i].id + "<br>mark is " + text[i].mark + "<br>model is " + text[i].model +
                "<br>release date: " + new Date(text[i].releaseDate).getFullYear() + "<br>price: " + text[i].price;
                if(text[i].isFree == 'y'){
                    out += "<br>is free";
                }
                if(text[i].isFree == 'n'){
                  out += "<br>on rent";
                }
            out+= "</p?";
        }

        document.getElementById("input").innerHTML = out;
    }

}