package domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

public class KundeTest {
	private PrivatKunde privatkunde;
	
	@Before
	public void setUp() {
		privatkunde = new PrivatKunde("Hans", "0123456789");
		privatkunde.setBirthdate(LocalDate.of(1951, Month.MARCH, 1));
	}

	@Test
	public void testAlder() {
		assertEquals(65, privatkunde.getAlder(LocalDate.of(2017, Month.FEBRUARY, 28)));
		assertEquals(66, privatkunde.getAlder(LocalDate.of(2017, Month.MARCH, 1)));
	}

}
