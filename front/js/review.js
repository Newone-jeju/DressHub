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
        var rank = $("div.td-rank:eq("+i+")").text();
        $("div.td-rank:eq("+i+")").rateYo({
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
            if ($("div.review-body:eq(" + index + ")").css('display') == "none") {
                $("div.review-body:eq(" + index + ")").removeClass('hidd');
            } else {
                $("div.review-body:eq(" + index + ")").addClass('hidd');
            }
        });
    }

    //페이지네이션 포함 리뷰 매핑 addhook 없으면 안됨
    review.map_review = function(review_data) {
        $(".review-pagination").addHook('beforeInit', function () {});
        $(".review-pagination").pagination({
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
                $(".review-pagination").prev().html(html);
                //별점
                 for(i=0; i<5; i++){
                    review.getStar(i);
                }
                //내용접기
                review.folding();
            }
        })
    }

    review.init = function() {
        $.getJSON('js/reviewData.json', function(data) {
           var review_cards = [];
            $.each(data, function(i, review_data)
            {
                review_cards.unshift(
                //리뷰 카드
                '<div class="review-head review'+review_data.head.no+'">'+        
                    '<div class="td-no">'+review_data.head.no+'</div>'+
                    '<div class="td-rank">'+review_data.head.rank+'</div>'+
                    '<div class="td-title">'+review_data.head.title+'</div>'+
                    '<div class="td-day">'+review_data.head.day+'</div>'+
                '</div>'+
                //리뷰 접혀진 부분
              '<div class="review-body hidd">'+
                '<div class="review-btn-wrap">'+
                    //리뷰 수정 버튼
                    '<button class="review-btn review-body-btn review-edit-btn">수정</button>'+
                    //리뷰 삭제버튼
                    '<form action="review-delete/'+review_data.head.no+'" method="post">'+
                        '<button type="submit" class="review-btn review-body-btn review-delete-btn">삭제</button>'+
                    '</form>'+
                '</div>'+
                //리뷰내용
                '<img src="'+review_data.body.img+'" alt="review_img">'+
                '<p>'+review_data.body.text+'</p>'+
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
        window.open(url, name, "location=no,status=0,scrollbars=" + scroll + ",resizable=1,width=" + w + ",height=" + h + 
        ",left=" + pozX + ",top=" + pozY +"resizable=no");
    }

    //리뷰쓰기 버튼
    review.write_btn = function(){
        $(".review-write-btn").click(function(){
            review.write_review("review-form.html", 660, 600, "리뷰쓰기","none");
        })
    }

    //review body 내 수정버튼
    review.edit_btn = function(){
        $(".review-edit-btn").click(function(){
            $(".edit-btn-state").text("true");
            review.write_review("review-form.html", 660, 600, "리뷰쓰기","none");
            $(".edit-btn-state").text("false");
        })
    }
    //review body 내 삭제버튼
    review.delete_btn = function(){
        $(".review-delete-btn").click(function(){})
    }


    review.init();
  });