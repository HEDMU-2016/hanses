package javaee.ejb.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javaee.domain.Konto;
import javaee.domain.Kunde;
import javaee.domain.Overforsel;
import javaee.domain.Postering;
import javaee.ejb.data.KontoDataBean;
import javaee.ejb.data.KundeDataBean;
import javaee.ejb.data.PosteringDataBean;

/**
 * Session Bean implementation class BankBean
 */
@Stateless
@LocalBean
public class BankBean implements BankBeanLocal {

	@EJB private KundeDataBean kundedata;
	@EJB private KontoDataBean kontodata;
	@EJB private PosteringDataBean posteringdata;
	@Resource private SessionContext ctx;
	
	@Override
	public long opretKunde(Kunde kunde) {
		return kundedata.opretKunde(kunde);
	}

	@Override
	public void opdaterKunde(Kunde kunde) {
		kundedata.opdaterKunde(kunde);
	}

	@Override
	public Optional<Kunde> hentKunde(String cprcvr, LocalDate dato) {
		return kundedata.hentKunde(cprcvr, dato);
	}

	@Override
	public Optional<Kunde> hentKunde(long kundeid, LocalDate dato) {
		return kundedata.hentKunde(kundeid, dato);
	}

	@Override
	public List<Kunde> findKunder(String soeg, LocalDate dato) {
		return kundedata.findKunder(soeg, dato);
	}

	@Override
	public List<Konto> hentKonti(long kundeid) {
		return kontodata.findKontiForKunde(kundeid);
	}

	@Override
	public List<Postering> hentPosteringer(int regnr, int kontonr) {
		return posteringdata.hentPosteringer(regnr, kontonr);
	}

	@Override
	public void overfor(Overforsel overforsel) {
		if (validerOverforsel(overforsel)) {
			Postering frapostering = new Postering();
			frapostering.setDatotid(LocalDateTime.now());
			frapostering.setRegnr(overforsel.getFraRegnr());
			frapostering.setKontonr(overforsel.getFraKontonr());
			frapostering.setTekst(overforsel.getFraTekst());
			frapostering.setBelob(overforsel.getBelob().negate());
			
			Postering tilpostering = new Postering();
			tilpostering.setDatotid(LocalDateTime.now());
			tilpostering.setRegnr(overforsel.getTilRegnr());
			tilpostering.setKontonr(overforsel.getTilKontonr());
			tilpostering.setTekst(overforsel.getTilTekst());
			tilpostering.setBelob(overforsel.getBelob());
			
			kontodata.opdaterKontoSaldo(overforsel.getFraRegnr(), overforsel.getFraKontonr(), overforsel.getBelob().negate());
			kontodata.opdaterKontoSaldo(overforsel.getTilRegnr(), overforsel.getTilKontonr(), overforsel.getBelob());
			posteringdata.opretPostering(frapostering);
			posteringdata.opretPostering(tilpostering);
		}
	}

	@Override
	public void opretKonto(Konto konto) {
		kontodata.opretKonto(konto);
	}

	private boolean validerOverforsel(Overforsel overforsel) {
		Optional<Konto> optionalFrakonto = kontodata.hentKonto(overforsel.getFraRegnr(), overforsel.getFraKontonr());
		if (!optionalFrakonto.isPresent()) {
			throw new RuntimeException("Frakonto findes ikke");
		}
		Optional<Konto> optionalTilkonto = kontodata.hentKonto(overforsel.getTilRegnr(), overforsel.getTilKontonr());
		if (!optionalTilkonto.isPresent()) {
			throw new RuntimeException("Tilkonto findes ikke");
		}
		Konto frakonto = optionalFrakonto.get();
		if (overforsel.getKundeid() != frakonto.getKundeid()) {
			throw new RuntimeException("Frakonto tilhører ikke kunde");
		}
		if (overforsel.getBelob().signum() < 0) {
			throw new RuntimeException("Beløb må ikke være negativt");
		}
		if (frakonto.getSaldo().subtract(overforsel.getBelob()).compareTo(frakonto.getMinimumSaldo()) < 0) {
			throw new RuntimeException("Saldo på frakonto bliver mindre end tilladt minimum");
		}
		return true;
	}

}
