/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class PedidoDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {
        
        Pedido p = (Pedido)ob;
        
        PreparedStatement ps;
        Connection conn;
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
    
    public ArrayList buscaPedidosDoFornecedor(int idUsuario){
        ArrayList <Pedido> pedidos = new ArrayList();
        
        PreparedStatement ps;
        Connection conn;
        ResultSet rs;
        
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT Pedidos.idPedido, Pedidos.data, Pedidos.quantidade, Pedidos.status, Pedidos.idUsuario, Pedidos.idProduto, Produtos.nome "
                                            + "FROM `Pedidos` "
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
                
                pedidos.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return pedidos;
    }
}
