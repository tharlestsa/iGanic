/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.util;

/**
 *
 * @author rafael
 */
public class Input {

    private String tipo = null;
    private String value = null;
    private String name = null;
    private String id = null;
    private String classCss = null;
    private String placeholder = null;
    private Label label = null;
    private String disabled = null;
     

    public Input() {
        this.disabled = "";
    }

    public Input(String tipo, String value, String name, String id, Label label, String disabled) {
        this.tipo = tipo;
        this.value = value;
        this.name = name;
        this.id = id;
        this.label = label;
        this.disabled = disabled;
        
    }
    public Input(String tipo, String value, String name, String id, Label label) {
        this.tipo = tipo;
        this.value = value;
        this.name = name;
        this.id = id;
        this.label = label;
        this.disabled = "";
        
    }
    

    public Input(String tipo, String value, String name, String id, String placeholder, Label label) {
        this.tipo = tipo;
        this.value = value;
        this.name = name;
        this.id = id;
        this.placeholder = placeholder;
        this.label = label;
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

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {

        String input = this.label +"<input type='" + this.tipo + "' name='" + this.name  
                + "' value='" + this.value + "' id='" + this.id +"' class='form-control' " +this.disabled + "></input>";

        return input;
    }

}