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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="./img/iganic_sem_fundo.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="./img/iganic_sem_fundo.ico" type="image/x-icon" />
        <title>iGanic - ${param.titulo}</title>
        <!-- Bootstrap core CSS-->
        <link rel="icon" href="MeuIcone.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="MeuIcone.ico" type="image/x-icon" />
        <link href="./template/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom fonts for this template-->
        <link href="./template/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Page level plugin CSS-->
        <link href="./template/admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template-->
        <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Abhaya+Libre" rel="stylesheet">
        <link href="./template/admin/css/sb-admin.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    </head>
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <div id="div-logo">
                <a id="logo" class="navbar-brand" href="./usuario?acao=">iGanic</a>
            </div>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <c:choose>

                    <c:when test="${sessionScope.tipoUsuario == 'C'}">
                        <jsp:include page="./area_cliente.jsp" />
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="./area_fornecedor.jsp"/>
                    </c:otherwise>
                </c:choose>

                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>
                <c:choose>
                    <c:when test="${sessionScope.tipoUsuario == 'C'}">
                        <ul class="navbar-nav ml-sm-auto" >
                            <li id="busca-produto" class="nav-item" >
                                <form class="form-inline" action="./buscaprodutos" method="POST">
                                    <div class="input-group input-group-md">
                                        <input class="form-control" type="text" id="buscaProduto" name="buscaProduto" placeholder="Digite o nome do alimento...">
                                        <span class="input-group-btn">
                                            <button class="btn btn-success" id="acao" name="acao" value="buscar" type="submit">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                                    </div>
                                </form>
                            </li> 
                        </ul>
                    </c:when>
                </c:choose>
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
                            <a class="dropdown-item" href="./usuario?acao=encEdicao">
                                <%
                                    out.print("<strong>" + usuarioModel.getNome() + "</strong>");
                                    out.print("<div class='dropdown-message small'>Cel: " + usuarioModel.getCel() + "</div>");
                                    out.print("<div class='dropdown-message small'>E-mail: " + usuarioModel.getEmail() + "</div>");
                                    out.print("<div class='dropdown-message small'>Geolocalização: " + usuarioModel.getLat() + " " + usuarioModel.getLng() + "</div>");
                                %>
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

        <!-- Modal -->
        <div class="modal fade" id="modal-pro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modal-prod-title"></h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div id="modal-prod-body" class="modal-body">
                        <div class="panel panel-default">
                            <h5 class="subtitulo">Fornecedor</h5><p>
                            <div id="fornecedor-modal" class="panel-body"></div><br>
                        </div>
                        <div class="panel panel-default">
                            <h5 class="subtitulo">Modo de Produção</h5><p>
                            <div id="modo-producao-modal" class="panel-body"></div><br>
                        </div
                        <div class="panel panel-default">
                            <h5 class="subtitulo">Endereço</h5><p>
                            <div id="endereco-modal" class="panel-body"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
                    <a href="./login?acao=sair" class="btn btn-success " role="button" aria-disabled="true">Sair</a>
                </div>
            </div>
        </div>
    </div>
    <!-- /Logout Modal-->
    <div id="background-tela" class="content-wrapper">
        <div class="container-fluid" style="opacity: 0.85;">

