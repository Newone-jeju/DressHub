
btn1 = document.getElementById("1")
btn2 = document.getElementById("2")
btn3 = document.getElementById("3")
btn4 = document.getElementById("4")
btn5 = document.getElementById("5")

btns = [btn1, btn2, btn3, btn4, btn5]
btn1.onclick = function (ev) {
    doPost(0)
};
btn2.onclick = function (ev) {
    doPost(1)
};
btn3.onclick = function (ev) {
    doPost(2)
};
btn4.onclick = function (ev) {
    doPost(3)
};
btn5.onclick = function (ev) {
    doPost(4)
};



function doPost(selectedNum) {
    var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post"); // Get 또는 Post 입력
    form.setAttribute("action", "result");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "selected");
    hiddenField.setAttribute("value", btns[selectedNum].value());
    btns.remove(selectedNum)
    form.appendChild(hiddenField);

    for (i = 0 ; i < 4 ; i ++){
        hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "other"+i);
        hiddenField.setAttribute("value", btns[i].value());
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();

}