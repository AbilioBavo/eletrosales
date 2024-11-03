package eletrosales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eletrosales.entities.Pedido;
import eletrosales.entities.PedidoProduto;
import eletrosales.entities.Produto;

public class PedidoProdutoDAO {

    public void inserirPedidoProduto(PedidoProduto pedidoProduto) throws SQLException {
        String sql = "INSERT INTO pedido_produto (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?,?, ?, ?)";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoProduto.getPedido().getId());
            stmt.setInt(2, pedidoProduto.getProduto().getId());
            stmt.setInt(3, pedidoProduto.getQuantidade());
            stmt.setDouble(4, pedidoProduto.getProduto().getPreco());
            stmt.executeUpdate();
        }
    }

    public List<PedidoProduto> buscarProdutosDoPedido(int pedidoId) throws SQLException {
        List<PedidoProduto> produtosDoPedido = new ArrayList<>();
        String sql = "SELECT * FROM pedido_produto WHERE pedido_id = ?";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new ProdutoDAO().buscarProdutoPorId(rs.getInt("produto_id"));
                    PedidoProduto pedidoProduto = new PedidoProduto(
                        new Pedido(pedidoId),
                        produto,
                        rs.getInt("quantidade")
                    );
                    produtosDoPedido.add(pedidoProduto);
                }
            }
        }
        return produtosDoPedido;
    }
}

