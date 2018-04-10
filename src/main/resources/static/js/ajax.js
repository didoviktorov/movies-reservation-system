function getCinemas() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8000/all-cinemas",
        success: function (data) {
            var containerDiv = $("#cinemas");
            var divRow = $("<div class='row row-eq-height pt-2 pb-2 justify-content-md-center'></div>");
            for (var i = 0; i < data.length; i++) {
                if (i > 0 && i % 2 === 0) {
                    containerDiv.append(divRow);
                    divRow = $("<div class='row pt-2 pb-2 justify-content-center display-flex'></div>");
                }

                var col = $("<div class='col-sm-6 text-center py-2' ></div>");

                var link = $("<a href='/cinema/" + data[i]["id"] + "' style='text-decoration: none'></a>");
                var card = $("<div class='card h-100'></div>");
                card.append("<img class='card-img-top img-fluid' src='" + data[i]["imageUrl"] + "' alt='Card image cinema'>");

                card.append("<p class='card-text' >" + data[i]["name"] + "</p>");
                card.append("<p class='card-text'>" + data[i]["address"] + "</p>");
                link.append(card);

                col.append(link);
                divRow.append(col);
            }
            containerDiv.append(divRow);

            $("#loader").fadeOut();
            containerDiv.fadeIn();
        }
    });
}