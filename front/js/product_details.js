var click = false;

$('.review-head').click(function(){	
	
	var index = $(this).index() - Math.floor($(this).index()/2);
	console.log(index);
	if (click == true) {
		click = false;
		$('.review-body:nth-child('+ index +')').addClass('hidd');
	}
	else{
		click= true;
		$('.review-body:nth-child('+ index +')').removeClass('hidd');
	}	
});