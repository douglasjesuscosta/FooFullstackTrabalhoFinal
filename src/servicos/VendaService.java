package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Caixa;
import modelos.Pagamento;
import modelos.Produto;
import modelos.ProdutoUnidade;
import modelos.Venda;

public class VendaService {

	private Venda venda;
	private EstoqueService estoqueService = new EstoqueService();
	
	public VendaService() {
	}

	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public EstoqueService getEstoqueService() {
		return estoqueService;
	}

	public void setEstoqueService(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	public void adicionarProdutoUnidade(int codProduto, int qntProduto) {
		ProdutoUnidade produtoUnidade = (ProdutoUnidade) estoqueService.getProduto(codProduto);
		venda.adicionaProduto(produtoUnidade); 
	}
	
	public void iniciarVenda(Caixa caixa) {
		Venda venda = new Venda();
		venda.setRespVenda(caixa.getPessoa());
		venda.setCaixaVenda(caixa);
		this.setVenda(venda);
	}
	
	public Venda finalizarVenda(Caixa caixa) {
		Double total = somarTotalVenda(this.getVenda());
		String relatorioVenda = caixa.gerarRelatorioVenda(this.getVenda());
		this.getVenda().setTotal(total);
		this.getVenda().setRelatorioVenda(relatorioVenda);
		
		return this.getVenda();
	}
	
	public void adicionarProduto(Produto produto) {
		this.getVenda().getItens().add(produto);		
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
		this.getVenda().setPagamento(pagamento);
	}
	

	
}
