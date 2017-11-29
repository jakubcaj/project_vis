$(document).ready(function () {
    initializeProfileComponents();
});

function initializeProfileComponents() {
    $("#createProfileButton").on("click",function () {
        if(validateProfileForm()) {
            $.ajax({
                url: "/profile/create",
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify({
                    firstName: $("#firstName").val(),
                    lastName: $("#lastName").val(),
                    email: $("#email").val(),
                    deceased: $("#deceased").prop("checked"),
                    phone: $("#phone").val(),
                    birthDate: $("#birthDate").val()
                }),
                success: function (data) {
                    if (data.success) {
                        swal({
                            title: "Profile created",
                            type: "success"
                        });
                    } else {
                        swal({
                            title: "Error",
                            text: data.errorMessage,
                            type: "error"
                        });
                    }
                }
            });
        }
    })
}

function validateProfileForm() {
    var firstName = $("#firstName");
    var lastname = $("#lastName");
    var birthDate = $("#birthDate");
    var deceased = $("#deceased");
    var email = $("#email");
    var phone = $("#phone");
    var validated = true;

    firstName.css("border-color", "");
    lastname.css("border-color", "");
    birthDate.css("border-color", "");
    email.css("border-color", "");
    phone.css("border-color", "");

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

    if(isEmpty(birthDate.val())) {
        birthDate.css("border-color", "red");
        validated = false;
        toastr.error('Birth Date cannot be empty');
    }

    if(!deceased.prop("checked") && isEmpty(email.val()) && isEmpty(phone.val()) ) {
        email.css("border-color", "red");
        phone.css("border-color", "red");
        validated = false;
        toastr.error('If deceased is not checked, email or phone must not be empty.');
    }

    return validated;
}