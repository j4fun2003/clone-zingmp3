
function validateUserPassForm() {
    let password = document.getElementById('cpass').value;
    let newPassword = document.getElementById('npass').value;
    let confirm = document.getElementById('vpass').value;
    if (password === '' || newPassword === '' || confirm === '') {
        return false;
    }
    return true;
}
function getPassToChange() {
    return new Promise((resolve, reject) => {
        try {
            let password = document.getElementById('cpass').value;
            let newPassword = document.getElementById('npass').value;
            let confirm = document.getElementById('vpass').value;
            let changePassData = {
                password: password,
                newPass: newPassword,
                confirm: confirm
            };

            changePass(document.getElementById("user-id").value, changePassData)
                .then(result => {
                    if (result.status === "Success") {
                        resolve(result.data);
                    } else {
                        reject(result.detail);
                    }
                })
                .catch(error => {
                    reject(error);
                });
        } catch (e) {
            reject(e);
        }
    });
}
function saveChangeUser(userId) {
    if (validateUserPassForm()) {
        getPassToChange()
            .then(user => {
                console.log(user);
                alertSuccess("Password was changed");
            })
            .catch(error => {
                console.log(error);
                alertError("Error: " + error);
            });
    } else {
        alertWarning("Please fill in all required fields!!");
    }
}
