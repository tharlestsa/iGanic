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
    private Double lat;
    private Double lng;
    private String rua;
    private String num;
    private String comp;
    private String bairro;
    private String cidade;
    private String uf;
    private String tipo;
    private String usuario;
    private String senha;

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;

    }

    public Usuario(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Usuario(String cpf, String usuario, String senha) {
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(String nome, String cpf, String cel, String email, Double lat, Double lng, String rua, String num, String comp, String bairro, String cidade, String uf, String tipo, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.cel = cel;
        this.email = email;
        this.lat = lat;
        this.lng = lng;
        this.rua = rua;
        this.num = num;
        this.comp = comp;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipo = tipo;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(Integer idUsuario, String nome, String cpf, String cel, String email, Double lat, Double lng, String rua, String num, String comp, String bairro, String cidade, String uf, String tipo, String usuario, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.cel = cel;
        this.email = email;
        this.lat = lat;
        this.lng = lng;
        this.rua = rua;
        this.num = num;
        this.comp = comp;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipo = tipo;
        this.usuario = usuario;
        this.senha = senha;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getRua() {
        if (this.rua == null) {
            return "";
        } else {
            return rua;
        }

    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNum() {
        if (num == null) {
            return "";
        } else {
            return num;
        }
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getComp() {
        if (comp == null) {
            return "";
        } else {
            return comp;
        }
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getBairro() {
        if (bairro == null) {
            return "";
        } else {
            return bairro;
        }
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        if (cidade == null) {
            return "";
        } else {
            return cidade;
        }
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        if (uf == null) {
            return "";
        } else {
            return uf;
        }
    }

    public void setUf(String uf) {
        this.uf = uf;
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

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nome=" + nome + ", cpf=" + cpf + ", cel=" + cel + ", email=" + email + ", lat=" + lat + ", lng=" + lng + ", rua=" + rua + ", num=" + num + ", comp=" + comp + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", tipo=" + tipo + ", usuario=" + usuario + ", senha=" + senha + '}';
    }

}
