package logic;

import java.math.BigDecimal;

import domain.Konto;
import domain.Kunde;

public class BankV1 implements Bank {

	@Override
	public Kunde hentKunde(String id) throws KundeFindesIkkeException {
		throw new KundeFindesIkkeException();
	}

	@Override
	public void opretKunde(Kunde kunde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void overforsel(Kunde kunde, Konto frakonto, Konto tilkonto, BigDecimal belob) {
		// TODO Auto-generated method stub
		
	}


}
