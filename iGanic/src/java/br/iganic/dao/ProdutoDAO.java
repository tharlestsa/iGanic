/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author guilherme
 */
public class ProdutoDAO implements DAO {

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

    public void salvarProduto(Object ob) throws Exception {
        Produto produto = (Produto) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
             
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("INSERT INTO `iGanic`.`Produtos` (`nome`, `unidade`, `preco`, `quantidade`, `modoProducao`, `idUsuario`) VALUES ((?),(?),(?),(?),(?),(?))");
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getUnidade());
            ps.setDouble(3, produto.getPreco());
            ps.setDouble(4, produto.getQuantidade());
            ps.setString(5, produto.getModoProducao());
            ps.setInt(6, produto.getIdUsuario());

            ps.executeUpdate();

          
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    
    public Produto buscaProduto(int idProduto) throws Exception{
        Produto p = new Produto();
        
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();
            
            ps = conn.prepareStatement("select * from Produtos where idProduto = ?");
            ps.setInt(1, idProduto);
            rs = ps.executeQuery();
            
            while(rs.next()){
                p.setIdProduto(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setUnidade(rs.getString(3));
                p.setPreco(rs.getDouble(4));
                p.setQuantidade(rs.getDouble(5));
                p.setModoProducao(rs.getString(6));
                p.setIdUsuario(rs.getInt(7));
            }
            
        }catch(SQLException e){
            throw new Exception(e);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
        
        return p;
    }

}
