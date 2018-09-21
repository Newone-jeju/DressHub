//class ReviewCard extends ajaxCard
function ReviewCard(){
    AjaxCard.apply(this, arguments)
}
ReviewCard.prototype = Object.create(AjaxCard.prototype);
ReviewCard.prototype.constructor = ReviewCard;

ReviewCard.prototype.getCard = function(){
    return this.cards;
}


function no_review(){
    var noReviewImg = "no-review.png"
    $(".td-group").html(
        '<div class="no-review flexcenter">' +
        '<image src="/review/image/'+noReviewImg+'" alt="no-review">' +
        '</div>'
    );
}

//숫자로 된 점수를 별점화
function getStar(i) {
    var $td_rank = $("div.td-rank:eq("+i+")");
    var rank = $td_rank.text();
    $td_rank.rateYo({
        starWidth: "20px",
        rating: rank,
        readOnly: true,
        halfStar: true
    });
}

//리뷰 내용 접기
function folding(){
    $(".review-head").click(function(){
        var $review_body = $(this).next();
        if ($review_body.hasClass('hidd')) {
            $($review_body).removeClass('hidd');
        } else {
            $($review_body).addClass('hidd');
        }
    });
}

//페이지네이션 포함 리뷰 매핑 addhook 없으면 안됨
function map_review(review_data) {
    reviewCard.mapCard($(".review-table > .td-group"));
    console.log(review_data.length);
    for(var i=0; i<review_data.length; i++){
        getStar(i);
    }
    //내용접기
    folding();
    //버튼처리
    edit_btn();
    write_btn();
    delete_btn();

}

function reviewInit() {
    $.each(data, function(i, review_data)
    {
        i = i+1;
        reviewCard.setCard(
            //리뷰 카드
            '<div class="review-head review'+i+'">'+
            '<div class="td-no">'+i+'</div>'+
            '<div class="td-rank">'+review_data.rate+'</div>'+
            '<div class="td-title">'+review_data.title+'</div>'+
            '<div class="td-author">'+review_data.user+'</div>'+
            '</div>'+
            //리뷰 접혀진 부분
            '<div class="review-body hidd">'+
            '<div class="review-btn-wrap">'+
            //리뷰 수정 버튼
            '<button class="review-btn review-body-btn review-edit-btn" data-reviewId="'+review_data.id+'">수정</button>'+
            //리뷰 삭제버튼
            '<button type="submit" class="review-btn review-body-btn review-delete-btn" data-reviewId="'+review_data.id+'">삭제</button>'+
            '</div>'+
            //리뷰 날짜정보
            '<div class="review-date-info">'+
            '<div class="lease-duration-wrap">'+
            '<div class="title">대여기간</div>'+
            '<div class="content">'+review_data.leaseStart+'  ~  '+review_data.leaseEnd+'</div>'+
            '</div>'+
            '<div class="write-date-wrap">'+
            '<div class="title">작성날짜</div>'+
            '<div class="content">'+review_data.date+'</div>'+
            '</div>'+
            '</div>'+

            //리뷰내용
            '<image src="/review/image/'+review_data.imageUrl+'" alt="review_img">'+

            '<p>'+review_data.comment+'</p>'+
            '</div>'
        )
    });
    var review_cards = reviewCard.getCard();
    console.log(review_cards)
    // 리뷰없음 검사
    if (review_cards == []) {
        no_review();
    } else {
        window.setTimeout(function(){
            map_review(review_cards);
        },500);
    }
}



//review write review
function write_review(url, w, h, name, option) {
    var pozX, pozY = 0;
    var sw = screen.availWidth;
    var sh = screen.availHeight;
    var scroll = 0;
    if (option == 'scroll') {
        scroll = 1;
    }
    pozX = (sw - w) / 2;
    pozY = (sh - h) / 2;
    return window.open(url, name, "location=no,status=0,scrollbars=" + scroll + ",resizable=1,width=" + w + ",height=" + h +
        ",left=" + pozX + ",top=" + pozY +"resizable=no");
}

//리뷰쓰기 버튼
function write_btn(){
    $(".review-write-btn").click(function(){
        //권한검사
        //로그인 안되있으면 로그인 페이지로 리다이렉션
        var user = new TokenUser();
        if(user.inspectId() == false){
            return false;
        }
        write_review("review-form.html", 660, 600, "review_form","none");
    })
}

var reviewId = "";
//자식창에서도 쓰일 리뷰아이디 핸들러 함수
function getReviewId(toNull){
    if(toNull == true){
        return reviewId;
    }else{
        reviewId = "";
    }
}

function getProductId(){
    return productId;
}

//review body 내 수정버튼
function edit_btn(){
    $(".review-edit-btn").click(function(){
        //권한검사
        var user = new TokenUser();
        //로그인 안되있으면 로그인 페이지로 리다이렉션
        if(user.inspectId() == false){
            return false;
        }해
        var reviewUser = $(this).parent().parent().prev().children(".td-author").text();
        console.log(reviewUser);
        if(user.inspectIdDiff(reviewUser) == false){
            return false
        }
        review_id = $(this).attr("data-reviewId");
        //새창 띄우기
        var review_form = write_review("review-form.html", 660, 600, "review_form");

    })
}

//review body 내 삭제버튼
function delete_btn(){
    $(".review-delete-btn").click(function(){
        //권한검사
        var user = new TokenUser();
        //로그인 안되있으면 로그인 페이지로 리다이렉션
        if(user.inspectId() == false){
            return false;
        }
        //아이디 불일치시
        var reviewUser = $(this).parent().parent().prev().children(".td-author").text();
        console.log(reviewUser);
        if(user.inspectIdDiff(reviewUser) == false){
            return false
        }
        var reviewId = $(this).attr("data-reviewId");
        //삭제되면 새로고침
        var ajaxDelete = new AjaxUtil('/review/'+reviewId);
        ajaxDelete.crudData("", 'DELETE', function(){
            window.location.reload();
        })
    })
}
function getURLParameter(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

var data = '';
var productId = getURLParameter("productId");
var ajaxData = new AjaxData('/review/list/search?productId='+productId, false);
data = ajaxData.getData();
ajaxData = undefined;
var reviewCard = new ReviewCard();

reviewInit();
