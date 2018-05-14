$(document).ready(function() {
    var review = new Object();
    review.no_review = function() {
        $(".td-group").html(
            '<div class="no-review flexcenter">' +
            '<img src="img/no-review.png" alt="no-review">' +
            '</div>'
        );
    }

    review.map_review = function(review_data) {
        var i;
        var html = '';
        for(i=0; i<5; i++){
            html += review_data[i];
        }
        $(".td-group").html(html);
        for(i=0; i<5; i++){
            review.getStar(i);
        }
        review.folding();

        
    }

    review.init = function() {
    	$.getJSON('js/reviewData.json', function(data) {
           var review_cards = [];
            $.each(data, function(i, review_data)
            {
                review_cards.unshift( 
                '<div class="review-head review'+review_data.head.no+'">'+        
                    '<div class="td-no">'+review_data.head.no+'</div>'+
                    '<div class="td-rank">'+review_data.head.rank+'</div>'+
                    '<div class="td-title">'+review_data.head.title+'</div>'+
                    '<div class="td-day">'+review_data.head.day+'</div>'+
                '</div>'+
  
              '<div class="review-body hidd">'+
                '<img src="'+review_data.body.img+'" alt="review_img">'+
                '<p>'+review_data.body.text+'</p>'+
              '</div>'
              )
            });
            if (review_cards == []) {
            review.no_review();
            } else {
                review.map_review(review_cards);
            }
        });
        $(".review-write-btn").click(function(){
            review.write_btn("review-form.html", 660, 600, "리뷰쓰기","none");
        })
            
    }

    review.getStar = function(i) {
        var rank = $("div.td-rank:eq("+i+")").text();
        console.log(rank);
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
            console.log(index);
            if ($("div.review-body:eq(" + index + ")").css('display') == "none") {
                $("div.review-body:eq(" + index + ")").removeClass('hidd');
            } else {
                $("div.review-body:eq(" + index + ")").addClass('hidd');
            }
        });
    }
    //review write btn
    review.write_btn = function(url, w, h, name, option) {
        console.log(w);
        var pozX, pozY = 0;
        var sw = screen.availWidth;
        var sh = screen.availHeight;
        var scroll = 0;
        if (option == 'scroll') {
            scroll = 1;
        }
        pozX = (sw - w) / 2;
        pozY = (sh - h) / 2;
        console.log(pozX);
        window.open(url, name, "location=no,status=0,scrollbars=" + scroll + ",resizable=1,width=" + w + ",height=" + h + 
        ",left=" + pozX + ",top=" + pozY +"resizable=no");
    }    
    review.init();

    
    
});