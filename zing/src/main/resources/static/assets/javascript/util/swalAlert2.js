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


function confirmToDoAction(message, action) {
    Swal.fire({
        title: 'Confirm Action?',
        text: `${message}`,
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            action();
        }
    });
}
