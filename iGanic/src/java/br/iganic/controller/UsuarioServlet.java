package br.iganic.controller;

import br.iganic.dao.CidadeDAO;
import br.iganic.dao.EstadoDAO;
import br.iganic.dao.UsuarioDAO;
import br.iganic.model.Cidade;
import br.iganic.model.Estado;
import br.iganic.model.Usuario;
import br.iganic.util.TipoUsuario;
import com.esri.core.geometry.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;

/**
 *
 * @author tharles
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuario"})
public class UsuarioServlet extends HttpServlet {

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
        String acao = (request.getParameter("acao") != null) ? request.getParameter("acao").toLowerCase().toString() : "";

        switch (acao) {
            case "buscarcidades":
                this.buscaCidadesEEstados(request, response);
                break;
            case "buscarcidadesdoestado":
                this.buscaCidadesDoEstado(request, response);
                break;
            case "registrar":
                this.salvarUsuario(request, response);
                break;
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

    private void buscaCidadesEEstados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            String localidade = request.getParameter("localidade");
            String uf = request.getParameter("uf");

            String loca_sem_espaco = localidade.trim();

            CidadeDAO cidDao = new CidadeDAO();
            ArrayList<Cidade> cidades = null;
            try {
                cidades = (ArrayList<Cidade>) cidDao.listaTodasCidadesDeUmEstado(new Estado(null, null, uf));
            } catch (Exception e) {

            }
            Cidade cid_usuario = null;
            Estado est_usuario = null;

            for (Cidade cid : cidades) {
                String nome = cid.getNome().trim();
                if (nome.equals(loca_sem_espaco)) {
                    cid_usuario = cid;
                }
            }

            EstadoDAO estDao = new EstadoDAO();

            try {
                est_usuario = (Estado) estDao.procura(new Estado(cid_usuario.getIdEstado(), null, null)).get(0);
            } catch (Exception e) {

            }

            JSONObject json = new JSONObject();

            json.put("idEstado", est_usuario.getIdEstado());
            json.put("estado", est_usuario.getNome());
            json.put("uf", est_usuario.getUf());
            json.put("idCidade", cid_usuario.getIdCidade());
            json.put("cidade", cid_usuario.getNome());

            out.print(json);
        }
    }

    private void buscaCidadesDoEstado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            JSONObject dados = new JSONObject();

            String uf = request.getParameter("uf");

            CidadeDAO cidDao = new CidadeDAO();
            ArrayList<Cidade> cidades = null;
            try {
                cidades = (ArrayList<Cidade>) cidDao.listaTodasCidadesDeUmEstado(new Estado(null, null, uf));
            } catch (Exception e) {

            }

            JSONObject pai = new JSONObject();
            JsonArray filhos = new JsonArray();

            for (Cidade cid : cidades) {
                dados = new JSONObject();
                dados.put("id", cid.getIdCidade().toString());
                dados.put("cidade", cid.getNome());
                filhos.add(dados);
            }

            pai.put("data", filhos);
            out.print(pai);
        }
    }

    private void salvarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try (PrintWriter out = response.getWriter()) {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String cel = request.getParameter("cel");
            
            String rua = request.getParameter("rua"); 
            String numero = request.getParameter("numero");
            String comp = request.getParameter("comp"); 
            String bairro = request.getParameter("bairro");
            String endereco =  rua + "," + numero + ","+ comp + "," + bairro;

            Integer idCidade = Integer.parseInt(request.getParameter("cidade"));
            String tipo = request.getParameter("tipo");
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            Double lat = Double.parseDouble(request.getParameter("lat"));
            Double lng = Double.parseDouble(request.getParameter("lng"));

            Point point = new Point(lat, lng);

            UsuarioDAO usuDao = new UsuarioDAO();

            try {
                Integer idUsuario = usuDao.salvarUsuario(new Usuario(nome, cpf, cel, email, endereco, point, tipo, usuario, senha, idCidade));
                if (idUsuario != 0) {
                    HttpSession sessao = request.getSession(true);
                    sessao.setAttribute("idUsuario", idUsuario);
                    request.getRequestDispatcher("/principal.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("tipo", "erro");
                request.setAttribute("mensagem", "Ao registrar a nova conta do usuário.");
                request.getRequestDispatcher("/cadastra_usuario.jsp").forward(request, response);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
