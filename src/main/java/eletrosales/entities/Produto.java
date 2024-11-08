package eletrosales.entities;

public class Produto {
	
	private int id;
	private String nome;
	private String descricao;
	private Double preco;
	private int quantidadeEmEstoque;
	
	public Produto() {
	}

	public Produto(int id, String nome, String descricao, Double preco, int quantidadeEmEstoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	
	public Produto(String nome, String descricao, Double preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Produto(int id, String nome, String descricao, Double preco) {
		super();
		this.id=id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco;
	}
	

}
