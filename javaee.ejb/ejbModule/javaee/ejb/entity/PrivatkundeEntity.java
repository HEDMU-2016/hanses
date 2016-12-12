package javaee.ejb.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javaee.domain.PrivatKunde;

/**
 * Entity implementation class for Entity: PrivatkundeEntity
 *
 */
@Entity(name="PrivatKunde")

public class PrivatkundeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long kundeId;
	@Column(length = 10, nullable = false, unique = true)
	private String cprnr;
	
	@Column(nullable = false)
	private LocalDate fdato;

	public PrivatkundeEntity() {
		super();
	}
	
	public PrivatkundeEntity(PrivatKunde kunde) {
		super();
		this.kundeId = kunde.getKundeId();
		update(kunde);
	}

	public void update(PrivatKunde kunde) {
		this.cprnr = kunde.getCprnr();
		this.fdato = kunde.getBirthdate();
	}
	
	public long getKundeId() {
		return kundeId;
	}

	public void setKundeId(long kundeId) {
		this.kundeId = kundeId;
	}

	public String getCprnr() {
		return cprnr;
	}

	public void setCprnr(String cprnr) {
		this.cprnr = cprnr;
	}

	public LocalDate getFdato() {
		return fdato;
	}

	public void setFdato(LocalDate fdato) {
		this.fdato = fdato;
	}

}
