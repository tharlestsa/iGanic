$(document).on('click', ".modal-produtos", function () {
    var title = $(this).find('#nome').val();
    var forn = $(this).find('#fornecedor').val();
    
    var body = $(this).find('#modo').val();
    
    console.log(title + " - "+ body);
    $("#modal-prod-title").text(title);
    $("#fornecedor-modal").text(forn);
    $("#modo-producao-modal").text(body);
});


