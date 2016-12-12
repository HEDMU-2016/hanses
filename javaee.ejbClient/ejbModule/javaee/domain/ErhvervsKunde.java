package javaee.domain;

public class ErhvervsKunde extends Kunde {
	private static final long serialVersionUID = 1L;
	private String cvrnr;

	public ErhvervsKunde(String name, String cvrnr) {
		super(name);
		this.cvrnr = cvrnr;
	}
	
	public String getCvrnr() {
		return cvrnr;
	}

	public ErhvervsKunde setCvrnr(String cvrnr) {
		this.cvrnr = cvrnr;
		return this;
	}

	@Override
	public String getId() {
		return cvrnr;
	}
	
}
