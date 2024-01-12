function createHistory(songId) {
    return callAPI(`api/history/${songId}`, "POST");
}


function clearHistory() {
    return callAPI(`api/history/clear`, "DELETE");
}