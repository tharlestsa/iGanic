<%-- 
    Document   : principal
    Created on : 29/11/2017, 19:43:05
    Author     : tharles
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="br.iganic.dao.AvaliarProdutoDAO"%>
<%@page import="br.iganic.view.Mensagem"%>
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
            AvaliarProdutoDAO avaDao = new AvaliarProdutoDAO();
            Double nota = 0.0;
            try {
                Double lat = Double.parseDouble(request.getSession().getAttribute("lat").toString());
                Double lng = Double.parseDouble(request.getSession().getAttribute("lng").toString());
                fornecedores = (ArrayList<Fornecedor>) prodDao.buscaFornecedoresProxDoCliente(new Usuario(lat, lng));

            } catch (Exception e) {
                System.out.println("Exceção na busca dos fornecedores: " + e.getMessage());
            }

        %>

        <div class="barra-titulo"><center><label class="label-titulo" >Alimentos que você encontra no <a id="logo-corpo">iGanic</a>!</label> </center></div>
                <% if (request.getAttribute("mensagem") != null) {
                        out.print(new Mensagem(request.getAttribute("tipo").toString(), String.valueOf(request.getAttribute("mensagem"))));
                    }
                %>
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <%  for (int i = 0; i < fornecedores.size(); i++) {
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
                                    + "      <img  class='d-block img-fluid' src='./img_produtos/" + forn.getImagem().getNome() + "' alt='Sem imagem'/>"
                                    + " </div>");
                        } else {
                            out.print("<div class='carousel-item img-carousel'>"
                                    + "      <img  class='d-block img-fluid' src='./img_produtos/" + forn.getImagem().getNome() + "'alt='Sem imagem' />"
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
                    String notaf = null;
                    try {
                        nota = avaDao.buscaNotaDoProduto(forn.getProduto().getIdProduto());
                        DecimalFormat df = new DecimalFormat("0.##");
                        notaf = df.format(nota);
                    } catch (Exception e) {
                        System.out.println("Problema ao buscar a nota: " + e.getMessage());
                    }
                    out.print("<form method='POST' action='./efetuarPedidos'>"
                            + "<input type='hidden' name='idProduto' id='idProduto' value='" + forn.getProduto().getIdProduto() + "'>"
                            + "<div class='card mb-3'>"
                            + "     <img class='card-img-top img-fluid w-100' src='./img_produtos/" + forn.getImagem().getNome() + "'>"
                            + "<div class='card-body'>"
                            + "      <label class='label-produto'>" + forn.getProduto().getNome() + "</label><p class='p-label'>"
                            + "      <label class='label-produto-corpo'>Nota: " + notaf + "</label><p class='p-label'>"
                            + "      <label id ='preco' class='label-produto-corpo'>Preço: R$ " + String.valueOf(forn.getProduto().getPreco()).replace(".", ",") + "</label><p class='p-label'>"
                            + "      <label class='label-produto-corpo'><button type='button' class='modal-produtos btn btn-success' data-toggle='modal' data-target='#modal-pro'> <i class='material-icons'>info</i> "
                            + "<input type='hidden' name='nome' id='nome' value='" + forn.getProduto().getNome() + "'>"
                            + "<input type='hidden' name='nome' id='fornecedor' value='" + forn.getUsuario().getNome() + "'>"
                            + "<input type='hidden' name='modo' id='modo' value='" + forn.getProduto().getModoProducao() + "'>"
                            + "<input type='hidden' name='rua' id='rua' value='" + forn.getUsuario().getRua() + "'>"
                            + "<input type='hidden' name='num' id='num' value='" + forn.getUsuario().getNum() + "'>"
                            + "<input type='hidden' name='comp' id='comp' value='" + forn.getUsuario().getComp() + "'>"
                            + "<input type='hidden' name='bairro' id='bairro' value='" + forn.getUsuario().getBairro() + "'>"
                            + "<input type='hidden' name='cidade' id='cidade' value='" + forn.getUsuario().getCidade() + "'>"
                            + "<input type='hidden' name='uf' id='uf' value='" + forn.getUsuario().getUf() + "'>"
                            + "</button></label>"
                            + "  <button class='btn btn-success button-pedido' id='acao' name='acao' value='pedir' type='submit'>"
                            + "         <i class='material-icons'>add_shopping_cart</i>"
                            + "  </button>"
                            + "</div>"
                            + "</div>"
                            + "</form>");
                }
            %>

        </div>
    </section>
    <!--//Produtos mais proxóximos usuarios--> 


    <jsp:include page="./base_Jsp/rodape.jsp" />

