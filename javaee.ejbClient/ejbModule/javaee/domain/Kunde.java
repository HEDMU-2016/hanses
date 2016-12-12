package javaee.domain;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Kunde implements Serializable {
	private static final long serialVersionUID = 1L;
	protected long kundeId;
	protected LocalDate startdato;
	protected LocalDate slutdato;
	protected String navn;
	protected String gade;
	protected String postnr;
	protected String by;
	protected String telefon;
	protected String email;
	
	public Kunde(String navn) {
		this.navn = navn;
	}
	
	public abstract String getId();

	public long getKundeId() {
		return kundeId;
	}

	public void setKundeId(long kundeId) {
		this.kundeId = kundeId;
	}

	public LocalDate getStartdato() {
		return startdato;
	}

	public void setStartdato(LocalDate startdato) {
		this.startdato = startdato;
	}

	public LocalDate getSlutdato() {
		return slutdato;
	}

	public void setSlutdato(LocalDate slutdato) {
		this.slutdato = slutdato;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getGade() {
		return gade;
	}

	public void setGade(String gade) {
		this.gade = gade;
	}

	public String getPostnr() {
		return postnr;
	}

	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
