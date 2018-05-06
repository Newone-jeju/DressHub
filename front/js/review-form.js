function getName(){
	$('.rental-product-content').text(window.opener.$("#product-details h1.title:eq(0)").text());
	console.log($('.rental-product-content').text());
	if($('.rental-product-content').text() ==''){
		alert('잘못된 경로로 접근');
		window.open('about:blank', '_self').close();
	}
}

function setHiddenName(){
	$('.rental-product-content').after('<input type="hidden" name="rental-product" value="'+$('.rental-product-content').text()+'">')
}

function setHiddenRating(){
	$("#rating").rateYo({
		starWidth: "30px",
		halfStar: true,
  onSet: function (rating, rateYoInstance) {
    $("#rating").after('<input type="hidden" name="rank" value="'+rating+'">');
  }
});
} 

function reviewFormInit() {
	getName();
	setHiddenName();
	setHiddenRating();
}


reviewFormInit();



$(".send-btn").click(function(){
	opener.parent.location.reload();
	window.open('about:blank', '_self').close();	
});

$(".cancel-btn").click(function(){
	window.open('about:blank', '_self').close();
});