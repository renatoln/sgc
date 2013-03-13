package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.dao.connection.ConnectionFactory;


/**
 * @author Magno
 *
 */
public abstract class DAO {

	private final String BANCO="emprestimo";
	private final String LOGIN="ifba";
	private final String SENHA="ifba";
	
	protected Vector<?> objetos = null;
	protected Object objeto;
	protected ResultSet rs;
	protected Connection connection;
	
	/**
	 * Informa qual o campo id da tabela a ser mapeado pelo Bean.
	 */
	protected String id;
		
	/**
	 * Implementando os query para facilar alterações e simplificar o código
	 */
	protected String table;
	protected String select;
	protected String delete;
	
	
	
	/**
	 * @throws SQLException 
	 * 
	 */
	protected DAO() throws SQLException{
		this.connection = ConnectionFactory.getMySQLConnection("localhost:3306/"+BANCO, LOGIN, SENHA);
	}
	
	/**
	 * Inserir objeto no banco de dados...
	*/
	public abstract void insert(Object o) throws SQLException;

	/**Atualizar dados...
	 * Para tanto é necessário que seja passado um objeto
	 * @param o
	 * @throws SQLException
	 */
	public abstract void updateDados(Object o) throws SQLException;	

	/**
	 * Buscar um objeto por um campo e valor qualquer
	 * por exemplo ID=1 ou Nome=Magno...
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	protected final Object getOne(String field, String value) throws SQLException{
		String queryComplementar =" WHERE "+field+" like '"+value+"'";
		objeto=this.getOne(queryComplementar);
		return objeto;
	}
	
	/**
	 * Buscar um objeto por um campo e valor qualquer
	 * por exemplo ID=1 ou Nome=Magno...
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	protected final Object getOne(String field, long value) throws SQLException{
		String queryComplementar =" WHERE "+field+" like '"+value+"'";
		objeto=this.getOne(queryComplementar);
		return objeto;
	}
	
	/**
	 * Buscar um objeto por dois campos
	 * exemplo login="magno", senha="123"
	 * @param field1
	 * @param value1
	 * @param field2
	 * @param value2
	 * @return
	 * @throws SQLException
	 */
	protected final Object getOne(String field1, String value1, String field2, String value2) throws SQLException{
		String queryComplementar =" WHERE "+field1+" like '"+value1+"' and "+field2+" like '"+value2+"'";
		objeto=this.getOne(queryComplementar);
		return objeto;
	}
	
	/**
	 * 
	 * @param field
	 * @param value
	 * @param field1
	 * @param value1
	 * @param field2
	 * @param value2
	 * @return
	 * @throws SQLException
	 */
	protected final Object getOne(String field, String value ,String field1, String value1, String field2, String value2) throws SQLException{
		String queryComplementar =" WHERE "+field+" like '"+value+"' and "+field1+" like '"+value1+"' and "+field2+" like '"+value2+"'";
		objeto=this.getOne(queryComplementar);
		return objeto;
	}
	
	protected final Object getOne(String field, String value ,String field1, String value1, String field2, String value2, String field3, String value3) throws SQLException{
		String queryComplementar =" WHERE "+field+" like '"+value+"' and "+field1+" like '"+value1+"' and "+field2+" like '"+value2+"' and "+field3+" like '"+value3+"'";
		objeto=this.getOne(queryComplementar);
		return objeto;
	}
	
	protected final Object getOne(String field, String value ,String field1, long value1, String field2, long value2) throws SQLException{
		String queryComplementar =" WHERE "+field+" like '"+value+"' and "+field1+" like '"+value1+"' and "+field2+" like '"+value2+"'";
		objeto=this.getOne(queryComplementar);
		return objeto;
	}
	
	/**
	 * Buscar um objeto por um campo
	 * por exemplo ID=1...
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public final Object getOne(long idObjeto) throws SQLException{
		String queryComplementar =" WHERE "+getId()+" ="+idObjeto;
		objeto=this.getOne(queryComplementar);
		return objeto;
	}

	/**
	 * Metodo que é especializado em receber os paremetros e devolver um objeto de um tipo específico.
	 * Deve ser implementado em todas as classes DAO.
	 * @param queryComplementar
	 * @return
	 * @throws SQLException
	 */
	protected abstract Object getOne(String queryComplementar) throws SQLException;
	
	/**
	 * Buscar lista com todos os objetos...
	 * @return
	 * @throws SQLException
	 */
	public final Vector<?> getList() throws SQLException {
		String queryComplementar ="";
		objetos=this.getList(queryComplementar);
		return objetos;
	}

	/**
	 * Buscar uma lista de objetos que atendam a um critério.
	 * exemplo todos os alunos do 4 módulo (modulo=4)
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public final Vector<?> getList(String field, String value) throws SQLException {
		String queryComplementar = " where " + field + " LIKE '" + value + "'"; 
		objetos=this.getList(queryComplementar);
		return objetos;
	}
	
	/**
	 * Buscar uma lista de objetos que atendam a um critério.
	 * exemplo todos os alunos do 4 módulo (modulo=4)
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public final Vector<?> getList(String field, long value) throws SQLException {
		String queryComplementar = " where " + field + " =" + value; 
		objetos=this.getList(queryComplementar);
		return objetos;
	}
	
	/**
	 * Buscar uma lista de objetos que estejam entre dois valores
	 * exemplo todos os alunos que tiveram média entre 6 e 8 (minValue=6, maxValue=8
	 * @param field
	 * @param minValue
	 * @param maxValue
	 * @return
	 * @throws SQLException
	 */
	protected final Vector<?> getList(String field, String minValue, String maxValue) throws SQLException {
		String queryComplementar = " WHERE " + field + " between " + minValue + " and " + maxValue;
		objetos=this.getList(queryComplementar);
		return objetos;
	}
	
	protected final Vector<?> getList(String field1, String value1, String field2, String value2) throws SQLException{
		String queryComplementar =" WHERE "+field1+" like '"+value1+"' and "+field2+" like '"+value2+"'";
		objetos=this.getList(queryComplementar);
		return objetos;
	}
	
	/**
	 * Metodo que de fato executa uma busca no banco de dados de acordo com os parametros definidos...
	 * @param queryComplementar
	 * @return
	 * @throws SQLException
	 */
	public abstract Vector<?> getList(String queryComplementar) throws SQLException ;	
	
	/**excluir dados...
	 * 
	 * @param o
	 * @throws SQLException
	 */
	protected abstract void exclui(Object o) throws SQLException;

	/**
	 * Metodo que obriga a identificação do nome correspondente ao id da tabela relacionada a classe Bean.
	 * @param idName
	 */
	protected final void setId(String idName){
		this.id=idName;
	}

	/**
	 * Metodo para uso interno e tornar mais dinamico a construção dos querys.
	 * @return
	 */
	protected final String getId() {
		return this.id;
	}
	
}
