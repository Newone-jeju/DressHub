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

//카테고리 변경
const categoryList = [
  {no : 10,
    name : '레포츠'},
  {no : 11,
    name : '자전거'},
  {no : 12,
    name : '스케이팅'},
  {no : 13,
    name : '서핑'},
  {no : 14,
    name : '스킨스쿠버'},
  {no : 20,
    name : '나들이'},
  {no : 21,
    name : '캐주얼'},
  {no : 22,
    name : '신발'},
  {no : 23,
    name : '모자'},
  {no : 24,
    name : '악세사리'},
  {no : 30,
    name : '물놀이'},
  {no : 31,
    name : '튜브'},
  {no : 32,
    name : '돗자리'},
  {no : 33,
    name : '보트'},
  {no : 34,
    name : '텐트/파라솔'},
  {no : 40,
    name : '사진촬영'},
  {no : 41,
    name : '예복'},
  {no : 42,
    name : '구두'},
  {no : 43,
    name : '악세사리'},
  {no : 44,
    name : '전통의상'},
]

changeCategory = categoryList => {
  categoryList.map(categoryList => 
    $(`#nav [href='productList?category=${categoryList.no}']`).text(categoryList.name)
  )
}

changeCategory(categoryList)
