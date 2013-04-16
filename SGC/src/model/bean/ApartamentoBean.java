package model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import model.Apartamentos;
import model.dao.FacadeSingletonDAO;

@ManagedBean
@SessionScoped
public class ApartamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Apartamentos apto = new Apartamentos();
    private DataModel <Apartamentos> aptos;
 
    public String novo(){
        apto = new Apartamentos();
        return "novoApartamento";
    }
 
    public String inserir(){
    	
    	String resultado = "erro";
    	try {
			new FacadeSingletonDAO(new Apartamentos()).insert(apto);
			resultado = "listaApartamento";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return resultado;
    }
 
    public void selecionar(){
        aptos = aptos.getRowData();
    }
 
    public String alterar(){
        String resultado = "erro";
        
        try {
			new FacadeSingletonDAO(new Apartamentos()).updateDados(apto);
			resultado = "listaApartamento";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return resultado;
    }
 
    public String remover(){
        String resultado = "erro";
        
        try {
			new FacadeSingletonDAO(new Apartamentos()).exclui(apto);
			resultado = "listaApartamentos";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return resultado;
    }
 
    public String cancelar(){
        apto = new Apartamentos();
        return "listaApartamento";
    }
    
    public Apartamentos getApartamentos() {
        return apto;
    }
 
    public void setApartamentos(Apartamentos apto) {
        this.apto = apto;
    }
 
    @SuppressWarnings("unchecked")
	public DataModel<Apartamentos> getAptos() {
        
        List<Apartamentos> ApartamentosList = null;
		try {
			ApartamentosList = (List<Apartamentos>) new FacadeSingletonDAO(new Apartamentos()).getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		aptos = new ListDataModel <Apartamentos>(ApartamentosList);
        return aptos;
    }
 
    public void setAptos(DataModel<Apartamentos> aptos) {
        this.aptos = aptos;
    }
 

}

	

