/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.UsuarioDAO;
import br.iganic.model.Usuario;
import br.iganic.util.Sessao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private String usuario;
    private String senha;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF8");

        this.usuario = request.getParameter("usuario");
        this.senha = (String) request.getParameter("senha");

        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "";
        }
        if ("toLogin".equals(acao) || "entrar".equals(acao)) {
            //continua sem tratar a sessão, pois será direcinada para o login do usuário. 
        } else {
            Sessao.trataSessao(request, response);
        }

        switch (acao) {
            case "":
                if (Sessao.existeSessao(request)) {
                    request.getRequestDispatcher("/principal.jsp").forward(request, response);
                } else {
                    request.getSession().invalidate();
                    request.getRequestDispatcher("./index.jsp").forward(request, response);
                }

                break;

            case "entrar":
                this.autenticarUsuario(request, response);
                break;
            case "toLogin":
                request.getSession().invalidate();
                request.getRequestDispatcher("./index.jsp").forward(request, response);
                break;
            case "sair":
                request.getSession().invalidate();
                request.getRequestDispatcher("./index.jsp").forward(request, response);
                break;
            default:
                request.getSession().invalidate();
                request.getRequestDispatcher("./index.jsp").forward(request, response);

        }

    }

    private void autenticarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsuarioDAO usuDao = new UsuarioDAO();
        List<Usuario> usu;
        try {
            usu = usuDao.buscaUsuPeloUsuarioESenha(new Usuario(null, this.usuario, this.senha));

            if (usu.isEmpty()) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Usuario ou senha incorretos!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("idUsuario", usu.get(0).getIdUsuario());
            sessao.setAttribute("tipoUsuario", usu.get(0).getTipo());
            sessao.setAttribute("lat", usu.get(0).getLat());
            sessao.setAttribute("lng", usu.get(0).getLng());
            if (usu.get(0).getTipo().equals("C")) {
                request.getRequestDispatcher("/principal.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/pedidosFornecedor.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao autenticar: "+ ex.getMessage());
            request.setAttribute("tipo", "erro");
            request.setAttribute("mensagem", "Ao buscar os dados do usuário!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
