package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Espaco;


public class EspacoDAO extends DAO {
	
	protected Espaco objeto;

	public EspacoDAO() throws SQLException {
		super();
		this.setId("idespaco");
		table = "tbespaco";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		objeto  = (Espaco)o;
		String sql = delete+objeto.getIdEspaco();
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
		Vector<Espaco> objetos = new Vector<Espaco>();
		while (rs.next()) {
			objeto = new Espaco();
			objeto.setIdEspaco(Integer.parseInt(rs.getString(getId())));
			objeto.setDescricao(rs.getString("descricao")); 
			objeto.setNome(rs.getString("nome"));
			objeto.setTorre(rs.getInt("idtorre"));

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

		objeto = new Espaco();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdEspaco(Integer.parseInt(rs.getString(getId())));
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setNome(rs.getString("nome"));
			objeto.setTorre(rs.getInt("idtorre"));

			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (Espaco)o;
		String sql = "INSERT INTO "+table+" (nome, descricao, torre) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setString(1,objeto.getNome());
		stmt.setString(2,objeto.getDescricao());
		stmt.setInt(3,objeto.getTorre().getIdTorre());
		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (Espaco)o;
		String sql = "UPDATE "+table+" set nome=?, descricao=?, torre=?  WHERE "+getId()+" ="+objeto.getIdEspaco();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setString(1,objeto.getNome());
		stmt.setString(2,objeto.getDescricao());
		stmt.setInt(3,objeto.getTorre().getIdTorre());


		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}