$(document).ready(function () {
    var year = new Date().getFullYear();
    $("#footer-span").html("&copy; Movies Reservation System " + year);
});

function getPosterUlr() {
    var imageUrl = $("#posterUrl").val();

    var pos = $("#edit-poster");
    pos.attr("src", imageUrl);
}
