const headerSetting = $('.header__setting');
const headerOverlay = $('.header__right-overlay');
const headerSettingList = $('.header__setting-list');
const headerUser = $('.header__user');
const headerUserList = $('.header__user-list');
const modal = document.getElementById('loginModal');
const openLoginModalBtn = document.getElementById('openLoginModal');
const openModalBtn = document.getElementById('loginModalBtn');
const closeModalBtn = document.getElementById('closeModalBtn');
const registerModal = document.getElementById('registerModal');
const openRegisterModalBtn = document.getElementById('registerModalBtn');
const openRegisterModalBtn2 = document.getElementById('openRegisterModal');
const closeRegisterModalBtn = document.getElementById('closeRegisterModalBtn');

// FORM
const registerForm = document.getElementById('registerForm');
const loginForm = document.getElementById('loginForm');

// KHI CLICK SETTING
headerSetting.on('click', function (e) {
    headerSetting.toggleClass('header__setting--active');
    headerOverlay.removeClass('hiden');
    e.stopPropagation(); // Ngăn chặn sự kiện click lan ra các phần tử cha khác
});

headerSettingList.on('click', function (e) {
    e.stopPropagation();
    headerOverlay.addClass('hiden');
    headerSetting.removeClass('header__setting--active');
});

headerOverlay.on('click', function () {
    headerSetting.removeClass('header__setting--active');
    headerOverlay.addClass('hiden');
});

// click user

headerUser.on('click', function (e) {
    headerUser.toggleClass('header__user--active');
    headerOverlay.removeClass('hiden');
    e.stopPropagation(); // Ngăn chặn sự kiện click lan ra các phần tử cha khác
});

headerUserList.on('click', function (e) {
    e.stopPropagation();
    headerOverlay.addClass('hiden');
    headerUser.removeClass('header__user--active');
});

headerOverlay.on('click', function () {
    headerUser.removeClass('header__user--active');
    headerOverlay.addClass('hiden');
});

// Login modal

openModalBtn.addEventListener('click', function () {
    registerModal.style.display = 'none';
    modal.style.display = 'block';
});

openLoginModalBtn.addEventListener('click', function () {
    registerModal.style.display = 'none';
    modal.style.display = 'block';
});


// Close login modal
closeModalBtn.addEventListener('click', function () {
    modal.style.display = 'none';
});

// Close login modal if clicked outside the modal content
document.addEventListener('click', function (event) {
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});

// Submit login form


loginForm.addEventListener('submit', function (event) {
    event.preventDefault();
    alert('Login logic goes here!');
    modal.style.display = 'none';
});

// Register Modal
openRegisterModalBtn.addEventListener('click', function () {
    modal.style.display = 'none';
    registerModal.style.display = 'block';
});

openRegisterModalBtn2.addEventListener('click', function () {
    modal.style.display = 'none';
    registerModal.style.display = 'block';
});

// Close registration modal
closeRegisterModalBtn.addEventListener('click', function () {
    registerModal.style.display = 'none';
});

document.addEventListener('click', function (event) {
    if (event.target === registerModal) {
        registerModal.style.display = 'none';
    }
});


registerForm.addEventListener('submit', function (event) {
    event.preventDefault();
    alert('Registration logic goes here!');
    registerModal.style.display = 'none';
});






