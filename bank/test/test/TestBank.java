package test;

import domain.Kunde;
import logic.Bank;
import logic.BankV1;
import logic.KundeFindesIkkeException;

public class TestBank {

	public static void main(String[] args) {
		Bank bank = new BankV1();
		Kunde kunde;
		try {
			kunde = bank.hentKunde("Hans");
			System.out.println(kunde);
		} catch (KundeFindesIkkeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
