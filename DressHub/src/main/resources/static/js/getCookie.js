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

(function(){
	userNavEdit();
})();