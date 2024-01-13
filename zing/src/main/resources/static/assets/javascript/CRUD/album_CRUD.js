
var formData = new FormData();


formData.append('title', document.getElementById("title").value);
formData.append('selectImg', document.getElementById("selectImg").files[0]);
formData.append('selectAuthor', $("#exampleFormControlSelect").val());

function getSelectedSongIds() {
    let selectedSongIds = [];
    $("[data-song-id]:checked").each(function () {
        selectedSongIds.push($(this).data("song-id"));
    });
    return selectedSongIds;
}

function getSongsByAuthorId() {
    var selectedAuthorId = $("#exampleFormControlSelect").val();

    $.ajax({
        type: "GET",
        url: "/api/songs/byAuthor/" + selectedAuthorId,
        success: function (data) {
            $("#songListBody").empty();
            if (data && data.status === "success" && data.data.length > 0) {
                $.each(data.data, function (index, song) {
                    var row = "<tr>" +
                        "<td>" + song.title + "</td>" +
                        "<td><img src='" + song.image + "' alt='Song Image' style='width: 50px; height: 50px;'></td>" +
                        "<td>" + song.duration + "</td>" +
                        "<td>" + song.description + "</td>" +
                        "<td><input type='checkbox' class='song-checkbox' data-song-id='" + song.songId + "'></td>"
                    "</tr>";
                    $("#songListBody").append(row);
                });
            } else {
                var emptyRow = "<tr><td colspan='4'>No songs found for the selected author.</td></tr>";
                $("#songListBody").append(emptyRow);
            }
        },
        error: function (xhr, status, error) {
            console.log("Error: " + error);
            console.log("Status: " + status);
            console.log("Response: " + xhr.responseText);
        }
    });
}

function insertAlbum(formData, selectedSongIds) {
    $.ajax({
        type: "POST",
        url: "/api/albums/insert",
        data: formData, // Sửa đổi ở đây
        processData: false,
        contentType: false,
        success: function (albumResponse) {
            console.log("Album Inserted:", albumResponse);
            if (selectedSongIds.length > 0) {
                updateSongsAlbumId(selectedSongIds, albumResponse.albumId);
            }
        },
        error: function (error) {
            console.error("Error:", error);
            // Xử lý khi có lỗi từ API
        }
    });
}


function updateSongsAlbumId(songIds, albumId) {
    $.ajax({
        type: "PUT",
        url: "/api/songs/updateAlbumId",
        contentType: "application/json",
        data: JSON.stringify({
            songIds: songIds,
            albumId: albumId
        }),
        success: function (response) {
            console.log("Songs Updated:", response);
            // Xử lý khi cập nhật thành công
        },
        error: function (error) {
            console.error("Error:", error);
            // Xử lý khi có lỗi từ API
        }
    });
}

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của form
        insertAlbum(formData, getSelectedSongIds());
    });
});

