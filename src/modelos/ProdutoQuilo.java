package modelos;

public class ProdutoQuilo extends Produto{
	private double quilo;

	
	public double getQuilo() {
		return quilo;
	}
	public void setQuilo(double quilo) {
		this.quilo = quilo;
	}

	@Override
	public double calcularPreco() {
		
		return quilo * this.getPreco();
	}
	
}
