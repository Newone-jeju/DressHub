var host = './join';

   function getUrlParams() {
       var params = {};
       window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
           params[key] = value;
       });
       return params;
   }

   function save() {
       var user = {
           name: $('#name').val(),
           id: $('#id').val(),
           password: $('#password').val(),
           passwordCheck: $('#passwordCheck').val(),
           email: $('#email').val(),
           address: $('#address').val(),
           phone_number: $('#phone_number').val(),
           nickname: $('#nickname').val(),
           introduce: $('#introduce').val(),
           radio: $('#radio').val()
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
             $('#name').val(),
             $('#id').val(),
             $('#password').val(),
             $('#passwordCheck').val(),
             $('#email').val(),
             $('#address').val(),
             $('#phone_number').val(),
             $('#nickname').val(),
             $('#introduce').val(),
             $('#radio').val()
           });
       }

       $('#addBtn').on("click", save);
   });
