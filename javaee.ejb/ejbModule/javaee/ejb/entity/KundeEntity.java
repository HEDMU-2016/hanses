package javaee.ejb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javaee.domain.Kunde;

/**
 * Entity implementation class for Entity: Kunde
 *
 */
@Entity(name="Kunde")
@IdClass(KundePK.class)

public class KundeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long kundeId;
	@Id
	private LocalDate startdato;
	
	@Column(nullable=false)
	private LocalDate slutdato;
	@Column(nullable=false)
	private String navn;
	@Column(nullable=false)
	private String gade;
	private String telefon;
	private String email;
	@ManyToOne
	@JoinColumn(name="postnr", referencedColumnName="postnr", nullable=false)
	private PostnummerEntity postnr;
	

	public KundeEntity() {
		super();
	}
	
	public KundeEntity(Kunde kunde) {
		super();
		this.kundeId = kunde.getKundeId();
		this.startdato = kunde.getStartdato();
		update(kunde);
	}
	
	public void update(Kunde kunde) {
		this.slutdato = kunde.getSlutdato();
		this.navn = kunde.getNavn();
		this.gade = kunde.getGade();
		this.postnr = new PostnummerEntity();
		this.postnr.setPostnr(kunde.getPostnr());
		this.postnr.setBy(kunde.getBy());
		this.email = kunde.getEmail();
		this.telefon = kunde.getTelefon();
	}
	
	public Kunde map(Kunde kunde) {
		kunde.setKundeId(this.kundeId);
		kunde.setStartdato(this.startdato);
		kunde.setSlutdato(this.slutdato);
		kunde.setNavn(this.navn);
		kunde.setGade(this.gade);
		kunde.setPostnr(this.postnr.getPostnr());
		kunde.setBy(this.postnr.getBy());
		kunde.setEmail(this.email);
		kunde.setTelefon(this.telefon);
		return kunde;
	}

	public long getKundeId() {
		return this.kundeId;
	}

	public void setKundeId(long kundeId) {
		this.kundeId = kundeId;
	}

	public LocalDate getStartdato() {
		return this.startdato;
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

	public PostnummerEntity getPostnr() {
		return postnr;
	}

	public void setPostnr(PostnummerEntity postnr) {
		this.postnr = postnr;
	}

}
