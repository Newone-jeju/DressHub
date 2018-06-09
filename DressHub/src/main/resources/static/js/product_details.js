(function(){

    var ajaxData ="";
    var ajaxCard ="";

    var data =[];

    function getURLParameter(name) {
        return decodeURI(
         (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
        );
    }




    function initMap(url){

        //데이터 가져오고
        ajaxData = new AjaxData(url, false);
        data = ajaxData.getData()[0];
        ajaxData = undefined;

        //상품 헤더 정보 카테고리, 이름
        $(".page-head-category span.category2").text(data.category);
        $(".productName").text(data.name);

        //카드매핑
        var cardString ="";
        ajaxCard = new AjaxCard();
        //이미지매핑
        //큰 이미지 매핑
        bigMap(1);

        //썸네일바 이미지 매핑 (눌러서 전환 되는) quantity는 이미지 갯수 이미지는 따로 필요
        var quantity = 2;
        for(var i=1; i<=quantity; i++){
            cardString = 
                '<img class="thumnail-img" src="image/tiny_jean'+i+'.jpg" alt = "Thumbnail '+i+'"/>'
            ajaxCard.setCard(cardString);
        }
        ajaxCard.mapCard($(".thumnamil-bar"));

        $(".thumnamil-bar > .thumnail-img").click(function(){
            console.log($(this).index());
            bigMap($(this).index()+1);
        })

        //상품 상세정보
        var productInfo = [
            {
                target: ".regi-day",
                value: data.consigmentStart
            },
            {
                target: ".least-rental",
                value: data.leastLeaseDay+" 일"
            },
            {
                target: ".provider-id",
                value: data.providerId
            },
            {
                target: ".product > .size",
                value: data.size  // 이름 변경 필요
            },
            {
                target: ".product > .location",
                value: data.location // 없음
            },
            {
                target: ".price > .deposit",
                value: data.deposit + " 원"
            },
            {
                target: ".price > .day",
                value: "일 "+ data.costPerDay + " 원"
            },
            {
                target: ".price > .deliver",
                value: "3000 원"
            }
        ]

        $.each(productInfo, function(i, v){
            $(v.target+" > .info").attr("value", v.value);
            $(v.target+" > .info").text(v.value);
        });

        //사이즈 아이콘 처리
        var sizeIcon = $(".product > .size > .info");
        sizeIcon.html('<img src="image/'+sizeIcon.text()+'_icon.png" alt="'+sizeIcon.text()+'">');
        
        //페이지 컨텐츠 상세
        cardString = 
            '<img src="'+data.contentsImage+'" alt="'+data.name+'">'+
            '<p>'+data.contents+'</p>'

        ajaxCard.setCard(cardString);
        ajaxCard.mapCard($("article-area .body"));
        
        ajaxCard = undefined;
    }

    // 대형 이미지 매핑
    function bigMap(i){
        ajaxCard = new AjaxCard();
        var cardString;
        cardString = 
                '<a href="image/big_jean'+i+'.jpg" class="cloud-zoom" id="zoom1" rel="adjustX: 10, adjustY:-4">'+
                  '<img class="big-img" src="image/small_jean'+i+'.jpg" alt="small_jean'+i+'" title="크게 보기" />'+
                '</a>'

        ajaxCard.setCard(cardString);
        ajaxCard.mapCard($(".main-image"));

    }

    //초기 매핑
    initMap("js/productData.json", false); // init mapping url 필요

    //주문 장바구니 버튼
    $(".submit-btn > button.request").click(function(){
        var ajaxUtil = new AjaxUtil(); // 주문URL 필요
        var postData = {
            id: data.id,
            lease_day: $("#s-date").attr("value"),
            return_day: $("#e-date").attr("value")
        };
        AjaxUtil.crudData(postData, "POST", function(){
            window.location.href = '/order.html';//결제페이지
        })
    })

    $(".submit-btn > button.add-cart").click(function(){
        var ajaxUtil = new AjaxUtil(); // 장바구니URL 필요
        var postData = {
            id: data.id
        };
        AjaxUtil.crudData(postData, "POST", function(){
            window.location.href = '/cart.html';//장바구니 페이지
        })
    })

})();




