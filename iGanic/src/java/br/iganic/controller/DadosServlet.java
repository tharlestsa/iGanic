/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.controller;

import br.iganic.dao.EstadoDAO;
import br.iganic.dao.UsuarioDAO;
import br.iganic.model.Estado;
import br.iganic.model.Usuario;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JsonArray;

/**
 *
 * @author tharles
 */
@WebServlet(name = "DadosServlet", urlPatterns = {"/dados"})
public class DadosServlet extends HttpServlet {
    int i = 0; 
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
        String uf = request.getParameter("uf");

        JSONObject dado = new JSONObject();
        switch (cmd) {
            case "edit":

                try {

                    this.salvaEstado(new Estado(id, nome, uf));
                    dado.put("dados", "ok");
                    out.println(dado);

                } catch (Exception e) {
                    dado.put("erro", e.getMessage());
                    out.println(dado);
                }

                break;

            case "delete":
                try {
                    this.removeEstado(new Estado(id, nome, uf));
                    dado.put("dados", "ok");
                    out.println(dado);

                } catch (Exception e) {
                    dado.put("erro", e.getMessage());
                    out.println(dado);
                }

                break;

            case "alimenta":
                this.alimentaUsuario(request, response);
                break;

            case "listarestados":
                this.listarEstados(request, response);
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

    private Boolean salvaEstado(Estado est) {
        Boolean salvou = true;
        try {
            EstadoDAO estDAo = new EstadoDAO();

            estDAo.atualizar(est);

        } catch (Exception e) {
            salvou = false;
        }

        return salvou;
    }

    private Boolean removeEstado(Estado est) {
        Boolean removeu = true;
        try {

            EstadoDAO estDAo = new EstadoDAO();

            estDAo.excluir(est);

        } catch (Exception e) {
            removeu = false;
        }

        return removeu;
    }

    private void listarEstados(HttpServletRequest request, HttpServletResponse response) {

        try {
            EstadoDAO estDao = new EstadoDAO();
            ArrayList<Estado> estados = null;

            estados = (ArrayList<Estado>) estDao.listaTodos();

            JSONObject dados = new JSONObject();
            JSONObject pai = new JSONObject();
            JsonArray filhos = new JsonArray();

            for (Estado est : estados) {
                dados = new JSONObject();
                dados.put("idEstado", est.getIdEstado());
                dados.put("nome", est.getNome());
                dados.put("uf", est.getUf());

                filhos.add(dados);
            }

            pai.put("data", filhos);

            response.getWriter().print(pai);
        } catch (Exception e) {

        }

    }

    private void alimentaUsuario(HttpServletRequest request, HttpServletResponse response) {

        try {
            PrintWriter out = response.getWriter();
            
            UsuarioDAO usuDao = new UsuarioDAO(); 
            String path = this.getServletContext().getRealPath("");
            
            System.out.println("\n\n\n Caminho do arquivo: " + path + "\n\n\n");
            
            Object obj = JSONValue.parse(new FileReader(path + "adm/city.json"));

            JSONArray array2 = (JSONArray) obj;
            ArrayList<Usuario> usuarios = new ArrayList<>(); 
             
            array2.forEach(city -> {
                JSONObject json = new JSONObject();
                json = (JSONObject) city;
                usuarios.add(new Usuario(
                        json.get("title").toString(),
                        json.get("id").toString()+i,
                        json.get("id_state").toString(),
                        json.get("population").toString(),
                        Double.parseDouble(json.get("lat").toString()),
                        Double.parseDouble(json.get("lng").toString()), 
                        json.get("title").toString(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        "C",
                        "teste"+i,
                        "teste"+i));
                i++; 
            });
            
            usuDao.salvarUsuarios(usuarios);

        } catch (Exception e) {
            System.out.println("\n\n\n Não Salvou os usuários erro: " + e.getMessage()+ "\n\n\n");
        }

    }

}
