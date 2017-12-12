
$(document).on('blur', "#usuario", function () {
    var usuario = $("#usuario").val();
    $.ajax({
        type: 'GET',
        url: './usuario?acao=buscausuario&usuario=' + usuario,
        mimeType: 'json',
        success: function (retorno) {
            if (retorno === false) {
                $("[data-toggle='usuario']").tooltip('show');
            } else {
                $("[data-toggle='usuario']").tooltip('hide');
            }
        },
        error: function (erro) {
            console.log("Erro: " + erro);
        }
    });
});

$(document).on('keypress', "#usuario", function () {
    $("[data-toggle='usuario']").tooltip('hide');
});

$().ready(function () {

    $("#form-login").validate({
        rules: {
            usuario: {
                required: true
            },
            senha: {
                required: true
            }
        },
        messages: {
            usuario: {
                required: "<label class='msg-required'>O preenchimento desse campo é obrigatório!</label>"
            },
            senha: {
                required: "<label class='msg-required'>O preenchimento desse campo é obrigatório!</label>"
            }
        }
    });
});