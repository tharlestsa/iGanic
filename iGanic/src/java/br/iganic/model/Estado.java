
package br.iganic.model;

/**
 *
 * @author tharles
 */
public class Estado {
    
    private Integer idEstado; 
    private String nome; 
    private String uf; 

    public Estado(Integer idEstado, String nome, String uf) {
        this.idEstado = idEstado;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
}
