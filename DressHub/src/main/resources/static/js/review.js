(function() {
    

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
        var noReviewImg = new AjaxData("/review/image/no-review.png");
        $(".td-group").html(
            '<div class="no-review flexcenter">' +
            '<image src="/image/'+noReviewImg.getData()+'" alt="no-review">' +
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
        $(".review-head").click(function() {
            var $review_body = $(this).next();
            if ($review_body.css('display') == "none") {
                $($review_body).removeClass('hidd');
            } else {
                $($review_body).addClass('hidd');
            }
        });
    }

    //페이지네이션 포함 리뷰 매핑 addhook 없으면 안됨
    function map_review(review_data) {
        var $review_pagination = $(".review-pagination");
        $review_pagination.addHook('beforeInit', function (){});
        $review_pagination.pagination({
            dataSource: review_data,
            pageSize: 5,
            showPrevious: true,
            showNext: true,
            callback: function(data, pagination) {
                var i;
                ReviewCard.mapCard($review_pagination.prev());
                //별점
                 for(i=0; i<5; i++){
                    getStar(i);
                }
            }
        })
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
                '<div class="td-author">'+review_data.userId+'</div>'+
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
            '<image src="/image/'+review_data.imageUrl+'" alt="review_img">'+

            '<p>'+review_data.comment+'</p>'+
          '</div>'
          )
        });
        var review_cards = reviewCard.getCard();
        // 리뷰없음 검사
        if (review_cards == []) {
            no_review();
        } else {
            window.setTimeout(function(){
                map_review(review_cards);
            },500);               
        }

        //내용접기
        folding();
        //버튼처리
        edit_btn();
        write_btn();
        delete_btn();
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
            var user = new CookieUser();
            user.inspectId(); //로그인 안되있으면 로그인 페이지로 리다이렉션
            write_review("review-form.html", 660, 600, "review_form","none");
        })
    }

    var reviewId = "null";
    //자식창에서도 쓰일 리뷰아이디 핸들러 함수
    function getReviewId(toNull){
        if(toNull == true){
            return reviewId;
        }else{
            reviewId = "null";
        }

    }

    //review body 내 수정버튼
    function edit_btn(){
        $(".review-edit-btn").click(function(){
            //권한검사
            var user = new CookieUser();
            var reviewUser = $(this).parent().parent().prev().children(".td-author").text();
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
            var user = new CookieUser();
            var reviewUser = $(this).parent().parent().prev().children(".td-author").text();
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


    var data = '';
    var reviewCard = new ReviewCard();

    reviewInit();


}())