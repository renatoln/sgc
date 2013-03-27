package model.dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.UsuarioHasAluguel;

public class UsuarioHasAluguelDAO extends DAO{

	protected UsuarioHasAluguel objeto;
	
	protected UsuarioHasAluguelDAO() throws SQLException {
		super();
		this.setId("idusuario");
		this.setId("idaluguel");
		table = "tbusuario_has_tbaluguel";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}
	
	//Duvida... É necessário colocar as duas chaves primárias?
	@Override
	protected void exclui(Object o) throws SQLException {
		objeto  = (UsuarioHasAluguel)o;
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
		Vector<UsuarioHasAluguel> objetos = new Vector<UsuarioHasAluguel>();
		while (rs.next()) {
			objeto = new UsuarioHasAluguel();
			objeto.setIdAluguel(Integer.parseInt(rs.getString(getId())));
			objeto.setIdUsuario(Integer.parseInt(rs.getString(getId()))); 
			objeto.setDataSolicitacao(rs.getDate("dataSolicitacao"));

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

		objeto = new UsuarioHasAluguel();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdAluguel(Integer.parseInt(rs.getString(getId())));
			objeto.setIdUsuario(Integer.parseInt(rs.getString(getId()))); 
			objeto.setDataSolicitacao(rs.getDate("dataSolicitacao"));

			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (UsuarioHasAluguel)o;
		String sql = "INSERT INTO "+table+" (idusuario, idaluguel, datasolicitacao) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdUsuario());
		stmt.setInt(2,objeto.getIdAluguel());
		stmt.setDate(3,objeto.getDataSolicitacao());
		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (UsuarioHasAluguel)o;
		String sql = "UPDATE "+table+" set idusuario=?, idaluguel=?, datasolicitacao=?  WHERE "+getId()+" ="+objeto.getIdAluguel();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setInt(1,objeto.getIdUsuario());
		stmt.setInt(2,objeto.getIdAluguel());
		stmt.setDate(3,objeto.getDataSolicitacao());


		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}


}
