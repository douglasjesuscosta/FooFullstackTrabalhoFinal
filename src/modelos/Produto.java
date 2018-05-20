package modelos;

import java.util.Date;

public abstract class Produto {
	private int codigo;
	private String codigoBarra;
	private String nome;
	private String marca;
	private Date prazoValidade;
	private String descricao;
	private double preco;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getPrazoValidade() {
		return prazoValidade;
	}
	public void setPrazoValidade(Date prazoValidade) {
		this.prazoValidade = prazoValidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public abstract double calcularPreco();

}
