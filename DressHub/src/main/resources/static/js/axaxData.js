//class AjaxData
function AjaxData(url, async) {
	this.url = url;
	this.async = async;
	var data = function(){
		$.ajax(
		{
			url: this.url,
		    dataType: 'json',
		    async: async,
		    type:'get',
		    error: function(jqXHR, textStatus, errorThrown) {
		        alert(textStatus);
		        alert(errorThrown);
		    },
		    success: function(json_data){
		    	data = json_data;
		    }
		})
	};
}

AjaxData.prototype.getData = function(){
	return this.data;
}

//class AjaxCard
function AjaxCard(data){
	this.data = data;
	var cards = [];
}

AjaxCard.prototype.setCard = function(){}

AjaxCard.prototype.mapCard = function(target, quantity){
	for(var i=0; i<quantity; i++){
		cards.push(this.setCard(i));
	}
	var html = "";
	$.each(cards, function(i, card) {
      html += card;
    });
    target.html(html);
}