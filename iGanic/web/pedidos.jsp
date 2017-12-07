<%-- 
   Document   : cadastra_produto
   Created on : 24/11/2017, 18:58:35
   Author     : guilherme
--%>

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
        <div class="card-header" style='font-weight:bold;' >Cadastro de Produto</div>
        <div class="card-body ">
            <% if (request.getAttribute("mensagem") != null) {
                    out.print(new Mensagem(String.valueOf(request.getAttribute("tipo")), String.valueOf(request.getAttribute("mensagem"))));
                }
            %>
            <form id="form-produto" action="./efetuarPedidos" method="post">

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <label>Nome Fornecedor</label>

                            <%
                                ProdutoDAO produtoDao = new ProdutoDAO();
                                Produto produto = produtoDao.buscaProduto(3);
                                UsuarioDAO usuario = new UsuarioDAO();
                                Usuario usu = null;
                                ArrayList<Usuario> usuarios = null;
                                try {
                                    usuarios = (ArrayList<Usuario>) usuario.buscaUsuPeloId(produto.getIdUsuario());
                                    for (Usuario e : usuarios) {
                                      out.print("<input class='form-control' type='text' id='nomeFornecedor' name='nomeFornecedor' value''" + e.getNome() + ">");
                                    }
                                } catch (Exception e) {

                                }
                            %>

<!--                            <input class="form-control" type="text" id="nomeFornecedor" name="nomeFornecedor" value="" required="">-->
                        </div>
                    </div>
                </div>
<!--                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label >Nome do Produto</label>
                            <input class="form-control" type="text" id="nomeProduto" name="nomeProduto" required="">
                        </div>
                    </div>
                </div>-->
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label>Quantidade</label>
                            <input class="form-control" type="text" id="quantidade" name="quantidade" placeholder="Informe a quantidade do pedido">
                        </div>
                    </div>
                </div>
                <button type="submit" id="acao" name="acao" value="efetuar" class="btn btn-success btn-block">Efetuar Pedido</button>
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