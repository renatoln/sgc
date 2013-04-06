package model;

import java.sql.Date;

public class UsuarioHasAluguel {

	private int idUsuario;
	private int idAluguel;
	private Date dataSolicitacao;
	
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdAluguel() {
		return idAluguel;
	}
	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	
}
