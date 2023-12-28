let current_user;


window.addEventListener("load", function () {
    $.ajax({
        url: "/api/session/CURRENT_USER",
        method:"GET",
        success: function (data) {
            if(data.status == 'success'){
                current_user =  data.data;
                console.log(current_user);
            }
        }
    })
})