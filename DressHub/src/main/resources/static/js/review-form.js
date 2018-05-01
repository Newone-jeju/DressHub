if(window.opener.$("#product-details h1.title:eq(0)").text() != null) {
	$('.rental-product-content').text(window.opener.$("#product-details h1.title:eq(0)").text());
}
else{

}



$("#rating").rateYo({
            starWidth: "30px",
            halfStar: true
        });


$("send-btn").click(function(){
	opener.parent.location.reload();
	window.close(); 
	self.close(); 
	window.opener = window.location.href; 
	self.close(); 
	window.open('about:blank','_self').close();
	
});

$("cancel-btn").click(function(){
	window.close(); 
	self.close(); 
	window.opener = window.location.href; 
	self.close(); 
	window.open('about:blank','_self').close();
});