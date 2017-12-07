/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.util;

import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class Tabela {

    protected ArrayList<String> colunas;
    protected ArrayList<String> linhas;
    private final String nomeDaTabela;
    private String id ="";

    public Tabela(String nome) {
        this.colunas = new ArrayList();
        linhas = new ArrayList();
        this.nomeDaTabela = nome;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public Tabela(String nome, String[] colunas) {
        this.colunas = new ArrayList();
        this.linhas = new ArrayList();
        this.setColunas(colunas);
        this.nomeDaTabela = nome;
    }

    public void addColuna(String coluna) {
        this.colunas.add(coluna);
    }

    protected String montaColunas() {

        String colunas = "<thead>\n"
                + "      <tr>\n";

        for (String coluna : this.colunas) {
            colunas += "<th  class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"dataTable\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Name\" style=\"width: 115px;\">" + coluna + "</th>";
        }

        colunas += "   </tr>\n"
                + "  </thead>";

        return colunas;
    }

    public void setColunas(String[] c) {
        this.colunas = new ArrayList<>();

        for (String coluna : c) {
            this.colunas.add(coluna);
        }
    }

    public void addLinha(String[] linha) {

        String linhaDaTabela;

        linhaDaTabela = "<tr>";

        for (String l : linha) {
            linhaDaTabela += "<td>" + l + "</td>";
        }
        linhaDaTabela += "</tr>";

        this.linhas.add(linhaDaTabela);

    }

    @Override
    public String toString() {

        String tabela;

        tabela = "<div id='tabela-busca' class=\"card mb-3\">\n"
                + " <div class=\"card-header\">\n"
                + "     <i class=\"fa fa-table\"></i> "+ this.nomeDaTabela
                + " </div>\n"
                + " <div class=\"card-body\">\n"
                + "     <div class=\"table-responsive\">";

        tabela += "<table class=\"table table-bordered \" id=\""+ this.id + "\" width=\"100%\" cellspacing=\"0\" role=\"grid\" aria-describedby=\"dataTable_info\" style=\"width: 100%;\">";
       
        tabela += this.montaColunas();
        
        tabela += "<tbody>";

        for (String linha : this.linhas) {
            tabela += linha;
        }

        tabela += "</tbody>";
        tabela += "</table>";
        tabela += "</div>"
                + "</div>"
                + "</div>"
                + "</div>";

        return tabela;
    }

}
