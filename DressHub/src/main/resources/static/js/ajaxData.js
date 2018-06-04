
//class AjaxData
function AjaxData(url, async) {
	this.url = url;
	this.data = (function(){
	var ajaxData = ""
	$.ajax(
		{
			url: url,
		    dataType: 'json',
		    async: async,
		    type:'GET',
		    error: function(jqXHR, textStatus, errorThrown) {
		        alert(textStatus);
		        alert(errorThrown);
		    },
		    success: function(json_data){
		    	ajaxData = json_data;
		    }
		})
	return ajaxData;
	})();
}

AjaxData.prototype.getData = function(){
	return this.data;
}


//class AjaxUtil
function AjaxUtil(){}

AjaxUtil.prototype.editVal = function(target, property, val){
	target.attr(property, val);
}

AjaxUtil.prototype.sendData = function(postData, func){
	$.ajax(
		{
			url: this.url,
		    dataType: 'json',
		    async: true,
		    type: 'POST',
		    data: JSON.stringfy(postData),
		    error: function(jqXHR, textStatus, errorThrown) {
		        alert("failed sending");
		    },
		    success: function(json_data){
		    	func();
		    }
		})
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
