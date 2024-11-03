package eletrosales.services;

import java.sql.SQLException;
import java.util.List;

import eletrosales.dao.PedidoDAO;
import eletrosales.dao.PedidoProdutoDAO;
import eletrosales.entities.Pedido;
import eletrosales.entities.PedidoProduto;
import eletrosales.entities.Produto;

public class PedidoService {
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO();

    public void criarPedido(Pedido pedido) throws SQLException {
        pedidoDAO.inserirPedido(pedido);
    }
    
    public Pedido buscarPedidoPorId(int pedidoId) throws SQLException {
        return pedidoDAO.buscarPedidoPorId(pedidoId);
    }

    public void adicionarProdutoAoPedido(Pedido pedido, Produto produto, int quantidade) throws SQLException {
        if (quantidade > 0 && quantidade <= produto.getQuantidadeEmEstoque()) {
            PedidoProduto pedidoProduto = new PedidoProduto(pedido, produto, quantidade);
            pedido.adicionarProduto(pedidoProduto);
            pedidoProdutoDAO.inserirPedidoProduto(pedidoProduto);

            pedidoDAO.atualizarValorTotal(pedido); 
        } else {
            throw new IllegalArgumentException("Quantidade inválida.");
        }
    }

    public List<PedidoProduto> listarProdutosDoPedido(int pedidoId) throws SQLException {
        return pedidoProdutoDAO.buscarProdutosDoPedido(pedidoId);
    }

    public void excluirProdutoDoPedido(Pedido pedido, PedidoProduto pedidoProduto) throws SQLException {
        pedido.removerProduto(pedidoProduto);
    }
    
    public List<Pedido> listarTodosPedidos() throws SQLException {
        return pedidoDAO.listarTodosPedidos();
    }
    
    public void finalizarPedido(Pedido pedido) throws SQLException {
        pedidoDAO.atualizarStatus(pedido.getId(), "ENVIADO");
    }
}

