//function card_carousel(carouselId,jsonFile){}
// $(document).ready(function() {
// 		var carousel ={};
// 	carousel.max_cardnum = 11;
// 	carousel.cardnum = [0,1,2,3];
// 	mainCardData = new AjaxData('./products/list',false)
// 	carousel.data = mainCardData.getData()
//
// 	carousel.mapcard = function(){
// 		// console.log('mapcard');
// 		// console.log(carousel.cardnum);
// 			var cards = '';
// 			for(var i= 0; i <carousel.cardnum.length; i++){
// 				cards +=
// 				'<a href="/product_details.html?productId='+carousel.data[carousel.cardnum[i]].id+'" class="card_carousel_container_content_card">'+
// 					'<div class="card_img_wrap">'+
// 						'<img src="/product_image/origin'+carousel.data[carousel.cardnum[i]].thumbnailImage+'" alt="blank" class="card_img">'+
// 					'</div>'+
// 					'<div class="card_text_wrap">'+
// 						'<p class="text_category">'+carousel.data[carousel.cardnum[i]].category+'</p>'+
// 						'<p class="text_title">'+carousel.data[carousel.cardnum[i]].name+'</p>'+
// 						'<p class="text_price">'+carousel.data[carousel.cardnum[i]].costPerDay+'/'+carousel.data[carousel.cardnum[i]].deposit+'</p>'+
// 						'<div class="location_wrap">'+
// 							'<img src="image/location_pin.png" alt="L" class="icon_location">'+
// 							'<span class="text_location">'+carousel.data[carousel.cardnum[i]].location+'</span>'+
// 						'</div>'+
// 					'</div>'+
// 				'</a>';
// 				// console.log(carousel.cardnum[i]);
// 			}
// 			$('.card_carousel_container_content').html(cards);//+id
// 	}
//
//
// 	carousel.btn_visiblily = function(cardnum){
// 		if(carousel.cardnum[0] <= 0){
// 			$(".btn_left").addClass('hidden');
// 		}
// 		else if(carousel.cardnum[carousel.cardnum.length-1] >= carousel.max_cardnum){
// 			$(".btn_right").addClass('hidden');
// 		}
// 		else{
// 			$(".btn_left").removeClass('hidden');
// 			$(".btn_right").removeClass('hidden');
// 		}
// 	}
//
//
// 	carousel.btn_click = function(dir){
// 		var dirm=1;
// 		if(dir=='btn_left'){
// 			dirm *= -1
// 		}
//
// 		for(var i= 0; i <carousel.cardnum.length; i++){
// 			carousel.cardnum[i] += carousel.cardnum.length*dirm;
// 		}
//
// 		carousel.mapcard();
// 		carousel.btn_visiblily(carousel.cardnum);
//
// 	}
//
//
// 	carousel.mapcard();
// 	carousel.btn_visiblily(carousel.cardnum);
//
// 	$(".btn_left").click(function(){
// 		carousel.btn_click($(this).attr('class'));
// 	})
// 	$(".btn_right").click(function(){
// 		carousel.btn_click($(this).attr('class'));
// 	})
//
//
// })



// 메인 캐러셀

var prevBtn = document.querySelectorAll('button').item(0);
    var nextBtn = document.querySelectorAll('button')[1];
    var carouselWrapper = document.querySelector('.carousel-wrapper');
    var position = 0;
    function prevCarousel() {
      if ( position === 0 ) {
        position = -100;
      }
      var _position = position + 25;
      carouselWrapper.style.transform = 'translateX('+ _position +'%)';
     position = _position;
     console.log('current position', position);
    }
    function nextCarousel() {
      if ( position === -75 ) {
        position = 25;
      }
      var _position = position - 25;
      carouselWrapper.style.transform = 'translateX('+ _position +'%)';
      position = _position;
      console.log('current position', position);
    }

    setInterval(nextCarousel, 3000);

    prevBtn.addEventListener('click', prevCarousel);
    nextBtn.addEventListener('click', nextCarousel);
