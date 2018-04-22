function showKeyCode(event) {
event = event || window.event;
var keyID = (event.which) ? event.which : event.keyCode;
if(( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || ( keyID >= 37 && keyID <= 40 ) ||keyID === 8 || keyID === 46){
          // document.getElementById("keyinfo").innerHTML = keyID;
          return keyID;
        }
        else
        {
          // document.getElementById("keyinfo").innerHTML = keyID + " = 숫자만 입력해주세요";
          return false;
        }

}

var primeReason = document.getElementById("primeReason");
console.log(primeReason.options[primeReason.selectedIndex].text);



var getParameters = function (paramName) {
    // 리턴값을 위한 변수 선언
    var returnValue;

    // 현재 URL 가져오기
    var url = location.href;

    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
    for (var i = 0; i < parameters.length; i++) {
        var varName = parameters[i].split('=')[0];
        if (varName.toUpperCase() == paramName.toUpperCase()) {
            returnValue = parameters[i].split('=')[1];
            return decodeURIComponent(returnValue);
        }
    }
};

var userid = getParameters("id");

var id = document.getElementById("userid");
id.setAttribute("value", userid);
console.log(id.getAttribute("value"))