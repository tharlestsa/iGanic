 
    Document   : produtosFornecedor
    Created on : 11/12/2017, 21:16:35
    Author     : guilherme


<%@page import="br.iganic.model.Produto"%>
<%@page import="br.iganic.util.Button"%>
<%@page import="br.iganic.model.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.PedidoDAO"%>
<%@page import="br.iganic.util.Tabela"%>
<%@page import="br.iganic.util.Label"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Produtos" />
</jsp:include>

<style>.tabledit-toolbar{width: 130px;} .botao{margin-left: 5px;}</style>
<div class="container cont">
<%
    String conteudo = "";

    ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");

    if (produtos == null) {
        request.getRequestDispatcher("./pedidosFornecedor").forward(request, response);
    }
    
    if(request.getAttribute("mensagem") != null) conteudo += request.getAttribute("mensagem");

    Tabela table = new Tabela("Produtos", new String[]{"#", "Nome", "Preco", "Quantidade", "Modo Produçao"});
    table.setId("tabela");

    for (Produto p : produtos) {
        String qtd = String.valueOf(p.getQuantidade());
        String preco = String.valueOf(p.getPreco());
        table.addLinha(new String[]{String.valueOf(p.getIdProduto()), p.getNome(), preco, qtd, p.getModoProducao()});
    }

    conteudo += table.toString();

    out.print(conteudo);
%>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://markcell.github.io/jquery-tabledit/assets/js/jquery.min.js"></script>
<script src="https://markcell.github.io/jquery-tabledit/assets/js/bootstrap.min.js"></script>
<script src="https://markcell.github.io/jquery-tabledit/assets/js/prettify.min.js"></script>
<script src="https://markcell.github.io/jquery-tabledit/assets/js/tabledit.min.js"></script>
<script type="text/javascript">
    $('#tabela').Tabledit({
        url: './produtosFornecedor',
        deleteButton: false,
        saveButton: true,
        autoFocus: false,
        buttons: {
            edit: {
                class: 'btn btn-sm btn-primary',
                html: '<span class="glyphicon glyphicon-pencil"></span>Editar',
                action: 'edit'
            },
            save: {
                class: 'btn btn-sm btn-success botao',
                html: '<span class="glyphicon glyphicon-pencil"></span>Salvar'
            }
        },
        columns: {
            identifier: [0, 'idPedido'],
            editable: [[3, 'status','{"A": "Em andamento", "F" :"Finalizado", "C" : "Cancelado"}']]
        }
    });
</script>
<jsp:include page="./base_Jsp/rodape.jsp" />
