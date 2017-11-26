
package br.iganic.model;

/**
 *
 * @author tharles
 */
public class Cidade {
    private Integer idCidade; 
    private String nome; 
    private Integer idEstado; 

    public Cidade(Integer idCidade, String nome, Integer idEstado) {
        this.idCidade = idCidade;
        this.nome = nome;
        this.idEstado = idEstado;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
    
    
    
}
