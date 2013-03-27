package model;

import java.sql.Date;

public class AndamentoServico {

	private int idAndamentoServico;
	private Date dat;
	private String descricao;
	private int operacao;
	private Usuario usuario;

	// private int Servico servico;

	public int getIdAndamentoServico() {
		return idAndamentoServico;
	}

	public void setIdAndamentoServico(int idAndamentoServico) {
		this.idAndamentoServico = idAndamentoServico;
	}

	public Date getDat() {
		return dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/*
	 * public Servico getServico() { return usuario; } public void
	 * setServico(Servico servico) { this.servico = servico; }
	 */
}
