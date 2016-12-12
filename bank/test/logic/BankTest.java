package logic;

import static org.junit.Assert.*;
import org.junit.Test;

import domain.Kunde;

public class BankTest {

	@Test
	public void test() {
		Bank bank = new BankV1();
		try {
			Kunde kunde = bank.hentKunde("0");
			assertEquals("Navn ikke udfyldt", "Hans", kunde.getNavn());
		} catch (KundeFindesIkkeException e) {
			fail("Kunde med id 0 findes ikke");
		}
	}

}
