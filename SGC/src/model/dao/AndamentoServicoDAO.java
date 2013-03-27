package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.AndamentoServico;
import model.NivelUsuario;
import model.Usuario;

public class AndamentoServicoDAO extends DAO{

	private PreparedStatement st;
	
	protected AndamentoServicoDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		AndamentoServico ob = (AndamentoServico)o;
		
		st = connection.prepareStatement("INSERT INTO tbandamentoservico (data,descricao,operacao,tbservico_idservico,tbusuario_idusuario)"
				+ " VALUES (?,?,?,?,?)");

		st.setDate(1, ob.getDat());
		st.setString(2, ob.getDescricao());
		st.setInt(3, ob.getOperacao());
		//st.setInt(4, ob.getServico().getIdServico);
		st.setInt(5, ob.getUsuario().getIdUsuario());
		st.execute();
		st.close();
	
	}

	@Override
	public void updateDados(Object o) throws SQLException {
		
		AndamentoServico ob = (AndamentoServico)o;
		
		// TODO Auto-generated method stub
		st = connection.prepareStatement("UPDATE tbandamentoservico SET data = ?,descricao = ?,operacao = ?,tbservico_idservico = ?,tbusuario_idusuario = ? WHERE idtbandamentoservico = ?");
		st.setDate(1, ob.getDat());
		st.setString(2, ob.getDescricao());
		st.setInt(3, ob.getOperacao());
		//st.setInt(4, ob.getServico().getIdServico);
		st.setInt(5, ob.getUsuario().getIdUsuario());
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
		st = connection.prepareStatement("SELECT * FROM tbandamentoservico");
		ResultSet rs = st.executeQuery();

		Vector<AndamentoServico> lista = new Vector<AndamentoServico>();
		
		while (rs.next()) {
			
			AndamentoServico ob = new AndamentoServico();
			ob.setIdAndamentoServico(rs.getInt("idtbandamentoservico"));
			ob.setDat(rs.getDate("data"));
			ob.setDescricao(rs.getString("descricao"));
			ob.setOperacao(rs.getInt("operacao"));
			//Servco s = new Servico();
			//s.setIdServico(rs.getInt("tbservico_idservico"))
			//ob.setServico(s);
			Usuario u = new Usuario();
			u.setIdUsuario(rs.getInt("tbusuario_idusuario"));
			//ob.setUsuario(u);
	
			
			lista.add(ob);
		}

		return lista;
	}

	@Override
	protected void exclui(Object o) throws SQLException {
		// TODO Auto-generated method stub
		AndamentoServico ob = (AndamentoServico)o;
		st = connection.prepareStatement("DELETE FROM tbandamentoservico WHERE idtbandamentoservico = "
				+ ob.getIdAndamentoServico());
		st.execute();
		st.close();
	}

}
