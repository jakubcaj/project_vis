$(document).ready(function () {
    initializeComponents();
});

function initializeComponents() {
    initializeLoginPopOver();
}

function initializeLoginPopOver() {

    $('[data-toggle="popover"]').popover({
        html: true,
        content: function () {
            return $('#loginPopOver').html();
        },
        animation: true,
        placement: "bottom"
    });
}

function registerForm() {
    if(validateRegisterForm()) {
        $.ajax({
            url: "/registerUser",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                firstName: $("#regFirstname").val(),
                lastName: $("#regLastname").val(),
                email: $("#regEmail").val(),
                username: $("#regUsername").val(),
                password: $("#regPassword").val()
            }),
            success: function (data) {
                if (data.success) {

                }
            }
        });
    }
}

function validateRegisterForm() {
    var firstName = $("#regFirstname");
    var lastname = $("#regLastname");
    var email = $("#regEmail");
    var username = $("#regUsername");
    var password = $("#regPassword");
    var validated = true;

    if(isEmpty(firstName.val())) {
        firstName.css("border-color", "red");
        validated = false;
        toastr.error('First Name cannot be empty');
    }

    if(isEmpty(lastname.val())) {
        lastname.css("border-color", "red");
        validated = false;
        toastr.error('Last Name cannot be empty');
    }

    if(isEmpty(email.val())) {
        email.css("border-color", "red");
        validated = false;
        toastr.error('Email cannot be empty');
    }

    if(isEmpty(username.val())) {
        username.css("border-color", "red");
        validated = false;
        toastr.error('Username Name cannot be empty');
    }

    if(isEmpty(password.val())) {
        password.css("border-color", "red");
        validated = false;
        toastr.error('Password Name cannot be empty');
    }

    return validated;
}

function validateLoginForm() {
    var username = $("#username");
    var password = $("#password");
    var validated = true;

    if(isEmpty(username.val())) {
        username.css("border-color", "red");
        validated = false;
        toastr.error('Username cannot be empty');
    }

    if(isEmpty(password.val())) {
        password.css("border-color", "red");
        validated = false;
        toastr.error('Password cannot be empty');
    }

    return validated;
}

function isEmpty(value) {
    return value === null || value.length === 0 || value == "";
}