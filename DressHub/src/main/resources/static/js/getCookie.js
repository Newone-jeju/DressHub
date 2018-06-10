
function getUserId(){
	var id = document.cookie.replace('uid=','');
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


//class CookieUser
function CookieUser(){
	this.userId = document.cookie.replace('uid=','');			
}

CookieUser.prototype.getUserId = function(){
	return this.userId;
}

CookieUser.prototype.inspectId = function(){
	if(this.userId == ''){
            alert("로그인 해주세요");
            window.location.href = '/login';
    }
}

CookieUser.prototype.inspectIdDiff = function(uid){
	if(this.userId != uid){
		alert("권한이 없습니다");
		return false;
	}
}


(function(){
	userNavEdit();
})();