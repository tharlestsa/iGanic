/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.PedidoDAO;
import br.iganic.dao.ProdutoDAO;
import br.iganic.model.Pedido;
import br.iganic.model.PedidoCliente;
import br.iganic.model.Produto;
import br.iganic.view.Mensagem;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author rafael
 */
@WebServlet(name = "PedidosCliente", urlPatterns = {"/pedidosCliente"})
public class PedidosClienteServlet extends HttpServlet {

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

        PedidoDAO dao = new PedidoDAO();
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

        String acao = request.getParameter("action");

        ArrayList<PedidoCliente> pedidos = new ArrayList();

        if (acao == null) {
            acao = "listar";
        }

        if (acao.equals("alt")) {
            String idPedido = request.getParameter("idPedido");
            ProdutoDAO produtoDAO = new ProdutoDAO();

            try {
                System.out.println(idPedido);
                PedidoCliente p = dao.buscaPedido(Integer.parseInt(idPedido));
                Produto produto = produtoDAO.buscaProduto(Integer.parseInt(p.getIdProduto()));

                produto.setQuantidade(produto.getQuantidade() + Double.parseDouble(p.getQtd()));
                produtoDAO.atualizar(produto);

                Pedido ped = new Pedido();
                ped.setIdPedido(Integer.parseInt(p.getIdPedido()));
                ped.setStatus("C");
                dao.atualizar(ped);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        //guilherme
        if (acao.equals("avaliar")) {
            String idPedido = request.getParameter("idPedido");
            ProdutoDAO produtoDAO = new ProdutoDAO();

            try {
                PedidoCliente p = dao.buscaPedido(Integer.parseInt(idPedido));
                Produto produto = produtoDAO.buscaProduto(Integer.parseInt(p.getIdProduto()));

                request.setAttribute("idProduto", produto.getIdProduto());
                request.getRequestDispatcher("/avaliarProduto.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            pedidos = dao.buscaPedidosDoCliente(idUsuario);

        } catch (Exception e) {

        }

        if (pedidos.isEmpty()) {
            request.setAttribute("mensagem", new Mensagem("info", "Nenhum pedido foi encontrado!"));
        }
        request.setAttribute("pedidos", pedidos);
        request.getRequestDispatcher("./pedidosCliente.jsp").forward(request, response);
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
