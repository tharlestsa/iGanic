package br.iganic.model;

import java.util.List;

/**
 *
 * @author tharles
 */
public class Usuario {

    private Integer idUsuario;
    private String nome;
    private String cpf;
    private String cel;
    private String email;
    private String endereco;
    private String lat;
    private String lng;
    private String tipo;
    private String usuario;
    private String senha;
    private Integer idCidade;

    public Usuario(String cpf, String usuario, String senha) {
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha; 
    }

    public Usuario(String nome, String cpf, String cel, String email, String endereco, String lat, String lng, String tipo, String usuario, String senha, Integer idCidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.cel = cel;
        this.email = email;
        this.endereco = endereco;
        this.lat = lat;
        this.lng = lng;
        this.tipo = tipo;
        this.usuario = usuario;
        this.senha = senha;
        this.idCidade = idCidade;
    }

    public Usuario(Integer idUsuario, String nome, String cpf, String cel, String email, String endereco, String lat, String lng, String tipo, String usuario, String senha, Integer idCidade) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.cel = cel;
        this.email = email;
        this.endereco = endereco;
        this.lat = lat;
        this.lng = lng;
        this.tipo = tipo;
        this.usuario = usuario;
        this.senha = senha;
        this.idCidade = idCidade;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

}
