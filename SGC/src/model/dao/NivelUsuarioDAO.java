package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import model.NivelUsuario;

public class NivelUsuarioDAO extends DAO{

	private PreparedStatement st;
	
	protected NivelUsuarioDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		//insert into tbnivelusuario(nivel,descricao) values ("nivel 1","descricao 1")
		NivelUsuario ob = (NivelUsuario)o;
		
		st = connection.prepareStatement("INSERT INTO tbnivelusuario (nivel,descricao)"
				+ " VALUES (?,?)");

		st.setString(1, ob.getNivel());
		st.setString(2, ob.getDescricao());
		st.execute();
		st.close();
		
	}

	@Override
	public void updateDados(Object o) throws SQLException {
		// TODO Auto-generated method stub
		NivelUsuario ob = (NivelUsuario)o;
		
		st = connection.prepareStatement("UPDATE tbnivelusuario SET nivel = ?,descricao=? WHERE idnivelusuario = ?");
		st.setString(1, ob.getNivel());
		st.setString(2, ob.getDescricao());
		st.setInt(3, ob.getIdNivelUsuario());
		st.execute();
		st.close();
	}

	@Override
	protected Object getOne(String queryComplementar) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<?> getList(String queryComplementar) throws SQLException {
		// TODO Auto-generated method stub
		st = connection.prepareStatement("SELECT * FROM tbnivelusuario");
		ResultSet rs = st.executeQuery();

		Vector<NivelUsuario> lista = new Vector<NivelUsuario>();
		
		while (rs.next()) {
			
			NivelUsuario ob = new NivelUsuario();
			ob.setIdNivelUsuario(rs.getInt("idnivelusuario"));
			ob.setNivel(rs.getString("nivel"));
			ob.setDescricao(rs.getString("descricao"));
			
			lista.add(ob);
		}

		return lista;
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		// TODO Auto-generated method stub
		NivelUsuario ob = (NivelUsuario)o;
		st = connection.prepareStatement("DELETE FROM tbnivelusuario WHERE idnivelusuario = "
				+ ob.getIdNivelUsuario());
		st.execute();
		st.close();
	}

}
