package servicos;

import java.util.ArrayList;
import java.util.List;

import modelos.Venda;

public class VendasService {

	private List<Venda> vendas;
	private static VendasService vendasService;
	
	private VendasService() {
		vendas = new ArrayList<>();
	}
	
	public static VendasService getInstanciaVendasService() {
		if(vendasService == null)
			return vendasService = new VendasService();
		else
			return vendasService;
	}
	
	public void adicionarVenda(Venda venda) {
		vendas.add(venda);
	}
}
