<%-- 
   Document   : cadastra_produto
   Created on : 24/11/2017, 18:58:35
   Author     : guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.iganic.view.Mensagem"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Cadastro de Produtos" />
</jsp:include>

<html lang="pt_br">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>iGanic - Cadastro de Produtos</title>
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
            <div class="card mx-auto mt-5">
                <div class="card-header" style='font-weight:bold;' >Cadastro de Produto</div>
                <div class="card-body ">
                    <% if (request.getAttribute("mensagem") != null) {
                            out.print(new Mensagem("erro", String.valueOf(request.getAttribute("mensagem"))));
                        }
                    %>
                    <form id="form-produto" action="./cadastroDeProduto" method="POST" >
                        <div class="form-group">
                            <label>Descriçao</label>
                            <input class="form-control" type="text" id="descricao" name="descricao" placeholder="Informe o nome do produto">
                        </div>
                        <div class="form-group">
                            <label >Unidade</label>
                            <!--                            <input class="form-control" id="unidade" name="unidade" type="text" placeholder="Informe a unidade do produto">-->
                            <select class="form-control form-control-sm" id="unidade" name="unidade">
                                <option value="g">Grama</option>
                                <option value="kg">Quilograma</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Preço</label>
                            <input class="form-control" type="text" id="preco" name="preco" placeholder="Informe o preço do produto">
                        </div>
                        <div class="form-group">
                            <label>Quantidade</label>
                            <input class="form-control" type="text" id="quantidade" name="quantidade" placeholder="Informe a quantidade do produto">
                        </div>
                        <div class="form-group">
                            <label>Modo de Produçao</label>
                            <input class="form-control" type="text" id="modo" name="modo" placeholder="Informe o modo de produçao do produto">
                        </div>
                        <button type="submit" id="acao" name="acao" value="cadastrar" class="btn btn-success btn-block">Cadastrar</button>
                    </form>

                </div>
            </div>
        </div>
        <!--         Bootstrap core JavaScript
                <script src="./template/admin/vendor/jquery/jquery.min.js" type="text/javascript"></script>
                <script src="./template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
                 Core plugin JavaScript
                <script src="./template/admin/vendor/jquery-easing/jquery.easing.min.js" type="text/javascript"></script>
                <script src="../jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>-->
    </body>

</html>

<jsp:include page="./base_Jsp/rodape.jsp" />
