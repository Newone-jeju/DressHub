var category = document.getElementsByClassName("category");
var allcategory = document.getElementsByClassName("allcategory");
console.log(category[0]);
var state=0;
category[0].addEventListener("click", function(){
  if(state==0){
    for(var i=0; i<allcategory.length ; i++){
      allcategory[i].style.display="block";
    }
    state=1;
  }else{
    for(var i=0; i<allcategory.length; i++){
      allcategory[i].style.display="none";
    }
    state=0;
  }


})
