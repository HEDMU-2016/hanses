package javaee.ejb.entity;

import java.io.Serializable;

public class KontoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private int regnr;
	private int kontonr;
	
	public KontoPK() {
		super();
	}
	
	public KontoPK(int regnr, int kontonr) {
		super();
		this.regnr = regnr;
		this.kontonr = kontonr;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kontonr;
		result = prime * result + regnr;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KontoPK other = (KontoPK) obj;
		if (kontonr != other.kontonr)
			return false;
		if (regnr != other.regnr)
			return false;
		return true;
	}
	

}
