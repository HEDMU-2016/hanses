package javaee.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javaee.domain.Postering;

/**
 * Entity implementation class for Entity: PosteringEntity
 *
 */
@Entity(name="Postering")

@NamedQueries({
	@NamedQuery(name = "getPosteringer", 
			query = "SELECT p FROM Postering p " 
					+ "WHERE p.regnr = :regnr "
					+ "AND p.kontonr = :kontonr "
					+ "ORDER BY p.timestamp DESC")
	})

public class PosteringEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private LocalDateTime timestamp;
	private int regnr;
	private int kontonr;
	private String tekst;
	private BigDecimal belob;
	

	public PosteringEntity() {
		super();
	}
	
	public PosteringEntity(Postering postering) {
		super();
		this.timestamp = postering.getDatotid();
		this.regnr = postering.getRegnr();
		this.kontonr = postering.getKontonr();
		this.tekst = postering.getTekst();
		this.belob = postering.getBelob();
	}
	
	public Postering map(Postering postering) {
		postering.setDatotid(timestamp);
		postering.setRegnr(regnr);
		postering.setKontonr(kontonr);
		postering.setTekst(tekst);
		postering.setBelob(belob);
		return postering;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}   
	public String getTekst() {
		return this.tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public BigDecimal getBelob() {
		return belob;
	}
	public void setBelob(BigDecimal belob) {
		this.belob = belob;
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
   
}
