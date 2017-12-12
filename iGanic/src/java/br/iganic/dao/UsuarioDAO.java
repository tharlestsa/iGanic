package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
                    + "`lat`, "
                    + "`lng`, "
                    + "`rua`, "
                    + "`num`, "
                    + "`comp`, "
                    + "`bairro`, "
                    + "`cidade`, "
                    + "`uf`, "
                    + "`tipo`, "
                    + "`usuario`, "
                    + "`senha`, "
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
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(9),
                        rs.getString(8),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16))
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

        int idInserido = 0;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" select cadastraUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getCel());
            ps.setString(4, usuario.getEmail());
            ps.setDouble(5, usuario.getLat());
            ps.setDouble(6, usuario.getLng());
            ps.setString(7, usuario.getRua());
            ps.setString(8, usuario.getNum());
            ps.setString(9, usuario.getComp());
            ps.setString(10, usuario.getBairro());
            ps.setString(11, usuario.getCidade());
            ps.setString(12, usuario.getUf());
            ps.setString(13, usuario.getTipo());
            ps.setString(14, usuario.getUsuario());
            ps.setString(15, usuario.getSenha());

            rs = ps.executeQuery();

            while (rs.next()) {
                idInserido = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage() + "\n\n\n");
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return idInserido;
    }

    public Boolean editaUsuario(Object ob) throws Exception {
        Usuario usuario = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        Boolean editou = false;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" UPDATE `Usuarios` SET "
                    + "`nome`=?,"
                    + "`cpf`=?,"
                    + "`cel`=?,"
                    + "`email`=?,"
                    + "`lat`=?,"
                    + "`lng`=?,"
                    + "`rua`=?,"
                    + "`num`=?,"
                    + "`comp`=?,"
                    + "`bairro`=?,"
                    + "`cidade`=?,"
                    + "`uf`=?,"
                    + "`tipo`=?,"
                    + "`usuario`=?,"
                    + "`senha`=?"
                    + " WHERE `idUsuario`=? ");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getCel());
            ps.setString(4, usuario.getEmail());
            ps.setDouble(5, usuario.getLat());
            ps.setDouble(6, usuario.getLng());
            ps.setString(7, usuario.getRua());
            ps.setString(8, usuario.getNum());
            ps.setString(9, usuario.getComp());
            ps.setString(10, usuario.getBairro());
            ps.setString(11, usuario.getCidade());
            ps.setString(12, usuario.getUf());
            ps.setString(13, usuario.getTipo());
            ps.setString(14, usuario.getUsuario());
            ps.setString(15, usuario.getSenha());
            ps.setInt(16, usuario.getIdUsuario());
            
            
            
            int i = ps.executeUpdate();
            editou = true;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage() + "\n\n\n");
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }

        return editou;
    }

    public List buscaUsuarioPorCpf(Object ob) throws Exception {
        Usuario usu = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idUsuario`, `nome`, `cpf`, `cel`, `email`, `lat`, `lng`, `rua`, `num`, `comp`, `bairro`, `cidade`, `uf`, `tipo`, `usuario`, `senha` FROM `Usuarios` WHERE cpf = (?) ");

            ps.setString(1, usu.getCpf());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(9),
                        rs.getString(8),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16))
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
            ps = conn.prepareStatement(" SELECT `idUsuario`, `nome`, `cpf`, `cel`, `email`, `lat`, `lng`, `rua`, `num`, `comp`, `bairro`, `cidade`, `uf`, `tipo`, `usuario`, `senha` FROM `Usuarios` WHERE usuario = (?) ");

            ps.setString(1, usu.getUsuario());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(9),
                        rs.getString(8),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16))
                );
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nErro na busca BD: " + sqle.getMessage() + sqle.getSQLState());
            System.out.println("\n\nSql state: " + sqle.getLocalizedMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return usuarios;

    }

    public List buscaUsuPeloId(Object ob) throws Exception {
        Usuario usu = (Usuario) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idUsuario`, `nome`, `cpf`, `cel`, `email`, `lat`, `lng`, `rua`, `num`, `comp`, `bairro`, `cidade`, `uf`, `tipo`, `usuario`, `senha` FROM `Usuarios` WHERE  idUsuario =  (?) ");

            ps.setInt(1, usu.getIdUsuario());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
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
                        rs.getString(16))
                );
            }

        } catch (SQLException sqle) {
            System.out.println("Erro na consulta: " + sqle.getMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return usuarios;

    }

    public List buscaUsuPeloUsuarioESenha(Object ob) throws Exception {
        Usuario usu = (Usuario) ob;
        System.out.println("\n\nErro na busca BD: " + usu.getUsuario());
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idUsuario`, `nome`, `cpf`, `cel`, `email`, `lat`, `lng`, `rua`, `num`, `comp`, `bairro`, `cidade`, `uf`, `tipo`, `usuario`, `senha` FROM `Usuarios` WHERE usuario = ? AND senha = ? ");

            ps.setString(1, usu.getUsuario());
            ps.setString(2, usu.getSenha());

            rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(9),
                        rs.getString(8),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16))
                );
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nErro na busca BD: " + sqle.getMessage() + sqle.getSQLState());
            System.out.println("\n\nSql state: " + sqle.getLocalizedMessage());
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return usuarios;

    }

    public void salvarUsuarios(ArrayList<Usuario> usuarios) throws Exception {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDAO.getConnection();
            conn.setAutoCommit(false);

            for (Usuario usuario : usuarios) {
                ps = conn.prepareStatement(" select cadastraUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

                ps.setString(1, usuario.getNome());
                ps.setString(2, usuario.getCpf());
                ps.setString(3, usuario.getCel());
                ps.setString(4, usuario.getEmail());
                ps.setDouble(5, usuario.getLat());
                ps.setDouble(6, usuario.getLng());
                ps.setString(7, usuario.getRua());
                ps.setString(8, usuario.getNum());
                ps.setString(9, usuario.getComp());
                ps.setString(10, usuario.getBairro());
                ps.setString(11, usuario.getCidade());
                ps.setString(12, usuario.getUf());
                ps.setString(13, usuario.getTipo());
                ps.setString(14, usuario.getUsuario());
                ps.setString(15, usuario.getSenha());

                System.out.println(usuario.toString());

                rs = ps.executeQuery();

            }
            conn.commit();

        } catch (SQLException sqle) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            ConnectionDAO.closeConnection(conn, ps);
        }
    }
}
