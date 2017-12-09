/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.model;

/**
 *
 * @author guilherme
 */
public class AvaliarProduto {
    private int idAvaliacao;
    private int nota;
    private String comentario;
    private int idProduto;

    public AvaliarProduto() {
    }

    public AvaliarProduto(int idAvaliacao, int nota, String comentario, int idProduto) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.idProduto = idProduto;
    }

    public AvaliarProduto(int nota, String comentario, int idProduto) {
        this.nota = nota;
        this.comentario = comentario;
        this.idProduto = idProduto;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    
}
