function load_items(){
    load_header();
    load_menu();
    load_footer();
}

function load_header(){
    var xhr = new XMLHttpRequest();
    var text;

    xhr.onreadystatechange = function (){
        if(this.readyState == 4&& this.status == 200){
            text = this.responseText;
            document.getElementById("header").innerHTML = text;
        }
    }

    xhr.open('GET', 'items/header'. true);
    xhr.send();
}

function load_footer(){
    var xhr = new XMLHttpRequest();
    var text;

    xhr.onreadystatechange = function (){
        if(this.readyState == 4&& this.status == 200){
            text = this.responseText;
            document.getElementById("footer").innerHTML = text;
        }
    }

    xhr.open('GET', 'items/footer'. true);
    xhr.send();
}

function load_menu(){
    var xhr = new XMLHttpRequest();
    var text;

    xhr.onreadystatechange = function (){
        if(this.readyState == 4&& this.status == 200){
            text = this.responseText;
            document.getElementById("menu").innerHTML = text;
        }
    }

    xhr.open('GET', 'items/menu'. true);
    xhr.send();
}