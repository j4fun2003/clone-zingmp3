function addAlbumsConfirm() {
    if (validateAddAlbumForm()) {
        confirmToDoAction("do you want to add an albums", function () {
            getAlbumInfomation().then(albumRequest => {
                createAlbum(albumRequest).then(result => {
                    if (result.status == "Success") {
                        alertSuccess("Add Album success");
                        location.href = "/admin/album";
                    } else {
                        alertError("Having Some Error When Adding albums" + result.detail);
                    }
                }).catch(error => {
                    console.log(error);
                    alertError("Having Some Error When getting albums info");
                });

            }).catch(error => {
                console.log(error);
                alertError("Having some error when get information")
            });
        })
    } else {
        alertWarning("Please fill all field needed");
    }
}

async function addSongToAlbums() {
    try {
        let songs = await getAllSongs();
        let optionsHTML = '';

        songs.data.forEach(song => {
            optionsHTML += `<option value="${song.songId}" >${song.title}</option>`;
        })
        document.getElementById("album-song").innerHTML += `<div class="form-group">
                                                    <div class="d-flex">
                                                        <select class="album-songs col-7 form-control my-1" name="selectAuthor">
                                                            <option disabled selected>Select Song</option>                                                                     
                                                            ${optionsHTML}
                                                        </select>
                                                        <button type="button" class="btn btn-primary ml-2" onclick="document.getElementById('album-song').removeChild(this.parentNode.parentNode)">Remove This Song</button>
                                                    </div>
                                                </div>`;
    } catch (error) {
        console.log(error);
        alertError("Error when getting all songs");
    }
}


function validateAddAlbumForm() {

    let imageFile = document.getElementById("album-image").files[0];
    let name = document.getElementById("album-name").value;
    let singerId = document.getElementById("album-singer").value;
    let songsNode = document.querySelectorAll(".album-songs");

    if (!imageFile) {
        return false;
    }

    if (!name.trim()) {
        return false;
    }

    if (!singerId) {
        return false;
    }

    for (let i = 0; i < songsNode.length; i++) {
        const songSelect = songsNode[i];
        if (!songSelect.value.trim()) {
            return false;
        }
    }
    return true;
}

function getAlbumInfomation() {
    return new Promise((resolve, reject) => {
        try {
            let imageFile = document.getElementById("album-image").files[0];
            let name = document.getElementById("album-name").value;
            let singerId = document.getElementById("album-singer").value;
            let songsNode = document.querySelectorAll(".album-songs");
            let songIds = Array.from(songsNode).map(songNode => parseInt(songNode.value));
            uploadAudioFromFireBase(imageFile);
            console.log(songIds);
            let albumRequest = {
                image: firebaseUrl + imageFile.name,
                name: name,
                singerId: singerId,
                songIds: songIds
            }
            resolve(albumRequest);
        } catch (e) {
            reject(e);
        }
    })
}

function removeSongFromAlbum(songId, ele) {
    confirmToDoAction("Are you sure you want to remove this song from the album?", function () {
        getSongById(songId)
            .then(result => {
                if (result.status === "Success") {
                    let song = result.data;
                    song.album = null;
                    updateSong(songId, song)
                        .then(result => {
                            console.log(result);
                            alertSuccess("Remove success");
                            // Remove the parent element (the div containing the select and button)
                            document.getElementById("album-song").removeChild(ele.parentNode.parentNode);
                        })
                        .catch(error => {
                            console.error("Error updating the song:", error);
                            alertError("Error updating the song");
                        });
                }
            })
            .catch(error => {
                console.error("Error getting with Song ID:", error);
                alertError("Error getting with Song ID");
            });
    });
}

function saveChangeConfirm() {
    if (validateAlbumUpdateForm()) {
        confirmToDoAction("Do You Want to save change?", function () {
            console.log("Confirmation received. Proceeding to save changes.");
            getUpdateAlbumInformation()
                .then(result => {
                    console.log("getUpdateInformation result:", result);
                    updateSong(result.albumId, result)
                        .then(result => {
                            console.log("updateSong result:", result);
                            alertSuccess("Update Success");
                        })
                        .catch(error => {
                            console.error("Error updating the song:", error);
                            alertError("Having some error when updating");
                        });
                })
                .catch(error => {
                    console.error("Error getting album information:", error);
                    alertError("Have got some error when getting information of albums");
                });
        });
    } else {
        alertWarning("Please fill all fields needed");
    }
}


function validateAlbumUpdateForm() {

    let name = document.getElementById("album-name").value;
    let singerId = document.getElementById("album-singer").value;
    let songsNode = document.querySelectorAll(".album-songs");

    if (!name.trim()) {
        return false;
    }

    if (!singerId) {
        return false;
    }

    for (let i = 0; i < songsNode.length; i++) {
        const songSelect = songsNode[i];
        if (!songSelect.value.trim()) {
            return false;
        }
    }
    return true;
}

async function getUpdateAlbumInformation() {
    try {
        const result = await getAlbumById(document.getElementById("album-id").value);
        if (result.status === "Success") {
            const imageFile = document.getElementById("album-image").files[0];
            const song = result.data;
            song.title = document.getElementById("album-name").value;
            song.singer.singerId = document.getElementById("album-singer").value;

            if (imageFile) {
                await uploadAudioFromFireBase(imageFile);
                song.image = firebaseUrl + imageFile.name;
                console.log("Ảnh đã thay đổi");
            } else {
                console.log("Ảnh không thay đổi");
            }

            console.log(song);
            return song;
        } else {
            alertError("Có lỗi khi truy xuất thông tin album");
            return null;
        }
    } catch (error) {
        console.error("Lỗi trong getUpdateAlbumInformation:", error);
        throw error;
    }
}

function confirmDeleteAlbum(albumId) {
    confirmToDoAction("Are you sure to delete this Album", function () {
        deleteAlbum(albumId).then(result => {
                alertSuccess("Delete this album success");
                location.reload();
            }
        ).catch(error => {
            console.log(error);
            alertError("Have got some error when delete album");
        });
    });
}

