function getAdminUI(apiName) {
   return new Promise((resolve, reject)=>{
       apiNameToGetPage = apiName;
       $.ajax({
           url:`/api/ui/${apiName}`,
           method: "GET",
           success: function (data) {
               resolve(data);
           },
           error: function (error) {
               reject(error);
           }
       })
   })
}


function renderSongToSongPage() {
    return new Promise((resolve, reject)=>{
        getAllSongs().then((result)=>{
            listSong = result.data;
            renderSongs(listSong);
        })
    })
}


function renderSongs(listSong) {
    let html  = "";
    listSong.forEach(song=>{
        html += `
            <tr>
                <td>${song.title?song.title:'Undefine song title'}</td>
                <td>${song.duration!='00:00:00'||song.duration?song.duration:'Undefine song duration'}</td>
                <td>${convertToDateTime(song.createDate)}</td>
                <td>${song.url?song.url:'Undefine song link'}</td>
                <td>${song.image?song.image:'Undefine image link'}</td>
                <td>${song.nation?song.nation:'Undefine nation'}</td>
                <td>${song.author? song.author.fullName : 'Undefine Singer'} </td>   
                <td>
                    <div class="d-flex align-items-center list-user-action">
                         <a class="iq-bg-primary" data-toggle="tooltip" data-placement="top" onclick="editSongRender(${song.songId})"><i class="ri-pencil-line"></i></a>
                         <a class="iq-bg-primary" data-toggle="tooltip" data-placement="top" onclick="deleteSongConfirm(${song.songId})"><i class="ri-delete-bin-line"></i></a>
                    </div>
                </td>                                      
            </tr>`;
    })
    document.getElementById("song-list-table-body").innerHTML = html;
}
function getAdminSong(){
    getAdminUI("song").then((data)=>{
        document.getElementById("content-page").innerHTML = data;
        renderSongToSongPage();
    }).catch(error=>{
       alert(error);
    })
}

