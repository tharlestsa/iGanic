package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Cidade;
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
public class CidadeDAO implements DAO {

    @Override
    public void atualizar(Object ob) throws Exception {

    }

    @Override
    public void excluir(Object ob) throws Exception {

    }

    @Override
    public List listaTodos() throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Cidade> listaCidades = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idCidade`, `nome`, `idEstado` FROM `iGanic`.`Cidades`");
            rs = ps.executeQuery();
            while (rs.next()) {
                listaCidades.add(new Cidade(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return listaCidades;
    }

    public List listaTodasCidadesDeUmEstado(Object ob) throws Exception {
        Estado est = (Estado) ob; 
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Cidade> listaCidades = new ArrayList<>();
        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT `idCidade`, `iGanic`.`Cidades`.`nome`, `iGanic`.`Cidades`.`idEstado` "
                    + " FROM `iGanic`.`Cidades`"
                    + " INNER JOIN `iGanic`.`Estados`"
                    + "   ON  `iGanic`.`Cidades`.`idEstado` = `iGanic`.`Estados`.`idEstado` "
                    + " WHERE `iGanic`.`Estados`.`uf` = ?");
            ps.setString(1, est.getUf());
            rs = ps.executeQuery();
            while (rs.next()) {
                listaCidades.add(new Cidade(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionDAO.closeConnection(conn, ps, rs);
        }
        return listaCidades;
    }

    @Override
    public List procura(Object ob) throws Exception {
        return null;
       
    }

    @Override
    public void salvar(Object ob) throws Exception {
    }

}
