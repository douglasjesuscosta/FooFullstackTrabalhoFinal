package modelos;

public class PagamentoDinheiro extends Pagamento{
	private Double troco;
	private Double valorPago;
	
	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public void calcularTroco(Double valorPago) {
		this.setTroco(this.getValorPago()- this.getValorPagamento());
	}

}
