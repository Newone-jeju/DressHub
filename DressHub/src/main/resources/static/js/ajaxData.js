
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
function AjaxUtil(url){
	this.url = url;
}

AjaxUtil.prototype.crudData = function(crudData, type ,func){
	$.ajax(
		{
			url: this.url,
		    contentType : "application/json; charset=UTF-8",
		    async: true,
		    type: type,
		    data: JSON.stringify(crudData),
		    error: function(jqXHR, textStatus, errorThrown) {
		        alert("failed crud");
		    },
		    success: function(json_data){
		    	func();
		    }
		})
}


//class AjaxCard
function AjaxCard(){
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
