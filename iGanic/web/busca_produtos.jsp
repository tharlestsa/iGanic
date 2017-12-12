<%-- 
    Document   : principal
    Created on : 29/11/2017, 19:43:05
    Author     : tharles
--%>

<%@page import="br.iganic.view.Mensagem"%>
<%@page import="br.iganic.model.Produto"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="br.iganic.model.Fornecedor"%>
<%@page import="br.iganic.util.Tabela"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Busca de Produtos" />
</jsp:include>



<section id="mapa-produtos">
    <div class="barra-titulo"><center><label class="label-titulo" >O alimento pesquisado é exibido no mapa pelo <a id="logo-corpo">iGanic</a>!</label> </center></div>
            <% if (request.getAttribute("mensagem") != null) {
                    out.print(new Mensagem(request.getAttribute("tipo").toString(), String.valueOf(request.getAttribute("mensagem"))));
                }
            %>
    <!-- Mapa -->
    <div id="mapa">   
    </div>
    <!-- /Mapa -->
</section>
<%
    try {
        String conteudo = "";
        conteudo += "<div class='barra-titulo'><center><label class='label-titulo' >TABELA DE PRODUTOS PESQUISADOS</label> </center></div>";

        ProdutoDAO prodDao = new ProdutoDAO();
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {
            String produto = request.getSession().getAttribute("nomeProduto").toString();

            Double lat = Double.parseDouble(request.getSession().getAttribute("lat").toString());
            Double lng = Double.parseDouble(request.getSession().getAttribute("lng").toString());
            Usuario usu = new Usuario(lat, lng);

            fornecedores = (ArrayList<Fornecedor>) prodDao.buscaFornecedores(new Fornecedor(usu, new Produto(produto, null, null, null, null, 0)));
        } catch (Exception e) {
            System.out.println("Exceção na busca dos fornecedores: " + e.getMessage());
        }

        if (fornecedores == null) {

            request.getRequestDispatcher("./principal.jsp").forward(request, response);
        }

        Tabela table = new Tabela("", new String[]{"#", "Fornecedor", "Alimento", "Preço", "Ação"});
        table.setId("tabela-busca");

        for (Fornecedor forn : fornecedores) {
            String pedir = "<form class='form-inline' action='./efetuarPedidos' method='POST'>"
                    + "        <div class='input-group input-group-md'>"
                    + "           <input class='form-control' type='hidden' name='idProduto' id='idProduto' value='" + String.valueOf(forn.getProduto().getIdProduto()) + "'>"
                    + "               <span class='input-group'>"
                    + "               <button class='btn btn-success' id='acao' name='acao' value='pedir' type='submit'>"
                    + "                       <i class='fa fa-cart-arrow-down'></i>"
                    + "                   </button>"
                    + "               </span>"
                    + "           </div>"
                    + "       </form>";
            table.addLinha(new String[]{String.valueOf(forn.getProduto().getIdProduto()), forn.getUsuario().getNome(), forn.getProduto().getNome(), String.valueOf(forn.getProduto().getPreco()), pedir});
        }

        conteudo += table.toString();

        out.print(conteudo);
    } catch (Exception e) {
        System.out.println("Erro - busca Produtos: "+e.getMessage());
    }
%>

<jsp:include page="./base_Jsp/rodape.jsp" />


