(function(){

    var ajaxData ="";
    var ajaxCard ="";
    var ajaxUtil ="";

    var data =[];

    function initMap(url){

        //데이터 가져오고
        ajaxData = new AjaxData(url);
        data = ajaxData.getData();
        ajaxData = undefined;

        //상품 헤더 정보 카테고리, 이름
        ajaxUtil.editVal($(".page-head-category span.category2"), "value", data[0].list.category);
        ajaxUtil.editVal($(".productName"), "value", data[0].list.name);

        //카드매핑
        var cardString ="";
        ajaxCard = new AjaxCard(data);
        //이미지매핑
        //큰 이미지 매핑
        cardString = 
            '<div class="main-image">'+
                '<a href="image/big_jean.jpg" class="cloud-zoom" id="zoom1" rel="adjustX: 10, adjustY:-4">'+
                  '<img class="big-img" src="image/small_jean.jpg" alt='' title="크게 보기" />'+
                '</a>'+
            '</div>'

        ajaxCard.setCard(cardString);
        ajaxCard.mapCard($(".image-area"));

        //썸네일바 이미지 매핑 (눌러서 전환 되는) quantity는 이미지 갯수 이미지는 따로 필요
        for(var i=0; i<quantity i++){
            cardString = 
                '<a href="image/big_jean.jpg" class="cloud-zoom-gallery" title="Thumbnail 1" rel="useZoom: "zoom1", smallImage: "image/small_jean.jpg" ">'+
                    '<img class="thumnail-img" src="image/tiny_jean.jpg" alt = "Thumbnail 1"/>'+
                '</a>'

            ajaxCard.setCard(cardString);
        }
        ajaxCard.mapCard($(".thumnamil-bar"));

        //상품 상세정보
        var productInfo = [
            {
                target: ".regi-day",
                value: data[0].list.consigmentStart
            },
            {
                target: ".least-rental",
                value: data[0].list.leastLeaseDay
            },
            {
                target: ".provider-id",
                value: data[0].list.providerId
            },
            {
                target: ".product > .size",
                value: data[0].list.ProductSize  // 이름 변경 필요
            },
            {
                target: ".product > .location",
                value: data[0].list.location // 없음
            },
            {
                target: ".price > .deposit",
                value: data[0].list.deposit + " 원"
            },
            {
                target: ".price > .day",
                value: "일 "+ data[0].list.costPerDay + " 원"
            },
            {
                target: ".price > .deliver",
                value: "3000 원"
            }
        ]

        $.each(productInfo, function(i, v){
            //ajaxUtil.editVal(target, property, val)
            ajaxUtil.editVal($(v.target+" > info"), "value", v.value);
        });

        //사이즈 아이콘 처리
        var sizeIcon = $(".product > .size > .info");
        sizeIcon.html('<img src="'+sizeIcon.text()+'" alt="'+sizeIcon.text()+'">');
        
        //페이지 컨텐츠 상세
        cardString = 
            '<img src="'+data[0].list.contentsImage+'" alt="'+data[0].list.name+'">'+
            '<p>'+data[0].list.contents+'</p>'

        ajaxCard.setCard(cardString);
        ajaxCard.mapCard($(".image-area"));
        
        ajaxCard = undefined;
    }

    function restSend(){

        $(".submit-btn > button.request").click(function(){
            var postData = {
                id: data[0].list.id,
                
            };
            AjaxUtil.sendData()
        })
        $(".submit-btn > button.add-cart").click(function(){

        })
    }

    ajaxUtil = new AjaxUtil();
    initMap();


})();




