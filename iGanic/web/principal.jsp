<%-- 
    Document   : principal
    Created on : 29/11/2017, 19:43:05
    Author     : tharles
--%>

<%@page import="br.iganic.util.Button"%>
<%@page import="br.iganic.util.Tabela"%>
<%@page import="br.iganic.model.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.PedidoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp" />


<%
    String conteudo;
    
    PedidoDAO pedidoDAO = new PedidoDAO();
    
    
    ArrayList <Pedido> pedidos = pedidoDAO.buscaPedidosDoFornecedor(2);
    
    Tabela table = new Tabela("Pedidos", new String[]{"Data", "Status", "Produto", "Quantidade", "Açao"});
    
    for (Pedido p : pedidos){
        String status = "";
        switch(p.getStatus()){
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
        
        String acao = "<form action='#' method='POST'>";
        acao += new Button("submit", "", "visualizarpedido", null, "Ver", "btn-success");
        acao += "</form>";
        
        table.addLinha(new String[]{p.getData(), status, p.getNomeProduto(), String.valueOf(p.getQtd()), acao});
    }
    
    conteudo = table.toString();
    
    out.print(conteudo);
%>

<jsp:include page="./base_Jsp/rodape.jsp" />

