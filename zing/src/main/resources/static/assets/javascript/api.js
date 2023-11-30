function loadSingerData(userId) {

    var url = '/api/users/' + userId;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);

            updateSingerPage(response);
        }
    };

    xhr.send();
}

// Hàm cập nhật dữ liệu lên trang singer.html
function updateSingerPage(data) {
    var singerContainer = document.getElementById('main-pertional');
    var singerHtml = generateSingerHtml(data);
    console.log(singerHtml);

    singerContainer.innerHTML = singerHtml;
}

function generateSingerHtml(data) {
    var authors = data.authors;
    var songs = data.songs;

    var authorHtml = '<div class="profile">' +
        '<img alt="avata" class="profile__img" src="/assets/img/mv/' + authors.avatar + '">' +
        '<span class="profile__name js__main-color">' + authors.fullName + '</span>' +
        '<div class="grid row">';

    // Thêm HTML cho mỗi bài hát
    songs.forEach(function (song) {
        authorHtml += '<div class="col l-4 m-6 s-6">' +
            '<div class="songs-item js__song-item1">' +
            '<div class="songs-item-left">' +
            '<div class="songs-item-left-img js__songs-item-left-img-1" style="background-image: url(/assets/img/songs/' + song.image + ');">' +
            '<div class="songs-item-left-img-playbtn"><i class="fas fa-play"></i></div>' +
            '<div class="songs-item-left-img-playing-box">' +
            '<img alt="playing" class="songs-item-left-img-playing" src="/assets/img/songs/icon-playing.gif">' +
            '</div>' +
            '</div>' +
            '<div class="songs-item-left-body">' +
            '<h3 class="songs-item-left-body-name js__main-color">' + song.title + '</h3>' +
            // '<span class="songs-item-left-body-singer js__sub-color">' + song.author.fullName + '</span>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
    });

    // Đóng thẻ div
    authorHtml += '</div></div>';

    return authorHtml;
}