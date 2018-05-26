package servicos;

import java.util.Iterator;

import modelos.Caixa;

public class RelatorioVendasService {
	private static RelatorioVendasService relatorioVendasService;
	private CaixaService caixaService;
	
	public static RelatorioVendasService getInstanciaRelatorioVendasService() {
		if(relatorioVendasService == null)
			return relatorioVendasService = new RelatorioVendasService();
		else
			return relatorioVendasService;
	}
	
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
