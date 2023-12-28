function callAPI(endpoint, method, requestData) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: endpoint,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function (data) {
                resolve(data); // Trả về dữ liệu khi thành công
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: "Error when connect to database?",
                    text: "please try with another?",
                    icon: "error"
                });
            }
        });
    });
}

