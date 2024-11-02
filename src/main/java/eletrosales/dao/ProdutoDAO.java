package eletrosales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eletrosales.entities.Produto;

public class ProdutoDAO {

    public boolean inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, descricao, preco) VALUES (?, ?, ?)";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.executeUpdate();
        }
		return false;
    }

    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = DaoConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                        rs.getDouble("preco"));
                produtos.add(produto);
            }
        }
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) throws SQLException {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
                            rs.getDouble("preco"), rs.getInt("quantidade_em_estoque"));
                }
            }
        }
        return produto;
    }

    public boolean atualizarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade_em_estoque = ?, categoria = ? WHERE id = ?";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidadeEmEstoque());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
        }
		return false;
    }

    public boolean deletarProduto(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = DaoConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
		return false;
    }
}

