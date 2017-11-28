package br.iganic.util;

/**
 *
 * @author Tharles
 */
public class Button {

    private String tipo = null;
    private String value = null;
    private String name = null;
    private String id = null;
    private String classCss = "btn ";
    private Boolean disabled = false;
    private String nome = null;

    public Button() {

    }

    public Button(String tipo, String value, String name, String id, String nome) {
        this.tipo = tipo;
        this.value = value;
        this.name = name;
        this.id = id;
        this.nome = nome;
    }

    public Button(String tipo, String value, String name, String id, String nome, String classCss) {
        this.tipo = tipo;
        this.value = value;
        this.name = name;
        this.id = id;
        this.nome = nome;
        this.classCss += classCss;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassCss() {
        return classCss;
    }

    public void setClassCss(String classCss) {
        this.classCss = classCss;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        String disabled = "";
        if (this.disabled == true) {
            disabled = "disabled";
        }

        String input = "<button type='" + this.tipo + "' name='" + this.name
                + "' value='" + this.value + "' id='" + this.id + "' class='"+ this.classCss +"'" + disabled + ">" + this.nome + "</button>";
        return input;
    }

}
