package domain;

import java.math.BigDecimal;

public class Konto {
	private int regnr;
	private int kontonr;
	private int kontotype;
	private BigDecimal saldo;
	
	public int getRegnr() {
		return regnr;
	}
	public Konto setRegnr(int regnr) {
		this.regnr = regnr;
		return this;
	}
	public int getKontonr() {
		return kontonr;
	}
	public Konto setKontonr(int kontonr) {
		this.kontonr = kontonr;
		return this;
	}
	public int getKontotype() {
		return kontotype;
	}
	public Konto setKontotype(int kontotype) {
		this.kontotype = kontotype;
		return this;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public Konto setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
		return this;
	}

}
