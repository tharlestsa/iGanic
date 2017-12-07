<%-- 
    Document   : principal
    Created on : 29/11/2017, 19:43:05
    Author     : tharles
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="br.iganic.model.Fornecedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="P&aacute;gina Principal" />
</jsp:include>


<!-- Seção de Promoções -->
<section id="promocao">
    <%
        ProdutoDAO prodDao = new ProdutoDAO();
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        try {
            Double lat = Double.parseDouble(request.getSession().getAttribute("lat").toString());
            Double lng = Double.parseDouble(request.getSession().getAttribute("lng").toString());
            fornecedores = (ArrayList<Fornecedor>) prodDao.buscaFornecedoresProxDoCliente(new Usuario(null, null, null, null, null, lat, lng, null, null, null, 0));
        } catch (Exception e) {
            System.out.println("Exceção na busca dos fornecedores: " + e.getMessage());
        }

    %>
    
    <div class="barra-titulo"><center><label class="label-titulo" >Promoções dos fornecedores mais próximos de você</label></center></div>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <%                for (int i = 0; i < fornecedores.size(); i++) {
                    if (i == 0) {
                        out.print("<li data-target='#carouselExampleIndicators' data-slide-to='0' class='active'></li>");
                    } else {
                        out.print("<li data-target='#carouselExampleIndicators' data-slide-to='" + i + "'></li>");
                    }
                }
            %>
        </ol>
        <div class="carousel-inner" role="listbox">

            <%
                for (Fornecedor forn : fornecedores) {
                    out.print("<div class='carousel-item active img-carousel'>"
                            + "      <img class='d-block img-fluid' src='./img_produtos/" + forn.getImagem().getNome() + "'/>"
                            + " </div>");
                }
            %>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Anterior</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Próximo</span>
        </a>
    </div>

</section>
<!--/Seção de Promoções--> 
<div id="cont" class="container">
    <!--Produtos mais proxóximos usuarios--> 
    <section id="produto-proximo" class="container">
        <div class="card-columns">

            <%
                for (Fornecedor forn : fornecedores) {
                    out.print("<div class='card mb-3'>"
                            + "<a href='#'>"
                            + "     <img class='card-img-top img-fluid w-100' src='./img_produtos/" + forn.getImagem().getNome() + "'>"
                            + "</a>"
                            + "<div class='card-body'>"
                            + "      <h6 class='card-title mb-1'><a href='#'>David Miller</a></h6>"
                            + "      <p class='card-text small'>These waves are looking pretty good today!"
                            + "             <a href='#'>#surfsup</a>"
                            + "      </p>"
                            + "</div>"
                            + "</div>");
                }

            %>

        </div>

    </section>
    <!--//Produtos mais proxóximos usuarios--> 
</div>

<jsp:include page="./base_Jsp/rodape.jsp" />

