package model.dao;

import model.Torre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TorreDAO extends DAO {

	protected Torre objeto;
	
	public TorreDAO() throws SQLException {
		super();
		this.setId("idtorre");
		table = "tbtorre";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	protected void exclui(Object o) throws SQLException {
		objeto  = (Torre)o;
		String sql = delete+objeto.getIdTorre();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

	protected Vector<?> getList(String queryComplementar)
			throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement(select+queryComplementar);

		ResultSet rs = stmt.executeQuery();
		Vector<Torre> objetos = new Vector<Torre>();
		while (rs.next()) {
			objeto = new Torre();
			objeto.setIdTorre(Integer.parseInt(rs.getString(getId())));
			objeto.setNome(rs.getString("nome"));
			objeto.setDescricao(rs.getString("descricao"));
			

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

		objeto = new Torre();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdTorre(Integer.parseInt(rs.getString(getId())));
			objeto.setNome(rs.getString("nome"));
			objeto.setDescricao(rs.getString("descricao"));
			
			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (Torre)o;
		String sql = "INSERT INTO "+table+" (idtorre, nome, descricao) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdTorre());
		stmt.setString(2,objeto.getNome());
		stmt.setString(3,objeto.getDescricao());

		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (Torre)o;
		String sql = "UPDATE "+table+" set idtorre=?, nome=?, descricao=?  WHERE "+getId()+" ="+objeto.getIdTorre();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdTorre());
		stmt.setString(2,objeto.getNome());
		stmt.setString(3,objeto.getDescricao());



		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}
