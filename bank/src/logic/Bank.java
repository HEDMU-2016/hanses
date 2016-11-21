package logic;

import java.math.BigDecimal;

import domain.Konto;
import domain.Kunde;

public interface Bank {
	
	// Kundevedligehold
	public Kunde hentKunde(String id) throws KundeFindesIkkeException;
	
	public void opretKunde(Kunde kunde); 
//	public ?? opdaterKunde(???);
//	public ??? sletKunde(???);
	
	// Kontovedligehold
//	???
	
			
	// Kontooverførsel
	// Kontroller at kunde og de to konti eksisterer.
	// Kontroller, at frakonto tilhører kunde, og at der er penge på kontoen.
	// Beløb skal være positivt. (Der må ikke kunne tages penge på frakonto).
	// Hvis alt er ok, laves to Posteringer, og saldo ændres på de to konti.
	public void overforsel(Kunde kunde, Konto frakonto, Konto tilkonto, BigDecimal belob);

}
