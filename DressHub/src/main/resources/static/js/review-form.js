function getName(){
    var $rental_product_content = $('.rental-product-content');
    $rental_product_content.text(window.opener.$("#product-details h1.title:eq(0)").text());
    console.log($rental_product_content.text());
    //부모창인 상품상세정보페이지로 부터 접근하지 않은 경우
    if($rental_product_content.text() ==""){
        alert('잘못된 경로로 접근');
        window.open('about:blank', '_self').close();
    }
}

function getauthor(){
    //작성자 이름 가져오기
    var user = new CookieUser();
    var userId = user.getUserId();
    $(".author-content").text(userId);

    $('.hid-id').val(productId);

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
    if ( review_id == ""){
        method = "POST";
        $("#rating").text(0);
        $("#rating").attr("value", 0);
        setHiddenRating();
    }else {
        var id = review_id;
        console.log(id)


        $.ajax({
            type: "GET",
            url: "/review/"+id,// id로 받아올 리뷰 url
            async: false,
            data: {'id': id},
            contentType : "application/json;charset=UTF-8",
            dataType: "json", // 서버에서 받을 데이터 형식
            success: function (response) {
                console.log(response);
                method = "PUT";
                $(".author-content").val(response[0].userId);
                $("#rating").text(response[0].rate);
                setHiddenRating();
                $(".title-content").val(response[0].title);
                $(".text-content").val(response[0].comment);
                $(".hid-id").val(response[0].id);
            }
        });
        //부모창 리뷰아이디 초기화
        opener.parent.getReviewId(false);
    }
}

function gatherForm(){
    return {
        productId: $(".hid-id").val(),
        userId: $(".author-content").text(),
        rate: $(".hid-rank").val(),
        title: $(".title-content").val(),
        comment: $(".text-content").val(),
        imageUrl: $(".img-content").val().split("\\").slice(-1)[0],
        image : $(".preview-img").attr("src")
    }
}

function gatherImage() {
    return{
        imageUrl: $(".img-content")[0].files[0].name
    }
}


function reviewFormInit() {
    getName();
    productId = opener.parent.getProductId();
    getauthor();
    setHiddenName();
    review_id = opener.parent.getReviewId(true);
    console.log(review_id);
    getEditInfo(review_id);
}

var review_id = "";
var productId = ""
var method = "POST";
reviewFormInit();
var formData = {}

$(".img-content").on("change", function () {
    getBase64($(".img-content")[0].files[0])
    $(".preview-img").css("display", "block")
})


$(".send-btn").click(function(){
    var ajaxUtil = new AjaxUtil('review/'+review_id);

    console.log($(".preview-img").attr("src"));

    ajaxUtil.crudData(gatherForm(), method, function(){
        console.log("send form!")
        //이미지 전송
        console.log(gatherForm().image)
        $("#imageForm").on("submit", function() {

        });
        opener.parent.location.reload();
        window.open('about:blank', '_self').close();

    })
    return true;
});

$(".cancel-btn").click(function(){
    window.open('about:blank', '_self').close();
});



function getBase64(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
        $(".preview-img").attr("src",reader.result)
    };
    reader.onerror = function (error) {
        console.log('Error: ', error);
    };
}