package br.iganic.view;

/**
 *
 * @author tharles
 */
public class Mensagem {

    private String tipo;
    private String mensagem;

    public Mensagem(String tipo, String mensagem) {
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        String alerta = "";

        switch (this.tipo) {

            case "suce":
                alerta = "<div class=\"alert alert-success\">\n"
                        + "  <strong>Sucesso!</strong> " + this.mensagem + "\n"
                        + "</div>";
                break;

            case "info":
                alerta = "<div class=\"alert alert-info\">\n"
                        + "  <strong>Informação!</strong> " + this.mensagem + "\n"
                        + "</div>";
                break;

            case "aten":
                alerta = "<div class=\"alert alert-warning\">\n"
                        + "  <strong>Atenção!</strong> " + this.mensagem + "\n"
                        + "</div>";
                break;

            case "erro":
                alerta = "<div class=\"alert alert-danger\">\n"
                        + "  <strong>Erro!</strong> "+ this.mensagem +"\n"
                        + "</div>";
                break;
        }

        return alerta;
    }

}
