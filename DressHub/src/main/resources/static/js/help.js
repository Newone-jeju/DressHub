var title = $(".help-content > header");
var nav_ul = $("#nav-tab > ul");
var content_area = $("#content");
var data = "";
var helpData = "";
var helpUi = "";


function mapHelp(i){
	//title description
	helpUi.setCard('<h1>'+data[i].title+'</h1>');
	helpUi.setCard('<p>'+data[i].description+'</p>');
	helpUi.mapCard(title);

	//contents
	helpUi.setCard(data[i].content);
	helpUi.mapCard(content_area);
}

function NavTabInit(){
	helpUi.setCard('<li class="active">'+data[0].title+'</li>');
	for(var i=1; i<data.length; i++){
		helpUi.setCard('<li>'+data[i].title+'</li>');
	}	
	helpUi.mapCard(nav_ul);
}

function navTabClickListen(){
	var nav_li = $("#nav-tab > ul > li");
	nav_li.click(function(){
		if(!$(this).hasClass("active")){
			nav_li.removeClass("active");
			$(this).addClass("active");
			mapHelp(nav_li.index(this));
		}
	})
}

function go_top(){
	$(".go-top").click(function(){
		$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
  		return false;
	})
}

function helpInit() {
	//get data array
	helpData = new AjaxData("js/help.json", false);
	data = helpData.getData();
	helpData = undefined;
	helpUi = new AjaxCard();
	//map nav-tab
	NavTabInit();
	//map title, description, content
	mapHelp(0);
	navTabClickListen();
	go_top();
}

helpInit();



