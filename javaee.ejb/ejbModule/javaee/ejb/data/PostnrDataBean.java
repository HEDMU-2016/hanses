package javaee.ejb.data;

import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.ejb.entity.PostnummerEntity;

/**
 * Session Bean implementation class BankDataBean
 */
@Stateless
@LocalBean
public class PostnrDataBean {

	@PersistenceContext private EntityManager em;
	@Resource private SessionContext ctx;
	
	public void opretPostnr(String postnr, String by) {
		PostnummerEntity entity = new PostnummerEntity(postnr, by);
		em.persist(entity);
	}
	
	public Optional<String> hentPostnr(String postnr) {
		PostnummerEntity entity = em.find(PostnummerEntity.class, postnr);
		if (entity != null) {
			return Optional.of(entity.getBy());
		} else {
			return Optional.empty();
		}
	}


}
