<%-- 
    Document   : principal
    Created on : 29/11/2017, 19:43:05
    Author     : tharles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp" />

<!-- Seção de Promoções -->
<section id="promocao">
    <div class="breadcrumb">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="6"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="7"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="8"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="9"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="carousel-item active">
                    <img class="d-block img-fluid" src="./img_produtos/01.jpeg"/>
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/02.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/03.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/04.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/05.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/06.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/07.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/08.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/09.jpeg" >
                </div>
                <div class="carousel-item">
                    <img class="d-block img-fluid" src="./img_produtos/10.jpeg" >
                </div>
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
    </div>
</section>
<!-- /Seção de Promoções -->

<jsp:include page="./base_Jsp/rodape.jsp" />

