package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Categoria;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tatuapu
 */
public class CategoriaDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void excluir(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List listaTodos() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Categoria> listaCategorias = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idCategoria`, `nome` FROM `Categorias` ");
            rs = ps.executeQuery();
            while (rs.next()) {
                listaCategorias.add(new Categoria(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return listaCategorias;
    }

    @Override
    public List procura(Object ob) throws Exception {
        Categoria adm = (Categoria) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Categoria> listaCategorias = new ArrayList<>();
//        try {
//            conn = ConnectionDAO.getConnection();
//            ps = conn.prepareStatement(" SELECT * FROM `view_login_adm` WHERE (user = ? AND passWd = ?) ");
//            ps.setString(1, adm.getUser());
//            ps.setString(2, adm.getPassWd());
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                listaAdministradores.add(new Administrador(rs.getInt(1), 
//                        rs.getString(2), 
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7)));
//            }
//
//        } catch (SQLException sqle) {
//            throw new Exception(sqle);
//        } finally {
//            ConnectionDAO.closeConnection(conn, ps, rs);
//        }
        return listaCategorias;
    }

    
    @Override
    public void salvar(Object ob) throws Exception {}
    
    
    public int salvarCategoria(Object ob) throws Exception {
        Categoria categoria = (Categoria) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null; 
        
        int idInserido = 0; 

        try {
            conn = ConnectionDAO.getConnection();

            ps = conn.prepareStatement(" INSERT INTO `Categorias`(`nome`) VALUES ((?)) LAST_INSERT_ID ");

            ps.setString(1, categoria.getNome());
            
            rs = ps.executeQuery();
            while (rs.next()) {                
               idInserido = rs.getInt(1); 
            }
            
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
        
        return idInserido;
    }

}
