package javaee.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Konto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int regnr; 
	private int kontonr;
	private long kundeid;
	private int kontotype;
	private BigDecimal saldo;
	private BigDecimal minimumSaldo;
	
	public long getKundeid() {
		return kundeid;
	}
	public void setKundeid(long kundeid) {
		this.kundeid = kundeid;
	}
	public int getRegnr() {
		return regnr;
	}
	public void setRegnr(int regnr) {
		this.regnr = regnr;
	}
	public int getKontonr() {
		return kontonr;
	}
	public void setKontonr(int kontonr) {
		this.kontonr = kontonr;
	}
	public int getKontotype() {
		return kontotype;
	}
	public void setKontotype(int kontotype) {
		this.kontotype = kontotype;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getMinimumSaldo() {
		return minimumSaldo;
	}
	public void setMinimumSaldo(BigDecimal minimumSaldo) {
		this.minimumSaldo = minimumSaldo;
	}
	
}
