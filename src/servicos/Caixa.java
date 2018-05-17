package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Funcionario;
import modelos.Produto;
import modelos.Venda;

public class Caixa {
	
	private Venda venda;

	public Caixa(Funcionario funcionario) {
		this.setVenda(new Venda());
		this.getVenda().setRespVenda(funcionario);
	}

	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	public void adicionarProduto(Produto produto) {
		this.getVenda().getItens().add(produto);		
	}
	
	public Venda finalizarCompra() {
		this.somarTotalCompra();
		this.gerarRelatorioCompra();
		return this.getVenda();
	}
	
	public void somarTotalCompra() {
		Double resultado = 0.0;
		ArrayList<Produto> itens = this.getVenda().getItens();
		for(int i =0; i < itens.size(); i++) {
			resultado += itens.get(i).getPreco();
		}
		this.getVenda().setTotal(resultado);
	}
	
	public void gerarRelatorioCompra() {
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
		this.getVenda().setRelatorioVenda(relatorioVenda);
	}
	
	

}
