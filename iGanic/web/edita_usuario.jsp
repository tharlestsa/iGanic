<%-- 
    Document   : edita_usuario
    Created on : 03/12/2017, 23:08:10
    Author     : tharles
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="br.iganic.dao.UsuarioDAO"%>
<%@page import="br.iganic.view.Mensagem"%>
<%@page import="br.iganic.model.Estado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.EstadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="Edição dos Dados do Usuário" />
</jsp:include>

<div class="container margem">
    <div class="card mx-auto mt-5">
        <div class="card-header" style='font-weight:bold;' >Cadastro de Produto</div>
        <div class="card-body">
            <% if (request.getAttribute("mensagem") != null) {
                    out.print(new Mensagem(request.getAttribute("tipo").toString(), String.valueOf(request.getAttribute("mensagem"))));
                }
            %>
            <form  id="form-usuario" action="./usuario" method="POST" >
                <%
                    UsuarioDAO usu = new UsuarioDAO();
                    ArrayList<Usuario> usuarios = new ArrayList<>();
                    Usuario usuarioModel = null;
                    try {
                        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

                        usuarios = (ArrayList<Usuario>) usu.buscaUsuPeloId(new Usuario(idUsuario));

                        for (Usuario u : usuarios) {
                            usuarioModel = u;
                        }

                        System.out.println("\n\n\n" + usuarioModel.toString() + "\n\n\n");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                %>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-8">
                            <label for="inputNome">Nome</label>
                            <input class="form-control" id="nome" name="nome" type="text" value="<% out.print(usuarioModel.getNome()); %>"  placeholder="Informe seu nome completo">
                        </div>
                        <div class="col-md-4">
                            <label for="inputCpf">CPF</label>
                            <input class="form-control" id="cpf" name="cpf" type="text" value="<% out.print(usuarioModel.getCpf()); %>" readonly="" placeholder="Informe o seu CPF">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-8">
                            <label for="inputEmail">E-mail</label>
                            <input class="form-control" id="email" name="email" type="email"  value="<% out.print(usuarioModel.getEmail()); %>" placeholder="Informe seu o E-mail">
                        </div>
                        <div class="col-md-4">
                            <label for="inputCel">Celular</label>
                            <input class="form-control" id="cel" name="cel" type="tel" value="<%out.print(usuarioModel.getCel());; %>" placeholder="nº de celular">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-4">
                            <label for="inputCep">CEP</label>
                            <input class="form-control" id="cep" name="cep" type="text" value="" placeholder="Informe o CEP" data-toggle="cep" title="">
                        </div>
                        <div class="col-md-6">
                            <label for="inputRua">Rua</label>
                            <input class="form-control" id="rua" name="rua" type="text" placeholder="Informe a rua de sua residência">
                        </div>
                        <div class="col-md-2">
                            <label for="inputNumero">nº</label>
                            <input class="form-control" id="numero" name="numero" type="text" placeholder="Número">
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="inputComp">Complemento</label>
                            <input class="form-control" id="comp" name="comp" type="text" placeholder="Complemento">
                        </div>
                        <div class="col-md-6">
                            <label for="inputBairro">Bairro</label>
                            <input class="form-control" id="bairro" name="bairro" type="text" placeholder="Informe o seu bairro">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="inputEstado">Estado</label>
                            <select class="form-control" id="estado" name="estado" onchange="buscarCidadesDoEstado()">
                                <option value="" >Informe o Estado</option>
                                <%
                                    EstadoDAO estDao = new EstadoDAO();
                                    ArrayList<Estado> estados = null;
                                    try {
                                        estados = (ArrayList<Estado>) estDao.listaTodos();
                                        for (Estado e : estados) {
                                            out.print("<option value='" + e.getIdEstado() + "' >" + e.getUf() + "</option>");
                                        }
                                    } catch (Exception e) {

                                    }
                                %>

                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="inputCidade">Cidade</label>
                            <select class="form-control" id="cidade" name="cidade" >
                                <option value="" >Informe a Cidade</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="inputTipo">Tipo da Conta</label>
                            <select class="form-control" id="tipo" name="tipo" disabled="">
                                <option value="-1" >Informe o tipo da conta do usuário</option>
                                <c:choose>
                                    <c:when test="${sessionScope.tipoUsuario == 'C'}">
                                        <option value='C' selected="">Cliente</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value='F' selected="">Fornecedor</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="inputUsuario">Usuário</label>
                            <input class="form-control" id="usuario" name="usuario" type="text" value="<%out.print(usuarioModel.getUsuario());%>" placeholder="Informe o usuário acesso ao login">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="inputSenha">Senha</label>
                            <input class="form-control" id="senha" name="senha"  type="password" placeholder="Informe a senha acesso">
                        </div>
                        <div class="col-md-6">
                            <label for="confirmSenha">Confirme a senha</label>
                            <input class="form-control" id="confirmSenha" name="confirmSenha" type="password" placeholder="Informe a senha novamente">
                        </div>
                    </div>
                </div>
                <input class="form-control" id="lat" name="lat" type="hidden">
                <input class="form-control" id="lng" name="lng" type="hidden">
                <button type="submit" id="acao" name="acao" value="editar" class="btn btn-success btn-block">Salvar</button>
            </form>

        </div>
    </div>
</div>
<jsp:include page="./base_Jsp/rodape.jsp" />

