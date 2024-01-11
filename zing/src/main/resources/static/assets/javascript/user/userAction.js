function favorite(songId) {
    callAPI(`/api/favorites/${songId}`,"POST").then(result=>{

    }).catch(error=>{
        alertError("Having Some Error When Create Favorite", error);
    });
}

function view() {

}


function downloadSong(songId) {
    getSongById(songId).then(result=>{
        if(result.status = "success"){
            let song =  result.data;
            song.download += 1;
            console.log(song);
            updateSong(song.songId,song);
        }else{
            alertError("Error when getting song", result.detail);
        }
    }).catch(error=>{
        alertError("Error when update song", error);
    });

}