package model.dao;

import java.sql.SQLException;
import java.util.Vector;

/**
 * 
 * @author magno.oliveira
 *
 */
public final class FacadeSingletonDAO {

	private DAO dao;
    private static FacadeSingletonDAO instance;
	 
     public static FacadeSingletonDAO getInstance(Object objeto){
    	 if(instance == null){
    		 instance = new FacadeSingletonDAO(objeto);
    	 }
    	 return instance;
     }
	
	/**
	 * Esta classe recebe um objeto e tenta instanciar um DAO para acessar o banco de dados e manipular suas informa��es.
	 * @param objeto
	 */
	public FacadeSingletonDAO(Object objeto){
		
		String nomeDAO;
		Class<?> objetoDAO;
		try {
			nomeDAO = "model.dao."+objeto.getClass().getSimpleName()+"DAO";
			objetoDAO = Class.forName(nomeDAO);
			dao = (DAO) objetoDAO.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("Essa classe ainda n�o implemnta DAO! ");
		} catch (InstantiationException e) {
			System.out.print("N�o foi possivel instaniar o DAO! ");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.print("N�o foi possivel acessar o DAO! ");
			e.printStackTrace();
		}
		
	}
	
	public void exclui(Object o) throws SQLException {
		dao.exclui(o);
	}
	
	public Vector<?> getList(String queryComplementar) throws SQLException {
		return dao.getList(queryComplementar);
	}
	
	public Vector<?> getList() throws SQLException {
		return dao.getList();
	}
	
	public Object getOne(String queryComplementar) throws SQLException {
		return dao.getOne(queryComplementar);
	}
	
	public Object getOne(int queryComplementar) throws SQLException {
		return dao.getOne(queryComplementar);
	}
	
	public void insert(Object o) throws SQLException {
		dao.insert(o);
	}
	
	public void updateDados(Object o) throws SQLException {
		dao.updateDados(o);
	}

}
