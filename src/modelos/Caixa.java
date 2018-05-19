package modelos;

import java.util.ArrayList;

import servicos.CaixaRegistradora;

public class Caixa {
	private int cod;
	private Funcionario funcionario;
	private ArrayList<Venda> vendas;
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
	
	public void iniciarCompra() {
		Venda venda = new Venda();
		venda.setRespVenda(funcionario);
		venda.setCaixaVenda(this);
		
		CaixaRegistradora caixaReg = new CaixaRegistradora();
		this.caixaReg.iniciarVenda(venda);
	}
	
	public void adicionarProduto(Produto produto) {
		this.caixaReg.adicionarProduto(produto);
	}
	
	public void receberPagamento(Pagamento pagamento) {
		this.getCaixaReg().receberPagamento(pagamento);
	}
	
	public Venda finalizarVenda() {
		Venda venda = this.getCaixaReg().finalizarCompra();
		this.getVendas().add(this.getCaixaReg().finalizarCompra());
		
		return venda;
	}

}
