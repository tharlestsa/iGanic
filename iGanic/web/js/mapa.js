var map;

$(document).ready(function () {

    map = new GMaps({
        el: '#mapa',
        lat: -16.372137,
        lng: -49.4998514,
        zoom: 4
    });

    $.getJSON('./data/fornecedores.json', function (pontos) {
        var dados = pontos.dados;
        $.each(dados, function (index, ponto) {
            map.addMarker({
                lat: ponto.lat,
                lng: ponto.lng,
                title: 'Marker with InfoWindow',
                infoWindow: {
                     content: '<div class="card mb-3 card-map">\n\
                                    <a href="#">\n\
                                        <img class="card-img-top img-fluid w-100" src="./img_produtos/'+ponto.img+'">\n\
                                    </a><div class="card-body">   \n\
                                   <h6 class="card-title mb-1"><a href="#">David Miller</a></h6>  \n\
                                   <p class="card-text small">These waves are looking pretty good today!     \n\
                                   <a href="#">#surfsup</a>      </p></div></div>'
//                    content: ' <img width="60%" style="margin: auto;"class="img-responsive" src="./img_produtos/'+ponto.img+'"/><p>'+ponto.nomeProduto+'</p>'
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