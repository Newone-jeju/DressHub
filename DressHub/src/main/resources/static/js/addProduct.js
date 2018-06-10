var filesUpload = document.getElementById("files-upload"),
    fileList = document.getElementById("file-list");
var fileName;
function traverseFiles (files) {
    var li,
        file,
        fileInfo;
    fileList.innerHTML = "";

    for (var i=0, il=files.length; i<il; i++) {
        li = document.createElement("li");
        file = files[i];
        fileInfo = "<div><strong>FileName:</strong> "
            + file.name + "</div>";
        fileName=file.name;
        li.innerHTML = fileInfo;
        fileList.appendChild(li);
    };
};

filesUpload.onchange = function () {
    traverseFiles(this.files);
};

//ajax
var host = './products';

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
        params[key] = value;
    });
    return params;
}

function save() {
    var product = {
        name:$('#name').val(),
        costPerDay: $('#costPerDay').val(),
        deposit: $('#deposit').val(),
        salePrice: $('#salePrice').val(),
        category: $('#category').val(),
        consigmentStart: $('#consigmentStrat').val(),
        consigmentEnd: $('#consigmentEnd').val(),
        leastLeaseDay: $('#leastLeaseDay').val(),
        location: $('#location').val(),
        state: $('#state').val(),
        size: $('#size').val(),
        contents: $('#contents').val(),
        thumbnailImage:fileName
    };

    var method = 'POST';

    if(product.id)
        method = 'PUT';
    requestData(method, product);
    return false;
}

function requestData(method, data) {
    $.ajax({
        url: host,
        method: method,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(data)
    }).done(function () {
        window.location.href = 'productList';
    });
}

$(document).ready(function () {
    var params = getUrlParams();
    if (params.id) {
        $.get(host + "/" + params.id, function (product) {
            $('#id').val(product.id);
            $('#costPerDay').val(product.costPerDay),
                $('#deposit').val(product.deposit),
                $('#salePrice').val(product.salePrice),
                $('#category').val(product.category),
                $('#consigmentEnd').val(product.consigmentEnd),
                $('#leastLeaseDay').val(product.leastLeaseDay),
                $('#location').val(product.location),
                $('#state').val(product.state),
                $('#size').val(product.size),
                $('#contents').val(product.contents),
                $('#image').val(product.image)
        });
    }
    $('#imageForm').submit(function(){
        save();
        return true;
    } ) ;


    // $('#addBtn').on("click", save);
});
