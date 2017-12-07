var map;

$(document).ready(function () {

    map = new GMaps({
        el: '#mapa',
        lat: -16.372137,
        lng: -49.4998514
    });

    $.getJSON('./data/fornecedores.json', function (pontos) {
        var dados = pontos.dados;
        $.each(dados, function (index, ponto) {
            map.addMarker({
                lat: ponto.lat,
                lng: ponto.lng,
                title: 'Marker with InfoWindow',
                infoWindow: {
                    content: ' <img width="60%" style="margin: auto;"class="img-responsive" src="./img_produtos/'+ponto.img+'"/><p>'+ponto.nomeProduto+'</p>'
                }
            });
        });

    });

});



//$(document).ready(function () {
//
//    map.addMarker({
//        lat: -12.043333,
//        lng: -77.03,
//        title: 'Lima',
//        details: {
//            database_id: 42,
//            author: 'HPNeo'
//        },
//        click: function (e) {
//            if (console.log)
//                console.log(e);
//            alert('You clicked in this marker');
//        },
//        mouseover: function (e) {
//            if (console.log)
//                console.log(e);
//        }
//    });
//
//});