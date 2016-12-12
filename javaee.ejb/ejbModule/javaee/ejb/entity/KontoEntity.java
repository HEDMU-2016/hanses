package javaee.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javaee.domain.Konto;

/**
 * Entity implementation class for Entity: KontoEntity
 *
 */
@Entity(name="Konto")

@NamedQueries({
	@NamedQuery(name = "getKontiForKunde", 
			query = "SELECT k FROM Konto k " 
					+ "WHERE k.kundeId = :kundeid")
	})

@IdClass(KontoPK.class)
public class KontoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	private int regnr;   
	@Id
	private int kontonr;
	@Column(nullable=false)
	private long kundeId;
	
	@ManyToOne
	@JoinColumn(name="kontotype", referencedColumnName="kontotype", nullable=false)
	private KontoTypeEntity kontotype;
	
	private BigDecimal saldo;
	private BigDecimal minimumSaldo;

	@OneToMany
	@JoinColumns({
		@JoinColumn(name="regnr", referencedColumnName="regnr"),
		@JoinColumn(name="kontonr", referencedColumnName="kontonr")
	})
	private Collection<PosteringEntity> posteringer;
	
	public KontoEntity() {
		super();
	}  
	
	public KontoEntity(Konto konto) {
		super();
		this.kundeId = konto.getKundeid();
		this.regnr = konto.getRegnr();
		this.kontonr = konto.getKontonr();
		this.kontotype = new KontoTypeEntity();
		this.kontotype.setKontotype(konto.getKontotype());
		this.minimumSaldo = konto.getMinimumSaldo();
		this.saldo = konto.getSaldo();
	}
	
	public void update(Konto konto) {
		this.kontotype = new KontoTypeEntity();
		this.kontotype.setKontotype(konto.getKontotype());
		this.minimumSaldo = konto.getMinimumSaldo();
		this.saldo = konto.getSaldo();

	}
	
	public Konto map(Konto konto) {
		konto.setKundeid(kundeId);
		konto.setRegnr(regnr);
		konto.setKontonr(kontonr);
		konto.setKontotype(kontotype.getKontotype());
		konto.setMinimumSaldo(minimumSaldo);
		konto.setSaldo(saldo);
		return konto;
	}
	
	public int getRegnr() {
		return this.regnr;
	}

	public void setRegnr(int regnr) {
		this.regnr = regnr;
	}   
	public int getKontonr() {
		return this.kontonr;
	}

	public void setKontonr(int kontonr) {
		this.kontonr = kontonr;
	}
	public KontoTypeEntity getKontotype() {
		return kontotype;
	}
	public void setKontotype(KontoTypeEntity kontotype) {
		this.kontotype = kontotype;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getMinimumSaldo() {
		return minimumSaldo;
	}
	public void setMinimumSaldo(BigDecimal minimumSaldo) {
		this.minimumSaldo = minimumSaldo;
	}
	public long getKundeId() {
		return kundeId;
	}
	public void setKundeId(long kundeId) {
		this.kundeId = kundeId;
	}
	public Collection<PosteringEntity> getPosteringer() {
		return posteringer;
	}
	public void setPosteringer(Collection<PosteringEntity> posteringer) {
		this.posteringer = posteringer;
	}
   
}
