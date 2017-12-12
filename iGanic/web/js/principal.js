$(document).on('click', ".modal-produtos", function () {
    var title = $(this).find('#nome').val();
    var forn = $(this).find('#fornecedor').val();

    var body = $(this).find('#modo').val();
    var rua = $(this).find('#rua').val();
    var num = $(this).find('#num').val();
    var comp = $(this).find('#comp').val();
    var bairro = $(this).find('#bairro').val();
    var cidade = $(this).find('#cidade').val();
    var uf = $(this).find('#uf').val();

    var endereco = rua + ', ' + num + ', ' + bairro + ', ' + cidade + '-' + uf;

    console.log(title + " - " + body);
    $("#modal-prod-title").text(title);
    $("#fornecedor-modal").text(forn);
    $("#modo-producao-modal").text(body);
    $("#endereco-modal").text(endereco);

});


