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
public class Label {
    private String texto;
    private String atributoExtra = "";

    public Label(String texto) {
        this.texto = texto;
    }
    
    public Label (){}

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAtributoExtra() {
        return atributoExtra;
    }

    public void setAtributoExtra(String atributoExtra) {
        this.atributoExtra = atributoExtra;
    }
    

    @Override
    public String toString() {
        String atributoExtra = ""; 
        
        if (this.atributoExtra.isEmpty()){
            //continua
        }else{
            atributoExtra = this.atributoExtra; 
        }
        return "<label "+atributoExtra+" >" + this.texto + "</label>";
    }

    
}
