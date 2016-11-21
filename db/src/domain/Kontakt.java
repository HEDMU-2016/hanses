package domain;

import java.util.List;

public class Kontakt {
	private int kontaktid;
	private String navn;
	private String gade;
	private PostNummer postnummer;
	private String email;
	private List<String> telefoner;

	public Kontakt() {
	}

	public int getKontaktid() {
		return kontaktid;
	}

	public void setKontaktid(int kontaktid) {
		this.kontaktid = kontaktid;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getGade() {
		return gade;
	}

	public void setGade(String gade) {
		this.gade = gade;
	}

	public PostNummer getPostnummer() {
		return postnummer;
	}

	public void setPostnummer(PostNummer postnummer) {
		this.postnummer = postnummer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getTelefoner() {
		return telefoner;
	}

	public void setTelefoner(List<String> telefoner) {
		this.telefoner = telefoner;
	}

	@Override
	public String toString() {
		return "Kontakt [kontaktid=" + kontaktid + ", navn=" + navn + ", gade="
				+ gade + ", postnummer=" + postnummer + ", email=" + email
				+ ", telefoner=" + telefoner + "]";
	}


}
