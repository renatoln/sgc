package model;

import javax.faces.bean.ManagedBean;

/**
 * 
 * @author magno.oliveira
 *
 */
@ManagedBean
public class EspacoAlugavel extends Espaco {
	
	private String descricao;
	private int qtdPessoas;
	private double valor;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getQtdPessoas() {
		return qtdPessoas;
	}
	
	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDescricao();
	}
	
}
