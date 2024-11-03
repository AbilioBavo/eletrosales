package eletrosales.entities;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private String status;
    private double valorTotal;
    private List<PedidoProduto> produtosPedidos = new ArrayList<>();

    public Pedido() {
        this.status = "PENDENTE";
    }

    public Pedido(int pedidoId) {
		// TODO Auto-generated constructor stub
	}

	public void adicionarProduto(PedidoProduto pedidoProduto) {
        produtosPedidos.add(pedidoProduto);
        valorTotal += pedidoProduto.getProduto().getPreco() * pedidoProduto.getQuantidade();
    }

    public void removerProduto(PedidoProduto pedidoProduto) {
        produtosPedidos.remove(pedidoProduto);
        valorTotal -= pedidoProduto.getProduto().getPreco() * pedidoProduto.getQuantidade();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<PedidoProduto> getProdutosPedidos() {
		return produtosPedidos;
	}

	public void setProdutosPedidos(List<PedidoProduto> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}

}

