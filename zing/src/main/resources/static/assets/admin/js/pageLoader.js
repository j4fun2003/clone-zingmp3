let apiNameToGetPage;

window.addEventListener('beforeunload', function(event) {
    localStorage.setItem("apiName",apiNameToGetPage);
});
window.addEventListener('load', function() {
    let  apiName = localStorage.getItem("apiName");
    if(apiName = "song"){
        getAdminSong();
    }
});

