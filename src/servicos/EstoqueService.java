package servicos;

import java.util.ArrayList;
import java.util.List;

import modelos.Produto;
import modelos.ProdutoQuilo;
import modelos.ProdutoUnidade;

public class EstoqueService {

	private static EstoqueService estoqueService;
	private List<Produto> produtos;
	
	private EstoqueService() {
		produtos = new ArrayList<Produto>();
	}
	
	public static EstoqueService getInstanciaEstoqueService() {
		if(estoqueService == null)
			return estoqueService = new EstoqueService();
		else 
			return estoqueService;
	}
	
	public void adicionarProduto(Produto produto) {
		this.produtos.add(produto);
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public boolean verificarCodigo(int codigo) {
		
		for (Produto produto : produtos) {
			if(produto.getCodigo() == codigo) 
				return true;
		}
		
		return false;
	}
	
	public Produto getProdutoByCodigoBarra(String codigoBarra) { 
		Produto produtoSelecionado = null;
		for (Produto produto : produtos) {
			if(codigoBarra.equals(produto.getCodigoBarra())) {
				produtoSelecionado = produto;
				break;
			}
		}
		
		return produtoSelecionado;
	}

	public Produto getProduto(int codProduto) {
		
		Produto produto = null;
		
		for (Produto p : produtos) {
			if(p.getCodigo() == codProduto)
				produto = p;
		}
		return produto;
	}

	public boolean verificarQuantidadeUnidade(int codProduto, int qntProduto) {
		
		for (Produto produto : produtos) {
			if(produto.getCodigo() == codProduto) {
				ProdutoUnidade produtoUnidade = (ProdutoUnidade) produto;
				if(produtoUnidade.getQuantidade() >= qntProduto)
					return true;
			}
		}
		return false;
	}

	public void retiraEstoque(Produto produtoVendido) {
		
		for (Produto produto : produtos) {
			if(produto.getCodigo() == produtoVendido.getCodigo()) {
				
				if(produtoVendido instanceof ProdutoUnidade) {
					ProdutoUnidade produtoUnidade = (ProdutoUnidade) produto;
					produtoUnidade.setQuantidade(produtoUnidade.getQuantidade() - ((ProdutoUnidade)produtoVendido).getQuantidade());
					break;
				}
				else {
					ProdutoQuilo produtoQuilo = (ProdutoQuilo) produto;
					produtoQuilo.setQuilo(produtoQuilo.getQuilo() - ((ProdutoQuilo)produtoVendido).getQuilo());
					break;
				}
				
			}
		}
	}

	public boolean verificarQuantidadeKg(int codProduto, double kgProduto) {
		for (Produto produto : produtos) {
			if(produto.getCodigo() == codProduto) {
				ProdutoQuilo produtoKg = (ProdutoQuilo) produto;
				if(produtoKg.getQuilo() >= kgProduto)
					return true;
			}
		}
		return false;
	}
}
