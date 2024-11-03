package eletrosales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eletrosales.entities.Pedido;

public class PedidoDAO {

	public Pedido buscarPedidoPorId(int id) throws SQLException {
        Pedido pedido = null;
        String sql = "SELECT * FROM pedido WHERE id = ?";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido();
                    pedido.setId(rs.getInt("id"));
                    pedido.setStatus(rs.getString("status"));
                    pedido.setValorTotal(rs.getDouble("valor_total"));
                }
            }
        }
        return pedido;
    }
	
	 public List<Pedido> listarTodosPedidos() throws SQLException {
	        List<Pedido> pedidos = new ArrayList<>();
	        String sql = "SELECT * FROM pedido"; 

	        try (Connection conn = DaoConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Pedido pedido = new Pedido();
	                pedido.setId(rs.getInt("id")); 
	                pedido.setStatus(rs.getString("status")); 
	                pedido.setValorTotal(rs.getDouble("valor_total")); 
	                pedidos.add(pedido);
	            }
	        }
	        return pedidos;
	    }
	
    public void inserirPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (status, valor_total) VALUES (?, ?)";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pedido.getStatus());
            stmt.setDouble(2, 0);  // Valor inicial zero
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));
                }
            }
        }
    }

    public void atualizarStatus(int pedidoId, String novoStatus) throws SQLException {
        String sql = "UPDATE pedido SET status = ? WHERE id = ?";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, pedidoId);
            stmt.executeUpdate();
        }
    }

    public double atualizarValorTotal(Pedido pedido) throws SQLException {
    	String sql = "SELECT SUM(pp.quantidade * p.preco) AS valor_total " +
                "FROM pedido_produto pp " +
                "JOIN produto p ON pp.produto_id = p.id " +
                "WHERE pp.pedido_id = ?";
   
	   try (Connection conn = DaoConnection.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql)) {
	       stmt.setInt(1, pedido.getId());
	       
	       try (ResultSet rs = stmt.executeQuery()) {
	           if (rs.next()) {
	               return rs.getDouble("valor_total");
	           }
	       }
	   }
	return 0;
    }
}

