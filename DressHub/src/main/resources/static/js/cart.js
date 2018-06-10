$(document).ready(function () {
					  $.ajax({
                url: '/baskets/search?uid=aaaa',
                dataType: 'json',
                type: 'get',
                success: function (data) {
                    var product = {};

                    data = data.content;
                    product.mapcard = function () {
                        var cards = ' <tr>\n' +
                            '      <th style="width: 10%;">이미지</th>\n' +
                            '      <th>상품정보</th>\n' +
                            '      <th>상품금액</th>\n' +
                            '      <th>배송비</th>\n' +
                            '    </tr>';
                        for (var i = 0; i < data.length; i++) {
                            cards +=
																'<tr>'+
																	'<td><img style="width:100%"src="./product_image/'+data[i].thumbnailImage+'"/></td>'+
																	'<td>'+data[i].name+'</td>'+
																	'<td>'+data[i].costPerDay+'</td>'+
																	'<td>'+data[i].salePrice+'</td>'+
																'</tr>'
                        }
                        var totalPrice=0;
                        for(var i=0; i<data.length; i++){
                            totalPrice+=data[i].costPerDay;
                        }
                        var totalShipping=0;
                        for(var i=0; i<data.length; i++){
                            totalShipping+=data[i].salePrice;
                        }
                        $('.total_price').html(totalPrice);
                        $('.total_shipping').html(totalShipping);
                        $('.payment_amount').html(totalPrice+totalShipping);

                        $('.cartAdd_container').html(cards);
                        $('#cart_table').html(cards);

                    }
                    product.mapcard();
                }
            })
        });
