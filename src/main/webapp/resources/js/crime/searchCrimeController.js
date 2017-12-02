var TABLE;

$(document).ready(function () {
    initializeSearchCrimeComponents();
});

function initializeSearchCrimeComponents(){
    $("#searchButton").on("click", function () {
        searchCrime();
    })
}

function validateSearchCrimeForm() {
    var shortDescription = $("#shortDescription");

    shortDescription.css("border-color", "");

    var validated = true;

    if(isEmpty(shortDescription.val())) {
        validated = false;
        toastr.error("Short description must not be empty.")
    }

    return validated;
}


function searchCrime() {
    if (validateSearchCrimeForm()) {
        $.ajax({
            url: "/profile/crime/search",
            type: "POST",
            contentType: 'application/json',
            data: $("#shortDescription").val(),
            success: function (data) {
                if (data.success) {
                    TABLE = $("#crimeTable").DataTable({
                        destroy: true,
                        data: data.object,
                        columns: [
                            {"data": "id"},
                            {"data": "shortDescription"},
                            {"data": "dateCommitted"},
                            {
                                "data": null,
                                "defaultContent": "<button onclick='editCrime(this)' class='btn btn-info'>Edit</button>"
                            }
                        ]
                    });
                } else {
                    swal({
                        text: data.errorMessage,
                        title: "Search crime",
                        icon: "error"
                    })
                }
            }
        });
    }
}

function editCrime(button) {
    var data = TABLE.row( button.parentNode.parentNode ).data();

    window.location.href = "/crime/edit/" + data.id;
}