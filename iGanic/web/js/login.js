
$(document).on('blur', "#usuario", function () {
    var usuario = $("#usuario").val();
    $.ajax({
        type: 'GET',
        url: './usuario?acao=buscausuario&usuario=' + usuario,
        mimeType: 'json',
        success: function (retorno) {
            if (retorno === false) {
                alert("Esse usuário não está resgistrado!");
            }
        },
        error: function (erro) {
            console.log("Erro: " + erro);
        }
    });
});

$().ready(function () {
    // validate the comment form when it is submitted
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