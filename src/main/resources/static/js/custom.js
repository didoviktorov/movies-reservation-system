$(document).ready(function () {
    var year = new Date().getFullYear();
    $("#footer-span").html("&copy; Movies Reservation System " + year);
});

function getPosterUlr() {
    var imageUrl = $("#posterUrl").val();

    var pos = $("#edit-poster");
    pos.attr("src", imageUrl);
}

function getAllCinemas() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8000/all-cinemas",
        success: function (data) {
            var containerDiv = $("#cinema");
            for (var i = 0; i < data.length; i++) {
                var newOption = $("<option></option>");
                newOption.val(data[i]["id"]);
                newOption.text(data[i]["name"]);
                containerDiv.append(newOption);
            }
        }
    });
}

function getHalls() {
    var cinemaId = $("#cinema").find(":selected").val();
    if (cinemaId !== "") {
        $.ajax({
            type: "GET",
            url: "http://localhost:8000/cinema_ajax/" + cinemaId,
            success: function (data) {
                clearOptions();
                var halls = data["halls"];
                var hallsContainer = $("#hall");
                for (var i = 0; i < halls.length; i++) {
                    var newOption = $("<option></option>");
                    newOption.val(halls[i]["id"]);
                    newOption.text(halls[i]["name"]);
                    hallsContainer.append(newOption);
                }
            }
        });
    } else {
        clearOptions();
    }

    function clearOptions() {
        var options = $("#hall option");
        for (var i = 1; i < options.length; i++) {
            options[i].remove();
        }
    }
}
