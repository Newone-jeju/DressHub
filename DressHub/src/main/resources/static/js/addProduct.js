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
