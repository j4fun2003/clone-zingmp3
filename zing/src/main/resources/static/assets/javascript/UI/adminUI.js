function getAdminUI(apiName) {
   return new Promise((resolve, reject)=>{
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


function renderDataToContenPage(data) {
    document.getElementById("content-page").innerHTML = data;
}

function getAdminSongAdd(){
    getAdminUI("songAdd").then((data)=>{
       renderDataToContenPage(data);
    }).catch(error=>{
       alert(error);
    })
}
function getAdminSongEdit(){
    getAdminUI("songEdit").then((data)=>{
       renderDataToContenPage(data);
    }).catch(error=>{
       alert(error);
    })
}
function getAdminSongList(){
    getAdminUI("songList").then((data)=>{
       renderDataToContenPage(data);
    }).catch(error=>{
       alert(error);
    })
}
