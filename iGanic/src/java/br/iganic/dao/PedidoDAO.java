/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Pedido;
import br.iganic.model.PedidoCliente;
import br.iganic.model.Pedidoo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class PedidoDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {

        Pedido p = (Pedido) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();

            ps = conn.prepareStatement("update Pedidos set status = ? where idPedido = ?");
            ps.setString(1, p.getStatus());
            ps.setInt(2, p.getIdPedido());

            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList buscaPedidosDoFornecedor(int idUsuario) throws Exception {
        ArrayList<Pedido> pedidos = new ArrayList();

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT Pedidos.idPedido, Pedidos.data, Pedidos.quantidade, Pedidos.status, Pedidos.idUsuario, Pedidos.idProduto, Produtos.nome, Usuarios.nome, Produtos.unidade "
                    + "FROM `Pedidos` "
                    + "INNER JOIN Usuarios on "
                    + "Pedidos.idUsuario = Usuarios.idUsuario "
                    + "INNER JOIN Produtos on "
                    + "`Pedidos`.`idProduto` = Produtos.idProduto and Produtos.idUsuario = ? order by Pedidos.data desc");
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt(1));
                p.setData(rs.getString(2));
                p.setQtd(rs.getFloat(3));
                p.setStatus(rs.getString(4));
                p.setIdUsuario(rs.getInt(5));
                p.setIdProduto(rs.getInt(6));
                p.setNomeProduto(rs.getString(7));
                p.setCliente(rs.getString(8));
                p.setUnidadeProduto(rs.getString(9));
                pedidos.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return pedidos;
    }

    public ArrayList buscaPedidosDoCliente(int idUsuario) throws Exception {
        ArrayList<PedidoCliente> pedidos = new ArrayList();

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("SELECT Pedidos.data, Produtos.nome,Pedidos.quantidade, Pedidos.status, Usuarios.nome, Produtos.unidade, format((Pedidos.quantidade * Produtos.preco), 2, 'de_DE') AS total, Pedidos.idPedido "
                    + "from Pedidos "
                    + "INNER JOIN Produtos ON Pedidos.idProduto = Produtos.idProduto "
                    + "INNER JOIN Usuarios ON Usuarios.idUsuario = Produtos.idUsuario WHERE Pedidos.idUsuario = ? ORDER BY Pedidos.data DESC");

            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();
            while (rs.next()) {
                PedidoCliente p = new PedidoCliente();
                p.setData(rs.getString(1));
                p.setProduto(rs.getString(2));
                p.setQtd(rs.getString(3));
                p.setStatus(rs.getString(4));
                p.setFornecedor(rs.getString(5));
                p.setUnidade(rs.getString(6));
                p.setTotal(rs.getString(7));
                p.setIdPedido(rs.getString(8));

                pedidos.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return pedidos;
    }

    public PedidoCliente buscaPedido(int idPedido) throws Exception {
        PedidoCliente p = new PedidoCliente();

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("select * from Pedidos where idPedido = ?");

            ps.setInt(1, idPedido);

            rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdPedido(rs.getString(1));
                p.setData(rs.getString(2));
                p.setQtd(rs.getString(3));
                p.setStatus(rs.getString(4));
                p.setIdUsuario(rs.getString(5));
                p.setIdProduto(rs.getString(6));

            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return p;
    }

        public void salvarPedido(Object ob) throws Exception {

        Pedidoo pedido = (Pedidoo) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {

            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("INSERT INTO `iGanic`.`Pedidos`(`data`,`quantidade`, `status`, `idUsuario`, `idProduto`) VALUES ((?),(?),(?),(?),(?))");

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setFloat(2, pedido.getQuantidade());
            ps.setString(3, pedido.getStatus());
            ps.setInt(4, pedido.getIdUsuario());
            ps.setInt(5, pedido.getIdProduto());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
}
