package javaee.ejb.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import javaee.domain.Kunde;

/**
 * Entity implementation class for Entity: KundeId
 *
 */
@Entity(name="KundeId")

@NamedQueries({
	@NamedQuery(name = "getKundeFromCpr", 
			query = "SELECT k FROM KundeId k " 
					+ "WHERE k.privatkunde.cprnr = :cpr "),
	@NamedQuery(name = "getKundeFromCvr", 
			query = "SELECT k FROM KundeId k " 
					+ "WHERE k.erhvervskunde.cvrnr = :cvr "),
	@NamedQuery(name = "alleKunder", 
			query = "SELECT k FROM KundeId k ") 
	})


public class KundeIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long kundeId;
	
	@OneToMany
	@JoinColumn(name = "kundeId", referencedColumnName = "kundeId")
	@OrderBy("startdato")
	private Collection<KundeEntity> kunder;
	
	@OneToMany
	@JoinColumn(name = "kundeId", referencedColumnName = "kundeId")
	private Collection<KontoEntity> konti;
	
	@OneToOne
	@JoinColumn(name = "kundeId", referencedColumnName = "kundeId")
	private PrivatkundeEntity privatkunde;

	@OneToOne
	@JoinColumn(name = "kundeId", referencedColumnName = "kundeId")
	private ErhvervskundeEntity erhvervskunde;

	public KundeIdEntity() {
		super();
	}
	
	public KundeIdEntity(Kunde kunde) {
		super();
		this.kundeId = kunde.getKundeId();
		update(kunde);
	}
	
	public void update(Kunde kunde) {
	}

	public long getKundeId() {
		return this.kundeId;
	}

	public void setKundeId(long kundeId) {
		this.kundeId = kundeId;
	}


	public PrivatkundeEntity getPrivatkunde() {
		return privatkunde;
	}

	public void setPrivatkunde(PrivatkundeEntity privatkunde) {
		this.privatkunde = privatkunde;
	}

	public ErhvervskundeEntity getErhvervskunde() {
		return erhvervskunde;
	}

	public void setErhvervskunde(ErhvervskundeEntity erhvervskunde) {
		this.erhvervskunde = erhvervskunde;
	}

	public Collection<KundeEntity> getKunder() {
		return kunder;
	}

	public void setKunder(Collection<KundeEntity> kunder) {
		this.kunder = kunder;
	}

	public Collection<KontoEntity> getKonti() {
		return konti;
	}

	public void setKonti(Collection<KontoEntity> konti) {
		this.konti = konti;
	}

}
