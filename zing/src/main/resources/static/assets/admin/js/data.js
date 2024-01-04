let listSong;
getAllSongs().then(
    result => {
        listSong = result.data;
    }
);