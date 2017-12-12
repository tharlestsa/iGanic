package br.iganic.controller;

import br.iganic.dao.AvaliarProdutoDAO;
import br.iganic.dao.ProdutoDAO;
import br.iganic.model.Estado;
import br.iganic.model.Fornecedor;
import br.iganic.model.Produto;
import br.iganic.model.Usuario;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;

/**
 *
 * @author tharles
 */
@WebServlet(name = "BuscaProdutosServlet", urlPatterns = {"/buscaprodutos"})
public class BuscaProdutosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acao = (request.getParameter("acao") != null) ? request.getParameter("acao").toString() : "";
            String produto = (request.getParameter("buscaProduto") != null) ? request.getParameter("buscaProduto").toString() : "";
            switch (acao) {
                case "buscar":
                    if (produto.isEmpty()) {
                        request.setAttribute("tipo", "aten");
                        request.setAttribute("mensagem", "Informe o nome do alimento que você deseja procurar!");
                        request.getRequestDispatcher("/principal.jsp").forward(request, response);
                    } else {
                        this.buscaProdutos(request, response);
                    }

                    break;

            }
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

    public void buscaProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String produto = (request.getParameter("buscaProduto") != null) ? request.getParameter("buscaProduto").toString() : "";
        request.getSession().setAttribute("nomeProduto", produto);
        ArrayList<Fornecedor> fornecedores = null;

        try {
            ProdutoDAO prodDao = new ProdutoDAO();
            fornecedores = new ArrayList<>();
            try {
                Double lat = Double.parseDouble(request.getSession().getAttribute("lat").toString());
                Double lng = Double.parseDouble(request.getSession().getAttribute("lng").toString());
                Usuario usu = new Usuario(lat, lng);

//                fornecedores = (ArrayList<Fornecedor>) prodDao.buscaFornecedoresProxDoCliente(usu);
                fornecedores = (ArrayList<Fornecedor>) prodDao.buscaFornecedores(new Fornecedor(usu, new Produto(produto, null, null, null, null, 0)));
                if (fornecedores.isEmpty()) {
                    request.setAttribute("tipo", "aten");
                    request.setAttribute("mensagem", "Nenhum alimento encontrado com esse nome!");
                }
            } catch (Exception e) {
                System.out.println("Exceção na busca dos fornecedores: " + e.getMessage());
            }

            JSONObject pai = new JSONObject();
            JSONObject dados = new JSONObject();
            JsonArray filhos = new JsonArray();

            for (Fornecedor forn : fornecedores) {
                AvaliarProdutoDAO avaDao = new AvaliarProdutoDAO();
                Double nota = 0.0;
                String notaf = null;
                try {
                    nota = avaDao.buscaNotaDoProduto(forn.getProduto().getIdProduto());
                    DecimalFormat df = new DecimalFormat("0.##");
                    notaf = df.format(nota);
                } catch (Exception e) {
                    System.out.println("Problema ao buscar a nota: " + e.getMessage());
                }
                dados = new JSONObject();
                dados.put("lat", forn.getUsuario().getLat());
                dados.put("lng", forn.getUsuario().getLng());
                dados.put("nomeFornecedor", forn.getUsuario().getNome());
                dados.put("idProduto", forn.getProduto().getIdProduto());
                dados.put("nomeProduto", forn.getProduto().getNome());
                dados.put("un", forn.getProduto().getUnidade());
                dados.put("preco", String.valueOf(forn.getProduto().getPreco()).replace(".", ","));
                dados.put("img", forn.getImagem().getNome());
                dados.put("modo", forn.getProduto().getModoProducao());
                dados.put("nota", notaf);

                dados.put("rua", forn.getUsuario().getRua());
                dados.put("num", forn.getUsuario().getNum());
                dados.put("comp", forn.getUsuario().getComp());
                dados.put("bairro", forn.getUsuario().getBairro());
                dados.put("cidade", forn.getUsuario().getCidade());
                dados.put("uf", forn.getUsuario().getUf());
                filhos.add(dados);
            }

            pai.put("dados", filhos);

            String path = this.getServletContext().getRealPath("");
            System.out.println("Path: " + path);
            try (FileWriter file = new FileWriter(path + "data/fornecedores.json")) {
                file.write(pai.toJSONString());
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: " + pai);
                request.getSession().setAttribute("fornecedores", fornecedores);
                request.getRequestDispatcher("/busca_produtos.jsp").forward(request, response);

            }

        } catch (Exception e) {
            System.out.println("Falha ao buscar os fornecedores: " + e.getMessage());
        }

    }

}
