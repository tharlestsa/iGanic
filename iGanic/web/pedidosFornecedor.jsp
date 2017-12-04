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
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Pedidos" />
</jsp:include>

<style>.tabledit-toolbar{width: 130px;} .botao{margin-left: 5px;}</style>
<%
    String conteudo;

    PedidoDAO pedidoDAO = new PedidoDAO();

    ArrayList<Pedido> pedidos = pedidoDAO.buscaPedidosDoFornecedor(2);

    Tabela table = new Tabela("Pedidos", new String[]{"#", "Data", "Status", "Produto", "Quantidade"});
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

        table.addLinha(new String[]{String.valueOf(p.getIdPedido()), p.getData(), status, p.getNomeProduto(), String.valueOf(p.getQtd())});
    }

    conteudo = table.toString();

    out.print(conteudo);
%>

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
            editable: [[2, 'status','{"A": "Em andamento", "F" :"Finalizado", "C" : "Cancelado"}']]
        }
    });
</script>
<jsp:include page="./base_Jsp/rodape.jsp" />
