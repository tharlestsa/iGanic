/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafael
 */
public class Sessao {
    
    private static boolean existeSessao(HttpServletRequest request){
        HttpSession session = request.getSession();

        return session.getAttribute("idUsuario") != null;
    }
    
    public static void trataSessao(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        try {   
            
            if(!existeSessao(request)){
                request.getRequestDispatcher("./index.jsp").forward(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
