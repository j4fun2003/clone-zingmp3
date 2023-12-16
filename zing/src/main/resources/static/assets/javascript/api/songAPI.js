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
// Tạo một đối tượng newSong mới có kiểu dữ liệu phù hợp
const newSong = {
    title: 'Tên bài hát',
    description: 'Mô tả bài hát',
    image: 'URL',
    duration: moment.duration('10:10').toISOString(), // Chuyển đổi chuỗi thời gian sang định dạng phù hợp
    download: 1, // Kiểu dữ liệu số thay vì chuỗi số
    url: 'url',
    quantity: 1 // Kiểu dữ liệu số thay vì chuỗi số
};

createSong(newSong)
    .then(response => {
        console.log('Đã tạo bài hát:', response);
        // Xử lý khi tạo bài hát thành công
    })
    .catch(error => {
        console.error('Lỗi khi tạo bài hát:', error);
        // Xử lý khi gặp lỗi
    });

