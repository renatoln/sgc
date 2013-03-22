package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Aluguel;


public class AluguelDAO extends DAO {
	
	Aluguel objeto;

	protected AluguelDAO() throws SQLException {
		super();
		this.setId("idaluguel");
		table = "tbaluguel";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		objeto  = (Aluguel)o;
		String sql = delete+objeto.getIdAluguel();
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
		Vector<Aluguel> objetos = new Vector<Aluguel>();
		while (rs.next()) {
			objeto = new Aluguel();
			objeto.setIdAluguel(Integer.parseInt(rs.getString(getId())));
			objeto.setEspacoAlugavel(rs.getInt("idespacoalugavel")); 
			objeto.setHoraFim(rs.getTime("horaFim"));
			objeto.setHoraInicio(rs.getTime("horaInicio"));
			objeto.setStatus(rs.getBoolean("status"));

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

		objeto = new Aluguel();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdAluguel(Integer.parseInt(rs.getString(getId())));
			objeto.setEspacoAlugavel(rs.getInt("idespacoalugavel"));
			objeto.setHoraFim(rs.getTime("horaFim"));
			objeto.setHoraInicio(rs.getTime("horaInicio"));
			objeto.setStatus(rs.getBoolean("disponivel"));

			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (Aluguel)o;
		String sql = "INSERT INTO "+table+" (dataaluguel, espacoalugavel, horafim, horainicio, status) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setDate(1,objeto.getDataAluguel());
		stmt.setInt(2,objeto.getEspacoAlugavel().getIdEspaco());
		stmt.setTime(3,objeto.getHoraFim());
		stmt.setTime(4,objeto.getHoraInicio());
		stmt.setBoolean(5,objeto.isStatus());
		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (Aluguel)o;
		String sql = "UPDATE "+table+" set dataaluguel=?, espacoalugavel=?, horafim=?, horainicio=?, status=?  WHERE "+getId()+" ="+objeto.getIdAluguel();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setDate(1,objeto.getDataAluguel());
		stmt.setInt(2,objeto.getEspacoAlugavel().getIdEspaco());
		stmt.setTime(3,objeto.getHoraFim());
		stmt.setTime(4,objeto.getHoraInicio());
		stmt.setBoolean(5,objeto.isStatus());


		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}