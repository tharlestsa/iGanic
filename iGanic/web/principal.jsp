<%-- 
    Document   : principal
    Created on : 29/11/2017, 19:43:05
    Author     : tharles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./base_Jsp/cabecalho.jsp">
    <jsp:param name="titulo" value="P&aacute;gina Principal" />
</jsp:include>


<!-- Seção de Promoções -->
<section id="promocao">
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
            <div class="carousel-item active img-carousel">
                <img w="34px"class="d-block img-fluid" src="./img_produtos/01.jpeg"/>
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

</section>
<!--/Seção de Promoções--> 
<div id="cont" class="container">
    <!--Produtos mais proxóximos usuarios--> 
    <section id="produto-proximo" class="container">
        <div class="card-columns">
            <!--Example Social Card-->
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/01.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">David Miller</a></h6>
                    <p class="card-text small">These waves are looking pretty good today!
                        <a href="#">#surfsup</a>
                    </p>
                </div>
            </div>
            <!--Example Social Card-->
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/02.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">John Smith</a></h6>
                    <p class="card-text small">Another day at the office...
                        <a href="#">#workinghardorhardlyworking</a>
                    </p>
                </div>
            </div>
            <!--Example Social Card-->
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/03.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/04.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/05.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/06.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/07.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/08.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
            <div class="card mb-3">
                <a href="#">
                    <img class="card-img-top img-fluid w-100" src="./img_produtos/09.jpeg" alt="">
                </a>
                <div class="card-body">
                    <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                    <p class="card-text small">Nice shot from the skate park!
                        <a href="#">#kickflip</a>
                        <a href="#">#holdmybeer</a>
                        <a href="#">#igotthis</a>
                    </p>
                </div>
                <hr class="my-0">
            </div>
        </div>

    </section>
    <!--//Produtos mais proxóximos usuarios--> 
</div>

<jsp:include page="./base_Jsp/rodape.jsp" />

