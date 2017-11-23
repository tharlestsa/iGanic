/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.CategoriaDAO;
import br.iganic.model.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author tharles
 */
@WebServlet(name = "DadosServlet", urlPatterns = {"/dados"})
public class DadosServlet extends HttpServlet {

    HttpServletRequest request;
    HttpServletResponse response;

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
        this.request = request;
        this.response = response;
        PrintWriter out = response.getWriter();
        String cmd = (request.getParameter("action") != null) ? request.getParameter("action").toLowerCase().toString() : "";
                
        Integer id = Integer.parseInt((request.getParameter("id") != null) ? request.getParameter("id") : "0");
        
        String nome = request.getParameter("nome");

        switch (cmd) {
            case "edit":
                this.salvaCategoria(new Categoria(id, nome));

                JSONObject dados = new JSONObject();
                dados.put("dados", "ok"); 
                out.println(dados);

                break;
            case "delete":
                this.removeCategoria(new Categoria(id, nome));
                break;
            case "restore":

                break;
            default:

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

    private Boolean salvaCategoria(Categoria categoria) {
        Boolean salvou = true;
        try {
            CategoriaDAO catDAo = new CategoriaDAO();

            if (categoria == null) {
                salvou = false;
            } else {
                catDAo.atualizar(categoria);
            }

        } catch (Exception e) {
            salvou = false;
        }

        return salvou;
    }

    private Boolean removeCategoria(Categoria categoria) {
        Boolean removeu = true;
        try {

            CategoriaDAO catDAo = new CategoriaDAO();

            if (categoria == null) {
                removeu = false;
            } else {
                catDAo.excluir(categoria);
            }

        } catch (Exception e) {
            removeu = false;
        }

        return removeu;
    }
}
