/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.AvaliarProduto;
import br.iganic.model.Pedido;
import br.iganic.model.PedidoCliente;
import br.iganic.model.Pedidoo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.websocket.CloseReason;

/**
 *
 * @author guilherme
 */
public class AvaliarProdutoDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void salvarAvaliacao(Object ob) throws Exception {

        AvaliarProduto avaliacao = (AvaliarProduto) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {

            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("INSERT INTO `iGanic`.`Avaliacoes`(`nota`, `comentario`, `idProduto`) VALUES ((?),(?),(?))");

            ps.setInt(1, avaliacao.getNota());
            ps.setString(2, avaliacao.getComentario());
            ps.setInt(3, avaliacao.getIdProduto());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
    }

    public boolean verificaAvaliacaoDoPedido(Pedido ob) throws Exception {
        Pedido pedido = (Pedido) ob;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        boolean avaliado = false;
        int qtde = 0;
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(idAvaliacao) as qtde\n"
                    + "	FROM `iGanic`.`Avaliacoes`\n"
                    + "      INNER JOIN `iGanic`.`Pedidos`\n"
                    + "    	ON `iGanic`.`Pedidos`.`idProduto` = `iGanic`.`Avaliacoes`.`idProduto`\n"
                    + "    WHERE `iGanic`.`Pedidos`.`idPedido` = ? AND `iGanic`.`Pedidos`.`idUsuario`= ?");

            ps.setInt(1, pedido.getIdPedido());
            ps.setInt(2, pedido.getIdUsuario());

            rs = ps.executeQuery();

            while (rs.next()) {
                qtde = rs.getInt(1);
            }
            if (qtde > 0) {
                avaliado = true;
            } 

        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return avaliado;
    }

    public Double buscaNotaDoProduto(int idProduto) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;
        Double nota = 0.0;
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("SELECT (SUM(nota) / COUNT(`idAvaliacao`)) AS nota_media FROM `Avaliacoes` WHERE `idProduto` = ? ");

            ps.setInt(1, idProduto);

            rs = ps.executeQuery();

            while (rs.next()) {
                nota = rs.getDouble(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
        return nota;
    }

}

//SELECT *
//	FROM `iGanic`.`Avaliacoes`
//      INNER JOIN `iGanic`.`Pedidos`
//    	ON `iGanic`.`Pedidos`.`idProduto` = `iGanic`.`Avaliacoes`.`idProduto`
//      INNER JOIN `iGanic`.`Usuarios`
//    	ON `iGanic`.`Usuarios`.`idUsuario` = `iGanic`.`Avaliacoes`.`idProduto`
//WHERE `iGanic`.`Pedidos`.`idPedido` = 
