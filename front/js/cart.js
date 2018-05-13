$(document).ready(function () {
					  $.ajax({
                url: './basketList?userId='+userID,
                dataType: 'json',
                type: 'get',
                success: function (data) {
                    totalCount = data.count;
                    var product = {};
                    data = data.list;
                    product.mapcard = function () {
                        var cards = '';
                        for (var i = 0; i < data.length; i++) {
                            cards +=
																'<tr>'+
																	'<td>'+data[i].image+'</td>'+
																	'<td>'+data[i].information+'</td>'+
																	'<td>'+data[i].account+'</td>'+
																	'<td>'+data[i].shipping+'</td>'+
																'</tr>'
                        }
                        $('.cartAdd_container').html(cards);
                    }
                    product.mapcard();
                }
            })
        });
