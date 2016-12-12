package javaee.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Overforsel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long kundeid;
	private int fraRegnr;
	private int fraKontonr;
	private String fraTekst;
	private int tilRegnr;
	private int tilKontonr;
	private String tilTekst;
	private BigDecimal belob;
	private LocalDate prdato;
	
	public long getKundeid() {
		return kundeid;
	}
	public void setKundeid(long kundeid) {
		this.kundeid = kundeid;
	}
	public int getFraRegnr() {
		return fraRegnr;
	}
	public void setFraRegnr(int fraRegnr) {
		this.fraRegnr = fraRegnr;
	}
	public int getFraKontonr() {
		return fraKontonr;
	}
	public void setFraKontonr(int fraKontonr) {
		this.fraKontonr = fraKontonr;
	}
	public int getTilRegnr() {
		return tilRegnr;
	}
	public void setTilRegnr(int tilRegnr) {
		this.tilRegnr = tilRegnr;
	}
	public int getTilKontonr() {
		return tilKontonr;
	}
	public void setTilKontonr(int tilKontonr) {
		this.tilKontonr = tilKontonr;
	}
	public String getFraTekst() {
		return fraTekst;
	}
	public void setFraTekst(String fraTekst) {
		this.fraTekst = fraTekst;
	}
	public String getTilTekst() {
		return tilTekst;
	}
	public void setTilTekst(String tilTekst) {
		this.tilTekst = tilTekst;
	}
	public BigDecimal getBelob() {
		return belob;
	}
	public void setBelob(BigDecimal belob) {
		this.belob = belob;
	}
	public LocalDate getPrdato() {
		return prdato;
	}
	public void setPrdato(LocalDate prdato) {
		this.prdato = prdato;
	}

}
