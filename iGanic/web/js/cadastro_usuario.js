
$(document).ready(function () {

    $('#cpf').mask("000.000.000-00");
    $('#cel').mask("(00)00000-0000");
    $('#cep').mask("00.000-000");

    $('#rua').attr('disabled', true);
    $('#numero').attr('disabled', true);
    $('#bairro').attr('disabled', true);
    $('#cidade').attr('disabled', true);
    $('#estado').attr('disabled', true);
});



$().ready(function () {
    // validate the comment form when it is submitted
    $("#form-usuario").validate({
        rules: {
            nome: {
                required: true
            },

            cpf: {
                required: true,
                minlength: 11
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
//                remote: 'https://viacep.com.br/ws/' + cepSemPonto.replace('-', '') + '/json/'
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
                required: true
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
                minlength: "<label class='msg-required'>O CPF contém 11 digitos, verifique e digite novamente!</label>"
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
//                remote: "<label class='msg-required'>CEP inválido!</label>"
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
                required: "<label class='msg-required'>Preencha o usuário de acesso ao sistema!</label>"
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

            buscarCidadeEEstado(retorno);

            if ((retorno.logradouro == '' || retorno.logradouro == undefined) || (retorno.bairro == '' || retorno.bairro == undefined)) {
                $('#rua').attr('disabled', false);
                $('#numero').attr('disabled', false);
                $('#comp').attr('disabled', false);
                $('#bairro').attr('disabled', false);
                $('#cidade').attr('disabled', false);
                $('#estado').attr('disabled', false);

//                $('#cep').val('');
//                $('#rua').val('');
//                $('#comp').val('');
//                $('#bairro').val('');
//
//                $('#cidade option:selected').prop("selected", false);
//                $('#estado option:selected').prop("selected", false);
                $("#form-usuario").validate({
                    rules: {
                        numero: {
                            required: true
                        }
                    },
                    messages: {
                        numero: {
                            required: "<label class='msg-required'>Informe o número da residência</label>"
                        }
                    }
                });

                alert("Endereço não encontrado!");
            } else {
                $('#numero').attr('disabled', false);
                $('#comp').attr('disabled', false);
                $('#rua').val(retorno.logradouro);
                $('#comp').val(retorno.complemento);
                $('#bairro').val(retorno.bairro);

            }
            buscarPointDoCep(cepSemPonto);
        },
        error: function () {
            alert("CEP não encontrado");

            $('#rua').attr('disabled', true);
            $('#numero').attr('disabled', true);
            $('#comp').attr('disabled', true);
            $('#bairro').attr('disabled', true);
            $('#cidade').attr('disabled', true);
            $('#estado').attr('disabled', true);
        }
    });
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
            var optionCidade = new Option(retorno.cidade, retorno.idCidade);
            console.log(retorno.idCidade + " - " + retorno.idEstado);
            optionCidade.selected = true;
            comboEstado.val(retorno.idEstado);
            comboCidade.append(optionCidade);
        },
        error: function (erro) {
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
                console.log("CEP não encontrado" + erro);
            }
        },
        error: function (erro) {
            console.log("Erro ao buscar as coordenadas na API do Google Meg: " + erro);
        }
    });
}
