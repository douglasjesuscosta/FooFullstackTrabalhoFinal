package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Caixa;
import modelos.Data;
import modelos.Produto;
import modelos.ProdutoQuilo;
import modelos.ProdutoUnidade;
import modelos.Venda;

public class RelatorioEstoqueService {
	
	private static RelatorioEstoqueService relatorioEstoqueService;
	private EstoqueService estoque;
	private CaixaService caixa;
	
	public static RelatorioEstoqueService getInstanciaRelatorioEstoqueService() {
		if(relatorioEstoqueService == null)
			return relatorioEstoqueService = new RelatorioEstoqueService();
		else
			return relatorioEstoqueService;
	}
	
	public EstoqueService getEstoqueService() {
		return estoque;
	}
	public void setEstoqueService(EstoqueService estoque) {
		this.estoque = estoque;
	}
	
	public CaixaService getCaixaService() {
		return caixa;
	}
	public void setCaixaService(CaixaService caixa) {
		this.caixa = caixa;
	}
	public ArrayList<Produto> computarProdutos(ArrayList<Venda> vendas, Data data) {
		ArrayList<Produto> produtos = (ArrayList<Produto>) estoque.getProdutos();
		ArrayList<Produto> produtosVenda;
		ArrayList<Produto> produtosInicioDia = new ArrayList<Produto>();
		
		Venda vend;
		Produto prod1;
		Produto prod2;
		
		for(int i = 0; i < vendas.size(); i++) {
			vend = vendas.get(i);
			
			if(vend.getDataVenda().getDia() == data.getDia() && vend.getDataVenda().getMes() == data.getMes() && vend.getDataVenda().getAno() == data.getAno()) {
				produtosVenda = vend.getItens();
				
				for(int y = 0; y < produtosVenda.size(); y++) {
					prod1 = produtosVenda.get(y);
					
					for(int z = 0; z < produtos.size(); z++) {
						prod2 = produtos.get(z);
						
						if(prod1.getCodigo() == prod2.getCodigo()) {
							
							if(prod2 instanceof ProdutoQuilo) {
								ProdutoQuilo produto = new ProdutoQuilo();
								atribuirAtributos(prod2, produto);
								
								produto.setQuilo(((ProdutoQuilo) prod2).getQuilo() + ((ProdutoQuilo)prod1).getQuilo());
								produtosInicioDia.add(produto);
							}else {
								ProdutoUnidade produto = new ProdutoUnidade();
								atribuirAtributos(prod2, produto);
								
								produto.setQuantidade(((ProdutoUnidade) prod2).getQuantidade() + ((ProdutoUnidade) prod1).getQuantidade());
								produtosInicioDia.add(produto);
							}
						}
					}	
					
				}
			}
		}
		
		return produtosInicioDia;
	}
	
	public void atribuirAtributos(Produto origem, Produto destino) {
		destino.setCodigo(origem.getCodigo());
		destino.setCodigoBarra(origem.getCodigoBarra());
		destino.setDescricao(origem.getDescricao());
		destino.setMarca(origem.getMarca());
		destino.setNome(origem.getNome());
		destino.setPrazoValidade(origem.getPrazoValidade());
		destino.setPreco(origem.getPreco());
	}


	public ArrayList computarTotalProdutos(Data diaRelatorio) {
		String relatorio = "";
		ArrayList<Produto> produtosInicioDia = new ArrayList<Produto>();
		
		Iterator it2 = caixa.getCaixas().iterator();
		
		Produto produto;
		
		while(it2.hasNext()) {
			Caixa caixa = (Caixa) it2.next();
			produtosInicioDia.addAll((computarProdutos(caixa.getVendas(), diaRelatorio)));
		}
		
		return produtosInicioDia;	
	}

	
	public String gerarRelatorio(Data data) {
		ArrayList<Produto> produtosInicioDia = computarTotalProdutos(data);
		ArrayList<Produto> produtosEstoque = (ArrayList<Produto>) estoque.getProdutos();
		
		Iterator it = produtosEstoque.iterator();
		Produto prod;
		String relatorio = "============Relatorio de Produtos==============\n";
		relatorio += "PRODUTOS EM ESTOQUE:\n";
		
		while(it.hasNext()) {
			prod = (Produto) it.next();
			relatorio += "Produto: " + prod.getNome() + "\n";
			relatorio += "Descrição: " + prod.getDescricao() + "\n";
			
			if(prod instanceof ProdutoQuilo) {
				relatorio += "Quilos: " + ((ProdutoQuilo) prod).getQuilo() + "\n\n";
			}else {
				relatorio += "Quantidade: " + ((ProdutoUnidade) prod).getQuantidade() + "\n\n";
			}
		}
		relatorio += "QUANTIDADE AO INICIO DO DIA DE PRODUTOS COMPRADOS NA DATA: " + data.getDia() + "/" + data.getMes() + "/" + data.getAno() + "\n";
		Iterator it2 = produtosInicioDia.iterator();
		
		if(!it2.hasNext())
			System.out.println("Nenhuma alteração!");
		
		while(it2.hasNext()) {
			prod = (Produto) it2.next();
			relatorio += "Produto: " + prod.getNome() + "\n";
			relatorio += "Descrição: " + prod.getDescricao() + "\n";
			
			if(prod instanceof ProdutoQuilo) {
				relatorio += "Quilos: " + ((ProdutoQuilo) prod).getQuilo() + "\n\n";
			}else {
				relatorio += "Quantidade: " + ((ProdutoUnidade) prod).getQuantidade() + "\n\n";
			}
		}
		
		return relatorio;
		
	}
}
