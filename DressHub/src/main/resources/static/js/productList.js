$(document).ready(function () {


    categoryString = categoryCodeConvert(categoryCode);

    paging(1, 25, 10, 1);

    function paging(totalData, dataPerPage, pageCount, currentPage) {
        console.log("abc");
        console.log("currentPage : " + currentPage);
        console.log("totalData :" + totalData);
        var totalPage = Math.ceil(totalData / dataPerPage);    // 총 페이지 수
        var pageGroup = Math.ceil(currentPage / pageCount);    // 페이지 그룹
        console.log("totalPage : " + totalPage);
        console.log("pageGroup : " + pageGroup);

        var last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호
        if (last > totalPage)
            last = totalPage;
        var first = last - (pageCount - 1);    // 화면에 보여질 첫번째 페이지 번호

        if (first <= 0) {
            first = 1;
        }

        var next = last + 1;
        var prev = first - 1;

        console.log("last : " + last);
        console.log("first : " + first);
        console.log("next : " + next);
        console.log("prev : " + prev);
        console.log("total:" + totalPage);

        var $pingingView = $("#paging");

        var html = "";

        if (prev > 0)
            html += "<a href=# id='prev'><</a> ";

        for (var i = first; i <= last; i++) {
            html += "<a href='#' id=" + i + ">" + i + "</a> ";
        }

        if (last < totalPage)
            html += "<a href=# id='next'>></a>";

        $("#paging").html(html);    // 페이지 목록 생성
        $("#paging a").css("color", "black");
        $("#paging a#" + currentPage).css({
            "text-decoration": "none",
            "color": "red",
            "font-weight": "bold"
        });    // 현재 페이지 표시
        $("#paging a").click(function () {
            var $item = $(this);
            var $id = $item.attr("id");
            selectedPage = $item.text();
            console.log("selectPage = " + selectedPage);
            if ($id == "next") selectedPage = next;
            if ($id == "prev") selectedPage = prev;
            $.ajax({
                url: './products/search?page=' + selectedPage + "&category=" + categoryString,
                dataType: 'json',
                type: 'get',
                success: function (data) {
                    totalCount = data.count;
                    var product = {};
                    console.log("$item=" + $item);
                    data = data.list;
                    console.log(data);
                    product.max_cardnum = 11;

                    product.mapcard = function () {
                        var cards = '';
                        console.log("datalengh=" + data);
                        if (data == null) {
                            alert("아직 등록된 상품이 없습니다.");
                        } else {
                            for (var i = 0; i < data.length; i++) {
                                cards +=
                                    '<a href="/productDetail?productId=' + data[i].id +'" class="product_container_content_card">' +
                                    '<div class="card_img_wrap">' +
                                    '<img src="../product_image/' + data[i].thumbnailImage + '" alt="blank" class="card_img">' +
                                    '<div class="hover-content">' +
                                    '<img src="../image/' + data[i].state + '_icon.png}" alt="" class="hover-size">' +
                                    '<div class="hover-btn-wrap">' +
                                    '<img src="../image/like_btn.png" alt="" class="like_btn" name="' + data[i].id + '">' +
                                    '<img src="../image/cart_btn_0.png" alt="" class="cart_btn" name="' + data[i].id + '">' +// json 추가 필요
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="card_text_wrap">' +
                                    '<h3 class="text_name">' + data[i].name + '</h3>' +
                                    '<p class="text_deposit">보증금 : ' + data[i].deposit + '</p>' +
                                    '<p class="text_costPerDay">1일 렌탈료 : ' + data[i].costPerDay + '</p>' +
                                    '</div>' +
                                    '</a>';
                            }
                            $('.product_container_content').html(cards);
                        }

                    }
                    product.mapcard();
                    //페이징

                    paging(totalCount, 25, 10, selectedPage);
                    console.log("test");

                    $(".like_btn").click(function(e){
                        console.log("likebtntest");
                        e.stopPropagation();
                        var id = $(this).attr('name');
                        var state = $(".like_btn").hasClass("0");
                        $.ajax({
                            type: "POST",
                            url: "", //좋아요 눌렀을 때 상태정보 전달할 url
                            data: {'id': id, 'like' : state }, // 서버로 보낼 데이터
                            dataType: "json",
                            success: function(response){
                                if(state){
                                    $(this).attr('src', 'img/like_btn_1');
                                    $(this).removeClass("0");
                                    $(this).addClass("1");
                                }
                                else{
                                    $(this).attr('src', 'img/like_btn_0');
                                    $(this).removeClass("1");
                                    $(this).addClass("0");
                                }
                            }
                        });
                    })

                    $(".cart_btn").click(function(){
                        var id = $(this).attr('name');
                        var state = $(".cart_btn").hasClass("0");
                        $.ajax({
                            type: "POST",
                            url: "", //좋아요 눌렀을 때 상태정보 전달할 url
                            data: {'id': id, 'like' : state }, // 서버로 보낼 데이터
                            dataType: "json",
                            success: function(response){
                                if(state){
                                    $(this).attr('src', 'img/cart_btn_1');
                                    $(this).removeClass("0");
                                    $(this).addClass("1");
                                }
                                else{
                                    $(this).attr('src', 'img/cart_btn_0');
                                    $(this).removeClass("1");
                                    $(this).addClass("0");
                                }
                            }
                        });
                    })
                }
            })

        });
    }


    function categoryCodeConvert(category) {
        switch (category) {
            case "10":
                return "캐쥬얼";
            case "11":
                return "캐쥬얼>남성";
            case "12":
                return "캐쥬얼>여성";
            case "13":
                return "캐쥬얼>기타";
            case "20":
                return "예복";
            case "21":
                return "예복>경조사";
            case "22":
                return "예복>면접";
            case "23":
                return "예복>실생활";
            case "24":
                return "예복>기타";
            case "30":
                return "악세사리";
            case "31":
                return "악세사리>남성";
            case "32":
                return "악세사리>여성";
            case "33":
                return "악세사리>고가";
            case "34":
                return "악세사리>기타";
            case "40":
                return "할인기획";
            case "41":
                return "할인기획>의류";
            case "42":
                return "할인기획>악세사리";
            case "43":
                return "할인기획>신발";
            case "44":
                return "할인기획>기타";
            default:
                return "캐쥬얼";
        }
    }







    $("#paging a").trigger("click");
    // 퀵메뉴
    $(document).ready(function () {
        $(function () {
            $(window).scroll(function () {
                var position = $(window).scrollTop(); // 현재 스크롤바의 위치
                console.log(position);
                if (position == 0) {
                    $(".quick-menu-area").stop().animate({"top": -position + 750 + "px"}, 500);//750은 현재top값
                } else if (position > 0 && position < 699) {
                    $(".quick-menu-area").stop().animate({"top": -position + 750 - 25 + "px"}, 500);//고정된 header가 있는데, 그 높이가 25
                } else if (position > 700) {
                    $(".quick-menu-area").stop().animate({"top": 70 + "px"}, 500);
                }
            });
        });
    })
})
