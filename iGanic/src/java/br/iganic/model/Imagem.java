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
public class Imagem {
    private int idImagem;
    private String nome;
    private int idProduto;

    public Imagem(int idProduto) {
        this.idProduto = idProduto;
    }

    
    public Imagem(String nome, int idProduto) {
        this.nome = nome;
        this.idProduto = idProduto;
    }

    public Imagem(int idImagem, String nome, int idProduto) {
        this.idImagem = idImagem;
        this.nome = nome;
        this.idProduto = idProduto;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "Imagem{" + "idImagem=" + idImagem + ", nome=" + nome + ", idProduto=" + idProduto + '}';
    }
    
}
