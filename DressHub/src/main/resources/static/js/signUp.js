$("#submitButton").click(function() {
        if($("#password").val() === $("#passwordCheck").val()){
            $.ajax({
                url: './user/signup' ,
                contentType: "application/json",

                data:JSON.stringify({
                    uid : $('#id').val(),
                    password : $("#password").val(),
                    nickname : $("#nickname").val(),
                    name : $("#name").val(),
                    email : $("#email").val(),
                    address : $("#address").val(),
                    phoneNumber : $("#phoneNumber").val(),
                    introduce : $("#introduce").val(),
                    user_type : $("#user_type").val(),
                    resister_date : $("#resister_date").val()
                        // .split("\\").slice(-1)[0]
                }),
                type: 'post',
                success: function (data) {
                    $.each(data, function (i, result) {
                    })
                },
                error: function (data, textStatus, jqXHR) {
                    alert(data.responseJSON.message)
                }
            })

            $("#submitButton").attr("type", "submit")
            alert("회원 가입이 완료 되었습니다.")
            window.location.href = '/'
        }else{

            $("#submitButton").attr("type", "button")
            $("#inputForm").attr("onsubmit", "return false;")
            alert("비빌번호가 다릅니다.")
        }
    }
)