<%-- 
    Document   : cabecalho
    Created on : 23/11/2017, 21:16:21
    Author     : tharles
--%>

<%@page import="br.iganic.util.Sessao"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="br.iganic.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>iGanic - Página Principal</title>
        <!-- Bootstrap core CSS-->
        <link href="./template/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom fonts for this template-->
        <link href="./template/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Page level plugin CSS-->
        <link href="./template/admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template-->
        <link href="./template/admin/css/sb-admin.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
    Sessao.trataSessao(request, response);
    %>
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <!--<a class="navbar-brand" href="index.html" style="color: #ff6200; font-weight: bold;">iGanic</a>-->
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <% if (session.getAttribute("tipoUsuario").equals("C")) {%>
                <jsp:include page="./area_cliente.jsp" />
                <% } else {%>
                <jsp:include page="./area_fornecedor.jsp" />
                <% }%>

                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>

                <% if (session.getAttribute("tipoUsuario").equals("C")) {%>
                <ul class="navbar-nav ml-sm-auto" >
                    <li id="busca-produto" class="nav-item" >
                        <form class="form-inline" action="./busca_produtos.jsp" method="POST">
                            <div class="input-group input-group-md">
                                <input class="form-control" type="text" placeholder="Digite o nome do alimento...">
                                <span class="input-group-btn">
                                    <button class="btn btn-success" name="acao" value="pesquisar" type="submit">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                        </form>
                    </li> 
                </ul>
                <% } %>
                <ul class="navbar-nav ml-auto" >
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                            <i class="fa fa-user-circle-o"></i>
                            <%
                                UsuarioDAO usu = new UsuarioDAO();
                                ArrayList<Usuario> usuarios = new ArrayList<>();
                                Usuario usuarioModel = null;
                                try {
                                    int idUsuario = (int) request.getSession().getAttribute("idUsuario");
                                    usuarios = (ArrayList<Usuario>) usu.buscaUsuPeloId(new Usuario(idUsuario));

                                    for (Usuario u : usuarios) {
                                        usuarioModel = u;
                                        out.print("<strong>" + u.getUsuario() + "</strong>");
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            %>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="">
                                <%
                                    out.print("<strong>" + usuarioModel.getNome() + "</strong>");
                                    out.print("<div class='dropdown-message small'>Cel: " + usuarioModel.getCel() + "</div>");
                                    out.print("<div class='dropdown-message small'>E-mail: " + usuarioModel.getEmail() + "</div>");
                                    out.print("<div class='dropdown-message small'>Geolocalização: " + usuarioModel.getLat() + " " + usuarioModel.getLng() + "</div>");
                                %>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <form action="./edita_usuario.jsp" method="POST"> <button class="btn btn-success" name="acao" value="editar_usuario" type="submit">Editar</button></form>
                            </a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                            <i class="fa fa-fw fa-sign-out"></i>Sair</a>
                    </li>
                </ul>

            </div>
        </nav>
        <!-- Logout Modal-->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Tem certeza que deseja sair?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Selecione "Sair" somente se estiver pronto para finalizar essa sessão.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                        <form action="./index.jsp" method="POST"> <button class="btn btn-success" name="acao" value="sair" type="submit">Sair</button></form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Logout Modal-->
        <div class="content-wrapper" style="background-image: url('./img/img_login/organicos_3.jpeg');background-repeat: no-repeat;background-position: center;">
            <div class="container-fluid" style="opacity: 0.85;">

