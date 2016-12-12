package javaee.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: KontoTypeEntity
 *
 */
@Entity(name="KontoType")

public class KontoTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	private int kontotype;
	private String beskrivelse; 
	private BigDecimal rente;

	public KontoTypeEntity() {
		super();
	}   
	public int getKontotype() {
		return this.kontotype;
	}

	public void setKontotype(int kontotype) {
		this.kontotype = kontotype;
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
