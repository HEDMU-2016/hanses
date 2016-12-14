package logic;

import java.time.LocalDateTime;

import data.DataAccess;
import data.KontoData;
import data.PosteringData;
import domain.Konto;
import domain.Kunde;
import domain.Overforsel;
import domain.Postering;

public class BankV1 implements Bank {

	private KontoData kontodata = new KontoData();
	private PosteringData posteringdata = new PosteringData();

	@Override
	public Kunde hentKunde(String id) throws KundeFindesIkkeException {
		throw new KundeFindesIkkeException();
	}

	@Override
	public void opretKunde(Kunde kunde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void overforsel(Overforsel overforsel) {
		try (DataAccess access = new DataAccess()) {
			try {
				//Hent konti
				Konto frakonto = kontodata.hentKonto(access, overforsel.getFraRegnr(), overforsel.getFraKontonr());
				Konto tilkonto = kontodata.hentKonto(access, overforsel.getTilRegnr(), overforsel.getTilKontonr());
				//Opdater beløb
				frakonto.setSaldo(frakonto.getSaldo().subtract(overforsel.getBelob()));
				tilkonto.setSaldo(tilkonto.getSaldo().add(overforsel.getBelob()));
				kontodata.opdaterKonto(access, frakonto);
				kontodata.opdaterKonto(access, tilkonto);
				//Indsæt posteringer
				Postering frapost = new Postering(LocalDateTime.now(), overforsel.getBelob().negate(), overforsel.getFraRegnr(), overforsel.getFraKontonr(), overforsel.getFraTekst());
				Postering tilpost = new Postering(LocalDateTime.now(), overforsel.getBelob(), overforsel.getTilRegnr(), overforsel.getTilKontonr(), overforsel.getTilTekst());
				posteringdata.opretPostering(access, frapost);
				posteringdata.opretPostering(access, tilpost);
				//Afslut normalt
				access.commit();
				
			} catch (Exception e) {
				access.rollback();
				throw e;
			}
			
		}
		
	}


}
