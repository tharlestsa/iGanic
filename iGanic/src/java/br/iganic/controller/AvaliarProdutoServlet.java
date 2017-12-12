/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.AvaliarProdutoDAO;
import br.iganic.dao.ProdutoDAO;
import br.iganic.model.AvaliarProduto;
import br.iganic.model.Produto;
import br.iganic.util.Sessao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author guilherme
 */
@WebServlet(name = "AvaliarProdutoServlet", urlPatterns = {"/avaliarProduto"})
public class AvaliarProdutoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");

        Sessao.trataSessao(request, response);
        switch (acao) {
            case "avaliar":

                avaliar(request, response);
                break;
        }

    }

    public void avaliar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nota = Integer.parseInt(request.getParameter("nota"));
        String comentario = request.getParameter("comentario");
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

        AvaliarProdutoDAO avaliarDAO = new AvaliarProdutoDAO();
        AvaliarProduto avaliar = new AvaliarProduto(nota, comentario, idProduto);

        try {
            try {
                avaliarDAO.salvarAvaliacao(avaliar);
                request.setAttribute("tipo", "suce");
                request.setAttribute("mensagem", "Avaliaçao Realizada!");
                

            } catch (Exception ex) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Nao foi possivel avaliar esse produto!!");

            }

            request.getRequestDispatcher("./principal.jsp").forward(request, response);

        } catch (Exception ex) {
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
