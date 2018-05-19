package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Pagamento;
import modelos.Produto;
import modelos.Venda;

public class CaixaRegistradora {
	
	private Venda venda;

	public CaixaRegistradora() {
		
	}

	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public void iniciarVenda(Venda venda) {
		this.setVenda(venda);
	}
	
	public void adicionarProduto(Produto produto) {
		this.getVenda().getItens().add(produto);		
	}
	
	public Venda finalizarCompra() {
		venda.setTotal(this.somarTotalCompra());
		venda.setRelatorioVenda(this.gerarRelatorioCompra());
		
		Venda venda = this.getVenda();
		this.setVenda(new Venda());
		
		return venda;
	}
	
	public Double somarTotalCompra() {
		Double resultado = 0.0;
		ArrayList<Produto> itens = this.getVenda().getItens();
		for(int i =0; i < itens.size(); i++) {
			resultado += itens.get(i).getPreco();
		}
		return resultado;
	}
	
	public void receberPagamento(Pagamento pagamento) {
		this.getVenda().setPagamento(pagamento);
	}
	
	public String gerarRelatorioCompra() {
		ArrayList<Produto> itens = this.getVenda().getItens();
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
