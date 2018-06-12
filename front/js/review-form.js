function getName(){
	var $rental_product_content = $('.rental-product-content');
	$rental_product_content.text(window.opener.$("#product-details h1.title:eq(0)").text());
	console.log($rental_product_content.text());
	//부모창인 상품상세정보페이지로 부터 접근하지 않은 경우
	if($rental_product_content.text() ==''){
		alert('잘못된 경로로 접근');
		window.open('about:blank', '_self').close();
	}
}

function setHiddenName(){
	$('.rental-product-content').after('<input type="hidden" name="rental-product" value="'+$('.rental-product-content').text()+'">')
}

function setHiddenRating(){
	var $rating = $("#rating"); 
	$rating.rateYo({
		starWidth: "30px",
		halfStar: true,
  	onSet: function (rating, rateYoInstance) {
    $rating.after('<input type="hidden" name="rank" value="'+rating+'">');
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
