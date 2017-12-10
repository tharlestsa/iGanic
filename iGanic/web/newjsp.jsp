<%-- 
    Document   : newjsp
    Created on : 04/12/2017, 19:10:28
    Author     : guilherme
--%>

<%@page import="br.iganic.view.Mensagem"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Cadastro de Produtos" />
</jsp:include>

<div class="container">
    <div class="card mx-auto mt-5">
        <div class="card-header" style='font-weight:bold;' >Upload de imagem</div>
        <div class="card-body ">
            <% if (request.getAttribute("mensagem") != null) {
                    out.print(new Mensagem(String.valueOf(request.getAttribute("tipo")), String.valueOf(request.getAttribute("mensagem"))));
                }
            %>
            <form id="form-uploadimagem" action ="./newServlet"  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-12">
                            <input type ="file" name ="file"/>
                            <%
                                String idProduto = request.getAttribute("idProduto").toString();
                            %>
                            <input type="hidden" name="idProduto" value="<%= idProduto%>"/> <br>
                            <button type="submit" id="acao" name="acao" value="enviar" class="btn btn-success btn-block">Enviar</button>
                        </div>
                    </div>
                </div>
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

