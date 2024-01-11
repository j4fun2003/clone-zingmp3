function addSinger() {
    if (validateSingerDetail()) {
        //     thuc hien khi form ok
        getInformationDetails().then(newSinger => {
                createSinger(newSinger).then(result => {
                    if (result.status = "success") {
                        Swal.fire({
                            icon: 'success',
                            title: 'Success!',
                            text: "Add Singer Successfully",
                        });
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Error",
                            text: "Error when add song!",
                            footer: '<a href="#">Why do I have this issue?</a>'
                        });
                    }
                });
            }
        )
    } else {
        Swal.fire({
            icon: 'warning',
            title: 'Oops...',
            text: 'Please fill in all fields and select an image!!'
        });
        return false;
    }
}

function validateSingerDetail() {
    let singerName = document.getElementById('singer-name').value;
    let singerEmail = document.getElementById('singer-email').value;
    let singerBirthday = document.getElementById('singer-birthday').value;
    let singerDes = document.getElementById('singer-des').value;
    let singerImage = document.getElementById('singer-image').files[0];

    if (singerName.trim() === '' || singerEmail.trim() === '' || singerBirthday.trim() === '' || singerDes.trim() === '' || !singerImage) {
        return false;
    }
    return true;
}

// lay thong tin tu form va tra ve new singer
function getInformationDetails() {
    return new Promise((resolve, reject) => {
        let singerName = document.getElementById('singer-name').value;
        let singerEmail = document.getElementById('singer-email').value;
        let singerBirthday = document.getElementById('singer-birthday').value;
        let singerDes = document.getElementById('singer-des').value;
        let singerImageFile = document.getElementById('singer-image').files[0];

        uploadAudioFromFireBase(singerImageFile);
        const newSinger = {
            singerFullName: singerName,
            singerBirthDay: singerBirthday,
            singerDescription: singerDes,
            singerEmail: singerEmail,
            singerImage: "https://storage.googleapis.com/zing-mp3-2118c.appspot.com/" + singerImageFile.name
        };
        resolve(newSinger);
    })
}


function validateUpdateSingerForm() {
    let singerName = document.getElementById('singer-name').value;
    let singerEmail = document.getElementById('singer-email').value;
    let singerBirthday = document.getElementById('singer-birthday').value;
    let singerDes = document.getElementById('singer-des').value;
    if (singerName === '' || singerEmail === '' || singerBirthday === '' || singerDes === '') {
        return false;
    }
    return true;
}

function renderUpdateSinger() {

}

function getSingerImagePreviews() {
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
        imageTag.src = "/assets/img/placeholder/person-placeholder.jpg";
    }
}

function saveChangeSinger(singerId) {
    if (validateUpdateSingerForm()) {
        getInformationToUpdate().then(singer => {
            console.log(singer);
            updateSinger(singer).then(result => {
                alertSuccess("Update Success");
            }).catch(error => {
                alertError("Have Some Error When Updating", error);
            });
        }).catch(error => {
            console.log(error);
            alertError("Have Some Error When Get Singer Data");
        });
    } else {
        alertWarning("Please fill all field needed!!");
    }
}

function getInformationToUpdate() {
    return new Promise((resolve, reject) => {
        try {
            let singerName = document.getElementById('singer-name').value;
            let singerEmail = document.getElementById('singer-email').value;
            let singerBirthday = document.getElementById('singer-birthday').value;
            let singerDes = document.getElementById('singer-des').value;
            let singerImage = document.getElementById("singer-image").files[0];

            getSingerById(document.getElementById("singer-id").value).then(result => {
                let singer;
                if (result.status == "success") {
                    singer = result.data;
                }
                singer.singerFullName = singerName;
                singer.singerDescription = singerDes;
                singer.singerBirthday = singerBirthday;
                singer.singerEmail = singerEmail;
                if (singerImage) {
                    uploadAudioFromFireBase(singerImage);
                    singer.image = firebaseUrl + singerImage.name;
                }
                resolve(singer);
            });
        } catch (e) {
            reject(e);
        }
    })
}
