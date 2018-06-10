(function(){

    // function getURLParameter(name) {
    //     return decodeURI(
    //      (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    //     );
    // }
    function getURLId(url){
        return url.match(/\/([^\/]+)\/?$/)[1];
    }

    //데이터 가져오기
    function dataInit(){
        var productId = getURLId(document.location.href);
        var ajaxData = '';
        //상품 데이터
        ajaxData = new AjaxData('products/'+productId+, false);
        data = ajaxData.getData()[0];
        ajaxData = undefined;
        //이미지 데이터
        var size = ['origin', 'medium', 'small'];
        for(var i = 0; i<size.length; i++){
            ajaxData = new AjaxData('/products/image/image/'+size[i]+data.name+'.jpg', false);
            Object.keys(ajaxImg)[i] = ajaxData.getDate();
        }
    }

    function initMap(){
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
                value: data.size
            },
            {
                target: ".product > .location",
                value: data.location
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

    //날짜 차이 계산
    function calDay(){
        var sDay = $("#s-date").val();
        var eDay = $("#e-date").val();
        var diffDay = "";
        sDay = new Date(sDay);
        eDay = new Date(eDay);
        if(diffDay>=0){
            alert("날짜입력오류: 시작일보다 종료일이 늦습니다");
            return false;
        }
        diffDay = (eDay.getTime() - sDay.getTime())/1000/60/60/24;
        return diffDay;
    }

    //상품이름, 배송비등 보내고 주문 금액 계산후 세션 스토리지 저장
    function sessionPrice(){
        var totalPrice = calDay()*data.costPerDay
        var deposit = data.deposit;
        var totalShipment = 3000;
        var totalPayment = deposit+totalShipment+totalPrice;

        sessionStorage.setItem("productId", data.id);
        sessionStorage.setItem("productName", data.name);
        sessionStorage.setItem("deposit", data.deposit);
        sessionStorage.setItem("totalPrice", totalPrice);
        sessionStorage.setItem("totalShipment", 3000);
        sessionStorage.setItem("totalPayment", totalPayment);
    }

    var ajaxCard ="";
    var ajaxImg = {
        origin: "",
        medium: "",
        small: ""
    };
    var data =[];
    
    //데이터 가져오기
    dataInit();
    //초기 매핑
    initMap(); // init mapping url 필요

    //주문 버튼
    $(".submit-btn > button.request").click(function(){
        var user = new CookieUser();
        user.inspectId();
        sessionPrice();
        AjaxUtil.crudData(postData, "POST", function(){
            window.location.href = '/order.html';//결제페이지
        })
        
    })

    //장바구니 버튼
    $(".submit-btn > button.add-cart").click(function(){
        var user = new CookieUser();
        var userId = user.getUserId();
        user.inspectId();

        var ajaxUtil = new AjaxUtil('/baskets'); // 장바구니URL 필요
        var postData = {
            id: data.id,
            userId: userId
        };
        AjaxUtil.crudData(postData, "POST", function(){
            window.location.href = '/cart.html';//장바구니 페이지
        })
    })


})();




