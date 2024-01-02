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
    if (current_user && current_user.userId) {
        var userId = current_user.userId;
        $.ajax({
            url: "/api/history/" + userId,
            method: "GET",
            success: function (data) {
                if (data.status == 'success') {
                    var histories =  data.data;
                    var historiesHtml = '<ul>';
                    histories.forEach(function(history){
                        historiesHtml += ' <div class="nextsong__fist-item nextsong__item nextsong__fist-item-headding--active nextsong__fist-item-background--active">\n' +
                            '            <div class="nextsong__item-img" style="background-image: url(${history.song.image});">\n' +
                            '                <div class="nextsong__item-playbtn"><i class="fas fa-play"></i></div>\n' +
                            '                <div class="songs-item-left-img-playing-box">\n' +
                            '                    <img class="songs-item-left-img-playing" src="/assets/img/songs/icon-playing.gif" alt="playing">\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '            <div class="nextsong__item-body">\n' +
                            '                <span class="nextsong__item-body-heading js__main-color">${history.song.title}</span>\n' +
                            '                <!-- Kiểm tra xem có tác giả nào không trước khi truy cập thuộc tính -->\n' +
                            '                <span class="nextsong__item-body-depsc js__sub-color">${authors ? authors.fullName : \'\'}</span>\n' +
                            '            </div>\n' +
                            '            <div class="nextsong__item-action">\n' +
                            '                <span class="nextsong__item-action-heart">\n' +
                            '                    <i class="fas fa-heart nextsong__item-action-heart-icon1"></i>\n' +
                            '                    <i class="far fa-heart nextsong__item-action-heart-icon2"></i>\n' +
                            '                </span>\n' +
                            '                <span class="nextsong__item-action-dot">\n' +
                            '                    <i class="fas fa-ellipsis-h "></i>\n' +
                            '                </span>\n' +
                            '            </div>\n' +
                            '        </div>`;';
                    });
                    historiesHtml += '</ul>';
                }
            }
        });
    } else {
        console.error("current_user is null or does not have userId property.");
    }


})