package br.com.fintech.dao;

import br.com.fintech.DBException.DBException;
import br.com.fintech.entities.UsuarioCadastro;
import br.com.fintech.entities.UsuarioExclusao;
import br.com.fintech.entities.UsuarioLogin;

public interface UsuarioDAO {
	
	void cadastrar(UsuarioCadastro usuario) throws DBException;
    void atualizarSenha(int codigoUsuario, String novaSenha) throws DBException;
    void remover(int codigoUsuario) throws DBException;

    boolean verificarUsuarioExistentePorCPF(String cpf);
    boolean verificarUsuarioExistentePorEmail(String email);
    boolean validarUsuario(UsuarioLogin usuarioLogin);
	boolean validarSenhaUsuario(UsuarioExclusao usuarioExclusao);
	
} // FIM
