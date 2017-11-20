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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tatuapu
 */
@WebServlet(name = "ServletGetAjaxData", urlPatterns = {"/getCategorias.iga"})
public class ServletGetAjaxData extends HttpServlet {

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

        String cmd = (request.getParameter("cmd") != null) ? request.getParameter("cmd").toLowerCase().toString() : "";
        ArrayList listaRetorno = new ArrayList();
        //RequestDispatcher rd =  request.getRequestDispatcher("/home.jsp");

        switch (cmd) {
            case "listacategorias":
                setViewData(getListaCategorias(), "lista-categorias", "categorias");
                abreViewDestino("./data/listaJSON.jsp");
                break;
            default:
                abreViewDestino("");
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

    private ArrayList<Categoria> getListaCategorias() {
        ArrayList<Categoria> lista = null;
        try {
            CategoriaDAO catDao = new CategoriaDAO();
            lista = (ArrayList<Categoria>) catDao.listaTodos();

        } catch (Exception ex) {
            Logger.getLogger(ServletGetAjaxData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    private void setViewData(ArrayList lista, String nmParametro, String classe) {
        this.request.setAttribute(nmParametro, lista);
        this.request.setAttribute("classe", classe);
    }

    private void abreViewDestino(String urlDestino) throws ServletException, IOException {
        if (urlDestino != "") {
            this.request.getRequestDispatcher(urlDestino).forward(this.request, this.response);
        } else {
            this.response.sendRedirect("./");
        }
    }

}
