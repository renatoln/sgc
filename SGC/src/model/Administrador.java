package model;

public class Administrador extends Usuario {

	public Administrador(){
		NivelUsuario n =  new NivelUsuario();
		n.setIdNivelUsuario(1);
		this.setNivelUsuario(n);
	}
}
