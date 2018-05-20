package modelos;

import java.util.ArrayList;

import servicos.CaixaRegistradora;

public class Caixa {
	private int cod;
	private Funcionario funcionario;
	private ArrayList<Venda> vendas;
	private Venda vendaAtual;
	private CaixaRegistradora caixaReg;
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public ArrayList<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(ArrayList<Venda> vendas) {
		this.vendas = vendas;
	}
	public CaixaRegistradora getCaixaReg() {
		return caixaReg;
	}
	public void setCaixaReg(CaixaRegistradora caixaReg) {
		this.caixaReg = caixaReg;
	}
	
	public Venda getVendaAtual() {
		return vendaAtual;
	}
	public void setVendaAtual(Venda vendaAtual) {
		this.vendaAtual = vendaAtual;
	}
	
	public void iniciarCompra() {
		Venda venda = new Venda();
		venda.setRespVenda(funcionario);
		venda.setCaixaVenda(this);
		this.setVendaAtual(venda);
		
		CaixaRegistradora caixaReg = new CaixaRegistradora();
		this.setCaixaReg(caixaReg);
	}
	
	public void adicionarProduto(Produto produto) {
		this.getVendaAtual().getItens().add(produto);
	}
	
	public void receberPagamento(Pagamento pagamento) {
		this.getCaixaReg().receberPagamento(pagamento, this.getVendaAtual());
	}
	
	public Venda finalizarVenda() {
		Double total = this.getCaixaReg().somarTotalCompra(this.getVendaAtual());
		String relatorioVenda = this.getCaixaReg().gerarRelatorioCompra(this.getVendaAtual());
		this.getVendaAtual().setTotal(total);
		this.getVendaAtual().setRelatorioVenda(relatorioVenda);
		this.getVendas().add(this.getVendaAtual());
		
		return this.getVendaAtual();
	}

}
