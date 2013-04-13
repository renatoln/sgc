package model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import model.Aluguel;
import model.dao.FacadeSingletonDAO;
 
/**
 *
 * @author Magno
 */
@ManagedBean
@SessionScoped
public class AluguelBean implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private Aluguel aluguel = new Aluguel();
    private DataModel<Aluguel> alugueis;
 
    public String novo(){
        aluguel = new Aluguel();
        return "novoAluguel";
    }
 
    public String inserir(){
    	
    	String resultado = "erro";
    	try {
			new FacadeSingletonDAO(new Aluguel()).insert(aluguel);
			resultado = "listaAluguel";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return resultado;
    }
 
    public void selecionar(){
        aluguel = alugueis.getRowData();
    }
 
    public String alterar(){
        String resultado = "erro";
        
        try {
			new FacadeSingletonDAO(new Aluguel()).updateDados(aluguel);
			resultado = "listaAluguel";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return resultado;
    }
 
    public String remover(){
        String resultado = "erro";
        
        try {
			new FacadeSingletonDAO(new Aluguel()).exclui(aluguel);
			resultado = "listaAluguel";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        return resultado;
    }
 
    public String cancelar(){
        aluguel = new Aluguel();
        return "listaAluguel";
    }
    
    public Aluguel getAluguel() {
        return aluguel;
    }
 
    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }
 
    @SuppressWarnings("unchecked")
	public DataModel<Aluguel> getAlugueis() {
        
        List<Aluguel> aluguelList = null;
		try {
			aluguelList = (List<Aluguel>) new FacadeSingletonDAO(new Aluguel()).getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		alugueis = new ListDataModel<Aluguel>(aluguelList);
        return alugueis;
    }
 
    public void setAlugueis(DataModel<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }
 
}