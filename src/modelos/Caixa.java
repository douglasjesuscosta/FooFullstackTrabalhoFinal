package modelos;

import java.util.ArrayList;
import java.util.Iterator;

public class Caixa {
	private int cod;
	private Pessoa pessoa;
	private ArrayList<Venda> vendas;
	
	public Caixa() {
		vendas = new ArrayList<Venda>();
	}
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Pessoa getPessoa() {
		return this.pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public ArrayList<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(ArrayList<Venda> vendas) {
		this.vendas = vendas;
	}
	
	public String gerarRelatorioVenda(Venda venda) {
		ArrayList<Produto> itens = venda.getItens();
		String relatorioVenda = "";
		relatorioVenda += "\t\t\t===== RELATORIO DE VENDAS =====\t\t\t\n";
		relatorioVenda += "Vendedor: " + venda.getRespVenda().getNome() + "\n";
		relatorioVenda += "Produtos: \n";
		
		Iterator it = itens.iterator();
		Produto prod;
		
		while(it.hasNext()) {
			prod = (Produto) it.next();
			relatorioVenda += "-----------------------------------------\n";
			relatorioVenda += "Item: " + prod.getNome() + " ";
			relatorioVenda += "Preco: " + prod.getPreco() + "\n";
			relatorioVenda += "-----------------------------------------";
		}
		
		relatorioVenda += "Total: " + venda.getTotal() + "\n";
		return relatorioVenda;
	}
	
	public String gerarRelatorioVendas() {
		
		String relatorioVendas = "";
		relatorioVendas += "\t\t\t===== RELATORIO DE VENDAS CAIXA" + this.getCod() +"=====\t\t\t\n";
		
		Iterator it = vendas.iterator();
		Venda vend;
		
		while(it.hasNext()) {
			vend = (Venda) it.next();
			relatorioVendas += "-------------------VENDA----------------------\n";
			relatorioVendas += "Realizada por : " + vend.getRespVenda().getNome() + " ";
			relatorioVendas += "Total: " + vend.getTotal() + "\n";
			relatorioVendas += "-----------------------------------------\n";
		}
	
		return relatorioVendas;
	}
	

}
