package br.com.fintech.entities;

import java.io.Serializable;
import java.sql.Date;

public class Investimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codInvest;
	private String tipoInvest;
	private double valorInvest;
	private Date dataInvest;
	private int codUsuario;
	private String frmRecebida;
	
	public Investimento() {
		super();
	}
	
	public Investimento(int codInvest, String tipoInvest, double valorInvest, Date dataInvest, int codUsuario, String frmRecebida) {
		this.codInvest = codInvest;
		this.tipoInvest = tipoInvest;
		this.valorInvest = valorInvest;
		this.dataInvest = dataInvest;
		this.codUsuario = codUsuario;
		this.frmRecebida = frmRecebida;
	}

	public int getCodInvest() {
		return codInvest;
	}

	public void setCodInvest(int codInvest) {
		this.codInvest = codInvest;
	}

	public String getTipoInvest() {
		return tipoInvest;
	}

	public void setTipoInvest(String tipoInvest) {
		this.tipoInvest = tipoInvest;
	}

	public double getValorInvest() {
		return valorInvest;
	}

	public void setValorInvest(double valorInvest) {
		this.valorInvest = valorInvest;
	}

	public Date getDataInvest() {
		return dataInvest;
	}

	public void setDataInvest(Date dataInvest) {
		this.dataInvest = dataInvest;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getFrmRecebida() {
		return frmRecebida;
	}

	public void setFrmRecebida(String frmRecebida) {
		this.frmRecebida = frmRecebida;
	}	

} // FIM
