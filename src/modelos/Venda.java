package modelos;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	private Double total;
	private ArrayList<Produto> itens;
	private Funcionario respVenda;
	private Date dataVenda;
	private String relatorioVenda;
	
	public Venda() {
		this.setItens(new ArrayList<Produto>());
	}
	
	
	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
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
	public Funcionario getRespVenda() {
		return respVenda;
	}
	public void setRespVenda(Funcionario respVenda) {
		this.respVenda = respVenda;
	}
	
	

}
