<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/bootstrap-yeti.min.css" rel="stylesheet" id="theme-file">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/prettify.min.css" rel="stylesheet">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/prettify-bootstrap.min.css" rel="stylesheet">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/font-awesome.min.css" rel="stylesheet">
        <link href="https://markcell.github.io/jquery-tabledit/assets/css/custom.css" rel="stylesheet">
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="table-responsive">
                        <table class="table table-striped table-bordered" id="example6">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Avatar</th>
                                <th class="tabledit-toolbar-column"></th></tr>
                            </thead>
                            <tbody>     
                                <tr id="1" class="danger">
                                    <td><span class="tabledit-span tabledit-identifier">1</span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="1" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">markcell</span><input class="tabledit-input form-control input-sm" type="text" name="username" value="markcell" style="display: none;" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">markcell@example.com</span><input class="tabledit-input form-control input-sm" type="text" name="email" value="markcell@example.com" style="display: none;" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">Iron Man</span><select class="tabledit-input form-control input-sm" name="avatar" style="display: none;" disabled=""><option value="1">Black Widow</option><option value="2">Captain America</option><option value="3" selected="">Iron Man</option></select></td>
                                </tr>
                                <tr id="2">
                                    <td><span class="tabledit-span tabledit-identifier">2</span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="2" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">dotz</span><input class="tabledit-input form-control input-sm" type="text" name="username" value="dotz" style="display: none;" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">dotz@example.com</span><input class="tabledit-input form-control input-sm" type="text" name="email" value="dotz@example.com" style="display: none;" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">Captain America</span><select class="tabledit-input form-control input-sm" name="avatar" style="display: none;" disabled=""><option value="1">Black Widow</option><option value="2" selected="">Captain America</option><option value="3">Iron Man</option></select></td>
                                </tr>
                                <tr id="3" class="danger">
                                    <td><span class="tabledit-span tabledit-identifier">3</span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="3" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">zikospeed</span><input class="tabledit-input form-control input-sm" type="text" name="username" value="zikospeed" style="display: none;" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">zikospeed@example.com</span><input class="tabledit-input form-control input-sm" type="text" name="email" value="zikospeed@example.com" style="display: none;" disabled=""></td>
                                    <td class="tabledit-view-mode"><span class="tabledit-span">Black Widow</span><select class="tabledit-input form-control input-sm" name="avatar" style="display: none;" disabled=""><option value="1" selected="">Black Widow</option><option value="2">Captain America</option><option value="3">Iron Man</option></select></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/jquery.min.js"></script>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/bootstrap.min.js"></script>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/prettify.min.js"></script>
        <script src="https://markcell.github.io/jquery-tabledit/assets/js/tabledit.min.js"></script>
        
        <script>
            $('#example6').Tabledit({
                url: 'example.php',
                columns: {
                    identifier: [0, 'id'],
                    editable: [[1, 'username'], [2, 'email'], [3, 'avatar', '{"1": "Black Widow", "2": "Captain America", "3": "Iron Man"}']]
                },
                onDraw: function() {
                    console.log('onDraw()');
                },
                onSuccess: function(data, textStatus, jqXHR) {
                    console.log('onSuccess(data, textStatus, jqXHR)');
                    console.log(data);
                    console.log(textStatus);
                    console.log(jqXHR);
                },
                onFail: function(jqXHR, textStatus, errorThrown) {
                    console.log('onFail(jqXHR, textStatus, errorThrown)');
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                },
                onAlways: function() {
                    console.log('onAlways()');
                },
                onAjax: function(action, serialize) {
                    console.log('onAjax(action, serialize)');
                    console.log(action);
                    console.log(serialize);
                }
            });
        </script>
    </body>
</html>
