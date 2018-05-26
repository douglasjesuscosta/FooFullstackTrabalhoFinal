package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Caixa;

public class RelatorioVendasService {
	private CaixaService caixaService;
	
	

	public CaixaService getCaixaService() {
		return caixaService;
	}
	public void setCaixaService(CaixaService caixaService) {
		this.caixaService = caixaService;
	}

	public String relatorioVendas() {
		
		Iterator it = caixaService.getCaixas().iterator();
		Caixa caixa;
		String relatorio = "";
		
		while(it.hasNext()) {
			caixa = (Caixa) it.next();
			relatorio += "==================Caixa " + caixa.getCod() + "==================\n";
			relatorio += caixa.gerarRelatorioVendas() + "\n";
		}
		
		return relatorio;
	}

}
