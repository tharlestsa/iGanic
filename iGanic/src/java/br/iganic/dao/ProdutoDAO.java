/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Produto;
import br.iganic.model.Fornecedor;
import br.iganic.model.Imagem;
import br.iganic.model.Pedido;
import br.iganic.model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author guilherme
 */
public class ProdutoDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {

        Produto p = (Produto) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();

            ps = conn.prepareStatement("update Produtos set nome = ?, unidade = ?, preco = ?, quantidade = ?, modoProducao = ? "
                    + "where idProduto = ?");

            ps.setString(1, p.getNome());
            ps.setString(2, p.getUnidade());
            ps.setDouble(3, p.getPreco());
            ps.setDouble(4, p.getQuantidade());
            ps.setString(5, p.getModoProducao());
            ps.setInt(6, p.getIdProduto());

            ps.executeUpdate();
//            
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

    }

    @Override
    public void excluir(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List listaTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List procura(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int salvarProduto(Object ob) throws Exception {
        Produto produto = (Produto) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        int idInserido = 0;

        try {

            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" select cadastraProduto(?, ?, ?, ?, ?, ?) ");
            ps.setString(1, produto.getNome());
            ps.setDouble(3, produto.getPreco());
            ps.setString(2, produto.getUnidade());
            ps.setDouble(4, produto.getQuantidade());
            ps.setString(5, produto.getModoProducao());
            ps.setInt(6, produto.getIdUsuario());

            rs = ps.executeQuery();

            while (rs.next()) {
                idInserido = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao cadastrar o produto: " + sqle.getMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return idInserido;
    }

    public ArrayList buscaProdutosDosFornecedores(int idUsuario) throws Exception {
        ArrayList<Produto> produtos = new ArrayList();

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" select * from Produtos where idUsuario = ?");
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setUnidade(rs.getString(3));
                p.setPreco(rs.getDouble(4));
                p.setQuantidade(rs.getDouble(5));
                p.setModoProducao(rs.getString(6));
                p.setIdUsuario(idUsuario);
                produtos.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return produtos;
    }

    public Produto buscaProduto(int idProduto) throws Exception {
        Produto p = new Produto();

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();

            ps = conn.prepareStatement("select * from Produtos where idProduto = ?");
            ps.setInt(1, idProduto);
            rs = ps.executeQuery();

            while (rs.next()) {
                p.setIdProduto(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setUnidade(rs.getString(3));
                p.setPreco(rs.getDouble(4));
                p.setQuantidade(rs.getDouble(5));
                p.setModoProducao(rs.getString(6));
                p.setIdUsuario(rs.getInt(7));
            }

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return p;
    }

    public List buscaFornecedoresProxDoCliente(Object ob) throws Exception {

        Usuario usu = (Usuario) ob;

        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cl = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {

            conn = ConnectionDAO.getConnection();
            cl = conn.prepareCall("{call procedure_procura_produtos_prox_cliente(?, ?)}");

            cl.setDouble(1, usu.getLat());
            cl.setDouble(2, usu.getLng());

            rs = cl.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16));

                Produto produto = new Produto(
                        rs.getInt(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getDouble(20),
                        rs.getDouble(21),
                        rs.getString(22),
                        rs.getInt(23));

                Imagem imagem = new Imagem(
                        rs.getInt(24),
                        rs.getString(25),
                        rs.getInt(26));

                fornecedores.add(new Fornecedor(usuario, produto, imagem));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, cl, rs);
        }
        return fornecedores;

    }

    public List buscaFornecedores(Object ob) throws Exception {

        Fornecedor forn = (Fornecedor) ob;

        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cl = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {

            conn = ConnectionDAO.getConnection();
            cl = conn.prepareCall("{call procedure_procura_produtos(?, ?, ?)}");

            cl.setDouble(1, forn.getUsuario().getLat());
            cl.setDouble(2, forn.getUsuario().getLng());
            cl.setString(3, forn.getProduto().getNome());

            rs = cl.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16));

                Produto produto = new Produto(
                        rs.getInt(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getDouble(20),
                        rs.getDouble(21),
                        rs.getString(22),
                        rs.getInt(23));

                Imagem imagem = new Imagem(
                        rs.getInt(24),
                        rs.getString(25),
                        rs.getInt(26));
                fornecedores.add(new Fornecedor(usuario, produto, imagem));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, cl, rs);
        }
        return fornecedores;

    }

    public void alterarProduto(Produto ob) throws Exception {
        Produto p = (Produto) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDAO.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement("update Produtos set nome = ?, unidade = ?, preco = ?, quantidade = ?, modoProducao = ? "
                    + "where idProduto = ?");

            ps.setString(1, p.getNome());
            ps.setString(2, p.getUnidade());
            ps.setDouble(3, p.getPreco());
            ps.setDouble(4, p.getQuantidade());
            ps.setString(5, p.getModoProducao());
            ps.setInt(6, p.getIdProduto());

            ps.executeUpdate();

            ps = conn.prepareStatement(" DELETE FROM `Imagens` WHERE `idProduto` = ? ");

            ps.setInt(1, p.getIdProduto());

            ps.executeUpdate();

            conn.commit();

        } catch (SQLException sqle) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            ConnectionDAO.closeConnection(conn, ps);
        }
    }

    public void adicionaQtdProduto(int idProduto, Double Qtd) {

    }

}
