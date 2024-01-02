function convertToDateTime(inputDateTime) {
    // Tạo một đối tượng Date từ chuỗi thời gian
    let date = new Date(inputDateTime);

    // Lấy thông tin ngày, tháng, năm, giờ, phút, giây
    let day = date.getDate().toString().padStart(2, '0');
    let month = (date.getMonth() + 1).toString().padStart(2, '0'); // Tháng bắt đầu từ 0 nên cần cộng thêm 1
    let year = date.getFullYear();
    let hours = date.getHours().toString().padStart(2, '0');
    let minutes = date.getMinutes().toString().padStart(2, '0');
    let seconds = date.getSeconds().toString().padStart(2, '0');

    // Tạo chuỗi mới theo định dạng "dd/MM/yyyy h:p:s"
    let formattedDateTime = `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
    return formattedDateTime;
}

function convertToDate(inputDateTime) {
    // Tạo một đối tượng Date từ chuỗi thời gian
    let date = new Date(inputDateTime);

    // Lấy thông tin ngày, tháng, năm
    let day = date.getDate().toString().padStart(2, '0');
    let month = (date.getMonth() + 1).toString().padStart(2, '0'); // Tháng bắt đầu từ 0 nên cần cộng thêm 1
    let year = date.getFullYear();

    // Tạo chuỗi mới theo định dạng "dd/MM/yyyy h:p:s"
    let formattedDateTime = `${day}/${month}/${year}`;
    return formattedDateTime;
}

