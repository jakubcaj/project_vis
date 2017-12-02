var SUSPECTS_COUNT = 0;
var SUSPECT_RESULT = [];

var VICTIMS_COUNT = 0;
var VICTIMS_RESULT = [];

$(document).ready(function () {
    initializeCrimeComponents();
});

function initializeCrimeComponents() {
    $("#addSuspect").on("click", function () {
        addSuspectsTextbox();
    });

    $("#addVictim").on("click", function () {
        addVictimsTextbox();
    });

    $("#createCrimeButton").on("click", function () {
        createCrime();
    });
}

function createCrime() {
    $.ajax({
        url: "/profile/crime/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            shortDescription: $("#shortDescription").val(),
            dateCommitted: $("#dateCommitted").val(),
            description: $("#description").val(),
            suspects: SUSPECT_RESULT,
            victims: VICTIMS_RESULT
        }),
        success: function (data) {
            if (data.success) {
                swal({
                    title: "Crime created",
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

function addSuspectsTextbox() {

    var textBox = $(document.createElement('div')).attr("id", "suspectsDiv_" + SUSPECTS_COUNT)
        .css({
            display: "-webkit-inline-box",
            marginBottom: "10px"
        });
    textBox.after().html('<label for="textbox' + SUSPECTS_COUNT + '">Suspect : </label>' +
        '<input type="text" class="form-control" id="textbox' + SUSPECTS_COUNT + '" value="" >' +
        '<button style="margin-left: 7px" class="btn btn-warning " onclick="removeSuspect(' + SUSPECTS_COUNT + ')">Remove</button>')

    textBox.appendTo("#suspectsRow");

    $("#textbox" + SUSPECTS_COUNT).autoComplete({
        source: function (request, response) {
            $.ajax({
                url: "/profile/search",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    term: request,
                    crimeProfileDtoList: SUSPECT_RESULT
                }),
                success: function (data) {
                    response($.map(data.object, function (item) {
                        return {
                            value: item.id,
                            label: item.fullName
                        }
                    }));
                }
            });
        },
        renderItem: function (item, search) {
            search = search.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
            var re = new RegExp("(" + search.split(' ').join('|') + ")", "gi");
            return '<div class="autocomplete-suggestion" data-label="' + item.label + '" data-id="' + item.value + '" data-val="' + item.label + '">' + item.label.replace(re, "<b>$1</b>") + '</div>';
        },
        minlength: 3,
        onSelect: function (e, term, item) {
            SUSPECT_RESULT.push({
                id: item.data("id"),
                fullName: item.data("label")
            });
        }
    });

    SUSPECTS_COUNT++;
}

function addVictimsTextbox() {

    var textBox = $(document.createElement('div')).attr("id", "victimsDiv_" + VICTIMS_COUNT)
        .css({
            display: "-webkit-inline-box",
            marginBottom: "10px"
        });
    textBox.after().html('<label for="textboxVi"' + VICTIMS_COUNT + '">Victim : </label>' +
        '<input type="text" class="form-control" id="textboxVi' + VICTIMS_COUNT + '" value="" >' +
        '<button style="margin-left: 7px" class="btn btn-warning " onclick="removeVictim(' + VICTIMS_COUNT + ')">Remove</button>')

    textBox.appendTo("#victimsRow");

    $("#textboxVi" + VICTIMS_COUNT).autoComplete({
        source: function (request, response) {
            $.ajax({
                url: "/profile/search",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    term: request,
                    crimeProfileDtoList: VICTIMS_RESULT
                }),
                success: function (data) {
                    response($.map(data.object, function (item) {
                        return {
                            value: item.id,
                            label: item.fullName
                        }
                    }));
                }
            });
        },
        renderItem: function (item, search) {
            search = search.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
            var re = new RegExp("(" + search.split(' ').join('|') + ")", "gi");
            return '<div class="autocomplete-suggestion" data-label="' + item.label + '" data-id="' + item.value + '" data-val="' + item.label + '">' + item.label.replace(re, "<b>$1</b>") + '</div>';
        },
        minlength: 3,
        onSelect: function (e, term, item) {
            VICTIMS_RESULT.push({
                id: item.data("id"),
                fullName: item.data("label")
            });
        }
    });

    VICTIMS_COUNT++;
}

function removeSuspect(suspectCounter) {
    var suspect = $("#suspectsDiv_" + suspectCounter);
    var suspectInput = $("#textbox" + suspectCounter);
    SUSPECT_RESULT = SUSPECT_RESULT.filter(function (obj) {
        return obj.fullName !== suspectInput.val();
    });

    suspect.remove();
}

function removeVictim(victimCounter) {
    var victim = $("#victimsDiv_" + victimCounter);
    var victimInput = $("#textboxVi" + victimCounter);
    VICTIMS_RESULT = VICTIMS_RESULT.filter(function (obj) {
        return obj.fullName !== victimInput.val();
    });

    victim.remove();
}