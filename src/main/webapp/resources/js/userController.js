$(document).ready(function () {
    initializeComponents();
});

$("#editForm").bind("ajax:complete", function (data) {
    debugger;
});

function validateUserEditForm() {
    var firstName = $("#firstName");
    var lastname = $("#lastName");
    var email = $("#email");
    var username = $("#username");
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

    return validated;
}

function initializeComponents() {
    $("#editFormButton").on("click", function () {
        editUser();
    })
}

function editUser() {
    if(validateUserEditForm()) {
        $.ajax({
            url: "/user/edit",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                email: $("#email").val(),
                username: $("#username").val()
            }),
            success: function (data) {
                if (data.success) {
                    swal({
                        text: "Update Successful!",
                        title: "Edit Profile",
                        icon: "success",
                        timer: 2000
                    })
                } else {
                    swal({
                        text: data.errorMessage,
                        title: "Edit Profile",
                        icon: "error"
                    })
                }
            }
        });
    }
}