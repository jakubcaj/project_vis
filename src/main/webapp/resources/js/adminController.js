var TABLE;

$(document).ready(function () {
    initializeAdminComponents();
});

function validateUserSearchForm() {
    restoreBordersSearchForm();

    var firstName = $("#firstName");
    var lastname = $("#lastName");
    var email = $("#email");
    var username = $("#usernameS");
    var validated = false;

    if (!isEmpty(firstName.val())) {
        validated = true;
    }

    if (!isEmpty(lastname.val())) {
        validated = true;
    }

    if (!isEmpty(email.val())) {
        validated = true;
    }

    if (!isEmpty(username.val())) {
        validated = true;
    }

    if (!validated) {
        toastr.error("Atleast one of the fields must be filled.")
        firstName.css("border-color", "red");
        lastname.css("border-color", "red");
        email.css("border-color", "red");
        username.css("border-color", "red");
    }

    return validated;
}

function restoreBordersSearchForm() {
    var firstName = $("#firstName");
    var lastname = $("#lastName");
    var email = $("#email");
    var username = $("#usernameS");

    firstName.css("border-color", "");
    lastname.css("border-color", "");
    email.css("border-color", "");
    username.css("border-color", "");
}

function initializeAdminComponents() {
    $("#searchUserBtn").on("click", function () {
        searchUser();
    });
}

function searchUser() {
    if (validateUserSearchForm()) {
        $.ajax({
            url: "/admin/search",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                email: $("#email").val(),
                username: $("#usernameS").val()
            }),
            success: function (data) {
                if (data.success) {
                    TABLE = $("#usersTable").DataTable({
                        destroy: true,
                        data: data.object,
                        columns: [
                            {"data": "firstName"},
                            {"data": "lastName"},
                            {"data": "email"},
                            {"data": "username"},
                            {"data": "roles"},
                            {
                                "data": null,
                                "defaultContent": "<button onclick='editUser(this)' class='btn btn-info'>Edit</button>"
                            }
                        ]
                    });
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

function editUser(button) {
    var data = TABLE.row( button.parentNode.parentNode ).data();

    swal({
        title: 'Are you sure?',
        text: "Edit user " + data.username,
        html: $("#modalwindow"),
        showCancelButton: true,
        // confirmButtonColor: '#3085d6',
        // cancelButtonColor: '#d33',
        confirmButtonText: 'Change User',
        cancelButtonText: 'Cancel',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-warning',
        buttonsStyling: false
    }).then((result) => {
        if (result.value) {
            editUserAjax(data.id);
        } else if (result.dismiss === 'cancel') {

        }
    });

    var swalEl = $("#swal2-content");
    swalEl.find("#modalwindow").css('display','grid');
    swalEl.find("#firstNameModal").val(data.firstName);
    swalEl.find("#lastNameModal").val(data.lastName);
    swalEl.find("#emailModal").val(data.email);
    swalEl.find("#usernameModal").val(data.username);
    $.each(data.roles, function (key, value) {
        swalEl.find("#roleModal option[value='"+ value+ "']").prop("selected", true);
    });
    swalEl.find("#roleModal").multiselect({
        enableFiltering: true,
        maxHeight: 300,
        dropUp: true
    })

    // debugger;
}

function editUserAjax(id) {
    var swalEl = $("#swal2-content");
    $.ajax({
        url: "/admin/edit",
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify({
            firstName: swalEl.find("#firstNameModal").val(),
            lastName: swalEl.find("#lastNameModal").val(),
            email: swalEl.find("#emailModal").val(),
            username: swalEl.find("#usernameModal").val(),
            roles: swalEl.find("#roleModal").val(),
            id: id
        }),
        success: function (data) {
            if (data.success) {
                swal({
                    text: "User changed",
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