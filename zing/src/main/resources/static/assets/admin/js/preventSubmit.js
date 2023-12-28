document.getElementById('search-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của form submit
    searchSong(document.getElementById('search-song-input').value);
});


document.getElementById("search-song-input").addEventListener("keydown", function () {
    searchSong(document.getElementById('search-song-input').value);
})