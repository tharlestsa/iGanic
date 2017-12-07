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
public class Produto {

    private int idProduto;
    private String nome;
    private String unidade;
    private Double preco;
    private Double quantidade;
    private String modoProducao;
    private int idUsuario;

    public Produto(String nome, String unidade, Double preco, Double quantidade, String modoProducao, int idUsuario) {
        this.nome = nome;
        this.unidade = unidade;
        this.preco = preco;
        this.quantidade = quantidade;
        this.modoProducao = modoProducao;
        this.idUsuario = idUsuario;
    }

    
    
    public Produto(int idProduto, String nome, String unidade, Double preco, Double quantidade, String modoProducao, int idUsuario) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.unidade = unidade;
        this.preco = preco;
        this.quantidade = quantidade;
        this.modoProducao = modoProducao;
        this.idUsuario = idUsuario;
    }
    
    public Produto(){}

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getModoProducao() {
        return modoProducao;
    }

    public void setModoProducao(String modoProducao) {
        this.modoProducao = modoProducao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Produto{" + "idProduto=" + idProduto + ", nome=" + nome + ", unidade=" + unidade + ", preco=" + preco + ", quantidade=" + quantidade + ", modoProducao=" + modoProducao + ", idUsuario=" + idUsuario + '}';
    }

}
