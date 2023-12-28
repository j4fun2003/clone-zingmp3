const nextSongHeadding = $('.nextsong__fist');
function handleLoginFormSubmit(event) {
    event.preventDefault();
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var loginData = {
        username: username,
        password: password
    };
    login(loginData)
        .then(function(response) {
        if (response.user) {
            var userAvatar = response.user.avatar;
            changeUserImage(userAvatar);
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Dữ liệu đã được nhập thành công.',
                showConfirmButton: false,
                timer: 1500 // Tự động đóng thông báo sau 1.5 giây
            });
        } else {
            Swal.fire({
                icon: 'fail',
                title: 'Thành công!',
                text: 'Dữ liệu đã được nhập thành công.',
                showConfirmButton: false,
                timer: 1500 // Tự động đóng thông báo sau 1.5 giây
            });
        }
    })
}


function handleRegisterFormSubmit(event) {
    event.preventDefault();
    var username = document.getElementById('createUsername').value;
    var password = document.getElementById('createPassword').value;
    var email = document.getElementById('email').value;
    var fullName = document.getElementById('fullName').value;
    var provider = 'Application';
    var genders = true;

    var registerData = {
        username: username,
        password: password,
        email:email,
        fullName:fullName,
        provider: provider,
        genders: genders
    };
    registerUser(registerData)
        .then(function(response) {
            if (response.user) {
                var userAvatar = response.user.avatar;
                changeUserImage(userAvatar);
                console.log('Đăng ký thành công:', response);
                document.getElementById('registerModalBtn').style.display = 'none';
                document.getElementById('loginModalBtn').style.display = 'none';
                setSession(response.user);
            } else {
                console.error('Đăng ký thất bại:', response);
            }
        })
}
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
            '<div class="songs-item-left-img js__songs-item-left-img-1" style="background-image: url(\'/assets/img/songs/' + song.image + '\')">' +
            '<div class="songs-item-left-img-playbtn"><i class="fas fa-play"></i></div>' +
            '<div class="songs-item-left-img-playing-box">' +
            '<img alt="playing" class="songs-item-left-img-playing" src="/assets/img/songs/icon-playing.gif">' +
            '</div>' +
            '</div>' +
            '<div class="songs-item-left-body">' +
            '<h3 class="songs-item-left-body-name js__main-color">' + song.title + '</h3>' +
            '<span class="songs-item-left-body-singer js__sub-color">' + song.author.fullName + '</span>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
    });

    // Đóng thẻ div
    authorHtml += '</div></div>';

    return authorHtml;
}

function changeUserImage(avatarUrl) {
    // Lấy đối tượng ảnh theo class
    var userImage = document.querySelector('.header__user-img');

    // Kiểm tra xem đối tượng ảnh có tồn tại không
    if (avatarUrl == null) {
        userImage.src = "/assets/img/header/user/null.jpg";
        userImage.alt = "user";
    } else {
        userImage.src = "/assets/img/header/user/"+avatarUrl;
        userImage.alt = "user";
    }
}

// Song

function getSongById(songId) {
    var url = '/api/songs/' + songId;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            updateHistory(response);
        }
    };
    xhr.send();
}

function updateHistory(data) {
    var nextSongFirstElements = document.getElementsByClassName('nextsong__fist');

    // Kiểm tra xem có phần tử nào có class 'nextsong__fist' hay không
    if (nextSongFirstElements.length > 0) {
        var nextSongFirst = nextSongFirstElements[0]; // Chọn phần tử đầu tiên nếu có nhiều phần tử cùng class
        var songHtml = generateSong(data);
        console.log(songHtml);
        nextSongFirst.innerHTML = songHtml;
    } else {
        console.error("Element with class 'nextsong__fist' not found.");
    }
}


function generateSong(data) {
    var song = data.data.songs;
    var authors = data.data.authors;

    var songHtml = `
        <!-- nextsong__fist-item-headding--active nextsong__fist-item-playbtn--active-->
        <div class="nextsong__fist-item nextsong__item nextsong__fist-item-headding--active nextsong__fist-item-background--active">
            <div class="nextsong__item-img" style="background-image: url(${song.image});">
                <div class="nextsong__item-playbtn"><i class="fas fa-play"></i></div>
                <div class="songs-item-left-img-playing-box">
                    <img class="songs-item-left-img-playing" src="/assets/img/songs/icon-playing.gif" alt="playing">
                </div>
            </div>
            <div class="nextsong__item-body">
                <span class="nextsong__item-body-heading js__main-color">${song.title}</span>
                <!-- Kiểm tra xem có tác giả nào không trước khi truy cập thuộc tính -->
                <span class="nextsong__item-body-depsc js__sub-color">${authors ? authors.fullName : ''}</span>
            </div>
            <div class="nextsong__item-action">
                <span class="nextsong__item-action-heart">
                    <i class="fas fa-heart nextsong__item-action-heart-icon1"></i>
                    <i class="far fa-heart nextsong__item-action-heart-icon2"></i>
                </span>
                <span class="nextsong__item-action-dot">
                    <i class="fas fa-ellipsis-h "></i>
                </span>
            </div>
        </div>`;

    return songHtml;
}
