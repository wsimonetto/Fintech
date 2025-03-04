package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.DBException.DBException;
import br.com.fintech.entities.Investimento;

public interface InvestimentoDAO {

	void cadastrar(Investimento investimento) throws DBException;
	void atualizar(Investimento investimento) throws DBException;
	void remover(int investimento) throws DBException;
	
	Investimento buscar(int codInvestimento);
	List<Investimento> listar();
	
}
