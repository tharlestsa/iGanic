package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author tatuapu
 */
public class UsuarioDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {
        
    }

    @Override
    public void excluir(Object ob) throws Exception {
        
    }

    @Override
    public List listaTodos() throws Exception {
        return null; 
    }

    @Override
    public List procura(Object ob) throws Exception {
        return null; 
    }

    @Override
    public void salvar(Object ob) throws Exception {
        
    }

    public int salvarUsuario(Object ob) throws Exception {
        Usuario usuario = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        int idInserido = 0;

        try {
            conn = ConnectionDAO.getConnection();

            ps = conn.prepareStatement(" INSERT INTO `iGanic`.`Usuarios`("
                    + " `nome`,"
                    + " `cpf`, "
                    + " `cel`,"
                    + " `email`,"
                    + " `endereco`,"
                    + " `geolocalizacao`,"
                    + " `tipo`,"
                    + " `usuario`,"
                    + " `senha`,"
                    + " `idCidade`) VALUES ((?),(?),(?),(?),(?),ST_GeomFromText(?)),(?),(?),(?),(?)) LAST_INSERT_ID ");
            
            String lat = String.valueOf(usuario.getGeolocalizacao().getX()); 
            String lng = String.valueOf(usuario.getGeolocalizacao().getY()); 

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getNome());
            ps.setString(3, usuario.getNome());
            ps.setString(5, usuario.getNome());
            ps.setString(6, "POINT("+lat +" "+ lng+ ")");
            ps.setString(7, usuario.getNome());
            ps.setString(8, usuario.getNome());
            ps.setString(9, usuario.getNome());
            ps.setInt(10, usuario.getIdCidade());
            

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
