package model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import model.Torre;
import model.dao.FacadeSingletonDAO;

@ManagedBean
@SessionScoped
public class TorreBean implements Serializable{
	 
		private static final long serialVersionUID = 1L;
		private Torre torre = new Torre();
	    private DataModel<Torre> torres;
	 
	    public String novo(){
	        torre = new Torre();
	        return "novaTorre";
	    }
	 
	    public String inserir(){
	    	
	    	String resultado = "erro";
	    	try {
				new FacadeSingletonDAO(new Torre()).insert(torre);
				resultado = "listaTorre";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	return resultado;
	    }
	 
	    public void selecionar(){
	        torres = torres.getRowData();
	    }
	 
	    public String alterar(){
	        String resultado = "erro";
	        
	        try {
				new FacadeSingletonDAO(new Torre()).updateDados(torre);
				resultado = "listaTorres";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	        return resultado;
	    }
	 
	    public String remover(){
	        String resultado = "erro";
	        
	        try {
				new FacadeSingletonDAO(new Torre()).exclui(torre);
				resultado = "listaTorres";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	        return resultado;
	    }
	 
	    public String cancelar(){
	        torre = new Torre();
	        return "listaTorres";
	    }
	    
	    public Torre getTorre() {
	        return torre;
	    }
	 
	    public void setTorre(Torre torre) {
	        this.torre = torre;
	    }
	 
	    @SuppressWarnings("unchecked")
		public DataModel<Torre> getTorres() {
	        
	        List<Torre> torreList = null;
			try {
				torreList = (List<Torre>) new FacadeSingletonDAO(new Torre()).getList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			torres = new ListDataModel<Torre>(torreList);
	        return torres;
	    }
	 
	    public void setTorres(DataModel<Torre> Torres) {
	        this.torres = Torres;
	    }
	 
	}
}
