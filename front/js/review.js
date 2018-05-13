$(function() {
    var review = new Object();
    review.no_review = function() {
        $(".td-group").html(
            '<div class="no-review flexcenter">' +
            '<img src="image/no-review.png" alt="no-review">' +
            '</div>'
        );
    }

    //숫자로 된 점수를 별점화
    review.getStar = function(i) {
        var $td_rank = $("div.td-rank:eq("+i+")");
        var rank = $td_rank.text();
        $td_rank.rateYo({
            starWidth: "20px",
            rating: rank,
            readOnly: true,
            halfStar: true
        });
    }

    //fold review
    review.folding = function(){
        $(".review-head").click(function() {
            var index = ($(this).index()+1 - Math.floor($(this).index() / 2) - 1);
            var $review_body = $("div.review-body:eq(" + index + ")");
            if ($review_body.css('display') == "none") {
                $($review_body).removeClass('hidd');
            } else {
                $($review_body).addClass('hidd');
            }
        });
    }

    //페이지네이션 포함 리뷰 매핑 addhook 없으면 안됨
    review.map_review = function(review_data) {
        var $review_pagination = $(".review-pagination");
        $review_pagination.addHook('beforeInit', function () {});
        $review_pagination.pagination({
            dataSource: review_data,
            pageSize: 5,
            showPrevious: true,
            showNext: true,
            callback: function(data, pagination) {
                var i;
                var html = '';
                $.each(data, function (index, item) {
                  html += item;
                });
                $review_pagination.prev().html(html);
                //별점
                 for(i=0; i<5; i++){
                    review.getStar(i);
                }
                //내용접기
                review.folding();
                review.edit_btn();
            }
        })
    }

    review.init = function() {
        $.getJSON('dresshub.co.kr/review?productId=0', function(data) {
           var review_cards = [];
            $.each(data, function(i, review_data)
            {  
                i = i+1;
                review_cards.unshift(
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
                    '<form name="review_edit" id="review_edit" onsubmit="return false;">'+
                        '<input type="hidden" name="review_id" id="review_id" value="'+review_data.id+'">'+
                        '<button class="review-btn review-body-btn review-edit-btn">수정</button>'+
                    '</form>'+
                    //리뷰 삭제버튼
                    '<form action="dresshub.co.kr/review/delete/'+review_data.id+'" method="post">'+
                        '<button type="submit" class="review-btn review-body-btn review-delete-btn">삭제</button>'+
                    '</form>'+
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
                '<img src="'+review_data.image+'" alt="review_img">'+
                '<p>'+review_data.comment+'</p>'+
              '</div>'
              )
            });
            if (review_cards == []) {
            review.no_review();
            } else {
                window.setTimeout(function(){
                    review.map_review(review_cards,1);
                },500);               
            }
            review.write_btn();
        }); 
    }

    //review write review
    review.write_review = function(url, w, h, name, option) {
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
    review.write_btn = function(){
        $(".review-write-btn").click(function(){
            review.write_review("review-form.html", 660, 600, "review_form","none");
        })
    }

    //review body 내 수정버튼
    review.edit_btn = function(){
        $(".review-edit-btn").click(function(){
            var $post_no = Math.floor($(this).parent().parent().index()/2);
            var review_form = review.write_review("review-form.html", 660, 600, "review_form","none");
            var review_edit = $('#review_edit');
            var review_id = $('#review_id').val();
            $("review_form.document.body .form-url").attr("action", "dresshub.co.kr/reveiw/update/"+review_id);
            review_edit.target = "review_form";
            review_edit.method="post"
            review_edit.submit();
        })
    }

    //review body 내 삭제버튼
    review.delete_btn = function(){
        $(".review-delete-btn").click(function(){})
    }


    review.init();
  });
