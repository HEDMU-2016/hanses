package javaee.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javaee.domain.ErhvervsKunde;

/**
 * Entity implementation class for Entity: ErhvervskundeEntity
 *
 */
@Entity(name="ErhvervsKunde")

public class ErhvervskundeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long kundeId;
	@Column(length = 8, nullable = false, unique = true)
	private String cvrnr;
	
	public ErhvervskundeEntity() {
		super();
	}

	public ErhvervskundeEntity(ErhvervsKunde kunde) {
		super();
		this.kundeId = kunde.getKundeId();
		update(kunde);
	}
	
	public void update(ErhvervsKunde kunde) {
		this.cvrnr = kunde.getCvrnr();
	}

	public long getKundeId() {
		return kundeId;
	}

	public void setKundeId(long kundeId) {
		this.kundeId = kundeId;
	}

	public String getCvrnr() {
		return cvrnr;
	}

	public void setCvrnr(String cvrnr) {
		this.cvrnr = cvrnr;
	}

}
