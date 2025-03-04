package br.com.fintech.entities;

import br.com.fintech.util.Criptografia;

public class UsuarioLogin {

	private String email;
	private String senha;
	private int codigoUsuario;
	
	public UsuarioLogin() {
		super();
	}

	public UsuarioLogin(String email, String senha, int codigoUsuario) {
		super();
		this.email = email;
		this.senha = senha;
		this.codigoUsuario = codigoUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void criptografarSenha() {
        try {
            this.senha = Criptografia.criptografar(this.senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

} // FIM
