package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.entities.Investimento;
import br.com.fintech.singleton.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO {

	private Connection conexao;
	
	@Override
	public void cadastrar(Investimento investimento) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			int proximoCodReceita = obterProximoCodigoInvestimento();

			String sql = "INSERT INTO TB_FIN_INVEST (cod_invest, tip_invest, vlr_recebido_invest, dt_invest, tb_fin_usuario_cod_usuario,  frm_adicionada) VALUES (?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, proximoCodReceita);
			stmt.setString(2, investimento.getTipoInvest());
			stmt.setDouble(3, investimento.getValorInvest());
			Date data = new java.sql.Date(investimento.getDataInvest().getTime());
			stmt.setDate(4, data);
			stmt.setInt(5, investimento.getCodUsuario());
			stmt.setString(6, investimento.getFrmRecebida());

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
	} // FIM CADASTRAR DESPESA
	
	private int obterProximoCodigoInvestimento() throws SQLException {
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
	public void atualizar(Investimento investimento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(int investimento) {
		// TODO Auto-generated method stub

	}

	@Override
	public Investimento buscar(int codInvestimento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Investimento> listar() {
		// TODO Auto-generated method stub
		return null;
	}

} // FIM
