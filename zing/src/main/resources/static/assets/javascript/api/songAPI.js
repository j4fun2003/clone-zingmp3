function getAllSongs() {
    return callAPI('/api/songs', 'GET', null);
}

function createSong(songData) {
    return callAPI('/api/songs', 'POST', songData);
}

function getSongById(songId) {
    return callAPI(`/api/songs/${songId}`, 'GET', null);
}

function updateSong(songId, updatedSongData) {
    return callAPI(`/api/songs/${songId}`, 'PUT', updatedSongData);
}

function deleteSong(songId) {
    return callAPI(`/api/songs/${songId}`, 'DELETE', null);
}

function getSongsByAuthor(userId) {
    return callAPI(`/api/songs/byAuthor?userId=${userId}`, 'GET', null);
}

// getSongsByAuthor(1).then(data => console.log(data));
// const newSong = {
//     title: 'Tên bài hát',
//     description: 'Mô tả bài hát',
//     image: 'URL',
//     duration: '00:10:10', // Định dạng thời lượng là 'HH:mm:ss'
//     download: 1, // Kiểu dữ liệu số thay vì chuỗi số
//     url: 'URL bài hát',
//     quantity: 1,// Kiểu dữ liệu số thay vì chuỗi số
//     nation: "Viet Nam"
// };
//
// createSong(newSong)
//     .then(response => {
//         console.log('Đã tạo bài hát:', response);
//         // Xử lý khi tạo bài hát thành công
//     })
//     .catch(error => {
//         console.error('Lỗi khi tạo bài hát:', error);
//         // Xử lý khi gặp lỗi
//     });


