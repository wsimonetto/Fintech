package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.DBException.DBException;
import br.com.fintech.entities.Despesa;

public interface DespesaDAO {

	void cadastrar(Despesa despesa) throws DBException;
	void atualizar(Despesa despesa) throws DBException;
	void remover(int despesa) throws DBException;
	
	Despesa buscar(int codDespesa);
	List<Despesa> listar();
		
} // FIM
