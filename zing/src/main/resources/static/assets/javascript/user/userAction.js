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