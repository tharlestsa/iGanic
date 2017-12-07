
package br.iganic.model;

/**
 *
 * @author tharles
 */
public class Fornecedor {
    private Usuario usuario; 
    private Produto produto; 
    private Imagem imagem; 

    public Fornecedor(Usuario usuario, Produto produto) {
        this.usuario = usuario;
        this.produto = produto;
    }
    
    public Fornecedor(Usuario usuario, Produto produto, Imagem imagem) {
        this.usuario = usuario;
        this.produto = produto;
        this.imagem = imagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "usuario=" + usuario + ", produto=" + produto + ", imagem=" + imagem + '}';
    }
    
    
    
}
