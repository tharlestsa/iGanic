<%@page import="br.iganic.util.Input"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page import="javax.swing.JOptionPane"%>

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
    <jsp:param name="titulo" value="Produtos Fornecedor"/>
</jsp:include>

<div class="container cont">
    <%
        try {

            ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produto");

            if (produtos == null) {
                request.getRequestDispatcher("./produtosFornecedor").forward(request, response);
            }
            String conteudo = "";

            if (request.getAttribute("mensagem") != null) {
                conteudo += request.getAttribute("mensagem");
            }

            Tabela table = new Tabela("Produtos", new String[]{"#", "Produto", "Preco", "Quantidade", "Ação"});
            String qtd;
            for (Produto p : produtos) {
                String acao = "";

                acao = "<form action='./produtosFornecedor' method='POST'>"
                        + new Input("hidden", String.valueOf(p.getIdProduto()), "idProduto", null, new Label(""))
                        + new Button("submit", "editar", "action", null, "Editar", "btn-danger")
                        + "</form>";
                table.addLinha(new String[]{String.valueOf(p.getIdProduto()), p.getNome(), String.valueOf(p.getPreco()), String.valueOf(p.getQuantidade()), acao});
            }

            conteudo += table.toString();

            out.println(conteudo);
        } catch (Exception e) {
            System.out.println("Erro - produtosFornecedor: " + e.getMessage());
        }

    %>

</div>
<jsp:include page="./base_Jsp/rodape.jsp" />
