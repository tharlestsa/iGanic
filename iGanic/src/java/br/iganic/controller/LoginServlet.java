/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.UsuarioDAO;
import br.iganic.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafael
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String acao = request.getParameter("acao");

        UsuarioDAO usuDao = new UsuarioDAO();

        try {

            if (acao == null) {
                request.getSession().invalidate();
                request.getRequestDispatcher("./index.jsp").forward(request, response);
            } else if (acao.equalsIgnoreCase("entrar")) {
                List<Usuario> usu = usuDao.buscaUsuPeloUsuario(new Usuario(null, usuario, senha));
                response.getWriter().print(senha.equals(usu.get(0).getUsuario()));
                if (usu.isEmpty()) {
                    request.setAttribute("mensagem", "Usuario ou senha incorretos!");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } else if (senha.equals( usu.get(0).getUsuario())) {
                    
                    HttpSession sessao = request.getSession(true);
                    sessao.setAttribute("idUsuario", usu.get(0).getIdUsuario());
                    request.getRequestDispatcher("/principal.jsp").forward(request, response);

                }

            }
        } catch (Exception ex) {

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
