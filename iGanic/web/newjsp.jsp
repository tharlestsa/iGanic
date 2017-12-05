<%-- 
    Document   : newjsp
    Created on : 04/12/2017, 19:10:28
    Author     : guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action ="./newServlet"  method="post" enctype="multipart/form-data">
            <input type ="file" name ="file"/>
            <input type="submit"  value="Enviar"/>
        </form>
    </body>
</html>
