package javaee.ejb.data;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.domain.Konto;
import javaee.ejb.entity.KontoEntity;
import javaee.ejb.entity.KontoPK;

/**
 * Session Bean implementation class KundeDataBean
 */
@Stateless
@LocalBean
public class KontoDataBean {

	@PersistenceContext private EntityManager em;
	@Resource private SessionContext ctx;
	
	public void opretKonto(Konto konto) {
		KontoEntity entity = new KontoEntity(konto);
		em.persist(entity);
	}
	
	public Optional<Konto> hentKonto(int regnr, int kontonr) {
		KontoPK id = new KontoPK(regnr, kontonr);
		KontoEntity entity = em.find(KontoEntity.class, id);
		if (entity != null) {
			return Optional.of(entity.map(new Konto()));
		} else {
			return Optional.empty();
		}
	}
	
	public void opdaterKonto(Konto konto) {
		KontoPK id = new KontoPK(konto.getRegnr(), konto.getKontonr());
		KontoEntity entity = em.find(KontoEntity.class, id);
		if (entity != null) {
			entity.update(konto);
		} else {
			throw new RuntimeException("Konto findes ikke");
		}
	}
	public void opdaterKontoSaldo(int regnr, int kontonr, BigDecimal belob) {
		KontoPK id = new KontoPK(regnr, kontonr);
		KontoEntity entity = em.find(KontoEntity.class, id);
		if (entity != null) {
			entity.setSaldo(entity.getSaldo().add(belob));
		} else {
			throw new RuntimeException("Konto findes ikke");
		}
	}
	
	public List<Konto> findKontiForKunde(long kundeid) {
		List<KontoEntity> kunder = em.createNamedQuery("getKontiForKunde", KontoEntity.class)
				.setParameter("kundeid", kundeid)
				.getResultList();
		return kunder.stream()
		.map(k -> k.map(new Konto()))
		.collect(toList());
	}
	
}
