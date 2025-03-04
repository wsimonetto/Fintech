package br.com.fintech.entities;

import java.io.Serializable;

import br.com.fintech.util.Criptografia;

public class UsuarioExclusao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codUsuario;
	private String senhaUsuario;
	
	public UsuarioExclusao(){
		super();		
	}
	
	public UsuarioExclusao(int codUsuario, String senhaUsuario) {
		super();
		this.codUsuario = codUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	public void criptografarSenha() {
        try {
            this.senhaUsuario = Criptografia.criptografar(this.senhaUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
} //FIM
