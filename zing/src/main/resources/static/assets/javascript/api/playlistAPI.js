function createPlaylist(playlistName) {
    return callAPI('/api/playlists', 'POST', playlistName);
}

function getPlaylistsByUser() {
    return callAPI('/api/playlists', 'GET', null);
}

function getPlaylistById(playlistId) {
    return callAPI(`/api/playlists/${playlistId}`, 'GET', null);
}
