$(document).ready(function () {
    paging(1, 25, 10, 1);

    function paging(totalData, dataPerPage, pageCount, currentPage) {
        var totalPage = Math.ceil(totalData / dataPerPage);    // 총 페이지 수
        var pageGroup = Math.ceil(currentPage / pageCount);    // 페이지 그룹

        var last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호
        if (last > totalPage)
            last = totalPage;
        var first = last - (pageCount - 1);    // 화면에 보여질 첫번째 페이지 번호
        if (first <= 0) {
            first = 1;
        }
        var next = last + 1;
        var prev = first - 1;
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
            if ($id == "next") selectedPage = next;
            if ($id == "prev") selectedPage = prev;

            $.ajax({
                url: './products/search?page=' + selectedPage,
                dataType: 'json',
                type: 'get',
                success: function (data) {
                    totalCount = data.totalElements;
                    var product = {};
                    data = data.content;
                    product.max_cardnum = 11;

                    product.mapcard = function () {
                        var cards = '';
                        for (var i = 0; i < data.length; i++) {
                            cards +=
                                '<a href="' + data[i].url + '" class="product_container_content_card">' +
                                '<div class="card_img_wrap">' +
                                '<img src="/product/origin' + data[i].thumbnailImage + '" alt="blank" class="card_img">' +
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
                    product.mapcard();

                    paging(totalCount, 25, 10, selectedPage);
                }
            })
        });
    }

    $("#paging a").trigger("click");
})