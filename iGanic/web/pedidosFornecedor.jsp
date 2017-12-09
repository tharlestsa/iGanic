<%-- 
    Document   : pedidosFornecedor
    Created on : 28/11/2017, 16:46:31
    Author     : rafael
--%>

<%@page import="br.iganic.util.Button"%>
<%@page import="br.iganic.model.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.PedidoDAO"%>
<%@page import="br.iganic.util.Tabela"%>
<%@page import="br.iganic.util.Label"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Pedidos" />
</jsp:include>

<style>.tabledit-toolbar{width: 130px;} .botao{margin-left: 5px;}</style>
<div class="container cont">
<%
    String conteudo = "";

    ArrayList<Pedido> pedidos = (ArrayList<Pedido>) request.getAttribute("pedidos");

    if (pedidos == null) {
        request.getRequestDispatcher("./pedidosFornecedor").forward(request, response);
    }
    
    if(request.getAttribute("mensagem") != null) conteudo += request.getAttribute("mensagem");

    Tabela table = new Tabela("Pedidos", new String[]{"#", "Data", "Cliente", "Status", "Produto", "Quantidade"});
    table.setId("tabela");

    for (Pedido p : pedidos) {
        String status = "";
        switch (p.getStatus()) {
            case "A":
                status = "Em andamento";
                break;
            case "C":
                status = "Cancelado";
                break;
            case "F":
                status = "Finalizado";
                break;
        }
        
        String qtd = String.valueOf(p.getQtd()) + " " + p.getUnidadeProduto();

        table.addLinha(new String[]{String.valueOf(p.getIdPedido()), p.getData(), p.getCliente(),status, p.getNomeProduto(), qtd});
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
        url: './pedidosFornecedor',
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
