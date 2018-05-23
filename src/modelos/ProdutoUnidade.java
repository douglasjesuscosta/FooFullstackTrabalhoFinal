package modelos;

public class ProdutoUnidade extends Produto{
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public double calcularPreco() {
		return this.getPreco() * quantidade;
	}
}
