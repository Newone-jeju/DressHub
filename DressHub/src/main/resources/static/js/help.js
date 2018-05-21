var title = $(".help-content > header > h1");
var description = $(".help-content > header > p");
var nav_ul = $("#switch-nav > ul");
var content_area = $("#content");
var data = "";
var helpData = "";
var helpUi = "";

function Map(){
	//title
	helpUi.prototype.setCard = function() {
		cards.push('<h1>'+this.data.title+'</h1>');
	}
	helpUi.mapCard(title, cards.length);

	// //switch-nav
	// helpUi.prototype.setCard = function() {
	// 	cards.push('<li class="active">'+this.data.title+'</li>');
	// 	cards.push('<li>'+this.data.title+'</li>');
	// 	cards.push('<li>'+this.data.title+'</li>');
	// }
	// helpUi.mapCard(nav_ul, cards.length);

	//description
	helpUi.prototype.setCard = function() {
		cards.push('<p>'+this.data.description+'</p>');
	}
	helpUi.mapCard(description, cards.length);

	//contents
	helpUi.prototype.setCard = function() {
		cards.push(this.data.content);
	}
	helpUi.mapCard(content_area, cards.length);
}

function Init() {
	helpData = new AjaxData("js/help.json", true);
	data = helpData.getData();
	helpUi = new AjaxCard(data[0]);
	map();

	
}




