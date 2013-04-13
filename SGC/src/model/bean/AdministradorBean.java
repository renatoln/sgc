package model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import model.Administrador;
import model.dao.FacadeSingletonDAO;
 
/**
 *
 * @author Magno
 */
@ManagedBean
@SessionScoped
public class AdministradorBean implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private Administrador administrador = new Administrador();
    private DataModel<Administrador> administradores;
 
    public String novo(){
        administrador = new Administrador();
        return "novoAdministrador";
    }
 
    public String inserir(){
    	
    	String resultado = "erro";
    	try {
			new FacadeSingletonDAO(new Administrador()).insert(administrador);
			resultado = "listaAdministrador";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return resultado;
    }
 
    public void selecionar(){
        administrador = administradores.getRowData();
    }
 
    public String alterar(){
        String resultado = "erro";
        
        try {
			new FacadeSingletonDAO(new Administrador()).updateDados(administrador);
			resultado = "listaAdministrador";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return resultado;
    }
 
    public String remover(){
        String resultado = "erro";
        
        try {
			new FacadeSingletonDAO(new Administrador()).exclui(administrador);
			resultado = "listaAdministrador";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return resultado;
    }
 
    public String cancelar(){
        administrador = new Administrador();
        return "listaAdministrador";
    }
    
    public Administrador getAdministrador() {
        return administrador;
    }
 
    public void setAluguel(Administrador administrador) {
        this.administrador = administrador;
    }
 
    @SuppressWarnings("unchecked")
	public DataModel<Administrador> getAdministradores() {
        
        List<Administrador> administradorList = null;
		try {
			administradorList = (List<Administrador>) new FacadeSingletonDAO(new Administrador()).getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		administradores = new ListDataModel<Administrador>(administradorList);
        return administradores;
    }
 
    public void setAlugueis(DataModel<Administrador> administradores) {
        this.administradores = administradores;
    }
 
}