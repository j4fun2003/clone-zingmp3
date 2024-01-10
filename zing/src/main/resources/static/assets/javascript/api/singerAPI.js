function createSinger(singerData) {
    return callAPI('/api/singers', 'POST', singerData);
}