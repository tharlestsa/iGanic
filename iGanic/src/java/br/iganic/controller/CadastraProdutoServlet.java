/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.ProdutoDAO;
import br.iganic.model.Pedido;
import br.iganic.model.Produto;
import br.iganic.util.Sessao;
import br.iganic.util.Upload;
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
@WebServlet(name = "CadastraProdutoServlet", urlPatterns = {"/cadastraProdutos"})
public class CadastraProdutoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @javax.servlet.annotation.MultipartConfig;
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");

        Sessao.trataSessao(request, response);
        switch (acao) {
            case "cadastrar":
                cadastrarProduto(request, response);
                break;
            case "editar":
                editarProduto(request, response);
                break;
        }

    }

    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String unidade = request.getParameter("unidade");
        String precoS = request.getParameter("preco");
        String quantidadeS = request.getParameter("quantidade");

        precoS = precoS.replace(".", "'");
        precoS = precoS.replace(",", ".");
        precoS = precoS.replace("'", ".");

        quantidadeS = quantidadeS.replace(".", "'");
        quantidadeS = quantidadeS.replace(",", ".");
        quantidadeS = quantidadeS.replace("'", ".");

        Double preco = Double.parseDouble(precoS);
        Double quantidade = Double.parseDouble(quantidadeS);

        String modo = request.getParameter("modo");

        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

        ProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = new Produto(nome, unidade, preco, quantidade, modo, idUsuario);
        try {

            if (nome.equals("") || modo.equals("")) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Preencha todos os campos!");
                request.getRequestDispatcher("/cadastra_produto.jsp").forward(request, response);

                return;
            }

            try {
                int idProdu = produtoDAO.salvarProduto(produto);

                request.setAttribute("idProduto", idProdu);
                request.setAttribute("tipo", "suce");
                request.setAttribute("mensagem", "Produto Cadastrado!");
                request.getRequestDispatcher("/newjsp.jsp").forward(request, response);

            } catch (Exception ex) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Nao foi possivel cadastrar esse produto!!");
                request.getRequestDispatcher("/cadastra_produto.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String unidade = request.getParameter("unidade");
        String precoS = request.getParameter("preco");
        String quantidadeS = request.getParameter("quantidade");

        precoS = precoS.replace(".", "'");
        precoS = precoS.replace(",", ".");
        precoS = precoS.replace("'", ".");

        quantidadeS = quantidadeS.replace(".", "'");
        quantidadeS = quantidadeS.replace(",", ".");
        quantidadeS = quantidadeS.replace("'", ".");

        Double preco = Double.parseDouble(precoS);
        Double quantidade = Double.parseDouble(quantidadeS);

        String modo = request.getParameter("modo");
        int idProd = Integer.parseInt(request.getParameter("idProduto"));
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

        ProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = new Produto(idProd, nome, unidade, preco, quantidade, modo, idUsuario);
        try {

            if (nome.equals("") || modo.equals("")) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Preencha todos os campos!");
                request.getRequestDispatcher("/edita_produto.jsp").forward(request, response);

                return;
            }

            try {
                produtoDAO.alterarProduto(produto);
                request.setAttribute("idProduto", produto.getIdProduto());
                request.setAttribute("tipo", "suce");
                request.setAttribute("mensagem", "Produto Alterado!");
                request.getRequestDispatcher("/newjsp.jsp").forward(request, response);

            } catch (Exception ex) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Nao foi possivel editar esse produto!!");
                request.getRequestDispatcher("/cadastra_produto.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
