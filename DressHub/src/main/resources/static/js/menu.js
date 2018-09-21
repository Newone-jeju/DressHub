var category = document.getElementsByClassName("category");
var allcategory = document.getElementsByClassName("allcategory");
var memu1 = document.getElementsByClassName("memu1");
var memu2 = document.getElementsByClassName("memu2");
var memu3 = document.getElementsByClassName("memu3");
var memu4 = document.getElementsByClassName("memu4");
var list_name=document.getElementsByClassName("name");

// 전체카테고리
var state=0;
category[0].addEventListener("click", function(){
  if(state==0){
    for(var i=0; i<allcategory.length ; i++){
      allcategory[i].style.display="block";
    }
    state=1;
  }else{
    for(var i=0; i<allcategory.length; i++){
      allcategory[i].style.display="";
    }
    state=0;
  }
})


// 퀵메뉴
$(document).ready(function(){
  $(function(){
    $(window).scroll(function() {
      var position = $(window).scrollTop(); // 현재 스크롤바의 위치
      if (position == 0) {
        $(".quick-menu-area").stop().animate({"top":-position+750+"px"},500);//750은 현재top값
      }else if(position > 0 && position < 699){
        $(".quick-menu-area").stop().animate({"top":-position+750-25+"px"},500);//고정된 header가 있는데, 그 높이가 25
      }else if (position > 700) {
        $(".quick-menu-area").stop().animate({"top":70+"px"},500);
      }
    });
  });
})
