$(document).on('click', ".modal-produtos", function () {
    var title = $(this).find('#nome').val();
    var body = $(this).find('#modo').val();
    console.log(title + " - "+ body);
    $("#modal-prod-title").text(title);
    $("#modal-prod-body").text(body);
});


