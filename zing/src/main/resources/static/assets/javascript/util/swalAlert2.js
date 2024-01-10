function alertError(message, detail) {
    Swal.fire({
        icon: 'error',
        title: 'Error',
        text: message
    });
    console.log(detail);
}

function alertSuccess(message) {
    Swal.fire({
        icon: 'success',
        title: 'Success',
        text: message
    });
}

function alertWarning(message) {
    Swal.fire({
        icon: 'warning',
        title: 'Warning',
        text: message
    });
}
