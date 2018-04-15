$(document).ready(function () {
    let year = new Date().getFullYear();
    $("#footer-span").html("&copy; Movies Reservation System " + year);
});

function getPosterUlr() {
    let imageUrl = $("#posterUrl").val();

    let pos = $("#edit-poster");
    pos.attr("src", imageUrl);
}

function getAllCinemas() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8000/all-cinemas",
        success: function (data) {
            let containerDiv = $("#cinema");
            for (let i = 0; i < data.length; i++) {
                let newOption = $("<option></option>");
                newOption.val(data[i]["id"]);
                newOption.text(data[i]["name"]);
                containerDiv.append(newOption);
            }
        }
    });
}

function getHalls() {
    let cinemaId = $("#cinema").find(":selected").val();
    if (cinemaId !== "") {
        $.ajax({
            type: "GET",
            url: "http://localhost:8000/cinema_ajax/" + cinemaId,
            success: function (data) {
                clearOptions();
                let halls = data["halls"];
                let hallsContainer = $("#hall");
                for (let i = 0; i < halls.length; i++) {
                    let newOption = $("<option></option>");
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
        let options = $("#hall option");
        for (let i = 1; i < options.length; i++) {
            options[i].remove();
        }
    }
}

$(document).on("click", ".badge", function () {
    $(".badge").each(function () {
        $(this).removeClass("hour-selected");
    });
    let currentBadge = $(this);
    currentBadge.addClass("hour-selected");
    $("#projectionHour").val(currentBadge.text());

    let projectionId = $("#projection").val();
    let hour = currentBadge.text();
    $.ajax({
        type: "GET",
        url: "http://localhost:8000/reserved-seats?projection=" + projectionId + "&hour=" + hour,
        success: function (data) {
            console.log(data);
        },
        error: function (r) {
            console.log(r);
        }
    });

});

$(document).on("change", "#tickets", function () {
    let price = Number.parseFloat($("#price-per-person").text());
    let tickets = Number($("#tickets").find(":selected").text());
    let totalPrice = Number(price * tickets);

    $("#total-price").text(totalPrice.toFixed(1));
    $("#price").val(totalPrice);

    clearSeats(tickets);
});

function clearSeats(tickets) {
    let cells = $(".selected-seat");
    let options = $("#seats option");
    for (let i = cells.length; i >= tickets; i--) {
        $(cells[i]).removeClass("selected-seat");
        let cellId = $(cells[i]).attr("id");
        console.log(cellId);
        $("#seats option[value='" + cellId + "']").remove();
    }
}

$(document).on("click", ".seat-cell", function(e) {
    e.preventDefault();
    let seatId = e.target.id;
    let maxSeats = Number($("#tickets").find(":selected").text());

    let seats = $("#seats");
    let optionsSeats = seats.children();

    let seatsIds = [];
    $("#seats option").each(function () {
       seatsIds.push($(this).val());
    });

    if (seatsIds.indexOf(seatId) < 0) {
        if (optionsSeats.length < maxSeats) {
            let option = $("<option selected></option>");
            option.val(seatId).select();
            seats.append(option);
            $(e.target).addClass("selected-seat");
        }
    } else {
        $("#seats option[value='" + seatId + "']").remove();
        $(e.target).removeClass("selected-seat")
    }
});

