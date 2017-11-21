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

// $('#regSubmit').on('click', function() {
function test() {



    // $.post("/registerUser", {
    //     firstName: $("#regFirstname").val(),
    //     lastName: $("#regLastname").val(),
    //     email: $("#regEmail").val(),
    //     username: $("#regUsername").val(),
    //     password: $("#regPassword").val()
    // }, function (data) {
    //     debugger;
    // });

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
            debugger;
            alert("success: ");
        }
    });

    // $.ajax("/registerUser");
}

// });