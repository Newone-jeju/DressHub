
//class AjaxData
function AjaxData(url, async) {
	this.url = url;
	this.async = async;
	this.data = [];
}

AjaxData.prototype.setData = function(){
	var ajaxData = ""
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
		    	ajaxData = json_data;
		    }
		})
	this.data = ajaxData;
}

AjaxData.prototype.getData = function(){
	return this.data;
}


//class AjaxCard
function AjaxCard(data){
	this.data = data;
	this.cards = [];
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