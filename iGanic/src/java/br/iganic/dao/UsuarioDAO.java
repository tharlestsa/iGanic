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
        Usuario usu = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idUsuario`, "
                    + "`nome`,"
                    + "`cpf`, "
                    + "`cel`,"
                    + "`email`, "
                    + "`endereco`, "
                    + "`lat`, "
                    + "`lng`, "
                    + "`tipo`, "
                    + "`usuario`, "
                    + "`senha`, "
                    + "`idCidade` "
                    + "FROM `iGanic`.`Usuarios` WHERE `idUsuario` =  ? ");

            ps.setInt(1, usu.getIdUsuario());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12))
                );
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return usuarios;

    }

    @Override
    public void salvar(Object ob) throws Exception {

    }

    public int salvarUsuario(Object ob) throws Exception {
        Usuario usuario = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cStmt = null;

        int idInserido = 0;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" select cadastraUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

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

            rs = ps.executeQuery();

            while (rs.next()) {
                idInserido = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());

            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return idInserido;
    }

    public List buscaUsuarioPorCpf(Object ob) throws Exception {
        Usuario usu = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idUsuario`, "
                    + "`nome`,"
                    + "`cpf`, "
                    + "`cel`,"
                    + "`email`, "
                    + "`endereco`, "
                    + "`lat`, "
                    + "`lng`, "
                    + "`tipo`, "
                    + "`usuario`, "
                    + "`senha`, "
                    + "`idCidade` "
                    + "FROM `iGanic`.`Usuarios` WHERE `cpf` =  ? ");

            ps.setString(1, usu.getCpf());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12))
                );
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return usuarios;

    }

    public List buscaUsuPeloUsuario(Object ob) throws Exception {
        Usuario usu = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idUsuario`, "
                    + "`nome`,"
                    + "`cpf`, "
                    + "`cel`,"
                    + "`email`, "
                    + "`endereco`, "
                    + "`lat`, "
                    + "`lng`, "
                    + "`tipo`, "
                    + "`usuario`, "
                    + "`senha`, "
                    + "`idCidade` "
                    + "FROM `iGanic`.`Usuarios` WHERE `usuario` =  ? ");

            ps.setString(1, usu.getUsuario());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12))
                );
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return usuarios;

    }

}
