package br.com.fintech.entities;

import java.io.Serializable;
import java.sql.Date;

public class UsuarioCadastro implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codUsuario;
	private String cpfUsuario;
	private String nomeUsuario;
	private String emailUsuario;
	private String telefoneUsuario;
	private String senhaUsuario;
	private Date dataCadastro;

	public UsuarioCadastro() {
		super();
	}

	public UsuarioCadastro(int codUsuario, String cpfUsuario, String nomeUsuario, String emailUsuario, String telefoneUsuario,
			String senhaUsuario, Date dataCadastro) {
		super();
		this.codUsuario = codUsuario;
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.telefoneUsuario = telefoneUsuario;
		this.senhaUsuario = senhaUsuario;
		this.dataCadastro = dataCadastro;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Date getDataUsuario() {
		return dataCadastro;
	}

	public void setDataUsuario(Date dataUsuario) {
		this.dataCadastro = dataUsuario;
	}

} // FIM
