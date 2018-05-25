package modelos;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	private Double total;
	private Pagamento pagamento;
	private ArrayList<Produto> itens;
	private Pessoa respVenda;
	private Data dataVenda;
	private String relatorioVenda;
	private Caixa caixaVenda;

	public Venda() {
		this.setItens(new ArrayList<Produto>());
	}

	public Data getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Data dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getRelatorioVenda() {
		return relatorioVenda;
	}

	public void setRelatorioVenda(String relatorioVenda) {
		this.relatorioVenda = relatorioVenda;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public Pessoa getRespVenda() {
		return respVenda;
	}

	public void setRespVenda(Pessoa respVenda) {
		this.respVenda = respVenda;
	}

	public Caixa getCaixaVenda() {
		return caixaVenda;
	}

	public void setCaixaVenda(Caixa caixaVenda) {
		this.caixaVenda = caixaVenda;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public void adicionaProduto(Produto produto) {
		itens.add(produto);
	}
	
	
}

