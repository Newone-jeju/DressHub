// sessionStorage.setItem("productName", "가나다");
// sessionStorage.setItem("totalPrice", 2000 );
// sessionStorage.setItem("totalShipment", 3000);
// sessionStorage.setItem("deposit", 7000);


var productName  = sessionStorage.getItem('productName');
var totalPrice   = sessionStorage.getItem('totalPrice');
var totalShipment   = sessionStorage.getItem('totalShipment');
var deposit   = sessionStorage.getItem('deposit');
var totalPayment= parseInt(totalPrice)+parseInt(totalShipment)+parseInt(deposit);

$('.productName').html(productName);
$('.deposit').html(deposit);
$('.totalPrice').html(totalPrice);
$('.totalShipment').html(totalShipment);
$('.totalPayment').html(totalPayment);

// *******************************************

var host = './order';

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
        params[key] = value;
    });
    return params;
}

function save() {
    var user = {
        id: $('#id').val(),
        productId: $('#productId').val(),
        startDay: $('#startDay').val(),
        endDay: $('#endDay').val()
    };

    var method = 'POST';

    if(user.id!='')
        method = 'PUT';
    requestData(method, user);
    return false;
}

function requestData(method, data) {
    $.ajax({
        url: host,
        method: method,
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function () {
        window.location.href = 'list';
    });
}

$(document).ready(function () {
    var params = getUrlParams();
    if (params.id) {
        $.get(host + "/" + params.id, function (user) {
            $('#id').val(),
                $('#productId').val(),
                $('#startDay').val(),
                $('#endDay').val()
        });
    }

    $('#payment').on("click", save());
});



// $(document).ready(function () {
//                  $.ajax({
//                 url: '/basketList?userId=user1',
//                 dataType: 'json',
//                 type: 'get',
//                 success: function (data) {
//                     var product = {};
//
//                     data = data.product;
//                     product.mapcard = function () {
//                         var cards = ' <tr>\n' +
//                             '      <th>상품명</th>\n' +
//                             '      <th>상품금액</th>\n' +
//                             '      <th>배송비</th>\n' +
//                             '    </tr>';
//                         for (var i = 0; i < data.length; i++) {
//                             cards +=
//                                                 '<tr>'+
//                                                    '<td><img style="width:100%"src="./product_image/'+data[i].thumbnailImage+'"/></td>'+
//                                                    '<td>'+data[i].name+'</td>'+
//                                                    '<td>'+data[i].costPerDay+'</td>'+
//                                                    '<td>'+data[i].salePrice+'</td>'+
//                                                 '</tr>'
//                         }
//                         var totalPrice=0;
//                         for(var i=0; i<data.length; i++){
//                             totalPrice+=data[i].costPerDay;
//                         }
//                         var totalShipping=0;
//                         for(var i=0; i<data.length; i++){
//                             totalShipping+=data[i].salePrice;
//                         }
//                         $('.total_price').html(totalPrice);
//                         $('.total_shipping').html(totalShipping);
//                         $('.payment_amount').html(totalPrice+totalShipping);
//
//                         $('.cartAdd_container').html(cards);
//                         $('#cart_table').html(cards);
//
//                     }
//                     product.mapcard();
//                 }
//             })
//         });
