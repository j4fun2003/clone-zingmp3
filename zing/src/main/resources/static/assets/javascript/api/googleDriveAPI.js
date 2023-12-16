// gapi.load('auth2', () => {
//     gapi.auth2.init({
//         client_id: '490989970450-hgun3q277ccjrqs1ojp9jv8cfeov0ctu.apps.googleusercontent.com',
//         scope: '',
//     }).then(() => {
//         // Authenticated successfully
//     }).catch(error => {
//         // Handle authentication error
//         console.error('Authentication error:', error);
//     });
// });
//
//
// // Function to upload a file to Drive
// function uploadFileToDrive(file) {
//     const metadata = {
//         name: file.name,
//         mimeType: file.type,
//     };
//     const accessToken = gapi.auth2.getAuthInstance().currentUser.get().getAuthResponse().access_token;
//
//     const form = new FormData();
//     form.append('metadata', new Blob([JSON.stringify(metadata)], { type: 'application/json' }));
//     form.append('file', file);
//
//     fetch('https://www.googleapis.com/upload/drive/v3/files?uploadType=multipart&fields=id', {
//         method: 'POST',
//         headers: {
//             Authorization: `Bearer ${accessToken}`,
//         },
//         body: form,
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log('File uploaded:', data);
//             // Handle successful upload
//         })
//         .catch(error => {
//             console.error('Upload error:', error);
//             // Handle upload error
//         });
// }
