package modelos;

public class ProdutoUnidade extends Produto{
	private int quantidade;

	@Override
	public double calcularPreco() {
		return this.getPreco() * quantidade;
	}
}
