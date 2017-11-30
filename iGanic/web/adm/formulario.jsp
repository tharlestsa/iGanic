
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <form class="cmxform" id="commentForm" method="get" action="">
            <fieldset>
                <legend>Please provide your name, email address (won't be published) and a comment</legend>
                <p>
                <label for="cname">Name (required, at least 2 characters)</label>
                <input id="cname" name="cname" type="text">
                </p>
                <p>
                <label for="cemail">E-Mail (required)</label>
                <input id="cemail" type="cemail" name="email">
                </p>
                <p>
                <label for="curl">URL (optional)</label>
                </p>
                <p>
                <label for="ccomment">Your comment (required)</label>
                <textarea id="ccomment" name="ccomment"></textarea>
                </p>
                <p>
                <input class="submit" type="submit" value="Submit">
                </p>
            </fieldset>
        </form>
        
        <script src="../jquery/jquery.js" type="text/javascript"></script>
        <script src="../jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script>
            $().ready(function() {
		// validate the comment form when it is submitted
		$("#commentForm").validate({
                    rules:{
                        cname : {
                            required: true,
                            minlength: 10,
                            remote: "../servletValidador?cmd=validaNome"
                        }
                    },
                    messages:{
                        cname: {
                            required: "Preencha este campo, cavalo!!",
                            minlength: "Não acredito que seu nome seja só isso!",
                            remote: "Nome inválido!"
                        }
                    }
                });
            });  
                
        </script>
    </body>
</html>
