package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Administrador;
import model.NivelUsuario;

public class AdministradorDAO extends UsuarioDAO{
	
	private PreparedStatement st;
	
	protected AdministradorDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<?> getList(String queryComplementar) throws SQLException {
		// TODO Auto-generated method stub
		st = connection.prepareStatement("SELECT u.* FROM tbusuario u INNER JOIN tbnivelusuario n on n.idnivelusuario = u.idnivelusuario");
		ResultSet rs = st.executeQuery();

		Vector<Administrador> lista = new Vector<Administrador>();
		
		while (rs.next()) {
			
			Administrador ob = new Administrador();
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
}
