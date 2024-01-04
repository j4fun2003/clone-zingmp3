function getSongInfomationData() {
    return new Promise((resolve, reject) => {
        let audioFile = document.getElementById("song-audio").files[0];
        let imageFile = document.getElementById("song-image").files[0];
        let name = document.getElementById("song-name").value;
        let description = document.getElementById("song-des").value;
        let nation = document.getElementById("song-nation").value;

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
                    image:"https://storage.googleapis.com/zing-mp3-2118c.appspot.com/" +  imageFile.name
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
                    window.location.reload();
                }, 1500);
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
            },300)
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

    let isValid = true;

    if (!songName.value.trim()) {
        songName.classList.add('invalid-field');
        isValid = false;
    } else {
        songName.classList.remove('invalid-field');
    }

    if (!songDescription.value.trim()) {
        songDescription.classList.add('invalid-field');
        isValid = false;
    } else {
        songDescription.classList.remove('invalid-field');
    }

    if (!songNation.value.trim()) {
        songNation.classList.add('invalid-field');
        isValid = false;
    } else {
        songNation.classList.remove('invalid-field');
    }

    if (!songGenre.value.trim()) {
        songGenre.classList.add('invalid-field');
        isValid = false;
    } else {
        songGenre.classList.remove('invalid-field');
    }

    if (!imageFile.files[0]) {
        imageFile.classList.add('invalid-field');
        isValid = false;
    } else {
        imageFile.classList.remove('invalid-field');
    }

    if (!audioFile.files[0]) {
        audioFile.classList.add('invalid-field');
        isValid = false;
    } else {
        audioFile.classList.remove('invalid-field');
    }

    if (!isValid) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please fill in all required fields and upload an image.'
        });
    }

    return isValid;
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
//
// function uploadFileToFireBaseTest(file) {
//     let audioFile = document.getElementById("song-audio-file").files[0];
//     const formData = new FormData();
//     formData.append("file", audioFile);
//
//     // Gửi formData lên server sử dụng AJAX
//     $.ajax({
//         url: "/api/firebase/upload",
//         method: "POST",
//         data: formData,
//         contentType: false,
//         processData: false,
//         success: function(response) {
//             // Hiển thị thông báo thành công bằng SweetAlert
//             Swal.fire({
//                 icon: 'success',
//                 title: 'Upload thành công!',
//                 text: 'File đã được tải lên thành công.'
//             });
//             console.log("File uploaded successfully:", response);
//         },
//         error: function(error) {
//             // Hiển thị thông báo lỗi bằng SweetAlert
//             Swal.fire({
//                 icon: 'error',
//                 title: 'Lỗi khi tải lên!',
//                 text: 'Đã xảy ra lỗi khi tải file lên server.'
//             });
//             console.error("Error uploading file:", error);
//         }
//     });
// }
//
//
// function playAudioFromFirebase(fileName) {
//     fetch(`/api/firebase/download/${fileName}`)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Failed to fetch audio');
//             }
//             return response.arrayBuffer();
//         })
//         .then(arrayBuffer => {
//             const audioContext = new (window.AudioContext || window.webkitAudioContext)();
//             audioContext.decodeAudioData(arrayBuffer, function(audioBuffer) {
//                 const audioSource = audioContext.createBufferSource();
//                 audioSource.buffer = audioBuffer;
//                 audioSource.connect(audioContext.destination);
//                 audioSource.start();
//             }, function(error) {
//                 console.error('Failed to decode audio data:', error);
//             });
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
// //
// }


function getFileFromFirebase(fileName) {
    fetch(`/api/firebase/download/${fileName}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch audio');
            }
            return response.arrayBuffer();
        })
        .then(arrayBuffer => {
            const audioContext = new (window.AudioContext || window.webkitAudioContext)();
            audioContext.decodeAudioData(arrayBuffer, function(audioBuffer) {
                const audioSource = audioContext.createBufferSource();
                audioSource.buffer = audioBuffer;
                audioSource.connect(audioContext.destination);
                audioSource.start();
            }, function(error) {
                console.error('Failed to decode audio data:', error);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
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
    }else{
        const imageTag = document.getElementById("image-preview");
        imageTag.src = "/assets/img/placeholder/placeholderMusic.png";
    }
}