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
                title: ponto.nomeProduto,
                infoWindow: {
                    content: montaJanelaNoMapa(ponto.idProduto, ponto.img, ponto.nomeProduto, ponto.nomeFornecedor, ponto.preco, ponto.modo, ponto.nota, ponto.rua, ponto.num, ponto.comp, ponto.bairro, ponto.cidade, ponto.uf)
                }
            });
        });
    });
});
function montaJanelaNoMapa(idProduto, imagem, nomeProduto, nomeUsuario, preco, modoProducao, nota, rua, num, comp, bairro, cidade, uf) {
    var janela = '<div class="card" style=width: 20rem;">'
            + '<form method="POST" action="./efetuarPedidos">'
            + '<img class="card-img-top" src="./img_produtos/' + imagem + '" alt="Alimento">'
            + '         <div class="card-body">'
            + '                 <br><label class=" label-produto">' + nomeProduto + '</label>'
            + '                 <br><label class=" label-produto">Nota: ' + nota + '</label>'
            + '                 <label class="label-produto label-produto-corpo-posicao">R$ ' + preco + '<label>'
            + '         </div>'
            + '         <div class="card-footer">'
            + '         <button type="button" class="modal-produtos btn btn-sm btn-success" data-toggle="modal" data-target="#modal-pro"> <i class="material-icons">info</i>'
            + '             <input type="hidden" name="idProduto" id="idProduto" value="' + idProduto + '">'
            + '             <input type="hidden" name="nome" id="nome" value="' + nomeProduto + '">'
            + '             <input type="hidden" name="nome" id="fornecedor" value="' + nomeUsuario + '">'
            + '             <input type="hidden" name="modo" id="modo" value="' + modoProducao + '">'
            + '             <input type="hidden" name="rua" id="rua" value="' + rua + '">'
            + '             <input type="hidden" name="num" id="num" value="' + num + '">'
            + '             <input type="hidden" name="comp" id="comp" value="' + comp + '">'
            + '             <input type="hidden" name="bairro" id="bairro" value="' + bairro + '">'
            + '             <input type="hidden" name="cidade" id="cidade" value="' + cidade + '">'
            + '             <input type="hidden" name="uf" id="uf" value="' + uf + '">'
            + '         </button>'
            + '         <button class="btn btn-success btn-sm button-pedido" id="acao" name="acao" value="pedir" type="submit">'
            + '             <i class="material-icons">add_shopping_cart</i>'
            + '         </button>'
            + '         </div>'
            + '</form>'
            + '</div>';

    return janela;

}
