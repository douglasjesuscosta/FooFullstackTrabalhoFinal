package servicos;

import java.util.ArrayList;
import java.util.List;

import modelos.Venda;

public class VendasService {

	private List<Venda> vendas = new ArrayList<>();
	
	public void adicionarVenda(Venda venda) {
		vendas.add(venda);
	}
}
