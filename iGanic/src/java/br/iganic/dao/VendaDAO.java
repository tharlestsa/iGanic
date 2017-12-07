/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.dao;

import br.iganic.base.ConnectionDAO;
import br.iganic.base.DAO;
import br.iganic.model.Venda;
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
public class VendaDAO implements DAO {

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

    public ArrayList<Venda> buscaVendasDoFornecedor(int idUsuario) {

        ArrayList<Venda> vendas = new ArrayList();

        PreparedStatement ps;
        Connection conn;
        ResultSet rs;

        try {
            conn = ConnectionDAO.getConnection();
            ps = conn.prepareStatement(" SELECT Pedidos.data, Usuarios.nome as cliente, Produtos.nome as produto, Pedidos.quantidade, (Pedidos.quantidade * Produtos.preco) as valorVenda, Produtos.unidade "
                    + "FROM `Pedidos` "
                    + "INNER JOIN Usuarios on "
                    + " Usuarios.idUsuario = Pedidos.idUsuario "
                    + "INNER JOIN Produtos on "
                    + "`Pedidos`.`idProduto` = Produtos.idProduto and Produtos.idUsuario = ? and Pedidos.status = 'F' order by Pedidos.data desc");
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();
            while (rs.next()) {
               Venda v = new Venda();
               v.setData(rs.getString(1));
               v.setCliente(rs.getString(2));
               v.setProduto(rs.getString(3));
               v.setQuantidade(rs.getString(4));
               v.setValorTotal(rs.getString(5));
               v.setUnidadeProduto(rs.getString(6));
               
               vendas.add(v);

            }
        } catch (Exception ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendas;
    }

    @Override
    public void salvar(Object ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
