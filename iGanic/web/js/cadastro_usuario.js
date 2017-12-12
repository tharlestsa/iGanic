
$(document).ready(function () {

    $('#cpf').mask("000.000.000-00");
    $('#cel').mask("(00)00000-0000");
    $('#cep').mask("00.000-000");

    $('#rua').attr('readonly', true);
    $('#numero').attr('readonly', true);
    $('#bairro').attr('readonly', true);
    $('#cidade').attr('readonly', true);
    $('#estado').attr('readonly', true);

});



$().ready(function () {

    var cpf = $("#cpf").val();
    var usuario = $("#usuario").val();

    // validate the comment form when it is submitted
    $("#form-usuario").validate({
        rules: {
            nome: {
                required: true
            },

            cpf: {
                required: true,
                minlength: 11,
//                remote: './usuario?acao=buscausuarioporcpf&cpf=' + cpf
            },

            email: {
                required: true
            },

            cel: {
                required: true
            },

            cep: {
                required: true,
                minlength: 8
            },

            rua: {
                required: true
            },

            bairro: {
                required: true
            },

            estado: {
                required: true
            },

            cidade: {
                required: true
            },
            tipo: {
                required: true
            },
            usuario: {
                required: true,
//                remote: './usuario?acao=buscausuario&usuario=' + usuario
            },
            senha: {
                required: true
            },
            confirmSenha: {
                required: true
            }

        },
        messages: {

            nome: {
                required: " <label class='msg-required'>Preencha o nome do usuário!</label>"
            },

            cpf: {
                required: "<label class='msg-required'>Preencha o CPF do usuário!</label>",
                minlength: "<label class='msg-required'>O CPF contém 11 digitos, verifique e digite novamente!</label>",
//                remote: "<label class='msg-required'>Já existe um usuário cadastrado com esse CPF!</label>"
            },

            email: {
                required: "<label class='msg-required'>Preencha o e-mail do usuário!</label>",
                email: "<label class='msg-required'>Por favor, informe um e-mail válido!</label>"
            },

            cel: {
                required: "<label class='msg-required'>Preencha com o nº do celular do usuário!</label>"
            },

            cep: {
                required: "<label class='msg-required'>O CEP é obrigatório!</label>",
                minlength: "<label class='msg-required'>O CEP contém 8 digitos, verifique e digite novamente!</label>"
            },

            rua: {
                required: "<label class='msg-required'>Preencha a rua!</label>"
            },

            bairro: {
                required: "<label class='msg-required'>Preencha o bairro!</label>"
            },

            estado: {
                required: "<label class='msg-required'>Escolha o estado do usuário!</label>"
            },

            cidade: {
                required: "<label class='msg-required'>Escolha a cidade do usuário!</label>"
            },
            tipo: {
                required: "<label class='msg-required'>Escolha o tipo de conta do usuário!</label>"
            },
            usuario: {
                required: "<label class='msg-required'>Preencha o usuário de acesso ao sistema!</label>",
//                remote: "<label class='msg-required'>Já existe uma conta cadastrado com esse usuário!</label>"
            },
            senha: {
                required: "<label class='msg-required'>Preencha a senha do usuário!</label>"
            },
            confirmSenha: {
                required: "<label class='msg-required'>Confirme a senha do usuário!</label>"
            }
        }
    });
});



$(document).on('blur', "#cep", function () {
    $('#rua').val('');
    $('#numero').val('');
    $('#comp').val('');
    $('#bairro').val('');

    var cep = $('#cep').val();
    var cepSemPonto = cep.replace('.', '');
    $.ajax({
        type: 'GET',
        url: 'https://viacep.com.br/ws/' + cepSemPonto.replace('-', '') + '/json/',
        mimeType: 'json',
        success: function (retorno) {
            $("#lat").val('');
            $("#lng").val('');
            console.log(JSON.stringify(retorno));

            if ((retorno.localidade === '' || retorno.localidade === undefined) || (retorno.uf === '' || retorno.uf === undefined)) {
                $('#rua').attr('readonly', true);
                $('#numero').attr('readonly', true);
                $('#bairro').attr('readonly', true);
                $('#comp').attr('readonly', true);
                $('#cidade').attr('readonly', true);
                $('#estado').attr('readonly', true);
                $("[data-toggle='cep']").tooltip('show');

            } else if (retorno.logradouro === '') {
                $('#rua').attr('readonly', false);
                $('#comp').attr('readonly', false);
                $('#bairro').attr('readonly', false);
                $('#numero').attr('readonly', false);
            } else {
                $('#numero').attr('readonly', false);
                $('#comp').attr('readonly', false);
                $('#rua').val(retorno.logradouro);
                $('#comp').val(retorno.complemento);
                $('#bairro').val(retorno.bairro);
            }
            $('#cidade').val(retorno.localidade);
            $('#estado').val(retorno.uf);
            buscarPointDoCep(cepSemPonto);
        },
        error: function () {
            $("[data-toggle='cep']").tooltip('show');

            $('#rua').attr('readonly', true);
            $('#numero').attr('readonly', true);
            $('#comp').attr('readonly', true);
            $('#bairro').attr('readonly', true);
            $('#cidade').attr('readonly', true);
            $('#estado').attr('readonly', true);
        }
    });
});

$(document).on('keypress', "#cep", function () {
    $("[data-toggle='cep']").tooltip('hide');
});

$(document).on('blur', "#confirmSenha", function () {
    if ($("#senha").val() !== $("#confirmSenha").val()) {
        alert('Senha diferentes');
    }
});

function buscarCidadeEEstado(dados) {
    var comboEstado = $("#estado");
    var comboCidade = $("#cidade");
    $('#cidade option').remove();
    $.ajax({
        type: 'GET',
        url: './usuario?acao=buscarcidades&localidade=' + dados.localidade + '&uf=' + dados.uf,
        mimeType: 'json',
        success: function (retorno) {
            console.log(JSON.stringify(retorno));
            var optionCidade = new Option(retorno.cidade, retorno.idCidade);
            optionCidade.selected = true;
            comboEstado.val(retorno.idEstado);
            comboCidade.append(optionCidade);
        },
        error: function (erro) {
            console.log(JSON.stringify(erro));
            console.log(erro);
        }
    });
}

function buscarCidadesDoEstado() {
    var comboCidade = $("#cidade");
    var uf = $("#estado option:selected").text();

    $.ajax({
        type: 'GET',
        url: './usuario?acao=buscarcidadesdoestado&&uf=' + uf,
        mimeType: 'json',
        success: function (retorno) {
            console.log(JSON.stringify(retorno));
            var dados = retorno.data;
            $('#cidade option').remove();
            $.each(dados, function (i, dados) {
                var optionCidade = new Option(dados.cidade, dados.id);
                comboCidade.append(optionCidade);
            });
        },
        error: function (erro) {
            alert("Cidades não encontradas, escolha o estado novamente!");
        }
    });
}

function buscarPointDoCep(cep) {
    var lat = '';
    var lng = '';

    $.ajax({
        type: 'GET',
        url: 'https://maps.google.com/maps/api/geocode/json?address=' + cep + '&sensor=false&key=AIzaSyAKRNk-dKdR-ahbI7IQcfRS-FedpmFQOLg',
        mimeType: 'json',
        success: function (retorno) {
            if (retorno.status == 'OK') {
                $.each(retorno.results, function (i, dados) {
                    $("#lat").val(dados.geometry.location.lat);
                    $("#lng").val(dados.geometry.location.lng);
                });
            } else {
                console.log("Localização não encontrada");
            }
        },
        error: function (erro) {
            console.log("Erro ao buscar as coordenadas na API do Google Msg: " + erro);
        }
    });
}

$(document).on('blur', "#cpf", function () {
    var cpf = $("#cpf").val();
    var usuario = $("#usuario").val();
    $.ajax({
        type: 'GET',
        url: './usuario?acao=buscausuarioporcpf&cpf=' + cpf,
        mimeType: 'json',
        success: function (retorno) {
            if (retorno == true) {
                alert("Já existe um usuário cadastrado com esse CPF!");
            }
        },
        error: function (erro) {
            console.log("Erro: " + erro);
        }
    });
});

$(document).on('blur', "#usuario", function () {
    var usuario = $("#usuario").val();
    $.ajax({
        type: 'GET',
        url: './usuario?acao=buscausuario&usuario=' + usuario,
        mimeType: 'json',
        success: function (retorno) {
            if (retorno == true) {
                alert("Já existe uma conta cadastrada com esse usuário!");
            }
        },
        error: function (erro) {
            console.log("Erro: " + erro);
        }
    });
});
