package javaee.ejb.data;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.domain.ErhvervsKunde;
import javaee.domain.Kunde;
import javaee.domain.PrivatKunde;
import javaee.ejb.entity.ErhvervskundeEntity;
import javaee.ejb.entity.KundeEntity;
import javaee.ejb.entity.KundeIdEntity;
import javaee.ejb.entity.PrivatkundeEntity;

/**
 * Session Bean implementation class KundeDataBean
 */
@Stateless
@LocalBean
public class KundeDataBean {

	@EJB private PostnrDataBean pd;
	@PersistenceContext private EntityManager em;
	@Resource private SessionContext ctx;
	
	public long opretKunde(Kunde kunde) {
		Optional<String> optional = pd.hentPostnr(kunde.getPostnr());
		if (!optional.isPresent()) {
			pd.opretPostnr(kunde.getPostnr(), kunde.getBy());
		}
		KundeIdEntity idEntity = new KundeIdEntity(kunde);
		em.persist(idEntity);
		kunde.setKundeId(idEntity.getKundeId());
		KundeEntity entity = new KundeEntity(kunde);
		em.persist(entity);
		if (kunde instanceof PrivatKunde) {
			PrivatkundeEntity pkEntity = new PrivatkundeEntity((PrivatKunde)kunde);
			em.persist(pkEntity);
		} else if (kunde instanceof ErhvervsKunde) {
			ErhvervskundeEntity ekEntity = new ErhvervskundeEntity((ErhvervsKunde)kunde);
			em.persist(ekEntity);
		}
		return idEntity.getKundeId();
	}
	
	public Optional<Kunde> hentKunde(long id, LocalDate dato) {
		KundeIdEntity entity = em.find(KundeIdEntity.class, id);
		return map(entity, dato);
	}
	
	public Optional<Kunde> hentKunde(String cprcvr, LocalDate dato) {
		List<KundeIdEntity> pkunder = em.createNamedQuery("getKundeFromCpr", KundeIdEntity.class)
				.setParameter("cpr", cprcvr)
				.getResultList();
		if (pkunder.size() == 1) {
			return map(pkunder.get(0), dato);
		} else {
			List<KundeIdEntity> ekunder = em.createNamedQuery("getKundeFromCvr", KundeIdEntity.class)
					.setParameter("cvr", cprcvr)
					.getResultList();
			if (ekunder.size() == 1) {
				return map(ekunder.get(0), dato);
			} else {
				return Optional.empty();
			}
		}
	}
	
	public void opdaterKunde(Kunde kunde) {
		KundeIdEntity entity = em.find(KundeIdEntity.class, kunde.getKundeId());
		KundeEntity aktuelkunde = null;
		if (entity == null) {
			throw new RuntimeException("Kunde ikke fundet med id " + kunde.getKundeId());
		} else {
			Iterator<KundeEntity> iterator = entity.getKunder().iterator();
			while (iterator.hasNext()) {
				KundeEntity next = iterator.next();
				if (aktuelkunde == null && next.getStartdato().isEqual(kunde.getStartdato())) {
					aktuelkunde = next;
					break;
				}
			}
			if (aktuelkunde == null) {
				throw new RuntimeException("Kunde ikke fundet med startdato " + kunde.getStartdato());
			} else {
				aktuelkunde.update(kunde);
			}
			if (entity.getPrivatkunde() != null && kunde instanceof PrivatKunde) {
				entity.getPrivatkunde().update((PrivatKunde)kunde);
			} else if (entity.getErhvervskunde() != null && kunde instanceof ErhvervsKunde){
				entity.getErhvervskunde().update((ErhvervsKunde)kunde);
			}
		}
	}
	
	public List<Kunde> findKunder(String soeg, LocalDate dato) {
		List<KundeIdEntity> kunder = em.createNamedQuery("alleKunder", KundeIdEntity.class)
				.getResultList();
		return kunder.stream()
		.map(k -> map(k, dato))
		.filter(o -> o.isPresent())
		.map(o -> o.get())
		.filter(k -> k.getNavn().contains(soeg) || 
				k.getEmail().contains(soeg) || 
				k.getGade().contains(soeg))
		.collect(toList());
	}
	
	private Optional<Kunde> map(KundeIdEntity entity, LocalDate date) {
		KundeEntity aktuelkunde = null;
		if (entity == null) {
			return Optional.empty();
		} else {
			Iterator<KundeEntity> iterator = entity.getKunder().iterator();
			while (iterator.hasNext()) {
				KundeEntity next = iterator.next();
				if (aktuelkunde == null && 
						!next.getStartdato().isAfter(date) &&
						next.getSlutdato().isAfter(date)) {
					aktuelkunde = next;
				} else if(!next.getStartdato().isAfter(date) &&
						next.getSlutdato().isAfter(date) &&
						aktuelkunde.getStartdato().isBefore(next.getStartdato())) {
					aktuelkunde = next;
				}
			}
			if (aktuelkunde == null) {
				return Optional.empty();
			} else {
				Kunde kunde = null;
				if (entity.getPrivatkunde() != null) {
					PrivatKunde pkunde = new PrivatKunde(aktuelkunde.getNavn(), entity.getPrivatkunde().getCprnr());
					pkunde.setBirthdate(entity.getPrivatkunde().getFdato());
					kunde = aktuelkunde.map(pkunde);
				} else if (entity.getErhvervskunde() != null) {
					ErhvervsKunde ekunde = new ErhvervsKunde(aktuelkunde.getNavn(), entity.getErhvervskunde().getCvrnr());
					kunde = aktuelkunde.map(ekunde);
				}
				return Optional.of(kunde);
			}
		}
	}


}
