
function createSinger(singerData) {
    return callAPI('/api/singers', 'POST', singerData);
}

function getSingerById(singerId) {
    return callAPI(`/api/singers/${singerId}`,"GET");
}


function updateSinger(singer) {
    return callAPI(`/api/singers`,"PUT",singer);
}

function deleteSinger(singerId) {
    return callAPI(`/api/singers/${singerId}`,"DELETE");
}

function getAllSinger() {
    return callAPI("/api/singers", "GET");
}