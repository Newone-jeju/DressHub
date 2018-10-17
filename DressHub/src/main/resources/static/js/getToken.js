

function getUserId(){
    var id = getTokenObject().uid; // 이건 뭔지 모르겠네요
    return id;
}

function userNavEdit(){
    var userId = getUserId();
    if(userId != ""){
        var ajaxCard = new AjaxCard();
        ajaxCard.setCard(
            '<li>'+userId+'님 환영합니다!</li>'+
            '<li><a href="cart.html">장바구니</a></li>'+
            '<li><a href="mypage.html">MYPAGE</a></li>'+
            '<li><a href="/logout">LOGOUT</a></li>'
        )
        ajaxCard.mapCard($(".topul"))
    }
}


//class TokenUser
function TokenUser(){
    this.userId = this.getTokenObject.uid;
    this.token = this.getTokenObject;
}

TokenUser.prototype.getUserId = function(){
    return this.userId;
}

TokenUser.prototype.getTokenObject = getTokenObject()

TokenUser.prototype.inspectId = function(){
    if(this.userId == ''){
        alert("로그인 해주세요");
        window.location.href = '/login';
        return false;
    }
}

TokenUser.prototype.inspectIdDiff = function(uid){
    if(this.userId != uid){
        alert("권한이 없습니다");
        return false;
    }
}


(function(){
    userNavEdit();
}());


function getTokenObject() {
    var token = sessionStorage.getItem("token")
    var str = token.split(".")[1];
    var enc64List, dec64List;

    enc64List = new Array();
    dec64List = new Array();
    var i;
    for (i = 0; i < 26; i++) {
        enc64List[enc64List.length] = String.fromCharCode(65 + i);
    }
    7
    for (i = 0; i < 26; i++) {
        enc64List[enc64List.length] = String.fromCharCode(97 + i);
    }
    for (i = 0; i < 10; i++) {
        enc64List[enc64List.length] = String.fromCharCode(48 + i);
    }
    enc64List[enc64List.length] = "+";
    enc64List[enc64List.length] = "/";
    for (i = 0; i < 128; i++) {
        dec64List[dec64List.length] = -1;
    }
    for (i = 0; i < 64; i++) {
        dec64List[enc64List[i].charCodeAt(0)] = i;
    }

    var c = 0, d = 0, e = 0, f = 0, i = 0, n = 0;
    var input = str.split("");
    var output = "";
    var ptr = 0;
    do {
        f = input[ptr++].charCodeAt(0);
        i = dec64List[f];
        if (f >= 0 && f < 128 && i != -1) {
            if (n % 4 == 0) {
                c = i << 2;
            } else if (n % 4 == 1) {
                c = c | (i >> 4);
                d = (i & 0x0000000F) << 4;
            } else if (n % 4 == 2) {
                d = d | (i >> 2);
                e = (i & 0x00000003) << 6;
            } else {
                e = e | i;
            }
            n++;
            if (n % 4 == 0) {
                output += String.fromCharCode(c) +
                    String.fromCharCode(d) +
                    String.fromCharCode(e);
            }
        }
    }
    while (typeof input[ptr] != "undefined");
    output += (n % 4 == 3) ? String.fromCharCode(c) + String.fromCharCode(d) :
        ((n % 4 == 2) ? String.fromCharCode(c) : "");
    return JSON.parse(output);

}
