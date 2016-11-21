package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Postering implements Comparable<Postering>{
	private LocalDateTime datotid;
	private BigDecimal belob;
	private long konto;
	private String tekst;
	
	public Postering(LocalDateTime datotid, BigDecimal belob, long konto, String tekst) {
		this.datotid = datotid;
		this.belob = belob;
		this.konto = konto;
		this.tekst = tekst;
	}
	
	public LocalDateTime getDatotid() {
		return datotid;
	}
	public BigDecimal getBelob() {
		return belob;
	}
	public long getKonto() {
		return konto;
	}
	public String getTekst() {
		return tekst;
	}
	@Override
	public String toString() {
		return "Postering [datotid=" + datotid + ", belob=" + belob + ", konto=" + konto + ", tekst=" + tekst + "]";
	}

	@Override
	public int compareTo(Postering o) {
		return this.datotid.isBefore(o.getDatotid()) ? 1 : -1;
	}

}
