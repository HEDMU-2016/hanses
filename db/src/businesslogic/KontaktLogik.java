package businesslogic;

import java.util.List;
import java.util.Optional;

import dataaccess.DataAccess;
import dataaccess.KontaktDataAccess;
import domain.Kontakt;
import exceptions.KontaktAllreadyExists;
import exceptions.KontaktDoesNotExists;
import exceptions.PostnummerDoesNotExists;

public class KontaktLogik implements KontaktInterface {


	@Override
	public List<Kontakt> listKontakter(String search) throws PostnummerDoesNotExists {
		try (DataAccess dataaccess = new DataAccess();) {
			KontaktDataAccess kontaktda = new KontaktDataAccess();
			try {
				List<Kontakt> list =  kontaktda.listKontakter(dataaccess, search);
				dataaccess.commit();
				return list;
			} catch (Exception e) {
				if (dataaccess != null) {
					dataaccess.rollback();
				}
				throw e;
			}
		}
	}

	@Override
	public Kontakt readKontakt(int kontaktid) throws PostnummerDoesNotExists, KontaktDoesNotExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				KontaktDataAccess kontaktda = new KontaktDataAccess();
				Optional<Kontakt> kontakt = kontaktda.readKontakt(dataacces, kontaktid);
				dataacces.commit();
				if (kontakt.isPresent()) {
					return kontakt.get();
				} else {
					throw new KontaktDoesNotExists();
				}
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}

	@Override
	public void createKontakt(Kontakt kontakt) throws KontaktAllreadyExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				KontaktDataAccess kontaktda = new KontaktDataAccess();
				kontaktda.createKontakt(dataacces, kontakt);
				dataacces.commit();
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}

	@Override
	public void updateKontakt(Kontakt kontakt) throws KontaktDoesNotExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				KontaktDataAccess kontaktda = new KontaktDataAccess();
				kontaktda.updateKontakt(dataacces, kontakt);
				dataacces.commit();
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}

	@Override
	public void deleteKontakt(int kontaktid) throws KontaktDoesNotExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				KontaktDataAccess kontaktda = new KontaktDataAccess();
				kontaktda.deleteKontakt(dataacces, kontaktid);
				dataacces.commit();
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}
}
