package br.iganic.util;

/**
 *
 * @author tharles
 */
public enum TipoUsuario {
    C("Cliente"), F("Fornecedor");

    private final String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
