package domain;

import java.time.LocalDate;
import java.time.Period;

public class PrivatKunde extends Kunde {
	private static final long serialVersionUID = 1L;
	private String cprnr;
	private LocalDate birthdate;

	public PrivatKunde(String name, String cprnr) {
		super(name);
		this.cprnr = cprnr;
	}
	
	public String getCprnr() {
		return cprnr;
	}

	public void setCprnr(String cprnr) {
		this.cprnr = cprnr;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return "PrivatKunde [cprnr=" + cprnr + ", name=" + name + ", birthdate=" + birthdate + "]";
	}

	@Override
	public String getId() {
		return cprnr;
	}
	
	public int getAlder() {
		return getAlder(LocalDate.now());
	}
	
	protected int getAlder(LocalDate date) {
		Period period = Period.between(birthdate, date);
		return period.getYears();
	}

	
}
