<%-- 
   Document   : cadastra_produto
   Created on : 24/11/2017, 18:58:35
   Author     : guilherme
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="br.iganic.dao.ProdutoDAO"%>
<%@page import="br.iganic.model.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.iganic.view.Mensagem"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Cadastro de Produtos" />
</jsp:include>

<div class="container">
    <div class="card mx-auto mt-5">
        <div class="card-header" style='font-weight:bold;' >Cadastro de Produto</div>
        <div class="card-body ">
            <% if (request.getAttribute("mensagem") != null) {
                    out.print(new Mensagem(String.valueOf(request.getAttribute("tipo")), String.valueOf(request.getAttribute("mensagem"))));
                }
            %>
            <form id="form-produto" action="./cadastraProdutos" method="post">

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <label>Nome</label>
                            <input class="form-control" type="text" id="nome" name="nome" placeholder="Informe o nome do produto" required="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label >Unidade</label>
                            <select class="form-control " id="unidade" name="unidade" required="">
                                <option value="g">Grama</option>
                                <option value="kg">Quilograma</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label>Preço</label>
                            <input class="form-control" data-affixes-stay="true" data-thousands="." data-decimal="," type="text" id="preco" name="preco" placeholder="Informe o preço do produto">
                        </div>
                        <div class="col-md-4">
                            <label>Quantidade</label>
                            <input class="form-control" data-affixes-stay="true" data-thousands="." data-decimal=","  type="text" id="quantidade" name="quantidade" placeholder="Informe a quantidade do produto">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <label>Modo de Produçao</label>
                            <textarea class="form-control" rows="6" type="text" id="modo" name="modo" placeholder="Informe o modo de produçao do produto" required=""></textarea>
                        </div>
                    </div>
                </div>
<!--                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label >Produtos</label>
                            <select class="form-control " id="unidade" name="unidade" required="">
                                <%
//                                    ArrayList<Produto> produtos = new ArrayList();
//                                    ProdutoDAO produtoDAO = new ProdutoDAO();
//                                    Produto produto;
//                                    produtos = produtoDAO.buscaProdutosDosFornecedores(1);
//                                    for (Produto prod : produtos) {
//                                        produto = (Produto) prod;
//                                        out.println("<option value " + produto.getNome() + ">" + "<option>");
//                                    }
                                %> 

                            </select>
                        </div>
                    </div>
                </div>-->
                <div class="col-md-12">
                    <input type="hidden" name="id-produto" value="id-produto"/> 
                </div>
                <button type="submit" id="acao" name="acao" value="cadastrar" class="btn btn-success btn-block">Cadastrar</button>
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
