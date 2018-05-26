package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Caixa;
import modelos.Pagamento;
import modelos.Produto;
import modelos.ProdutoQuilo;
import modelos.ProdutoUnidade;
import modelos.Venda;

public class VendaService {

	private Venda venda;
	private EstoqueService estoqueService = EstoqueService.getInstanciaEstoqueService();

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
		ProdutoUnidade produtoUnidadeVenda = new ProdutoUnidade();
		clone(produtoUnidade, produtoUnidadeVenda);
		produtoUnidadeVenda.setQuantidade(qntProduto);
		this.venda.adicionaProduto(produtoUnidadeVenda); 
		estoqueService.retiraEstoque(produtoUnidadeVenda);
	}

	private void clone(Produto produto, Produto produtoVenda) {
		produtoVenda.setCodigo(produto.getCodigo());
		produtoVenda.setCodigoBarra(produto.getCodigoBarra());
		produtoVenda.setDescricao(produto.getDescricao());
		produtoVenda.setMarca(produto.getMarca());
		produtoVenda.setNome(produto.getNome());
		produtoVenda.setPrazoValidade(produto.getPrazoValidade());
		produtoVenda.setPreco(produto.getPreco());
	}
	
	public void iniciarVenda(Caixa caixa) {
		this.venda = new Venda();
		venda.setRespVenda(caixa.getPessoa());
		venda.setCaixaVenda(caixa);
		this.setVenda(venda);
	}
	
	public Venda finalizarVenda(Caixa caixa) {
		Double total = somarTotalVenda();
		String relatorioVenda = caixa.gerarRelatorioVenda(this.getVenda());
		this.getVenda().setTotal(total);
		this.getVenda().setRelatorioVenda(relatorioVenda);
		
		return this.getVenda();
	}
	
	public void adicionarProduto(Produto produto) {
		this.getVenda().getItens().add(produto);		
	}
	
	public Double somarTotalVenda() {
		Double resultado = 0.0;
		ArrayList<Produto> itens = venda.getItens();
		for(int i =0; i < itens.size(); i++) {
			resultado += itens.get(i).calcularPreco();
		}
		return resultado;
	}
	
	public void receberPagamento(Pagamento pagamento) {
		this.getVenda().setPagamento(pagamento);
	}

	public void adicionarProdutoQuilo(int codProduto, double kgProduto) {
		ProdutoQuilo produtoKg = (ProdutoQuilo) estoqueService.getProduto(codProduto);
		ProdutoQuilo produtoKgVenda = new ProdutoQuilo();
		clone(produtoKg, produtoKgVenda);
		produtoKgVenda.setQuilo(kgProduto);
		this.venda.adicionaProduto(produtoKgVenda); 
		estoqueService.retiraEstoque(produtoKgVenda);		
	}
	

	
}
