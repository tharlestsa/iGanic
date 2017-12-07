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

<div class="container">
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

        <div class="barra-titulo"><center><label class="label-titulo" >Alimentos que você encontra no <a id="logo-corpo">iGanic</a>!</label> </center></div>
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
                    int i = 0;
                    for (Fornecedor forn : fornecedores) {
                        if (i == 0) {
                            out.print("<div class='carousel-item active img-carousel'>"
                                    + "      <img class='d-block img-fluid' src='./img_produtos/" + forn.getImagem().getNome() + "'/>"
                                    + " </div>");
                        } else {
                            out.print("<div class='carousel-item img-carousel'>"
                                    + "      <img class='d-block img-fluid' src='./img_produtos/" + forn.getImagem().getNome() + "'/>"
                                    + " </div>");
                        }
                        i++;
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

    <!--Produtos mais proxóximos usuarios--> 
    <section id="produto-proximo" class="container">
        <div class="barra-titulo"><center>O <label class="label-titulo" ><a id="logo-corpo">iGanic</a> traz os produtos localizados mais próximos de você!</label> </center></div>

        <div class="card-columns">

            <%
                for (Fornecedor forn : fornecedores) {
                    out.print("<form method='POST' action=''>"
                            + "<input type='hidden' name='idProduto' id='idProduto' value='" + forn.getProduto().getIdProduto() + "'>"
                            + "<div class='card mb-3'>"
                            + "     <img class='card-img-top img-fluid w-100' src='./img_produtos/" + forn.getImagem().getNome() + "'>"
                            + "<div class='card-body'>"
                            + "      <h6 class='card-title mb-1'><a href=''>David Miller</a></h6>"
                            + "      <p class='card-text small'>These waves are looking pretty good today!"
                            + "             <a href='' class='modal-produtos' rule='button' data-toggle='modal' data-target='#modal-pro'>Saiba mais ..."
                            + "<input type='hidden' name='nome' id='nome' value='" + forn.getProduto().getNome() + "'>"
                            + "<input type='hidden' name='modo' id='modo' value='" + forn.getProduto().getModoProducao() + "'>"
                            + "</a>"
                            + "      </p>"
                            + "</div>"
                            + "</div>"
                            + "</form>");
                }

            %>

        </div>
    </section>
    <!--//Produtos mais proxóximos usuarios--> 

    <jsp:include page="./base_Jsp/rodape.jsp" />

