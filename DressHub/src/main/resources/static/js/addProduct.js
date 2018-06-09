var filesUpload = document.getElementById("files-upload"),
    fileList = document.getElementById("file-list");

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
    var user = {
        id: $('#id').val(),
        costPerDay: $('#costPerDay').val(),
        deposit: $('#deposit').val(),
        salePrice: $('#salePrice').val(),
        category: $('#category').val(),
        consigmentEnd: $('#consigmentEnd').val(),
        leastLeaseDay: $('#leastLeaseDay').val(),
        location: $('#location').val(),
        state: $('#state').val(),
        size: $('#size').val(),
        contents: $('#contents').val(),
        image: $('#image').val()
    };

    var method = 'POST';

    if(user.id!='')
        method = 'PUT';
    requestData(method, user);
    return false;
}

function requestData(method, data) {
    $.ajax({
        url: host,
        method: method,
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function () {
        window.location.href = 'list';
    });
}

$(document).ready(function () {
    var params = getUrlParams();
    if (params.id) {
        $.get(host + "/" + params.id, function (user) {
            $('#id').val(user.id);
            $('#costPerDay').val(user.costPerDay),
                $('#deposit').val(user.deposit),
                $('#salePrice').val(user.salePrice),
                $('#category').val(user.category),
                $('#consigmentEnd').val(user.consigmentEnd),
                $('#leastLeaseDay').val(user.leastLeaseDay),
                $('#location').val(user.location),
                $('#state').val(user.state),
                $('#size').val(user.size),
                $('#contents').val(user.contents),
                $('#image').val(user.image)
        });
    }

    $('#addBtn').on("click", save);
});
