package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Postering implements Comparable<Postering>{
	private LocalDateTime datotid;
	private BigDecimal belob;
	private int regnr; 
	private int kontonr;
	private String tekst;
	
	public Postering(LocalDateTime datotid, BigDecimal belob, int regnr, int kontonr, String tekst) {
		this.datotid = datotid;
		this.belob = belob;
		this.regnr = regnr;
		this.kontonr = kontonr;
		this.tekst = tekst;
	}
	public LocalDateTime getDatotid() {
		return datotid;
	}

	public BigDecimal getBelob() {
		return belob;
	}

	public int getRegnr() {
		return regnr;
	}

	public int getKontonr() {
		return kontonr;
	}

	public String getTekst() {
		return tekst;
	}

	@Override
	public int compareTo(Postering o) {
		return this.datotid.isBefore(o.getDatotid()) ? 1 : -1;
	}
	@Override
	public String toString() {
		return "Postering [datotid=" + datotid + ", belob=" + belob + ", regnr=" + regnr + ", kontonr=" + kontonr
				+ ", tekst=" + tekst + "]";
	}



}
