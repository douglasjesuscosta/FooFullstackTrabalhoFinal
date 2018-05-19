package modelos;

public class PagamentoDinheiro extends Pagamento{
	private Double troco;
	
	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public void calcularTroco(Double valorPago) {
		this.setTroco(valorPago - this.getValorPagamento());
	}

}
