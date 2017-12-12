<%-- 
    Document   : avaliarProduto
    Created on : 07/12/2017, 17:03:52
    Author     : guilherme
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="br.iganic.dao.UsuarioDAO"%>
<%@page import="br.iganic.model.Produto"%>
<%@page import="br.iganic.model.Produto"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.iganic.view.Mensagem"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Avaliar Produtos" />
</jsp:include>

<div class="container">
    <div class="card mx-auto mt-5">
        <div class="card-header" style='font-weight:bold;' >Avaliar Produto</div>
        <div class="card-body ">
            <form id="form-avaliacao" action="./avaliarProduto" method="post">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <% if (request.getAttribute("mensagem") != null) {
                                    out.print(new Mensagem(String.valueOf(request.getAttribute("tipo")), String.valueOf(request.getAttribute("mensagem"))));
                                }
                            %>
                            <label>Nome produto</label>
                            <%
                                ProdutoDAO produtoDao = new ProdutoDAO();

                                int idProd = (int) request.getAttribute("idProduto");

                                Produto produto = produtoDao.buscaProduto(idProd);

                                UsuarioDAO usuarioDAO = new UsuarioDAO();
                                Usuario usu = new Usuario(produto.getIdUsuario());
                                ArrayList<Usuario> usuarios = null;
                                String nomeFornecedor = "";
                                String nomeProduto = produto.getNome();
                                int idProduto = produto.getIdProduto();

                                try {

                                    usuarios = (ArrayList<Usuario>) usuarioDAO.buscaUsuPeloId(usu);

                                    for (Usuario e : usuarios) {
                                        nomeFornecedor = e.getNome();

                                    }
                                } catch (Exception e) {
                                }
                            %>

                            <input class="form-control" type="text" id="nome" name="nome" value="<%=nomeProduto%>" disabled="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label>Nome Fornecedor</label>
                            <input class="form-control" type="text" id="nomeFornecedor" name="nomeFornecedor" value="<%=nomeFornecedor%>" disabled="">
                        </div>
                        <div class="col-md-4">
                            <label>Nota</label>
                            <input class="form-control" type="text" id="nota" name="nota" placeholder="Informe a nota do produto" required="">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <label>Comentario</label>
                            <textarea class="form-control" rows="6" type="text" id="comentario" name="comentario" placeholder="Comente sobre essa nota" required=""></textarea>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="idProduto" value="<%= idProduto%>"/> 
                <button type="submit" id="acao" name="acao" value="avaliar" class="btn btn-success btn-block">Avaliar</button>
            </form>

        </div>
    </div>

</div>
<!-- Bootstrap core JavaScript-->
<script src="./template/admin/vendor/jquery/jquery.min.js" type="text/javascript"></script>
<script src="./template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<!-- Core plugin JavaScript-->
<script src="./template/admin/vendor/jquery-easing/jquery.easing.min.js" type="text/javascript"></script>
<script src="./mask-plugin/jquery.mask.min.js" type="text/javascript"></script>
<script src="./jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/cadastro_produto.js" type="text/javascript"></script>


<jsp:include page="./base_Jsp/rodape.jsp" />

