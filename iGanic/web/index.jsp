<%-- 
    Document   : index
    Created on : 23/11/2017, 19:13:01
    Author     : tharles
--%>

<%@page import="br.iganic.view.Mensagem"%>
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
        <title>iGanic - Login de Acesso ao Sistema</title>
        <!-- Bootstrap core CSS-->
        <link href="./template/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <!-- Custom fonts for this template-->
        <link href="./template/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- Custom styles for this template-->
        <link href="./template/admin/css/sb-admin.css" rel="stylesheet" type="text/css"/>
        <link href="./css/estilos.css" rel="stylesheet" type="text/css"/>


    </head>

    <body >
        <div class="container">
            <div id="card-login" class="card card-login mx-auto mt-5">
                <img width="60%" style="margin: auto;"class="img-responsive" src="./img/logo/Logo_iGanic_fundo_branco.png" alt=""/>
                <div class="card-header" style='font-weight:bold;' >Login</div>
                <div class="card-body ">
                    <% if (request.getAttribute("mensagem") != null) {
                            out.print(new Mensagem("erro", String.valueOf(request.getAttribute("mensagem"))));
                        }
                    %>
                    <form id="form-login" action="./login" method="POST" >
                        <div class="form-group">
                            <label>Usuário</label>
                            <input class="form-control" type="text" id="usuario"  value="" name="usuario" placeholder="Informe o seu usuário" data-toggle="usuario" title="Este usuário não existe!">
                        </div>
                        <div class="form-group">
                            <label >Senha</label>
                            <input class="form-control" id="senha" name="senha" type="password" placeholder="Informe a sua senha">
                        </div>
                        <button type="submit" id="acao" name="acao" value="entrar" class="btn btn-success btn-block">Entrar</button>
                    </form>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="./usuario?acao=encaminhar">Cadastrar novo usuário</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="./template/admin/vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="./template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <!-- Core plugin JavaScript-->
        <script src="./template/admin/vendor/jquery-easing/jquery.easing.min.js" type="text/javascript"></script>
        <script src="./jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="./js/login.js" type="text/javascript"></script>

    </body>

</html>
