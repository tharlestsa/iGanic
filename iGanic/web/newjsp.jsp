<%-- 
    Document   : newjsp
    Created on : 04/12/2017, 19:10:28
    Author     : guilherme
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="form-uploadimagem" action ="./newServlet"  method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="form-row">
                    <div class="col-md-12">
                        <input type ="file" name ="file"/>
                        <input type="hidden" name="idProduto" value="idProduto"/> 
                        <button type="submit" id="acao" name="acao" value="enviar" class="btn btn-success btn-block">Enviar</button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
