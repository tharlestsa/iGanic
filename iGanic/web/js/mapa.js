var map;
$(document).ready(function () {

    map = new GMaps({
        el: '#mapa',
        lat: -16.372137,
        lng: -49.4998514,
        zoom: 6
    });
    $.getJSON('./data/fornecedores.json', function (pontos) {
        var dados = pontos.dados;
        $.each(dados, function (index, ponto) {
            map.addMarker({
                lat: ponto.lat,
                lng: ponto.lng,
                title: 'Alimentos',
                infoWindow: {
                    content: montaJanelaNoMapa(ponto.idProduto, ponto.img, ponto.nomeProduto, ponto.nomeFornecedor, ponto.preco, ponto.modo)
                }
            });
        });
    });
});
function montaJanelaNoMapa(idProduto, imagem, nomeProduto, nomeUsuario, preco, modoProducao) {

    var janela = '<div class="container">'
            + '<form method="POST" action="./efetuarPedidos">'
            + '<div class="form-group">'
            + '      <div class="form-group row">'
            + '         <img class="card-img-top" src="./img_produtos/' + imagem + '">'
            + '     </div>'
            + '      <div class="form-group row">'
            + '          <br><label class=" label-produto">' + nomeProduto + '</label>'
            + '          <label class="label-produto label-produto-corpo-posicao">R$ ' + preco + '<label>'
            + '     </div>'
            + '     <div class="form-group row">'
            + '     <div class="col-md-2">'
            + '         <button type="button" class="modal-produtos btn btn-sm btn-success" data-toggle="modal" data-target="#modal-pro"> <i class="material-icons">info</i>'
            + '             <input type="hidden" name="nome" id="nome" value="' + nomeProduto + '">'
            + '             <input type="hidden" name="nome" id="fornecedor" value="' + nomeUsuario + '">'
            + '             <input type="hidden" name="modo" id="modo" value="' + modoProducao + '">'
            + '         </button>'
            + ' </div>'
            + '     <div class="col-md-2">'

            + '         <button class="btn btn-success btn-sm button-pedido" id="acao" name="acao" value="pedir" type="submit">'
            + '             <i class="material-icons">add_shopping_cart</i>'
            + '         </button>'
            + ' </div>'

            + '     </div>'
            + '</div>'
            + '</form>'
            + '</div>';

    janela = '<div class="card" style=width: 20rem;">'
            + '<img class="card-img-top" src="./img_produtos/' + imagem + '" alt="Alimento">'
            + '         <div class="card-body">'
            + '                 <br><label class=" label-produto">' + nomeProduto + '</label>'
            + '                 <label class="label-produto label-produto-corpo-posicao">R$ ' + preco + '<label>'
            + '         </div>'
            + '         <div class="card-footer">'
                + '         <button type="button" class="modal-produtos btn btn-sm btn-success" data-toggle="modal" data-target="#modal-pro"> <i class="material-icons">info</i>'
                + '             <input type="hidden" name="nome" id="nome" value="' + nomeProduto + '">'
                + '             <input type="hidden" name="nome" id="fornecedor" value="' + nomeUsuario + '">'
                + '             <input type="hidden" name="modo" id="modo" value="' + modoProducao + '">'
                + '         </button>'
                + '         <button class="btn btn-success btn-sm button-pedido" id="acao" name="acao" value="pedir" type="submit">'
                + '             <i class="material-icons">add_shopping_cart</i>'
                + '         </button>'
            + '         </div>'
            + '</div>';
    return janela;

}
