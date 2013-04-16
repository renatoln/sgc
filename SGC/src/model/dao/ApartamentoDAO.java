package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Apartamentos;

public class ApartamentoDAO extends DAO{

	protected Apartamentos objeto;
	
	public ApartamentoDAO() throws SQLException {
		super();
		this.setId("idApartamento");
		table = "tbapartamento";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	protected void exclui(Object o) throws SQLException {
		objeto  = (Apartamentos)o;
		String sql = delete+objeto.getIdApartamento();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

	protected Vector<?> getList(String queryComplementar)
			throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement(select+queryComplementar);

		ResultSet rs = stmt.executeQuery();
		Vector<Apartamentos> objetos = new Vector<Apartamentos>();
		while (rs.next()) {
			objeto = new Apartamentos();
			objeto.setIdApartamento(Integer.parseInt(rs.getString(getId())));
			objeto.setNumero(rs.getInt("numero")); 
			objeto.setIdtorre(Integer.parseInt(rs.getString(getId())));
			

		objetos.add(objeto);
		}

		rs.close();
		stmt.close();
		return objetos;
	}

	@Override
	protected Object getOne(String queryComplementar) throws SQLException {
		String sql =select+queryComplementar;
		PreparedStatement stmt = connection.prepareStatement(sql);

		objeto = new Apartamentos();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdApartamento(Integer.parseInt(rs.getString(getId())));
			objeto.setIdtorre(Integer.parseInt(rs.getString(getId()))); 
			objeto.setNumero(rs.getInt("numero"));
			
			
			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (Apartamentos)o;
		String sql = "INSERT INTO "+table+" (idapartamento, idtorre, numero) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdApartamento());
		stmt.setInt(2,objeto.getIdtorre());
		stmt.setInt(3,objeto.getNumero());

		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (Apartamentos)o;
		String sql = "UPDATE "+table+" set idapartamento=?, idtorre=?, nome=?  WHERE "+getId()+" ="+objeto.getIdApartamento();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdApartamento());
		stmt.setInt(2,objeto.getIdtorre());
		stmt.setInt(3,objeto.getNumero());



		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}
