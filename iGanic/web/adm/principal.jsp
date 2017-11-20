<!doctype html>
<html lang="pt_br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../img/i-lend-1PP-sombra.png">

    <title>Dashboard - i-Lend v-1.0</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap-4/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="bootstrap-4/css/dataTables.bootstrap4.min.css">

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
              <a class="nav-link" href="#">Relatórios</a>
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

        <main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">
          <h1>Dashboard - i-Lend v-1.0</h1>

          <h2>Top 10 Produtos por Categoria</h2>
          <div class="table-responsive">
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
          </div>
        </main>

        
        
        
      </div>
    </div>

    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="../jquery/jquery.js"></script>  
    <script src="bootstrap-4/js/popper.js"></script>
    <script src="bootstrap-4/js/bootstrap.min.js"></script>
    <script src="../jquery/jquery.dataTables.min.js"></script>
    <script src="bootstrap-4/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
                
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/iLend/getDados.pvh?cmd=top10produtoscategoria',
            data: 'data',
            mimeType: 'json',
            success: function(retorno) {
                var dados = retorno.data;
                $.each(dados, function(i, dados) {
                        var body = "<tr>";
                        body    += "<td>" + dados.idCategoria + "</td>";
                        body    += "<td>" + dados.nome + "</td>";
                        body    += "<td>" + dados.desc + "</td>";
                        body    += "<td>" + dados.qtdProdutos + "</td>";
                        body    += "</tr>";
                        $("#tbody").append(body);
                       
                });
                /*DataTables instantiation.*/
                $( "#tabelaTop10ProdutosCategoria-ajax" ).DataTable();
            },
            error: function(erro) {
                alert('Fail!-'+erro);
            }
        });
            
      } );
    </script>
  </body>
</html>
