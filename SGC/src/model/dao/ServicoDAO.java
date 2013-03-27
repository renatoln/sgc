package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Aluguel;
import model.Servico;

public class ServicoDAO extends DAO {

	Servico objeto;
	
	protected ServicoDAO() throws SQLException {
		super();
		this.setId("idservico");
		table = "tbservico";
		select = "SELECT * FROM "+table;
		delete = "DELETE FROM "+table+ " WHERE "+getId()+"=";
	}

	protected void exclui(Object o) throws SQLException {
		objeto  = (Servico)o;
		String sql = delete+objeto.getIdServico();
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
		Vector<Servico> objetos = new Vector<Servico>();
		while (rs.next()) {
			objeto = new Servico();
			objeto.setIdServico(Integer.parseInt(rs.getString(getId())));
			objeto.setTipoServico(rs.getInt("idtiposervico")); 
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setEspaco(rs.getInt("idespaco"));
			

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

		objeto = new Servico();
		rs = stmt.executeQuery(sql);

		if (rs.next()){
			objeto.setIdServico(Integer.parseInt(rs.getString(getId())));
			objeto.setTipoServico(rs.getInt("idtiposervico"));
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setEspaco(rs.getInt("idespaco"));

			rs.close();
			stmt.close();
		}

		return objeto;
	}

	@Override
	protected void insert(Object o) throws SQLException {
		// prepared statement para inserção
		objeto = (Servico)o;
		String sql = "INSERT INTO "+table+" (descricao, espaco, tiposervico) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setString(1,objeto.getDescricao());
		stmt.setInt(2,objeto.getEspaco().getIdEspaco());
		stmt.setInt(3,objeto.getTipoServico().getIdTipoServico());

		// executa os query criado...
		stmt.execute();
		stmt.close();
	}

	@Override
	protected void updateDados(Object o) throws SQLException {
		objeto = (Servico)o;
		String sql = "UPDATE "+table+" set descricao=?, espaco=?, tiposervico=?  WHERE "+getId()+" ="+objeto.getIdServico();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// seta os valores...
		stmt.setString(1,objeto.getDescricao());
		stmt.setInt(2,objeto.getEspaco().getIdEspaco());
		stmt.setInt(3,objeto.getTipoServico().getIdTipoServico());



		// executa os query criado...
		stmt.executeUpdate();
		stmt.close();
	}

}
