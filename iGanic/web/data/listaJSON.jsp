<%-- 
    Document   : listaJSON
    Created on : 08/11/2017, 17:22:48
    Author     : tharles
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.io.StringWriter"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.iganic.model.Categoria"%>
<%@page import="org.json.simple.*"%>
<%
    JSONObject dados = new JSONObject();
    JSONParser parser = new JSONParser();
    String saida = "";
    String classe = (request.getAttribute("classe") != null) ? request.getAttribute("classe").toString() : "";
    ArrayList<Categoria> lista;
    if (classe.equalsIgnoreCase("categorias")) {
        lista = (ArrayList<Categoria>) request.getAttribute("lista-categorias");
        int i = 1;
        JSONObject pai = new JSONObject();
        JsonArray filhos = new JsonArray();
        for (Categoria cr : lista) {
            dados = new JSONObject();
            dados.put("idCategoria", cr.getIdCategoria().toString());
            dados.put("nome", cr.getNome().toString());

            filhos.add(dados);

//        saida+= dados.toJSONString();
//        if(i!=lista.size()) saida+=",";
//        i++;
        }
        pai.put("data", filhos);
        out.print(pai);
//    out.println("{");
//    out.println(saida);
//    out.println("}");
    }
%>
