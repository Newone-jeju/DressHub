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

function getauthor(){
	//작성자 이름 가져오기
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
    console.log(rating)
  }
});
}

function getEditInfo(review_id) {
	var id = review_id;
    $.ajax({ 
      type: "POST",
      url: "review?id="+id+"",// id로 받아올 리뷰 url 
      data: {'id': id },
      dataType: "json", // 서버에서 받을 데이터 형식
      success: function(response){
      	console.log(response)
      	$(".form-url").attr("action", "reveiw/update/"+id);
      	$(".author-content").val(response[0].userId)
      	$("#rating").rateYo({
      		starWidth: "30px",
      		rating: response[0].rate,
			halfStar: true,
      	});
        $(".title-content").val(response[0].title);
        $(".text-content").val(response[0].comment);
      }
    });
}


function reviewFormInit() {
	getName();
	getauthor();
	setHiddenName();
	setHiddenRating();
	var id = window.opener.getReviewId();
	getEditInfo(id);
}

reviewFormInit();


$(".send-btn").click(function(){
	opener.parent.location.reload();
	window.open('about:blank', '_self').close();	
});

$(".cancel-btn").click(function(){
	window.open('about:blank', '_self').close();
});