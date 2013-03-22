package model;


/**
 * 
 * @author magno.oliveira
 *
 */
public class ResponsaveisDoApartamento {
	
	private int idResponsavisDoApatamento;
	private Apartamento apartamento;
	private Usuario usuario;
	
	public int getIdResponsavisDoApatamento() {
		return idResponsavisDoApatamento;
	}
	
	public void setIdResponsavisDoApatamento(int idResponsavisDoApatamento) {
		this.idResponsavisDoApatamento = idResponsavisDoApatamento;
	}
	
	public Apartamento getApartamento() {
		return apartamento;
	}
	
	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	public void setApartamento(int apartamento) {
		this.apartamento = new ApartamentoDAO().getOne(apartamento);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setUsuario(int usuario) {
		this.usuario = new UsuarioDAO().getInt(usuario);
	}
}
