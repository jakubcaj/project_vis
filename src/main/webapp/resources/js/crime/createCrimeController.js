
var SUSPECTS_COUNT = 0;

$(document).ready(function () {
    initializeCrimeComponents();
});

function initializeCrimeComponents() {
    $("#addSuspect").on("click", function () {
        addSuspectsTextbox();
    })
}

function addSuspectsTextbox() {

    var textBox = $(document.createElement('div')).attr("id", "suspectsDiv_" + SUSPECTS_COUNT)
        .css({display: "-webkit-inline-box",
        marginBottom: "10px"});
    textBox.after().html('<label for="textbox"'+SUSPECTS_COUNT+'">Suspect : </label>' +
        '<input type="text" class="form-control" id="textbox' + SUSPECTS_COUNT + '" value="" >' +
        '<button style="margin-left: 7px" class="btn btn-warning " onclick="removeSuspect(' + SUSPECTS_COUNT + ')">Remove</button>')

    textBox.appendTo("#suspectsRow");
    SUSPECTS_COUNT++;
}

function removeSuspect(suspectCounter) {
    $("#suspectsDiv_" + suspectCounter).remove();
}