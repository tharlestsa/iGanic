/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.PedidoDAO;
import br.iganic.model.Pedido;
import br.iganic.util.Sessao;
import br.iganic.view.Mensagem;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafael
 */
@WebServlet(name = "PedidosFornecedorServlet", urlPatterns = {"/pedidosFornecedor"})
public class PedidosFornecedorServlet extends HttpServlet {

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
        PedidoDAO dao = new PedidoDAO();
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

        String acao = request.getParameter("action");

        ArrayList<Pedido> pedidos = new ArrayList();

        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {
            case "listar":
                try {
                    pedidos = dao.buscaPedidosDoFornecedor(idUsuario);
                } catch (Exception e) {

                }
                break;
            case "edit":
                Pedido p = new Pedido();
                p.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));
                p.setStatus(request.getParameter("status"));
                try {
                    dao.atualizar(p);
                } catch (Exception e) {
                }
                break;
        }
        
        if(pedidos.isEmpty()) request.setAttribute("mensagem", new Mensagem("info", "Nenhum pedido encontrado!"));

        request.setAttribute("pedidos", pedidos);
        request.getRequestDispatcher("./pedidosFornecedor.jsp").forward(request, response);
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
