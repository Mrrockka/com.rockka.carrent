'user strict'

function createUserAccount(){
    showAccount();
}

function showAccount(){
    var xhr = new XMLHttpRequest():

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var info = "", json = JSON.parse(this.responseText);
//            TODO: write account html-presentation
            document.getElementById("info_div").innerHTML = info;
        }
    }

    xhr.open("GET", '/user/info', true);
    xhr.send();
}

function updateAccount(){
    var xhr = new XMLHttpRequest(), smth;
//    TODO: write code to update user info
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var text = this.responseText;
            showAccount();
            alert(text);
        }
    }

    xhr.open("POST", '/user/update/firstname/'+ firstname
                        + '/secondname/' + secondname
                        + '/lastname/' + lastname
                        + '/address/' + address
                        + '/about_me/' + about_me
                        + '/birthday/' + birthday
             , true);
    xhr.send();
}

function showOrders(){
    var xhr = new XmlHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var info = "", json = JSON.parse(this.responseTest);
//          TODO: write orders html-presentation
            document.getElementById("info_div").innerHTML = info;
        }
    }

    xhr.open("GET", '/user/order/show_all', true);
    xhr.send();
}

