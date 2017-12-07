/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.scene.chart.PieChart;

/**
 *
 * @author guilherme
 */
public class Pedidoo {

    private int idPedido;
    private LocalDateTime data;
    private Float quantidade;
    private String status;
    private int idUsuario;
    private int idProduto;

    public Pedidoo() {
    }

    public Pedidoo(int idPedido, LocalDateTime data, Float quantidade, String status, int idUsuario, int idProduto) {
        this.idPedido = idPedido;
        this.data = data;
        this.quantidade = quantidade;
        this.status = status;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
    }

    public Pedidoo(LocalDateTime data, Float quantidade, String status, int idUsuario, int idProduto) {
        this.data = data;
        this.quantidade = quantidade;
        this.status = status;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    
}
