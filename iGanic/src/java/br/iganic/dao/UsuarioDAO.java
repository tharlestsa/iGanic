package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Usuario;
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
                    + " `nome`, "
                    + " `cpf`, "
                    + " `cel`, "
                    + " `email`, "
                    + " `endereco`, "
                    + " `lat`, "
                    + " `lng`, "
                    + " `tipo`, "
                    + " `usuario`, "
                    + " `senha`, "
                    + " `idCidade`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) LAST_INSERT_ID()");

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getCel());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getEndereco());
            ps.setString(6, usuario.getLat());
            ps.setString(7, usuario.getLng());
            ps.setString(8, usuario.getTipo());
            ps.setString(9, usuario.getUsuario());
            ps.setString(10, usuario.getSenha());
            ps.setInt(11, usuario.getIdCidade());

            idInserido = ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, String.valueOf(idInserido));
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return idInserido;
    }

}
