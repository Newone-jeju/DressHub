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
	console.log($rating.text());
    $(".hid-rank").attr("value", $rating.text());
	$rating.rateYo({
		starWidth: "30px",
		halfStar: true,
		rating: $rating.text(),
  	onSet: function (rating, rateYoInstance) {
    $(".hid-rank").attr("value", rating);
  }
});
}



function getEditInfo(review_id) {
    if ( review_id == "null"){
        $("#rating").text(0);
        $("#rating").attr("value", 0);
        setHiddenRating();
    }else {
        var id = review_id;
        console.log(id)
        $.ajax({
            type: "get",
            url: "review",// id로 받아올 리뷰 url
            data: {'id': id},
            dataType: "json", // 서버에서 받을 데이터 형식
            success: function (response) {
                console.log(response);
                $(".form-url").attr("action", "review/update");
                $(".author-content").val(response[0].userId);
                $("#rating").text(response[0].rate);
                setHiddenRating();
                $(".title-content").val(response[0].title);
                $(".text-content").val(response[0].comment);
                $(".hid-id").val(response[0].id);
            }
        });
    }
}


function reviewFormInit() {
	getName();
	getauthor();
	setHiddenName();
	var edit_id = opener.parent.getEditNum(); 
	console.log(edit_id);
	getEditInfo(edit_id);
}

reviewFormInit();


$(".send-btn").click(function(){
	opener.parent.location.reload();
	// window.open('about:blank', '_self').close();
});

$(".cancel-btn").click(function(){
	// window.open('about:blank', '_self').close();
});



