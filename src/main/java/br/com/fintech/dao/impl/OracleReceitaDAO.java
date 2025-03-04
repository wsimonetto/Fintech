package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.entities.Receita;
import br.com.fintech.singleton.ConnectionManager;

public class OracleReceitaDAO implements ReceitaDAO {

	private Connection conexao;

	public void cadastrar(Receita receita) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			int proximoCodReceita = obterProximoCodigoReceita();

			String sql = "INSERT INTO TB_FIN_RECEITA (cod_receita, nm_desc_receita, vlr_receita, dt_receita, frm_recebimento, tb_fin_usuario_cod_usuario) VALUES (?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, proximoCodReceita);
			stmt.setString(2, receita.getDescricaoReceita());
			stmt.setDouble(3, receita.getValorReceita());
			java.sql.Date data = new java.sql.Date(receita.getDataReceita().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setString(5, receita.getFormaRecebimentoReceita());
			stmt.setInt(6, receita.getCodUsuario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao Cadastrar Receita!");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int obterProximoCodigoReceita() throws SQLException {
		String sqlSeq = "SELECT SEQ_RECEITA.NEXTVAL FROM DUAL";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlSeq); ResultSet resultSet = stmt.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				throw new SQLException("Não foi possível obter o próximo código da receita.");
			}
		}
	}

	@Override
	public void atualizar(Receita receita) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_FIN_RECEITA SET nm_desc_receita = ?, vlr_receita = ?, dt_receita = ?, frm_recebimento = ? WHERE cod_receita = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, receita.getDescricaoReceita());
			stmt.setDouble(2, receita.getValorReceita());
			java.sql.Date data = new java.sql.Date(receita.getDataReceita().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, receita.getFormaRecebimentoReceita());
			stmt.setInt(5, receita.getCodReceita());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(int receita) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_FIN_RECEITA WHERE cod_receita = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, receita);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Receita buscar(int codReceita) {

			Receita receita = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				stmt = conexao.prepareStatement("SELECT * FROM TB_FIN_RECEITA WHERE cod_receita = ?");
				stmt.setInt(1, codReceita);
				rs = stmt.executeQuery();

				if (rs.next()){
					int codigoReceita = rs.getInt("cod_receita");
					String descricao = rs.getString("nm_desc_receita");
					double valor = rs.getDouble("vlr_receita");
					java.sql.Date data = rs.getDate("dt_receita");
					Calendar dataReceita = Calendar.getInstance();
					dataReceita.setTimeInMillis(data.getTime());
					
					String formaRecebimento = rs.getString("frm_recebimento");
					int codUsuario = rs.getInt("tb_fin_usuario_cod_usuario");
					
					receita = new Receita(codigoReceita, descricao, valor, dataReceita, formaRecebimento, codUsuario);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
					rs.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return receita;
	}

	@Override
	public List<Receita> listar() {

		List<Receita> lista = new ArrayList<Receita>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FIN_RECEITA");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {

				int codReceita = rs.getInt("cod_receita");
				String descricao = rs.getString("nm_desc_receita");
				double valor = rs.getDouble("vlr_receita");

				java.sql.Date data = rs.getDate("dt_receita");
				Calendar dataReceita = Calendar.getInstance();
				dataReceita.setTimeInMillis(data.getTime());
				String formaRecebimento = rs.getString("frm_recebimento");
				int codUsuario = rs.getInt("tb_fin_usuario_cod_usuario");

				Receita receita = new Receita(codReceita, descricao, valor, dataReceita, formaRecebimento, codUsuario);
				lista.add(receita);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

} // FIM
