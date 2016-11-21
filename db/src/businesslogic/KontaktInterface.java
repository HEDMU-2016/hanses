package businesslogic;

import java.sql.SQLException;
import java.util.List;

import domain.Kontakt;
import exceptions.KontaktAllreadyExists;
import exceptions.KontaktDoesNotExists;
import exceptions.PostnummerDoesNotExists;

/**
 * 
 * @author hi
 *
 */
public interface KontaktInterface {

	/**
	 * 
	 * @param search
	 * @return
	 * @throws SQLException
	 * @throws PostnummerDoesNotExists
	 */
	public List<Kontakt> listKontakter(String search) throws PostnummerDoesNotExists;
	
	public Kontakt readKontakt(int kontaktid) throws PostnummerDoesNotExists, KontaktDoesNotExists;
	
	public void createKontakt(Kontakt kontakt) throws KontaktAllreadyExists;
	
	public void updateKontakt(Kontakt kontakt) throws KontaktDoesNotExists;
	
	public void deleteKontakt(int kontaktid) throws KontaktDoesNotExists; 
	
}
