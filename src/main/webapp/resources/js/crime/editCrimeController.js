var removeSuspect = [];

$(document).ready(function () {
    $("#releaseToPublicBtn").on("click", function () {
        releaseToPublic();
    })
});


function releaseToPublic() {
    $.ajax({
        url: "/profile/crime/releasetopublic",
        type: "POST",
        contentType: 'application/json',
        data: $("#crimeId").val(),
        success: function (data) {
            if (data.success) {
                swal({
                    title: "Profile released to public",
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



function addRemoveSuspect(id) {

}