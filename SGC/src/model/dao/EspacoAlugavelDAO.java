package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.EspacoAlugavel;


public class EspacoAlugavelDAO extends DAO {
	
	protected EspacoAlugavel objeto;

	protected EspacoAlugavelDAO() throws SQLException {
		super();
		this.setId("idespaco");
		table = "tbespacoalugavel";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		objeto  = (EspacoAlugavel)o;
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
		Vector<EspacoAlugavel> objetos = new Vector<EspacoAlugavel>();
		while (rs.next()) {
			objeto = new EspacoAlugavel();
			objeto.setIdEspaco(Integer.parseInt(rs.getString(getId())));
			objeto.setDescricao(rs.getString("descricao")); 
			objeto.setQtdPessoas(rs.getInt("qtdPessoas"));
			objeto.setValor(rs.getDouble("valor"));

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

		objeto = new EspacoAlugavel();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdEspaco(Integer.parseInt(rs.getString(getId())));
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setQtdPessoas(rs.getInt("qtdPessoas"));
			objeto.setValor(rs.getDouble("valor"));

			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (EspacoAlugavel)o;
		String sql = "INSERT INTO "+table+" (idespaco, descricao, qtdpessoas, valor) VALUES (?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdEspaco());
		stmt.setString(2,objeto.getDescricao());
		stmt.setInt(3,objeto.getQtdPessoas());
		stmt.setDouble(4,objeto.getValor());
		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (EspacoAlugavel)o;
		String sql = "UPDATE "+table+" set idespaco=?, descricao=?, qtdpessoas=?, valor=?  WHERE "+getId()+" ="+objeto.getIdEspaco();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdEspaco());
		stmt.setString(2,objeto.getDescricao());
		stmt.setInt(3,objeto.getQtdPessoas());
		stmt.setDouble(3,objeto.getValor());


		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}