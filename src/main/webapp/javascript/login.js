document.getElementById('loginBtn').addEventListener('click', function () {
    document.getElementById('loginForm').classList.add('active');
    document.getElementById('signUpForm').classList.remove('active');
});

document.getElementById('signUpBtn').addEventListener('click', function () {
    document.getElementById('signUpForm').classList.add('active');
    document.getElementById('loginForm').classList.remove('active');
});

document.querySelectorAll('.login-signup-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        document.querySelectorAll('.login-signup-btn').forEach(btn => {
            btn.classList.remove('active');
        });
        this.classList.add('active');
    });
});

function togglePasswordVisibility(inputId) {
    const passwordInput = document.getElementById(inputId);
    const eyeIcon = passwordInput.nextElementSibling.querySelector("i");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        eyeIcon.classList.remove("fa-eye");
        eyeIcon.classList.add("fa-eye-slash");
    } else {
        passwordInput.type = "password";
        eyeIcon.classList.remove("fa-eye-slash");
        eyeIcon.classList.add("fa-eye");
    }
}


