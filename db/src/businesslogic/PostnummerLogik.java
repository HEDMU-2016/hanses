package businesslogic;

import java.util.List;

import dataaccess.DataAccess;
import dataaccess.PostNummerDataAccess;
import domain.PostNummer;
import exceptions.PostnummerAllreadyExists;
import exceptions.PostnummerDoesNotExists;

public class PostnummerLogik {


	public List<PostNummer> listPostnumre(String search) {
		try (DataAccess dataaccess = new DataAccess();) {
			try {
				PostNummerDataAccess postnummerda = new PostNummerDataAccess();
				List<PostNummer> list =  postnummerda.listPostnumre(dataaccess, search);
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

	public PostNummer readPostnummer(String postnummer) throws PostnummerDoesNotExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				PostNummerDataAccess postnummerda = new PostNummerDataAccess();
				PostNummer postnr = postnummerda.readPostnummer(dataacces, postnummer).get();
				dataacces.commit();
				return postnr;
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}

	public void createPostnummer(PostNummer postnummer) throws PostnummerAllreadyExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				PostNummerDataAccess postnummerda = new PostNummerDataAccess();
				postnummerda.createPostnummer(dataacces, postnummer);
				dataacces.commit();
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}

	public void updatepostnummer(PostNummer postnummer) throws PostnummerDoesNotExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				PostNummerDataAccess postnummerda = new PostNummerDataAccess();
				postnummerda.updatePostnummer(dataacces, postnummer);
				dataacces.commit();
			} catch (Exception e) {
				if (dataacces != null) {
					dataacces.rollback();
				}
				throw e;
			}
		}
	}

	public void deletePostnummer(String postnummer) throws PostnummerDoesNotExists {
		try (DataAccess dataacces = new DataAccess();) {
			try {
				PostNummerDataAccess postnummerda = new PostNummerDataAccess();
				postnummerda.deletePostnummer(dataacces, postnummer);
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
