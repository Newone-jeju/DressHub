// console.log(sessionStorage.getItem("token"));
//
// if (sessionStorage.getItem("token") === null) {
//     $("#loginArea").html(
//         '<div id="signUpForm">\
//             <h1 class="loginTitle">로그인</h1>\
//             <input id = "id" type="text" class="loginInput" placeholder="아이디" autofocus required>\
//             <input id = "password" type="password" class="loginInput" placeholder="비밀번호" required>\
//             <button id = "loginSubmit"  class="submitButton">로그인</button>\
//             <a href="signUp.html">\
//             <button id = "signUp" class="submitButton" href="signUp.html">회원가입</button>\
//             </a>\
//             </div>')
// }
// else {
//     console.log(decodeData(sessionStorage.getItem("token")));
//
//     var jsonValues = JSON.parse(decodeData(sessionStorage.getItem("token")))
//     $("#loginArea").html('<div id="signUpForm">\
//     <h1 class="loginTitle">' + jsonValues["nickname"] + ' 님 환영합니다.</h1>\
//     <img class="userImage center" src = "/api/user/image/' + jsonValues["imageName"] + '">\
//     <a href = "newArticle.html">\
//     <div class = "newArticle submitButton">글쓰기</div>\
//     </a>\
//     <div id = "logout"  class="submitButton">로그아웃</div>')
// }
//
//
// $("#loginSubmit").click(function() {
//         $.ajax({
//             url: './api/auth/login',
//             contentType: 'application/json',
//             data:JSON.stringify({
//                 id : $("#id").val(),
//                 password : $("#password").val()
//             }),
//             type: 'post',
//             success: function (data) {
//                 sessionStorage.setItem("token", data)
//                 window.location.reload();
//             },
//             error:function (data, textStatus, jqXHR) {
//                 alert("아이디와 패스워드를 확인해 주세요")
//             }
//
//         })
//
//     }
//
// )
//
// $("#logout").click(function() {
//         sessionStorage.clear();
//         window.location.reload();
//     }
// )
//
//
//
//
// function decodeData(token) {
//
//     str = token.split(".")[1];
//     var enc64List, dec64List;
//
//     enc64List = new Array();
//     dec64List = new Array();
//     var i;
//     for (i = 0; i < 26; i++) {
//         enc64List[enc64List.length] = String.fromCharCode(65 + i);
//     }7
//     for (i = 0; i < 26; i++) {
//         enc64List[enc64List.length] = String.fromCharCode(97 + i);
//     }
//     for (i = 0; i < 10; i++) {
//         enc64List[enc64List.length] = String.fromCharCode(48 + i);
//     }
//     enc64List[enc64List.length] = "+";
//     enc64List[enc64List.length] = "/";
//     for (i = 0; i < 128; i++) {
//         dec64List[dec64List.length] = -1;
//     }
//     for (i = 0; i < 64; i++) {
//         dec64List[enc64List[i].charCodeAt(0)] = i;
//     }
//
//     var c=0, d=0, e=0, f=0, i=0, n=0;
//     var input = str.split("");
//     var output = "";
//     var ptr = 0;
//     do {
//         f = input[ptr++].charCodeAt(0);
//         i = dec64List[f];
//         if ( f >= 0 && f < 128 && i != -1 ) {
//             if ( n % 4 == 0 ) {
//                 c = i << 2;
//             } else if ( n % 4 == 1 ) {
//                 c = c | ( i >> 4 );
//                 d = ( i & 0x0000000F ) << 4;
//             } else if ( n % 4 == 2 ) {
//                 d = d | ( i >> 2 );
//                 e = ( i & 0x00000003 ) << 6;
//             } else {
//                 e = e | i;
//             }
//             n++;
//             if ( n % 4 == 0 ) {
//                 output += String.fromCharCode(c) +
//                     String.fromCharCode(d) +
//                     String.fromCharCode(e);
//             }
//         }
//     }
//     while (typeof input[ptr] != "undefined");
//     output += (n % 4 == 3) ? String.fromCharCode(c) + String.fromCharCode(d) :
//         ((n % 4 == 2) ? String.fromCharCode(c) : "");
//     return output;
// }