package br.com.fintech.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Despesa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codDespesa;
	private String descricaoDespesa;
	private double valorDespesa;
	private Calendar dataDespesa;
	private String formaPagamentoDespesa;
	private int codUsuario;
	
	public Despesa() {
		super();
	}
	
	public Despesa(int codDespesa, String descricaoDespesa, double valorDespesa, Calendar dataDespesa, String formaPagamentoDespesa, int codUsuario) {
		super();
		this.codDespesa = codDespesa;
		this.descricaoDespesa = descricaoDespesa;
		this.valorDespesa = valorDespesa;
		this.dataDespesa = dataDespesa;
		this.formaPagamentoDespesa = formaPagamentoDespesa;
		this.codUsuario = codUsuario;
	}

	public int getCodDespesa() {
		return codDespesa;
	}

	public void setCodDespesa(int codDespesa) {
		this.codDespesa = codDespesa;
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public Calendar getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Calendar dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public String getFormaPagamentoDespesa() {
		return formaPagamentoDespesa;
	}

	public void setFormaPagamentoDespesa(String formaPagamentoDespesa) {
		this.formaPagamentoDespesa = formaPagamentoDespesa;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

} // FIM
