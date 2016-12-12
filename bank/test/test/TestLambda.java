package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.PrivatKunde;

public class TestLambda {

	public static void main(String[] args) {
		TestLambda instans = new TestLambda();
		List<PrivatKunde> kunder = Arrays.asList(
				new PrivatKunde("Hans", "0123456789").setBirthdate(LocalDate.of(1951, 3, 1)),
				new PrivatKunde("Anders", "0123456789").setBirthdate(LocalDate.of(2001, 10, 1)),
				new PrivatKunde("Flemming", "0123456789").setBirthdate(LocalDate.of(2002, 10, 1)),
				new PrivatKunde("Mathilde", "0123456789").setBirthdate(LocalDate.of(1981, 10, 1)),
				new PrivatKunde("Søren", "0123456789").setBirthdate(LocalDate.of(1990, 1, 1))
				);

		System.out.println("Kunder yngre end 25 år");
		System.out.println(instans.kunderYngreEnd1(25, kunder));
		System.out.println("Kunder yngre end 50 år");
		System.out.println(instans.kunderYngreEnd1(50, kunder));

		System.out.println("Kunder yngre end 25 år");
		System.out.println(instans.kunderYngreEnd2(25, kunder));
		System.out.println("Kunder yngre end 50 år");
		System.out.println(instans.kunderYngreEnd2(50, kunder));
	}
	
	private List<String> kunderYngreEnd1(int alder, List<PrivatKunde> kunder) {
		List<String> navne = new ArrayList<>();
		for (PrivatKunde pk : kunder) {
			if (pk.getAlder() < alder) {
				navne.add(pk.getNavn());
			}
		}
		return navne;
	}
	
	private List<String> kunderYngreEnd2(int alder, List<PrivatKunde> kunder) {
		return kunder.parallelStream()
				.filter(pk -> pk.getAlder() < alder)
				.map(pk -> pk.getNavn())
				.collect(Collectors.toList());
	}


}
