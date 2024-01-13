function getAllUser() {
    return callAPI('/api/users', 'GET', null);
}

function login(loginData) {
    return callAPI('/api/users/login', 'POST', loginData);
}

function registerUser(registerData) {
    return callAPI('/api/users/register', 'POST', registerData);
}


function updateUser(userId, updatedUserData) {
    return callAPI(`/api/users/${userId}`, 'PUT', updatedUserData);
}function getAllUser() {
    return callAPI('/api/users', 'GET', null);
}

function login(loginData) {
    return callAPI('/api/users/login', 'POST', loginData);
}

function registerUser(registerData) {
    return callAPI('/api/users/register', 'POST', registerData);
}


function changePass(userId, changePassData) {
    return callAPI(`/api/users/change-pass/${userId}`, 'PUT', changePassData);
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