package br.com.fintech.factory;

import br.com.fintech.dao.impl.OracleDespesaDAO;
import br.com.fintech.dao.impl.OracleInvestimentoDAO;
import br.com.fintech.dao.impl.OracleReceitaDAO;
import br.com.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

	public static OracleUsuarioDAO getUsuarioDAO() {

		return new OracleUsuarioDAO();
	}
	
	public static OracleReceitaDAO getReceitaDAO() {
		return new OracleReceitaDAO();
	}
	
	public static OracleDespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}
	
	public static OracleInvestimentoDAO getInvestimentoDAO() {
		return new OracleInvestimentoDAO();
	}

} // FIM
