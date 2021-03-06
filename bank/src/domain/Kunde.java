package domain;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Kunde implements Serializable {
	private static final long serialVersionUID = 1L;
	protected long dbid;
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

	public long getDbid() {
		return dbid;
	}

	public Kunde setDbid(long dbid) {
		this.dbid = dbid;
		return this;
	}

	public LocalDate getStartdato() {
		return startdato;
	}

	public Kunde setStartdato(LocalDate startdato) {
		this.startdato = startdato;
		return this;
	}

	public LocalDate getSlutdato() {
		return slutdato;
	}

	public Kunde setSlutdato(LocalDate slutdato) {
		this.slutdato = slutdato;
		return this;
	}

	public String getNavn() {
		return navn;
	}

	public Kunde setNavn(String navn) {
		this.navn = navn;
		return this;
	}

	public String getGade() {
		return gade;
	}

	public Kunde setGade(String gade) {
		this.gade = gade;
		return this;
	}

	public String getPostnr() {
		return postnr;
	}

	public Kunde setPostnr(String postnr) {
		this.postnr = postnr;
		return this;
	}

	public String getBy() {
		return by;
	}

	public Kunde setBy(String by) {
		this.by = by;
		return this;
	}

	public String getTelefon() {
		return telefon;
	}

	public Kunde setTelefon(String telefon) {
		this.telefon = telefon;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Kunde setEmail(String email) {
		this.email = email;
		return this;
	}

	@Override
	public String toString() {
		return "Kunde [dbid=" + dbid + ", startdato=" + startdato + ", slutdato=" + slutdato + ", navn=" + navn
				+ ", gade=" + gade + ", postnr=" + postnr + ", by=" + by + ", telefon=" + telefon + ", email=" + email
				+ "]";
	}


}
