
function getData(url, async){
	var data =[];
	$.ajax(
	{
		url: url,
        dataType: 'json',
        async: async,
        type:'get',
        error: function(jqXHR, textStatus, errorThrown) {
            alert(textStatus);
            alert(errorThrown);
        },
        success: function(json_data){
        	data = json_data;
        } 
	})
	console.log(data);
	return data;
}

function setLogData(data_log){
	var log_cards = [];
	$.each(data_log, function(i,data){
		log_cards.push(
			'<tbody class="log-card">'+
              '<tr>'+
                '<td rowspan="3" class="log-no">'+i+'</td>'+
                '<td rowspan="3" class="status log-title">'+data.status+'</td>'+
                '<td class="name-phonenum log-content">dresshub : '+data.phoneNum+'</td>'+
              '</tr>'+
              '<tr>'+
                '<td class="how-long log-content">'+data.startDay+' ~ '+data.endDay+'</td>'+
              '</tr>'+
              '<tr>'+
                '<td class="msg log-content">'+data.message+'</td>'+
              '</tr>'+
            '</tbody>'
			)
	})
	return log_cards;
}

function setCard(data, quantity){
	var cards =[];
	for(var i=0; i<quantity; i++){
		cards.push(
			'<div class="product-card" data-id="'+data[i].id+'">'+
		        '<div class="card-header">'+
		          '<div class="thumnail">'+
		            '<img src="'+data[i].thumnailUrl+'" alt="thumnail_img">'+
		          '</div>'+
		          '<div class="title flexcenter-align">'+
		            '<p class="text">'+data[i].name+'</p>'+
		          '</div>'+
		          '<div class="order-day flexcenter-align">'+
		            '<p class="text">'+data[i].log[data[i].log.length-1].startDay+'</p>'+
		          '</div>'+
		          '<div class="recent-status flexcenter-align">'+
		            '<p class="text">'+data[i].log[0].status+'</p>'+
		          '</div>'+
		        '</div>'+
		        '<div class="card-body hidd">'+
		          '<div class="card-btn-wrap">'+
		              '<input type="text" name="msg" value="" placeholder="수령한 대여의상에 특이사항이 발생하면 입력해 주세요.... " class="msg-box">'+
		              '<button class="card-write-btn card-btn">메시지작성</button>'+
		          '</div>'+
		          '<table class="log-list">'
		          )
		var log_data = setLogData(data[i].log);
		cards = cards.concat(log_data);
		cards.push(
					'</table>'+
		        '</div>'+
		      '</div>'
			)	
	}
	return cards;
}

function map_card(cards, target){
	var html = "";
	$.each(cards, function (i, card) {
      html += card;
    });
    target.html(html);
}

function folding(target){// 다음 body요소(.hidd 가 붙은)에 folding 부여
	target.click(function(){
		var $cardBody = $($(this).next())
		var visibility = $cardBody.hasClass("hidd");
		if(visibility){
			$cardBody.removeClass("hidd");
		}
		else{
			$cardBody.addClass("hidd");
		}
	})
}

function msg_send(data){
	$(".card-write-btn").click(function(){
		var comment = $(this).prev()
		$.ajax({
            type: "post",
            url: "", //좋아요 눌렀을 때 상태정보 전달할 url
            data: {'comment': comment }, // 서버로 보낼 데이터
            dataType: "json",
            success: function(response){     
            }
        });		
	})
}	


var target = "";
var card = [];
//빌린웃
target = $(".rentaling-area .card-td");
card = setCard(getData("js/rental_status.json", false),3);
map_card(card, target);
//빌려 준 옷
target = $(".rented-area .card-td");
card = setCard(getData("js/rental_status.json", false),3);
map_card(card, target);

folding($(".card-header"));

