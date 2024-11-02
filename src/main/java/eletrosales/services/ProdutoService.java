package eletrosales.services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import eletrosales.dao.ProdutoDAO;
import eletrosales.entities.Produto;
import eletrosales.exceptions.DataAccessException;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    public Produto buscarProduto(int id) {
        try {
            return produtoDAO.buscarProdutoPorId(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar produto com ID " + id, e);
            throw new DataAccessException("Erro ao buscar produto", e);
        }
    }

    public List<Produto> listarProdutos() {
        try {
            return produtoDAO.listarProdutos();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar produtos", e);
            throw new DataAccessException("Erro ao listar produtos", e);
        }
    }

    public boolean criarProduto(Produto produto) {
        try {
            return produtoDAO.inserirProduto(produto);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao criar produto", e);
            throw new DataAccessException("Erro ao criar produto", e);
        }
    }

    public boolean atualizarProduto(Produto produto) {
        try {
            return produtoDAO.atualizarProduto(produto);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar produto com ID " + produto.getId(), e);
            throw new DataAccessException("Erro ao atualizar produto", e);
        }
    }

    public boolean excluirProduto(int id) {
        try {
            return produtoDAO.deletarProduto(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir produto com ID " + id, e);
            throw new DataAccessException("Erro ao excluir produto", e);
        }
    }
}
