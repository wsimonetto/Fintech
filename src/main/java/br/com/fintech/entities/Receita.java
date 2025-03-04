package br.com.fintech.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Receita implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codReceita;
	private String descricaoReceita;
	private double valorReceita;
	private Calendar dataReceita;
	private String formaRecebimentoReceita;
	private int codUsuario;
	
	public Receita() {
		super();
	}
	
	public Receita(int codReceita, String descricaoReceita, double valorReceita, Calendar dataReceita, String formaRecebimentoReceita, int codUsuario) {
		super();
		this.codReceita = codReceita;
		this.descricaoReceita = descricaoReceita;
		this.valorReceita = valorReceita;
		this.dataReceita = dataReceita;
		this.formaRecebimentoReceita = formaRecebimentoReceita;
		this.codUsuario = codUsuario;
	}

	public int getCodReceita() {
		return codReceita;
	}

	public void setCodReceita(int codReceita) {
		this.codReceita = codReceita;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}

	public double getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(double valorReceita) {
		this.valorReceita = valorReceita;
	}

	public Calendar getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Calendar dataReceita) {
		this.dataReceita = dataReceita;
	}

	public String getFormaRecebimentoReceita() {
		return formaRecebimentoReceita;
	}

	public void setFormaRecebimentoReceita(String formaRecebimentoReceita) {
		this.formaRecebimentoReceita = formaRecebimentoReceita;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	
} //FIM
