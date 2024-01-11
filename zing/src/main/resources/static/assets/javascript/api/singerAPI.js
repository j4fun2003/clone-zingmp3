
function createSinger(singerData) {
    return callAPI('/api/singers', 'POST', singerData);
}

function getSingerById(singerId) {
    return callAPI(`/api/singers/${singerId}`,"GET");
}


function updateSinger(singer) {
    return callAPI(`/api/singers`,"PUT",singer);
}