package model;

import model.dao.EspacoDAO;
import model.dao.ServicoDAO;


public class Servico {

	private int idServico;
	private String descricao;
	private Espaco espaco;
	private TipoServico tipoServico;
	
	
	
	
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Espaco getEspaco() {
		return espaco;
	}
	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	public void setTipoServico(int tipoServico) {
		this.tipoServico = new ServicoDAO().getOne(tipoServico);
	}
	public void setEspaco(int espaco) {
		this.espaco = new EspacoDAO().getOne(espaco);
	}
	

}
