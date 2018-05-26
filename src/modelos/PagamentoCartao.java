package modelos;

public class PagamentoCartao extends Pagamento{
	private String numeroCartao;
	private int qntParcelas;
	private double parcelas;
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public double getParcelas() {
		return parcelas;
	}
	public void setParcelas(double parcelas) {
		this.parcelas = parcelas;
	}
	public int getQntParcelas() {
		return qntParcelas;
	}
	public void setQntParcelas(int qntParcelas) {
		this.qntParcelas = qntParcelas;
	}
	
	
}
