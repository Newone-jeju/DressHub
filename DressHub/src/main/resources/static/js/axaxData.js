
//class AjaxData
function AjaxData(url, async) {
	this.url = url;
	this.async = async;
	var data = [];
}

AjaxData.prototype.setData = function(){
	$.ajax(
		{
			url: this.url,
		    dataType: 'json',
		    async: this.async,
		    type:'get',
		    error: function(jqXHR, textStatus, errorThrown) {
		        alert(textStatus);
		        alert(errorThrown);
		    },
		    success: function(json_data){
		    	data = json_data;
		    }
		})
}

AjaxData.prototype.getData = function(){
	return data;
}


//class AjaxCard
function AjaxCard(data){
	this.data = data;
	this.cards = []; //push 메소드를 쓸때는 this를 써줘야 한다.. 참 이상한 언어
}

AjaxCard.prototype.setCard = function(string){
	this.cards.push(string); 
}

AjaxCard.prototype.mapCard = function(target){
	var html = "";
	$.each(this.cards, function(i, card) {
      html += card;
    });
    target.html(html);
    this.cards = [];
}