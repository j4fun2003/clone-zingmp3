function createAlbum(albumRequest) {
    return callAPI("/api/albums", "POST", albumRequest);
}

function getAlbumById(albumId) {
    return callAPI(`/api/albums/${albumId}`, "GET");
}