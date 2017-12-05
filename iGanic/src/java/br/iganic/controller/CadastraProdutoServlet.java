/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.ProdutoDAO;
import br.iganic.model.Produto;
import br.iganic.util.Sessao;
import br.iganic.view.Mensagem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author guilherme
 */
@WebServlet(name = "CadastraProdutoServlet", urlPatterns = {"/produto"})
public class CadastraProdutoServlet extends HttpServlet {

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

        String nome = request.getParameter("descricao");
        String unidade = request.getParameter("unidade");
        Double preco = Double.parseDouble(request.getParameter("preco"));
        Double quantidade = Double.parseDouble(request.getParameter("quantidade"));
        String modo = request.getParameter("modo");

        Sessao.trataSessao(request, response);
        Integer idUsuario = Integer.parseInt((String) request.getSession().getAttribute("idUsuario"));

         ProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = new Produto(nome, unidade, preco, quantidade, modo, 1);

        if (acao == null) {
            return;
        }

        try {

            if (nome.equals("") || modo.equals("")) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Preencha todos os campos!");
                request.getRequestDispatcher("/cadastra_produto.jsp").forward(request, response);
                JOptionPane.showMessageDialog(null, "");
                return;
            }

            try {
                produtoDAO.salvarProduto(produto);
                JOptionPane.showMessageDialog(null, "Salvou");
                request.getRequestDispatcher("/cadastra_produto.jsp").forward(request, response);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "entrou no erro");
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Nao foi possivel cadastrar esse produto!!");
                request.getRequestDispatcher("/cadastra_usuario.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            response.getWriter().println(ex.getMessage());
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
