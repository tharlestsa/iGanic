<%@page import="br.iganic.dao.CategoriaDAO"%>
<%@page import="br.iganic.model.Estado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt_br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>iGanic</title>

        <!-- Bootstrap core CSS -->
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/bootstrap-yeti.min.css" rel="stylesheet" id="theme-file">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/prettify.min.css" rel="stylesheet">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/prettify-bootstrap.min.css" rel="stylesheet">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/font-awesome.min.css" rel="stylesheet">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/custom.css" rel="stylesheet">
        <link href="./bootstrap-4/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles -->
        <link href="dashboard.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                <a class="navbar-brand" href="#">Dashboard</a>
                <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Configurações</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Perfil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Ajuda</a>
                        </li>
                    </ul>
                    <form class="form-inline mt-2 mt-md-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Buscar" aria-label="Buscar">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                    </form>
                </div>
            </nav>
        </header>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
                    <ul class="nav nav-pills flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Overview <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Categorias</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Analytics</a>
                        </li>
                    </ul>

                    <ul class="nav nav-pills flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Páginas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Categorias</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Padrões de preços</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Produtos</a>
                        </li>
                    </ul>

                    <ul class="nav nav-pills flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Usuários</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Pessoas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Sair</a>
                        </li>
                    </ul>
                </nav>

                <main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-5">
                    <h2>Listagem de Categorias</h2>
                    <!--          <div class="table-responsive">
                                <table id="tabelaTop10ProdutosCategoria-ajax" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                    <tr>
                                        <th>idCategoria</th>
                                        <th>nome</th>
                                        <th>desc</th>
                                        <th>qtdProdutos</th>
                                    </tr>
                                  </thead>    
                                  <tbody id="tbody"></tbody>
                                </table>
                              </div>-->

                    <div class="table-responsive">
                        <table class="table table-striped table-bordered" id="tabela-categorias">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nome</th>
                                    <th>uf</th>
                                    <th>Foto</th>
                                    <th class="tabledit-toolbar-column"></th></tr>
                            </thead>
                            <tbody id="dados-tabela">

                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
            <!-- Logout Modal-->
            <div class="modal fade" id="foto-modal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Inserir Fotos</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <iframe style="width: 95%; height: 300px; border: none; " id="frame">
                                
                            </iframe>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                            <a class="btn btn-primary" href="">Sair</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/jquery.min.js"></script>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/bootstrap.min.js"></script>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/prettify.min.js"></script>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/tabledit.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $.ajax({
                    type: 'GET',
                    url: '../dados?action=listarestados',
                    mimeType: 'json',
                    success: function (retorno) {
                        console.log(JSON.stringify(retorno));
                        var dados = retorno.data;
                        $.each(dados, function (i, dados) {
                            var body = "<tr>";
                            body += "<td>" + dados.idEstado + "</td>";
                            body += "<td>" + dados.nome + "</td>";
                            body += "<td>" + dados.uf + "</td>";
                            body += "<td> <button type='button'class='btn btn-sm btn-dafualt' onClick='abriGerenciadorDeFotos(" + dados.idEstado + ")'><span class='glyphicon glyphicon-camera'></span> Upload</button></td>";
                            body += "</tr>";
                            $("#dados-tabela").append(body);

                        });
                        /*DataTables instantiation.*/
                        montaTabela();
                    },
                    error: function () {
                        alert('Fail!');
                    }
                });

            });

            function abriGerenciadorDeFotos(id) {
                $("#foto-modal").modal();
                $("#frame-if").attr('src', '../fotos?idProduto='+id+'&cmd=listar');
            }

            function montaTabela() {
                $('#tabela-categorias').Tabledit({
                    url: "../dados",
                    columns: {
                        identifier: [0, 'id'],
                        editable: [[1, 'nome'], [2, 'uf']]
                    },
                    onDraw: function () {
                        console.log('onDraw()');
                    },
                    onSuccess: function (data, textStatus, jqXHR) {
                        console.log('onSuccess(data, textStatus, jqXHR)');
                        console.log(data);
                        console.log(textStatus);
                        console.log(jqXHR);
                    },
                    onFail: function (jqXHR, textStatus, errorThrown) {
                        console.log('onFail(jqXHR, textStatus, errorThrown)');
                        console.log(jqXHR);
                        console.log(textStatus);
                        console.log(errorThrown);
                    },
                    onAlways: function () {
                        console.log('onAlways()');
                    }

                });
            }
        </script>
    </body>
</html>
