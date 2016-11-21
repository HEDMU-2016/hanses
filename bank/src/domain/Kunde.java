package domain;

import java.io.Serializable;

public abstract class Kunde implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String name;
	
	public Kunde(String name) {
		this.name = name;
	}
	
	public abstract String getId();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Kunde [name=" + name + ", id=" + getId() + "]";
	}

}
