
function setSession(user) {
    localStorage.setItem('user', JSON.stringify(user));
}

function getSession() {
    var userData = localStorage.getItem('user');
    return userData ? JSON.parse(userData) : null;
}

function removeSession() {
    localStorage.removeItem('user');
}