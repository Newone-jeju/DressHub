$(document).ready(function() {
	$.ajax({
					 url:'./product/',
					 dataType:'json',
					 type:'get',
					 success:function(data){
						 var product ={};
					 	product.max_cardnum = 11;
					 	product.mapcard = function(){
					 			var cards = '';
					 			for(var i=0 ; i <data.length; i++){
					 				cards +=
					 				'<a href="'+data[i].url+'" class="product_container_content_card">'+
					 					'<div class="card_img_wrap">'+
					 						'<img src="'+data[i].image+'" alt="blank" class="card_img">'+
					 					'</div>'+
					 					'<div class="card_text_wrap">'+
					 						'<h3 class="text_name">'+data[i].name+'</h3>'+
					 						'<p class="text_deposit">보증금 : '+data[i].deposit+'</p>'+
					 						'<p class="text_costPerDay">1일 렌탈료 : '+data[i].costPerDay+'</p>'+
					 					'</div>'+
					 				'</a>';
					 			}
					 			$('.product_container_content').html(cards);
					 	}
					 	product.mapcard();

					 	//페이징
					 			var totalData = data.length;    // 총 데이터 수
					 	    var dataPerPage = 16;    // 한 페이지에 나타낼 데이터 수
					 	    var pageCount = parseInt(data.length/16)+1;        // 한 화면에 나타낼 페이지 수

					 	    function paging(totalData, dataPerPage, pageCount, currentPage){

					 	        console.log("currentPage : " + currentPage);

					 	        var totalPage = Math.ceil(totalData/dataPerPage);    // 총 페이지 수
					 	        var pageGroup = Math.ceil(currentPage/pageCount);    // 페이지 그룹

					 	        console.log("pageGroup : " + pageGroup);

					 	        var last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호
					 	        if(last > totalPage)
					 	            last = totalPage;
					 	        var first = last - (pageCount-1);    // 화면에 보여질 첫번째 페이지 번호
					 	        var next = last+1;
					 	        var prev = first-1;

					 	        console.log("last : " + last);
					 	        console.log("first : " + first);
					 	        console.log("next : " + next);
					 	        console.log("prev : " + prev);

					 	        var $pingingView = $("#paging");

					 	        var html = "";

					 	        if(prev > 0)
					 	            html += "<a href=# id='prev'><</a> ";

					 	        for(var i=first; i <= last; i++){
					 	            html += "<a href='#' id=" + i + ">" + i + "</a> ";
					 	        }

					 	        if(last < totalPage)
					 	            html += "<a href=# id='next'>></a>";

					 	        $("#paging").html(html);    // 페이지 목록 생성
					 	        $("#paging a").css("color", "black");
					 	        $("#paging a#" + currentPage).css({"text-decoration":"none",
					 	                                           "color":"red",
					 	                                           "font-weight":"bold"});    // 현재 페이지 표시

					 	        $("#paging a").click(function(){

					 	            var $item = $(this);
					 	            var $id = $item.attr("id");
					 	            var selectedPage = $item.text();

					 	            if($id == "next")    selectedPage = next;
					 	            if($id == "prev")    selectedPage = prev;

					 	            paging(totalData, dataPerPage, pageCount, selectedPage);
					 	        });

					 	    }

					 	    $("document").ready(function(){
					 	        paging(totalData, dataPerPage, pageCount, 1);
					 	    });
					  })

					 }
			 })


 // 퀵메뉴
 $(document).ready(function(){
   $(function(){
     $(window).scroll(function() {
       var position = $(window).scrollTop(); // 현재 스크롤바의 위치
			 console.log(position);
       if (position == 0) {
         $(".quick-menu-area").stop().animate({"top":-position+750+"px"},500);//750은 현재top값
       }else if(position > 0 && position < 699){
         $(".quick-menu-area").stop().animate({"top":-position+750-25+"px"},500);//고정된 header가 있는데, 그 높이가 25
       }else if (position > 700) {
         $(".quick-menu-area").stop().animate({"top":70+"px"},500);
       }
     });
   });
 })
