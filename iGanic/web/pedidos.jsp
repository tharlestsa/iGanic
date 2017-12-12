<%-- 
   Document   : efetuarPedido
   Created on : 24/11/2017, 18:58:35
   Author     : guilherme
--%>

<%@page import="br.iganic.model.Imagem"%>
<%@page import="br.iganic.dao.ImagemDAO"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="br.iganic.model.Produto"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.iganic.view.Mensagem"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Efetuar Pedido" />
</jsp:include>

<div class="container">
    <div class="card mx-auto mt-5">
        <div class="card-header" style='font-weight:bold;' >Efetuar Pedido</div>
        <div class="card-body ">
            <% if (request.getAttribute("mensagem") != null) {
                    out.print(new Mensagem(String.valueOf(request.getAttribute("tipo")), String.valueOf(request.getAttribute("mensagem"))));
                }
            %>
            <form id="form-produto" action="./efetuarPedidos" method="POST">
                <%
                    String idProduto = (request.getParameter("idProduto") != null) ? request.getParameter("idProduto").toLowerCase() : "";
                    String nomeFornecedor = "";
                    String nomeProduto = "";
                    String modoProducao = "";
                    String qtd = "";
                    Imagem imagem = null;

                    if (idProduto.isEmpty()) {
                        request.getRequestDispatcher("/principal.jsp").forward(request, response);
                    } else {
                        ProdutoDAO produtoDao = new ProdutoDAO();

                        Produto produto = produtoDao.buscaProduto(Integer.parseInt(idProduto));
                        UsuarioDAO usuarioDAO = new UsuarioDAO();

                        Usuario usu = new Usuario(produto.getIdUsuario());
                        ArrayList<Usuario> usuarios = null;

                        nomeFornecedor = "";
                        nomeProduto = produto.getNome();
                        modoProducao = produto.getModoProducao();
                        qtd = String.valueOf(produto.getQuantidade());

                        ImagemDAO imgDao = new ImagemDAO();

                        try {

                            usuarios = (ArrayList<Usuario>) usuarioDAO.buscaUsuPeloId(usu);
                            imagem = (Imagem) imgDao.buscaImagem(new Imagem(Integer.parseInt(idProduto)));

                            for (Usuario e : usuarios) {
                                nomeFornecedor = e.getNome();

                            }
                        } catch (Exception e) {

                        }
                    }
                %>
                <input type="hidden" id="idProduto" name="idProduto" value="<%=idProduto%>">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <img  class="img-thumbnail" src="./img_produtos/<%=imagem.getNome()%>"  alt="...">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label>Nome Fornecedor</label>
                            <input class="form-control" type="text" id="nomeFornecedor" name="nomeFornecedor" value="<%=nomeFornecedor%>" disabled="">
                        </div>
                        <div class="col-md-6">
                            <label >Nome do Produto</label>
                            <input class="form-control" type="text" id="nomeProduto" name="nomeProduto" value="<%=nomeProduto%>" disabled="">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label >Modo de produçao</label>
                            <textarea class="form-control" rows="4" type="text" id="modoProducao" name="modoProducao" disabled=""><%=modoProducao%></textarea>
                        </div>
                        <div class="col-md-6">
                            <label>Quantidade Disponivel</label>
                            <input class="form-control" type="text" id="qtd" name="qtd" value="<%=qtd%>" disabled="">
                        </div>
                    </div>

                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label>Quantidade</label>
                            <input class="form-control" type="text" id="quantidade" name="quantidade" placeholder="Informe a quantidade do pedido" required="">
                        </div>
                    </div>
                </div>

                <button type="submit" id="acao" name="acao" value="efetuar" class="btn btn-success btn-block">Efetuar Pedido</button>
            </form>

        </div>
    </div>

</div>

<jsp:include page="./base_Jsp/rodape.jsp" />
