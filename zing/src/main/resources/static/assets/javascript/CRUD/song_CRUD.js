function getSongInfomationData() {
    return new Promise((resolve, reject) => {
        let audioFile = document.getElementById("song-audio").files[0];
        let imageFile = document.getElementById("song-image").files[0];
        let name = document.getElementById("song-name").value;
        let description = document.getElementById("song-des").value;
        let nation = document.getElementById("song-nation").value;
        let singerId = document.getElementById("song-singer").value;

        uploadAudioFromFireBase(imageFile);
        uploadAudioFromFireBase(audioFile);

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
                    url: "https://storage.googleapis.com/zing-mp3-2118c.appspot.com/" + audioFile.name,
                    image: "https://storage.googleapis.com/zing-mp3-2118c.appspot.com/" + imageFile.name,
                    singerId: singerId
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
    if (validateSongDetails()) {
        getSongInfomationData().then((newSong) => {
            createSong(newSong).then((data) => {
                Swal.fire({
                    icon: "success",
                    title: "Success",
                    text: "Add Succes!",
                    timer: 1500
                });
                setTimeout(function () {
                    window.location.href = "/admin/song"
                }, 700)
            }).catch(error => {
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: "Error when add song!",
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
    } else {
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
            setTimeout(function () {
                window.location.reload();
            }, 300)
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
    document.getElementById("song-name-edit").value = song.title;
    document.getElementById("song-des-edit").value = song.description;
    document.getElementById("song-nation-edit").value = song.nation;
    document.getElementById("song-id-edit").value = song.songId;
    document.getElementById("image-preview-edit").src = song.url;
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
                        window.location.reload();
                    });
                }).catch(error => {
                    Swal.fire({
                        title: 'Update error',
                        icon: 'error',
                        confirmButtonText: 'OK',
                        confirmButtonText: 'OK',
                    })
                    console.log(error);
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
    const songName = document.getElementById('song-name');
    const songDescription = document.getElementById('song-des');
    const songNation = document.getElementById('song-nation');
    const songGenre = document.getElementById('song-genre');
    const imageFile = document.getElementById('song-image');
    const audioFile = document.getElementById('song-audio');
    const singer = document.getElementById("song-singer");

    if (!songName.value.trim() ||
        !songDescription.value.trim() ||
        !songNation.value.trim() ||
        !songGenre.value.trim() ||
        !imageFile.files[0] ||
        !audioFile.files[0] ||
        !singer.value) {
        return false; // Trả về false nếu một trong các điều kiện không hợp lệ
    }
    return true; // Trả về true nếu tất cả các trường đều hợp lệ
}


function validateSongUpdate() {
    let songName = document.getElementById('song-name-edit').value;
    let songDescription = document.getElementById('song-des-edit').value;
    let songNation = document.getElementById('song-nation-edit').value;
    let songGenre = document.getElementById('song-genre-edit').value;
    let songSinger = document.getElementById('song-singer-edit').value;

    if (songName === '' || songDescription === '' || songNation === '' || songGenre === '' || songSinger === '') {
        return false;
    }
    return true;
}

function getImagePreview(event) {
    const input = event.target;
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const imageTag = document.getElementById("image-preview");
            imageTag.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        const imageTag = document.getElementById("image-preview");
        imageTag.src = "/assets/img/placeholder/placeholderMusic.png";
    }
}

function getSongUpdate() {
    return new Promise((resolve, reject) => {
        let songId = document.getElementById("song-id-edit").value;
        getSongById(songId).then(data => {
            if (data.status == `Success`) {
                let song = data.data;
                song.title = document.getElementById("song-name-edit").value;
                song.description = document.getElementById("song-des-edit").value;
                song.nation = document.getElementById("song-nation-edit").value;
                song.singer.singerId = document.getElementById("song-singer-edit").value;
                let audioFile = document.getElementById("song-audio-edit").files[0];
                let imageFile = document.getElementById("song-image-edit").files[0];
                if (audioFile) {
                    uploadAudioFromFireBase(audioFile);
                    song.url = firebaseUrl +  audioFile.name;
                }
                if (imageFile) {
                    uploadAudioFromFireBase(imageFile);
                    song.image = firebaseUrl + imageFile.name;
                }
                resolve(song);
            } else {
                reject(data.detail);
            }
        }).catch(error => {
            reject(error);
        });
    })
}

function saveSong() {
    if (validateSongUpdate()) {
        getSongUpdate().then(result => {
            updateSong(document.getElementById("song-id-edit").value, result)
                .then(() => {
                    alertSuccess("Update Success!");
                })
                .catch(error => {
                    alertError("Have Some Error When Updating!", error);
                });
        }).catch(error => {
            alertError("Have Some Error When Get Update Song Data!", error);
        });
    } else {
        alertWarning("Please Fill All Field Needed!");
    }
}