/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.PedidoDAO;
import br.iganic.dao.ProdutoDAO;
import br.iganic.model.Pedidoo;
import br.iganic.model.Produto;
import br.iganic.util.Sessao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart.Data;
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
@WebServlet(name = "EfetuarPedidoServlet", urlPatterns = {"/efetuarPedidos"})
public class EfetuarPedidoServlet extends HttpServlet {

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
        Sessao.trataSessao(request, response);

        String acao = request.getParameter("acao");

        if ("pedir".equals(acao)) {
            String idProduto = (request.getParameter("idProduto") != null) ? request.getParameter("idProduto").toLowerCase() : "";
            request.setAttribute("idProduto", idProduto);
            request.getRequestDispatcher("/pedidos.jsp").forward(request, response);

        } else if ("efetuar".equals(acao)) {
            String status = "A";

            String idProduto = (request.getParameter("idProduto") != null) ? request.getParameter("idProduto").toLowerCase() : "";

            if (idProduto.isEmpty()) {
                request.setAttribute("erro", "aten");
                request.setAttribute("mensagem", "Nao foi possivel realizar esse pedido!!");
                request.getRequestDispatcher("/principal.jsp").forward(request, response);
            } else {

                PedidoDAO pedidoDao = new PedidoDAO();
                LocalDateTime data = LocalDateTime.now();
                String qtde = request.getParameter("quantidade").replace(",", "."); 
                Float quantidade = Float.parseFloat(qtde);

                int idUsuario = (int) request.getSession().getAttribute("idUsuario");

                Pedidoo pedido = new Pedidoo(data, quantidade, status, idUsuario, Integer.parseInt(idProduto));

                try {

                    try {
                        ProdutoDAO produtoDAO = new ProdutoDAO();
                        Produto prod = produtoDAO.buscaProduto(Integer.parseInt(idProduto));
                        if (quantidade > prod.getQuantidade()) {
                            request.setAttribute("tipo", "aten");
                            request.setAttribute("mensagem", "O fornecedor não tem essa quantidade de produto disponivel!!");
                            request.getRequestDispatcher("/pedidos.jsp").forward(request, response);
                            return;
                        }

                        pedidoDao.salvarPedido(pedido);
                        prod.setQuantidade(prod.getQuantidade() - quantidade);
                        produtoDAO.atualizar(prod);
                        request.setAttribute("tipo", "suce");
                        request.setAttribute("mensagem", "Pedido realizado!");
                        request.getRequestDispatcher("/principal.jsp").forward(request, response);

                    } catch (Exception ex) {
                        request.setAttribute("tipo", "erro");
                        request.setAttribute("mensagem", "Nao foi possivel realizar esse pedido!!");
                        request.getRequestDispatcher("/principal.jsp").forward(request, response);
                    }

                } catch (Exception ex) {
                    response.getWriter().println(ex.getMessage());
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            request.getRequestDispatcher("/principal.jsp").forward(request, response);

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
