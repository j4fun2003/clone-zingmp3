let now_playing = document.querySelector(".now-playing");
let track_art = document.querySelector(".track-art");
let track_name = document.querySelector(".track-name");
let track_artist = document.querySelector(".track-artist");

let playpause_btn = document.querySelector(".playpause-track");
let next_btn = document.querySelector(".next-track");
let prev_btn = document.querySelector(".prev-track");

let seek_slider = document.querySelector(".seek_slider");
let volume_slider = document.querySelector(".volume_slider");
let curr_time = document.querySelector(".current-time");
let total_duration = document.querySelector(".total-duration");

let track_index = 0;
let isPlaying = false;
let updateTimer;
let currentSong;

// Create new audio element
let curr_track = document.createElement('audio');

// Define the tracks that have to be played
let track_list;
let listSong;
getAllSongs().then(result => {
    listSong = result.data;
    if (currentSong) {
        let currentSongIndex = -1;

        for (let i = 0; i < listSong.length; i++) {
            if (listSong[i].songId === currentSong.songId) {
                currentSongIndex = i;
                break;
            }
        }
        loadTrack(listSong[currentSongIndex].songId);
    } else {
        loadTrack(listSong[0].songId);
    }
})

function random_bg_color() {

    // Get a number between 64 to 256 (for getting lighter colors)
    let red = Math.floor(Math.random() * 256) + 64;
    let green = Math.floor(Math.random() * 256) + 64;
    let blue = Math.floor(Math.random() * 256) + 64;

    // Construct a color withe the given values
    let bgColor = "rgb(" + red + "," + green + "," + blue + ")";
}

function loadTrack(track_index) {
    getSongById(track_index).then(result => {
        clearInterval(updateTimer);
        resetValues();
        currentSong = result.data;
        curr_track.src = result.data.url;
        curr_track.load();


        playTrack();

        track_art.style.backgroundImage = "url(" + currentSong.image + ")";
        track_name.textContent = currentSong.title;
        track_artist.textContent = currentSong.singer == null ? 'undefine' : currentSong.singer.singerFullName;
        now_playing.textContent = "PLAYING " + (track_index + 1) + " OF " + listSong.length;

        updateTimer = setInterval(seekUpdate, 1000);
        curr_track.addEventListener("ended", nextTrack);
        random_bg_color();
        if (currentSong.songId) {
            createHistory(currentSong.songId).catch(error => {
                // alertError("Error when add song to your history", error);
                console.log(error);
            });
        }
    })
}

function resetValues() {
    curr_time.textContent = "00:00";
    total_duration.textContent = "00:00";
    seek_slider.value = 0;
}


function playpauseTrack() {
    if (!isPlaying) playTrack();
    else pauseTrack();
}

function playTrack() {
    curr_track.play();
    isPlaying = true;
    playpause_btn.innerHTML = '<i class="fa fa-pause-circle fa-3x"></i>';
}

function pauseTrack() {
    curr_track.pause();
    isPlaying = false;
    playpause_btn.innerHTML = '<i class="fa fa-play-circle fa-3x"></i>';
}

function nextTrack() {
    let currentSongIndex = -1;

    for (let i = 0; i < listSong.length; i++) {
        if (listSong[i].songId === currentSong.songId) {
            currentSongIndex = i;
            break;
        }
    }
    if (currentSongIndex + 1 == listSong.length) {
        loadTrack(listSong[0].songId);
    } else {
        loadTrack(listSong[currentSongIndex + 1].songId);
    }
}

function prevTrack() {
    let currentSongIndex = -1;

    for (let i = 0; i < listSong.length; i++) {
        if (listSong[i].songId === currentSong.songId) {
            currentSongIndex = i;
            break;
        }
    }
    if (currentSongIndex - 1 < 0) {
        loadTrack(listSong[listSong.length - 1].songId);
    } else {
        loadTrack(listSong[currentSongIndex - 1].songId);
    }
}

function seekTo() {
    seekto = curr_track.duration * (seek_slider.value / 100);
    curr_track.currentTime = seekto;
}

function setVolume() {
    curr_track.volume = volume_slider.value / 100;
}

function seekUpdate() {
    let seekPosition = 0;

    if (!isNaN(curr_track.duration)) {
        seekPosition = curr_track.currentTime * (100 / curr_track.duration);

        seek_slider.value = seekPosition;

        let currentMinutes = Math.floor(curr_track.currentTime / 60);
        let currentSeconds = Math.floor(curr_track.currentTime - currentMinutes * 60);
        let durationMinutes = Math.floor(curr_track.duration / 60);
        let durationSeconds = Math.floor(curr_track.duration - durationMinutes * 60);

        if (currentSeconds < 10) {
            currentSeconds = "0" + currentSeconds;
        }
        if (durationSeconds < 10) {
            durationSeconds = "0" + durationSeconds;
        }
        if (currentMinutes < 10) {
            currentMinutes = "0" + currentMinutes;
        }
        if (durationMinutes < 10) {
            durationMinutes = "0" + durationMinutes;
        }

        curr_time.textContent = currentMinutes + ":" + currentSeconds;
        total_duration.textContent = durationMinutes + ":" + durationSeconds;
    }
}


// Load the first track in the tracklist
loadTrack(track_index);

