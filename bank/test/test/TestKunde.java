package test;

import domain.Kunde;
import domain.PrivatKunde;

public class TestKunde {

	public static void main(String[] args) {
		Kunde kunde = new PrivatKunde("Hans", "010351");
		System.out.println(kunde.getId());
		System.out.println(kunde);
	}

}
