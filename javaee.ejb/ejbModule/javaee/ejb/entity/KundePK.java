package javaee.ejb.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class KundePK implements Serializable {
	private static final long serialVersionUID = 1L;
	private long kundeId;
	private LocalDate startdato;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (kundeId ^ (kundeId >>> 32));
		result = prime * result + ((startdato == null) ? 0 : startdato.hashCode());
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
		KundePK other = (KundePK) obj;
		if (kundeId != other.kundeId)
			return false;
		if (startdato == null) {
			if (other.startdato != null)
				return false;
		} else if (!startdato.equals(other.startdato))
			return false;
		return true;
	}
	

}
