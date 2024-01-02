let prefixImagePath = "/static/assets/img/";
let prefixAudioPath = "/static/assets/music/list-song/";

function getSongInfomationData() {
    return new Promise((resolve, reject) => {
        let audioFile = document.getElementById("song-audio-file").files[0];
        let name = document.getElementById("song-name").value;
        let description = document.getElementById("song-des").value;
        let nation = document.getElementById("song-nation").value;
        if (audioFile) {
            const audio = new Audio();
            audio.src = URL.createObjectURL(audioFile);
            audio.onloadedmetadata = function () {
                const durationInSeconds = audio.duration;
                const formattedDuration = formatDuration(durationInSeconds);
                const newSong = {
                    title: name,
                    description: description,
                    duration: formattedDuration,
                    nation: nation,
                    url: prefixAudioPath,
                    image: prefixImagePath
                };
                resolve(newSong);
            };
        } else {
            reject("Không tìm thấy file âm thanh");
        }
    });
}


function formatDuration(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    const formattedDuration = `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
    return formattedDuration;
}




function addSong() {
    if(validateSongDetails()){
        getSongInfomationData().then((newSong) => {
            createSong(newSong).then((data) => {
                Swal.fire({
                    icon: "success",
                    title: "Success",
                    text: "Add Succes!"
                });
            }).catch(error => {
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: "Error when addnig data!",
                    footer: '<a href="#">Why do I have this issue?</a>'
                });
            });
        }).catch(error => {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Error When Select Data From Database !",
                footer: '<a href="#">Why do I have this issue?</a>'
            });
        })
    }else{
        Swal.fire({
            title: 'Please Fill All Song Data',
            icon: 'warning',
            confirmButtonText: 'OK',
        })
    }
}


function searchSong(searchValue) {
    if (searchValue == '') {
        renderSongs(listSong);
    } else {
        let songsToRender = [];
        listSong.forEach(song => {
            if (song.title.toLowerCase().includes(searchValue.toLowerCase())) {
                songsToRender.push(song);
            }
        })
        renderSongs(songsToRender);
    }
}


function deleteSongConfirm(songId, button) {
    Swal.fire({
        title: 'Confirm Delete',
        text: 'Are you sure you want to delete this song?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteSong(songId);
        }
    });
}


function editSongRender(songId) {
    document.querySelector('#song-list').classList.remove('show', 'active');
    document.querySelector('#song-edit').classList.add('show', 'active');
    getSongById(songId).then(result => {
        if (result.status == "Success") {
            renderSongsToEdit(result.data);
        }
    }).catch(error => {
        console.log(error);
    })
}

function renderSongsToEdit(song) {
    // Hiển thị nội dung của tab "Song Edit"
    document.getElementById("song-name-edit").value = song.title;
    document.getElementById("song-des-edit").value = song.description;
    document.getElementById("song-nation-edit").value = song.nation;
    document.getElementById("song-id-edit").value = song.songId;
}


function backToListSong(song) {
    // Hiển thị nội dung của tab "Song Edit"
    document.querySelector('#song-edit').classList.remove('show', 'active');
    document.querySelector('#song-list').classList.add('show', 'active');
}


function updateSongConfirm() {

    if (validateSongUpdate()) {

        // Sử dụng SweetAlert2 để hiển thị hộp thoại xác nhận
        Swal.fire({
            title: 'Do you want to save all changes in this songs?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Update',
            cancelButtonText: 'Cancel',
        }).then((result) => {
            if (result.isConfirmed) {
                let songId = document.getElementById("song-id-edit").value;
                let songName = document.getElementById("song-name-edit").value;
                let songDes = document.getElementById("song-des-edit").value;
                // let songImageFile = document.getElementById("song-des-edit").files[0];
                // let SongAudioFile =  document.getElementById("song-audio-file-edit").files[0];
                let SongNation = document.getElementById("song-nation-edit").value;

                getSongById(songId).then(result => {
                    let songUpdating = result.data;
                    songUpdating.title = songName;
                    songUpdating.description = songDes;
                    songUpdating.nation = SongNation;
                    updateSong(songId, songUpdating).then(result => {
                        console.log(result);
                    });
                }).catch(error => {
                    Swal.fire({
                        title: 'Update error',
                        icon: 'error',
                        confirmButtonText: 'OK',
                        confirmButtonText: 'OK',
                    })
                    console.log(error);
                })
            }
        });
    } else {
        Swal.fire({
            title: 'Please Fill All Song Data',
            icon: 'warning',
            confirmButtonText: 'OK',
        })
    }
}


function validateSongDetails() {
    let songName = document.getElementById("song-name").value;
    let songDescription = document.getElementById("song-des").value;
    let songNation = document.getElementById("song-nation").value;
    let songAudioFile = document.getElementById("song-audio-file").files[0];

    if (!songName.trim() || !songDescription.trim() || !songNation || !songAudioFile) {
        return false;
    }
    return true;
}


function validateSongUpdate() {
    let songName = document.getElementById("song-name-edit").value;
    let songDescription = document.getElementById("song-des-edit").value;
    let songNation = document.getElementById("song-nation-edit").value;
    if (!songName.trim() || !songDescription.trim() || !songNation) {
        return false;
    }
    return true;
}

