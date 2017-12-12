/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Imagem;
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
public class ImagemDAO implements DAO {

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

    public Imagem buscaImagem(Object ob) throws Exception {
        Imagem img = (Imagem) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();

            ps = conn.prepareStatement(" SELECT `idImagem`, `nome`, `idProduto` FROM `iGanic`.`Imagens` WHERE `idProduto` = ? ");
            ps.setInt(1, img.getIdProduto());
            rs = ps.executeQuery();

            while (rs.next()) {
                img = new Imagem(rs.getInt(1), rs.getString(2),rs.getInt(3));
            }

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return img;
    }

    @Override
    public void salvar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void salvarImagem(Object ob) throws Exception {
        Imagem img = (Imagem) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {

            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement("INSERT INTO `iGanic`.`Imagens`(`nome`, `idProduto`) VALUES ((?),(?))");
            ps.setString(1, img.getNome());
            ps.setInt(2, img.getIdProduto());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
    
 

}
