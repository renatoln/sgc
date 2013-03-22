package model;

/**
 * 
 * @author magno.oliveira
 *
 */
public class Espaco {
	
	private int idEspaco;
	private String nome;
	private String descricao;
	private Torre torre;
	
	public int getIdEspaco() {
		return idEspaco;
	}
	
	public void setIdEspaco(int idEspaco) {
		this.idEspaco = idEspaco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Torre getTorre() {
		return torre;
	}
	
	public void setTorre(Torre torre) {
		this.torre = torre;
	}
	
	public void setTorre(int torre) {
		this.torre = new TorreDAO().getOne(torre);
	}

}
