/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idContest = request.getParameter("idContest");
        String user = request.getParameter("usuario");
        String pwd = request.getParameter("pwd");
        String acao = request.getParameter("acao");

        CandidatoDAO candidatoDAO = new CandidatoDAO();

        try {

            if (acao == null) {
                request.getSession().invalidate();
                request.getRequestDispatcher("./index.jsp").forward(request, response);
            } else if (acao.equalsIgnoreCase("login")) {
                List<Candidato> candidato;
                candidato = candidatoDAO.procura(new Candidato(0, null, null, null, null, user, pwd, Integer.parseInt(idContest)));

                if (candidato.isEmpty()) {
                    request.setAttribute("mensagem", "Usuario ou senha incorretos!");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }

                HttpSession sessao = request.getSession(true);
                sessao.setAttribute("idUsuario", candidato.get(0).getIdCandidato());
                request.getRequestDispatcher("/home.jsp").forward(request, response);

            }
        } catch (Exception ex) {
            response.getWriter().print(ex);
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
