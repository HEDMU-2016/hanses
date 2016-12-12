package javaee.ejb.data;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.domain.Postering;
import javaee.ejb.entity.PosteringEntity;

/**
 * Session Bean implementation class BankDataBean
 */
@Stateless
@LocalBean
public class PosteringDataBean {

	@PersistenceContext private EntityManager em;
	@Resource private SessionContext ctx;
	
	public void opretPostering(Postering postering) {
		PosteringEntity entity = new PosteringEntity(postering);
		em.persist(entity);
	}
	
	public List<Postering> hentPosteringer(int regnr, int kontonr) {
		List<PosteringEntity> posteringer = em.createNamedQuery("getPosteringer", PosteringEntity.class)
				.setParameter("regnr", regnr)
				.setParameter("kontonr", kontonr)
				.getResultList();
		return posteringer.stream()
				.map(p -> p.map(new Postering()))
				.collect(Collectors.toList());
	}


}
