package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Espaco;
import model.ResponsaveisDoApartamento;


public class ResponsaveisDoApartamentoDAO extends DAO {
	
	protected ResponsaveisDoApartamento objeto;

	protected ResponsaveisDoApartamentoDAO() throws SQLException {
		super();
		this.setId("tbresponsaveisdoapartamento");
		table = "idresponsaveisdoapartamento";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		objeto  = (ResponsaveisDoApartamento)o;
		String sql = delete+objeto.getIdResponsavisDoApatamento();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	protected Vector<?> getList(String queryComplementar)
			throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement(select+queryComplementar);

		ResultSet rs = stmt.executeQuery();
		Vector<ResponsaveisDoApartamento> objetos = new Vector<ResponsaveisDoApartamento>();
		while (rs.next()) {
			objeto = new ResponsaveisDoApartamento();
			objeto.setIdResponsavisDoApatamento(Integer.parseInt(rs.getString(getId())));
			objeto.setApartamento(rs.getInt("idapartamento")); 
			objeto.setUsuario(rs.getInt("idusuario"));

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

		objeto = new ResponsaveisDoApartamento();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdResponsavisDoApatamento(Integer.parseInt(rs.getString(getId())));
			objeto.setApartamento(rs.getInt("idapartamento"));
			objeto.setUsuario(rs.getInt("idusuario"));

			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (ResponsaveisDoApartamento)o;
		String sql = "INSERT INTO "+table+" (idapartamento, idusuario) VALUES (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getApartamento().getIdApartamento());
		stmt.setInt(2,objeto.getUsuario().getIdUsuario());
		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (ResponsaveisDoApartamento)o;
		String sql = "UPDATE "+table+" set idapartamento=?, idusuario=?  WHERE "+getId()+" ="+objeto.getIdResponsavisDoApatamento();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getApartamento().getIdApartamento());
		stmt.setInt(2,objeto.getUsuario().getIdUsuario());


		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}