<%-- 
    Document   : cadastra_usuario
    Created on : 23/11/2017, 22:11:06
    Author     : tharles
--%>

<%@page import="br.iganic.model.Estado"%>
<%@page import="br.iganic.dao.EstadoDAO"%>
<%@page import="br.iganic.view.Mensagem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    HttpSession sessao = request.getSession(true);
    sessao.invalidate();
%>

<html lang="pt_br">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="./img/iganic_sem_fundo.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="./img/iganic_sem_fundo.ico" type="image/x-icon" />
        <title>iGanic - Registro de Usuário </title>
        <!-- Bootstrap core CSS-->
        <link href="./template/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <!-- Custom fonts for this template-->
        <link href="./template/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- Custom styles for this template-->
        <link href="./template/admin/css/sb-admin.css" rel="stylesheet" type="text/css"/>

        <link href="./css/estilos.css" rel="stylesheet" type="text/css"/>

    </head>

    <body >
        <div class="card card-register mx-auto mt-3">
            <div class="card-header">Registro nova conta de usuário</div>
            <div class="card-body">
                <% if (request.getAttribute("mensagem") != null) {
                        out.print(new Mensagem(request.getAttribute("tipo").toString(), String.valueOf(request.getAttribute("mensagem"))));
                    }
                %>
                <form  id="form-usuario" action="./usuario" method="POST" >
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-8">
                                <label for="inputNome">Nome</label>
                                <input class="form-control" id="nome" name="nome" type="text" placeholder="Informe seu nome completo">
                            </div>
                            <div class="col-md-4">
                                <label for="inputCpf">CPF</label>
                                <input class="form-control" id="cpf" name="cpf" type="text" placeholder="Informe o seu CPF">
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-8">
                                <label for="inputEmail">E-mail</label>
                                <input class="form-control" id="email" name="email" type="email" placeholder="Informe seu o E-mail">
                            </div>
                            <div class="col-md-4">
                                <label for="inputCel">Celular</label>
                                <input class="form-control" id="cel" name="cel" type="tel" placeholder="nº de celular">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-4">
                                <label for="inputCep">CEP</label>
                                <input class="form-control" id="cep" name="cep" type="text" placeholder="Informe o CEP" data-toggle="cep" title="Este CEP não foi encontrado!">
                            </div>
                            <div class="col-md-6">
                                <label for="inputRua">Rua</label>
                                <input class="form-control" id="rua" name="rua" type="text" placeholder="Informe a rua de sua residência">
                            </div>
                            <div class="col-md-2">
                                <label for="inputNumero">nº</label>
                                <input class="form-control" id="numero" name="numero" type="text" placeholder="Número">
                            </div>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputComp">Complemento</label>
                                <input class="form-control" id="comp" name="comp" type="text" placeholder="Complemento">
                            </div>
                            <div class="col-md-6">
                                <label for="inputBairro">Bairro</label>
                                <input class="form-control" id="bairro" name="bairro" type="text" placeholder="Informe o seu bairro">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputCidade">Cidade</label>
                                <input class="form-control" id="cidade" name="cidade" >
                            </div>
                            <div class="col-md-6">
                                <label for="inputEstado">Estado</label>
                                <input class="form-control" id="estado" name="estado" >
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputTipo">Tipo da Conta</label>
                                <select class="form-control" id="tipo" name="tipo" >
                                    <option value="-1" >Informe o tipo da conta do usuário</option>
                                    <option value="C">Cliente</option>
                                    <option value="F" >Fornecedor</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="inputUsuario">Usuário</label>
                                <input class="form-control" id="usuario" name="usuario" type="text" placeholder="Informe o usuário acesso ao login">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputSenha">Senha</label>
                                <input class="form-control" id="senha" name="senha"  type="password" placeholder="Informe a senha acesso">
                            </div>
                            <div class="col-md-6">
                                <label for="confirmSenha">Confirme a senha</label>
                                <input class="form-control" id="confirmSenha" name="confirmSenha" type="password" placeholder="Informe a senha novamente">
                            </div>
                        </div>
                    </div>
                    <input class="form-control" id="lat" name="lat" type="hidden">
                    <input class="form-control" id="lng" name="lng" type="hidden">
                    <button type="submit" id="acao" name="acao" value="registrar" class="btn btn-success btn-block">Registrar</button>
                </form>
                <div class="text-center">
                    <a class="d-block small mt-3" href="./login?acao=toLogin">Efetuar login</a>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="./template/admin/vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="./template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <!-- Core plugin JavaScript-->
        <script src="./template/admin/vendor/jquery-easing/jquery.easing.min.js" type="text/javascript"></script>
        <script src="./mask-plugin/jquery.mask.min.js" type="text/javascript"></script>
        <script src="./jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="./js/cadastro_usuario.js" type="text/javascript"></script>
    </body>

</html>

