package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Estado;
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
public class EstadoDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {
        Estado est = (Estado) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        conn = ConnectionDAO.getConnection();

        if (est == null) {
            throw new Exception("O valor passado não pode ser nulo");
        }
        try {
            ps = conn.prepareStatement(" UPDATE `iGanic`.`Estados` SET `nome`= ?,`uf`= ? WHERE `idEstado`= ? ");

            ps.setString(1, est.getNome());
            ps.setString(2, est.getUf());
            ps.setInt(3, est.getIdEstado());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            throw new Exception("Erro ao atualizar dados: " + sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
    }

    @Override
    public void excluir(Object ob) throws Exception {
        Estado est = (Estado) ob;

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" DELETE FROM `iGanic`.`Estados` WHERE `idEstado`= ? ");
            ps.setInt(1, est.getIdEstado());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            throw new Exception("Erro ao excluir dados: " + sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps);
        }
    }

    @Override
    public List listaTodos() throws Exception {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Estado> listaEstados = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idEstado`, `nome`, `uf` FROM `iGanic`.`Estados`");

            rs = ps.executeQuery();
            while (rs.next()) {
                listaEstados.add(new Estado(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return listaEstados;

    }

    @Override
    public List procura(Object ob) throws Exception {
        Estado est = (Estado) ob;

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Estado> listaEstados = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idEstado`, `nome`, `uf` FROM `iGanic`.`Estados` WHERE `idEstado` = ? ");
            ps.setInt(1, est.getIdEstado());

            rs = ps.executeQuery();
            while (rs.next()) {
                listaEstados.add(new Estado(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return listaEstados;
    }

    @Override
    public void salvar(Object ob) throws Exception {
    }
}
