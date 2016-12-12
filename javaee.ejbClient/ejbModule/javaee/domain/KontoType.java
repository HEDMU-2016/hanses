package javaee.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class KontoType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int type;
	private String beskrivelse; 
	private BigDecimal rente;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	public BigDecimal getRente() {
		return rente;
	}
	public void setRente(BigDecimal rente) {
		this.rente = rente;
	}
	
}
