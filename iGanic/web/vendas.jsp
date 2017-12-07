<%-- 
    Document   : vendas
    Created on : 02/12/2017, 18:59:47
    Author     : rafael
--%>

<%@page import="br.iganic.util.Tabela"%>
<%@page import="br.iganic.model.Venda"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Vendas" />
</jsp:include>
<div class="container cont">
<%
    ArrayList<Venda> vendas = (ArrayList<Venda>) request.getAttribute("vendas");

    if (vendas == null) {
        request.getRequestDispatcher("./vendas").forward(request, response);
    }
    String conteudo = "";
    
    
    if(request.getAttribute("mensagem") != null) conteudo += request.getAttribute("mensagem");
    

    Tabela table = new Tabela("Vendas", new String[]{"#", "Data", "Cliente", "Produto", "Quantidade"});
    int i = 1;
    for (Venda v : vendas) {

        String qtd = v.getQuantidade() + " " + v.getUnidadeProduto();
        table.addLinha(new String[]{String.valueOf(i), v.getData(), v.getCliente(), v.getProduto(), qtd});
        i++;
    }

    conteudo += table.toString();
    out.println(conteudo);

%>
</div>
<jsp:include page="./base_Jsp/rodape.jsp" />