package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.TipoServico;

public class TipoServicoDAO extends DAO {

	protected TipoServico objeto;
	
	protected TipoServicoDAO() throws SQLException {
		super();
		this.setId("idtiposervico");
		table = "tbtiposervico";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}
	
	@Override
	protected void exclui(Object o) throws SQLException {
		objeto  = (TipoServico)o;
		String sql = delete+objeto.getIdTipoServico();
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
		Vector<TipoServico> objetos = new Vector<TipoServico>();
		while (rs.next()) {
			objeto = new TipoServico();
			objeto.setIdTipoServico(Integer.parseInt(rs.getString(getId())));
			objeto.setNome(rs.getString("nome")); 

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

		objeto = new TipoServico();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdTipoServico(Integer.parseInt(rs.getString(getId())));
			objeto.setNome(rs.getString("descricao"));
			
			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (TipoServico)o;
		String sql = "INSERT INTO "+table+" (idtiposervico, nome) VALUES (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdTipoServico());
		stmt.setString(2,objeto.getNome());
		
		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (TipoServico)o;
		String sql = "UPDATE "+table+" set idtiposervico=?, nome=?  WHERE "+getId()+" ="+objeto.getIdTipoServico();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdTipoServico());
		stmt.setString(2,objeto.getNome());
		

		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}
