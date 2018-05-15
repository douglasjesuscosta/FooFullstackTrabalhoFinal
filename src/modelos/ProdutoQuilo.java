package modelos;

public class ProdutoQuilo extends Produto{
	private double quilo;

	@Override
	public double calcularPreco() {
		
		return quilo * this.getPreco();
	}
	
}
