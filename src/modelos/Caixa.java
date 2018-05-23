package modelos;

import java.util.ArrayList;
import java.util.Iterator;

public class Caixa {
	private int cod;
	private Pessoa pessoa;
	private ArrayList<Venda> vendas;
	private Venda vendaAtual;
	
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
	public Venda getVendaAtual() {
		return vendaAtual;
	}
	public void setVendaAtual(Venda vendaAtual) {
		this.vendaAtual = vendaAtual;
	}
	
	public void iniciarVenda() {
		Venda venda = new Venda();
		venda.setRespVenda(this.getPessoa());
		venda.setCaixaVenda(this);
		this.setVendaAtual(venda);
	}
	
	public Venda finalizarVenda() {
		Double total = somarTotalVenda(this.getVendaAtual());
		String relatorioVenda = gerarRelatorioVenda(this.getVendaAtual());
		this.getVendaAtual().setTotal(total);
		this.getVendaAtual().setRelatorioVenda(relatorioVenda);
		this.getVendas().add(this.getVendaAtual());
		
		return this.getVendaAtual();
	}
	
	public void adicionarProduto(Produto produto) {
		this.getVendaAtual().getItens().add(produto);		
	}
	
	public Double somarTotalVenda(Venda venda) {
		Double resultado = 0.0;
		ArrayList<Produto> itens = venda.getItens();
		for(int i =0; i < itens.size(); i++) {
			resultado += itens.get(i).getPreco();
		}
		return resultado;
	}
	
	public void receberPagamento(Pagamento pagamento) {
		this.getVendaAtual().setPagamento(pagamento);
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

}
