<%-- 
    Document   : pedidosCliente
    Created on : 05/12/2017, 10:29:52
    Author     : rafael
--%>

<%@page import="br.iganic.dao.AvaliarProdutoDAO"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="br.iganic.util.Label"%>
<%@page import="br.iganic.util.Input"%>
<%@page import="br.iganic.util.Button"%>
<%@page import="br.iganic.util.Tabela"%>
<%@page import="br.iganic.model.PedidoCliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Pedidos" />
</jsp:include>

<div class="container cont">
    <%
        ArrayList<PedidoCliente> pedidos = (ArrayList<PedidoCliente>) request.getAttribute("pedidos");

        if (pedidos == null) {
            request.getRequestDispatcher("./pedidosCliente").forward(request, response);
        }
        String conteudo = "";

        if (request.getAttribute("mensagem") != null) {
            conteudo += request.getAttribute("mensagem");
        }

        Tabela table = new Tabela("Pedidos", new String[]{"Data", "Produto", "Quantidade", "Status", "Total", "Fornecedor", "Ação"});
        String qtd;
        for (PedidoCliente p : pedidos) {
            qtd = p.getQtd() + " " + p.getUnidade();
            String acao = "";
            String status = "";
            switch (p.getStatus()) {
                case "A":
                    status = "Em andamento";
                    acao = "<form action='./pedidosCliente' method='POST'>"
                            + new Input("hidden", p.getIdPedido(), "idPedido", null, new Label(""))
                            + new Button("submit", "alt", "action", null, "Cancelar", "btn-danger")
                            + "</form>";
                    break;
                case "C":
                    status = "Cancelado";
                    break;
                case "F":
                    status = "Finalizado";
                    acao = "<form action='./pedidosCliente' method='POST'>"
                            + new Input("hidden", p.getIdPedido(), "idPedido", null, new Label(""))
                            + new Input("hidden", p.getIdProduto(), "idProduto", null, new Label(""))
                            + new Button("submit", "avaliar", "action", null, "Avaliar", "btn btn-warning")
                            + "</form>";
                    break;
            }

            table.addLinha(new String[]{p.getData(), p.getProduto(), qtd, status, p.getTotal(), p.getFornecedor(), acao});

        }

        conteudo += table.toString();
        out.println(conteudo);

    %>
</div>

<jsp:include page="./base_Jsp/rodape.jsp" />