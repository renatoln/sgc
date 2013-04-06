package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.NivelUsuario;
import model.Usuario;

public class UsuarioDAO extends DAO{
	
	private PreparedStatement st;
	
	protected UsuarioDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		Usuario ob = (Usuario)o;
		
		st = connection.prepareStatement("INSERT INTO tbusuario (cpf,usuario,senha,nome,email,idnivelusuario)"
				+ " VALUES (?,?,?,?,?,?)");

		st.setString(1, ob.getCpf());
		st.setString(2, ob.getUsuario());
		st.setString(3, ob.getSenha());
		st.setString(4, ob.getNome());
		st.setString(5, ob.getEmail());
		st.setInt(6, ob.getNivelUsuario().getIdNivelUsuario());
		st.execute();
		st.close();
	}

	@Override
	public void updateDados(Object o) throws SQLException {
		// TODO Auto-generated method stub
		Usuario ob = (Usuario)o;
		
		st = connection.prepareStatement("UPDATE tbusuario SET cpf = ?,usuario = ?,senha = ?,nome = ?,email = ?,idnivelusuario = ? WHERE idusuario = ?");
		st.setString(1, ob.getCpf());
		st.setString(2, ob.getUsuario());
		st.setString(3, ob.getSenha());
		st.setString(4, ob.getNome());
		st.setString(5, ob.getEmail());
		st.setInt(6, ob.getNivelUsuario().getIdNivelUsuario());
		st.setInt(7, ob.getIdUsuario());
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
		st = connection.prepareStatement("SELECT * FROM tbusuario");
		ResultSet rs = st.executeQuery();

		Vector<Usuario> lista = new Vector<Usuario>();
		
		while (rs.next()) {
			
			Usuario ob = new Usuario();
			ob.setIdUsuario(rs.getInt("idusuario"));
			ob.setCpf(rs.getString("cpf"));
			ob.setUsuario(rs.getString("usuario"));
			ob.setSenha(rs.getString("senha"));
			ob.setNome(rs.getString("nome"));
			ob.setEmail(rs.getString("email"));
			
			NivelUsuario nv = new NivelUsuario();
			
			nv.setIdNivelUsuario(rs.getInt("idnivelusuario"));
			
			ob.setNivelUsuario(nv);
			
			lista.add(ob);
		}

		return lista;
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		// TODO Auto-generated method stub
		Usuario ob = (Usuario)o;
		st = connection.prepareStatement("DELETE FROM tbusuario WHERE idusuario = "
				+ ob.getIdUsuario());
		st.execute();
		st.close();
	}

}
