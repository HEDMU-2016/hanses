package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Postering;

public class PosteringData {
	
	public void opretPostering(Postering domain) {
		try (DataAccess access = new DataAccess()) {
			try {
				opretPostering(access, domain);
				access.commit();
			} catch (Exception e) {
				access.rollback();
				throw e;
			}
		}
	}
	
	public void opretPostering(DataAccess access, Postering domain) {
		try (PreparedStatement stmt = access.getConnection().prepareStatement("Noget SQL");) {
			stmt.setString(1, domain.getTekst());
			stmt.setInt(2, domain.getRegnr());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("SQLException caught", e);
		}
		
	}

}
