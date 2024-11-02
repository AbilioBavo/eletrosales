package eletrosales.entities;

public class Cliente {

	private int id;
	private String nome;
	private String senha;
	private String endereco;
	private String email;
	private String telefone;
	
	public Cliente() {
	}

	public Cliente(int id, String nome, String senha, String endereco, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Cliente(String nome, String senha, String endereco, String email, String telefone) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Cliente(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}

	public Cliente(int id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", senha=" + senha + ", endereco=" + endereco + ", email="
				+ email + ", telefone=" + telefone + "]";
	}
}
