function getAllUser() {
    return callAPI('/api/users', 'GET', null);
}

function login(loginData) {
    return callAPI('/api/users/login', 'POST', loginData);
}

function registerUser(registerData) {
    return callAPI('/api/users/register', 'POST', registerData);
}

function logout(){
    window.location.reload();
    return callAPI('/api/session/CURRENT_USER', 'POST', "");
}


function updateUser(userId, updatedUserData) {
    return callAPI(`/api/users/${userId}`, 'PUT', updatedUserData);
}


// const userIdToUpdate = 1; // Thay đổi thành userId cần cập nhật thông tin
// const updatedUserData = {
//     username: 'newUsername',
//     password: 'newPassword',
//     email: 'newEmail@example.com',
//     avatar: 'newAvatarLink',
//     fullName: 'New Full Name',
//     provider: 'New Provider', // Ví dụ: 'local', 'google', 'facebook',...
//     genders: false // Hoặc true tùy thuộc vào giới tính mới
// };
//
// updateUser(userIdToUpdate, updatedUserData)
//     .then(updateResult => {
//         console.log('Kết quả cập nhật:', updateResult);
//         // Xử lý kết quả cập nhật ở đây nếu cần thiết
//     })
//     .catch(error => {
//         console.error('Đã xảy ra lỗi khi cập nhật:', error);
//         // Xử lý lỗi nếu cần thiết
//     });