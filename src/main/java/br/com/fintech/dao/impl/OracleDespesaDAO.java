package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fintech.entities.Despesa;
import br.com.fintech.singleton.ConnectionManager;
import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.DespesaDAO;

public class OracleDespesaDAO implements DespesaDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Despesa despesa) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			int proximoCodReceita = obterProximoCodigoDespesa();

			String sql = "INSERT INTO TB_FIN_DESPESA (cod_despesa, nm_desc_despesa, vlr_despesa, dt_despesa, frm_pagamento, tb_fin_usuario_cod_usuario) VALUES (?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, proximoCodReceita);
			stmt.setString(2, despesa.getDescricaoDespesa());
			stmt.setDouble(3, despesa.getValorDespesa());
			java.sql.Date data = new java.sql.Date(despesa.getDataDespesa().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setString(5, despesa.getFormaPagamentoDespesa());
			stmt.setInt(6, despesa.getCodUsuario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao Cadastrar Despesa!");
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

	private int obterProximoCodigoDespesa() throws SQLException {
		String sqlSeq = "SELECT SEQ_DESPESA.NEXTVAL FROM DUAL";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlSeq); ResultSet resultSet = stmt.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				throw new SQLException("Não foi possível obter o próximo código da receita.");
			}
		}
	}

	@Override
	public void atualizar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_FIN_DESPESA SET nm_desc_despesa = ?, vlr_despesa = ?, dt_despesa = ?, frm_pagamento = ? WHERE cod_despesa = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getDescricaoDespesa());
			stmt.setDouble(2, despesa.getValorDespesa());
			java.sql.Date data = new java.sql.Date(despesa.getDataDespesa().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, despesa.getFormaPagamentoDespesa());
			stmt.setInt(5, despesa.getCodDespesa());

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
	public void remover(int despesa) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_FIN_DESPESA WHERE cod_despesa = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, despesa);
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
	public Despesa buscar(int codDespesa) {

		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FIN_DESPESA WHERE cod_despesa = ?");
			stmt.setInt(1, codDespesa);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigoDespesa = rs.getInt("cod_despesa");
				String descricao = rs.getString("nm_desc_despesa");
				double valor = rs.getDouble("vlr_despesa");
				java.sql.Date data = rs.getDate("dt_despesa");
				Calendar dataDespesa = Calendar.getInstance();
				dataDespesa.setTimeInMillis(data.getTime());
				
				String formaPagamento = rs.getString("frm_pagamento");
				int codUsuario = rs.getInt("tb_fin_usuario_cod_usuario");
				
				despesa = new Despesa(codigoDespesa, descricao, valor, dataDespesa, formaPagamento, codUsuario);
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
		return despesa;
	}

	@Override
	public List<Despesa> listar() {

		List<Despesa> lista = new ArrayList<Despesa>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FIN_DESPESA");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {

				int codDespesa = rs.getInt("cod_despesa");
				String descricao = rs.getString("nm_desc_despesa");
				double valor = rs.getDouble("vlr_despesa");

				java.sql.Date data = rs.getDate("dt_despesa");
				Calendar dataDespesa = Calendar.getInstance();
				dataDespesa.setTimeInMillis(data.getTime());
				String formaPagamento = rs.getString("frm_pagamento");
				int codUsuario = rs.getInt("tb_fin_usuario_cod_usuario");

				Despesa despesa = new Despesa(codDespesa, descricao, valor, dataDespesa, formaPagamento, codUsuario);
				lista.add(despesa);
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
