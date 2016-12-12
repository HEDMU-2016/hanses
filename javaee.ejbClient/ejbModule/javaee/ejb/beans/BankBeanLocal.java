package javaee.ejb.beans;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import javaee.domain.Konto;
import javaee.domain.Kunde;
import javaee.domain.Overforsel;
import javaee.domain.Postering;

@Local
public interface BankBeanLocal {
	
	public long opretKunde(Kunde kunde);

	public void opdaterKunde(Kunde kunde);

	public Optional<Kunde> hentKunde(String cprcvr, LocalDate dato);

	public Optional<Kunde> hentKunde(long kundeid, LocalDate dato);

	public List<Kunde> findKunder(String soeg, LocalDate dato);
	
	public void opretKonto(Konto konto);
	
	public List<Konto> hentKonti(long kundeid);
	
	public void overfor(Overforsel overforsel);
	
	public List<Postering> hentPosteringer(int regnr, int kontonr);
	
	
}
