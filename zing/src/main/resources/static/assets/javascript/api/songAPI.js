
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



