$(function() {
	var status = new Object();
	status.folding = function(){
		$(".card-header").click(function(){
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
	status.getLogData = function(p_data_log){
		console.log("log");
		var log_cards = []
		$.each(p_data_log, function(i,l_data){
			log_cards.unshift(
				'<tbody class="log-card">'+
	              '<tr>'+
	                '<td rowspan="3" class="log-no">'+i+'</td>'+
	                '<td rowspan="3" class="status log-title">'+l_data.status+'</td>'+
	                '<td class="name-phonenum log-content">dresshub : '+l_data.phoneNum+'</td>'+
	              '</tr>'+
	              '<tr>'+
	                '<td class="how-long log-content">'+l_data.startDay+' ~ '+l_data.endDay+'</td>'+
	              '</tr>'+
	              '<tr>'+
	                '<td class="msg log-content">'+l_data.message+'</td>'+
	              '</tr>'+
	            '</tbody>'
				)
		})
		return log_cards;
	}

	status.getData = function(){
		$.ajax(
		{
			url: 'js/rental_status.json',
            dataType: 'json',
            type:'get',
            error: function(jqXHR, textStatus, errorThrown) {
                alert('An error occurred');
            },
            success: function(data){

            	var cards =[];
            	$.each(data, function(i,p_data){
            		if(i==3){
            			return cards;
            		}
            		cards.push(
						'<div class="product-card" data-id="'+p_data.id+'">'+
					        '<div class="card-header">'+
					          '<div class="thumnail">'+
					            '<img src="'+p_data.thumnailUrl+'" alt="thumnail_img">'+
					          '</div>'+
					          '<div class="title flexcenter-align">'+
					            '<p class="text">'+p_data.name+'</p>'+
					          '</div>'+
					          '<div class="order-day flexcenter-align">'+
					            '<p class="text">'+p_data.log[p_data.log.length -1].startDay+'</p>'+
					          '</div>'+
					          '<div class="recent-status flexcenter-align">'+
					            '<p class="text">'+p_data.log[0].status+'</p>'+
					          '</div>'+
					        '</div>'+
					        '<div class="card-body hidd">'+
					          '<div class="card-btn-wrap">'+
					              '<input type="text" name="msg" value="" placeholder="수령한 대여의상에 특이사항이 발생하면 입력해 주세요.... " class="msg-box">'+
					              '<button class="card-write-btn card-btn">메시지작성</button>'+
					          '</div>'+
					          '<table class="log-list">'
					          )
            		var log_data = status.getLogData(p_data.log[i])
            		cards.concat(log_data);
            		cards.push(
								'</table>'+
					        '</div>'+
					      '</div>'
            			)		
            	})
            }

		})
	}

	status.map_card = function(data){
		console.log("map");
		var html = "";
		$.each(data, function (i, item) {
          html += item;
        });
        $(".card-td").html(html);
	}

	status.msg_send() = function(data){
		$("card-write-btn").click(function(){
			var comment = $(this).prev()
			$.ajax({
                type: "POST",
                url: "", //좋아요 눌렀을 때 상태정보 전달할 url
                data: {'comment': comment }, // 서버로 보낼 데이터
                dataType: "json",
                success: function(response){
                    
                }
            });
			$($(this))
		})
	}

	status.folding();
	status.map_card(status.getData());
})