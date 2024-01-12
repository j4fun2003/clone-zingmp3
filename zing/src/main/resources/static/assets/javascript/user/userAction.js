function favorite(songId) {

    let unFavoriteHeartClass = "ri-heart-line";
    let favoriteHeartClass = "ri-heart-fill";
    let heartIcon = document.getElementById("heart-icon");
    let isFavorite = heartIcon.classList.contains(favoriteHeartClass);

    if (!isFavorite) {
        callAPI(`/api/favorites/${songId}`, "POST").then(result => {
            changeHeart();
        }).catch(error => {
            alertError("Having Some Error When Create Favorite", error);
            console.log(error);
        });
    } else {
        callAPI(`/api/favorites/${songId}`, "DELETE").then(result => {
            changeHeart();
        }).catch(error => {
            console.log(error);
            alertError("Having Some Error When Delete Favorite", error);
        });
    }


}

function view() {

}


function downloadSong(songId) {
    getSongById(songId).then(result => {
        if (result.status = "success") {
            let song = result.data;
            song.download += 1;
            console.log(song);
            updateSong(song.songId, song);
        } else {
            alertError("Error when getting song", result.detail);
        }
    }).catch(error => {
        alertError("Error when update song", error);
    });

}

function changeHeart() {
    let unFavoriteHeartClass = "ri-heart-line";
    let favoriteHeartClass = "ri-heart-fill";
    let heartIcon = document.getElementById("heart-icon");
    let isFavorite = heartIcon.classList.contains(favoriteHeartClass);
    if (isFavorite) {
        heartIcon.classList.add(unFavoriteHeartClass);
        heartIcon.classList.remove(favoriteHeartClass);
    } else {
        heartIcon.classList.add(favoriteHeartClass);
        heartIcon.classList.remove(unFavoriteHeartClass);
    }
}


function clearHistoryConfirm() {
    confirmToDoAction("Are you sure you want to delete your entire music listening history!!",
        function () {
            clearHistory().then(result=>{
                alertSuccess("Clear your history successfully");
                setTimeout(function () {
                    location.reload();
                }, 700);
            }).catch(error =>{
                alertError("Have some error when clear your history");
            });
        }
    )
}