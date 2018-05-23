package servicos;

import java.util.ArrayList;
import java.util.List;

import modelos.Produto;
import modelos.ProdutoUnidade;

public class EstoqueService {

	private List<Produto> produtos = new ArrayList<>();
	
	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
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

	public boolean verificarQuantidade(int codProduto, int qntProduto) {
		
		for (Produto produto : produtos) {
			if(produto.getCodigo() == codProduto) {
				ProdutoUnidade produtoUnidade = (ProdutoUnidade) produto;
				if(produtoUnidade.getQuantidade() >= qntProduto)
					return true;
			}
		}
		return false;
	}
}
