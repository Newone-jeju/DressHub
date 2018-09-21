var newItemData = new AjaxData('/product/list', false)

issueCaroCard(newItemData, )

issuCaroBtn()

function issuCaroFrame(target, name) {
    var frame= new AjaxCard()
    frame.setCard(
    '<div class="card_carousel_container">'+
        '<div class="card_carousel_slide_btn">'+
          '<form>'+
            '<input class="btn_left" type="button">'+
          '</form>'+
        '</div>'+
  
        //card_carousel cardset
        '<div class="card_carousel_container_content">'+
          //js card gen
        '</div>'+
        '<div class="card_carousel_slide_btn">'+
          '<form>'+
            '<input class="btn_right" type="button">'+
          '</form>'+
        '</div>'+
      '</div>'
    )
    frame.mapCard(target)
    
    

}

function issueCaroCard(data, target, sindex, eindex){
    var card = new AjaxCard()
    for(var i= sindex; i <eindex; i++){
        card.setCard(
            '<a href="/product_details.html?productId='+data[i].id+'" class="card_carousel_container_content_card">'+
                '<div class="card_img_wrap">'+
                    '<img src="/product_image/origin'+data[i].thumbnailImage+'" alt="blank" class="card_img">'+
                '</div>'+
                '<div class="card_text_wrap">'+
                    '<p class="text_category">'+data[i].category+'</p>'+
                    '<p class="text_title">'+data[i].name+'</p>'+
                    '<p class="text_price">'+data[i].costPerDay+'/'+data[i].deposit+'</p>'+
                    '<div class="location_wrap">'+
                        '<img src="image/location_pin.png" alt="L" class="icon_location">'+
                        '<span class="text_location">'+data[i].location+'</span>'+
                    '</div>'+
                '</div>'+
            '</a>'
        )
    }

    card.mapCard(target)
    card = undefined;
}