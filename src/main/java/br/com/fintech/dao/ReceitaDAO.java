package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.DBException.DBException;
import br.com.fintech.entities.Receita;

public interface ReceitaDAO {
	
	void cadastrar(Receita receita) throws DBException;
	void atualizar(Receita receita) throws DBException;
	void remover(int receita) throws DBException;
	
	Receita buscar(int codReceita);
	List<Receita> listar();
	
} // FIM
