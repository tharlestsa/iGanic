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

    public static boolean existeSessao(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return session.getAttribute("idUsuario") != null;
    }

    public static void trataSessao(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (!existeSessao(request)) {
                request.getRequestDispatcher("./index.jsp").forward(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void trataSessaoAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession();

        if (session.getAttribute("idAdministrador") == null) {
            try {
                request.getRequestDispatcher("./adm/index.jsp").forward(request, response);
            } catch (IOException ex) {
                Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
