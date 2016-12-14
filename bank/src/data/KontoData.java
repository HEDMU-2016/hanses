package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Konto;

public class KontoData {
	
	public Konto hentGlKonto(int regnr, int kontonr) {
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "SA", "");
				PreparedStatement stmt = connection.prepareStatement("Noget SQL")) {
			stmt.setInt(1, regnr);
			stmt.setInt(2, kontonr);
			try (ResultSet rs = stmt.executeQuery();) {
				Konto domain = new Konto();
				if (rs.next()) {
					domain.setRegnr(regnr);
					domain.setKontonr(kontonr);
					domain.setKontotype(rs.getInt("kontotype"));
					return domain;
				} else {
					throw new RuntimeException("Konto findes ikke");
				}
			} catch (Exception e) {
				throw new RuntimeException("Noget gik helt galt", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(" Get Connection failed", e);
		}

	}
	
	public Konto hentKonto(int regnr, int kontonr) {
		try (DataAccess access = new DataAccess()) {
			try {
				Konto domain = hentKonto(access, regnr, kontonr);
				access.commit();
				return domain;
			} catch (Exception e) {
				access.rollback();
				throw e;
			}
		}

	}
	
	public Konto hentKonto(DataAccess access, int regnr, int kontonr){
		try (PreparedStatement stmt = access.getConnection().prepareStatement("Noget SQL")) {
			stmt.setInt(1, regnr);
			stmt.setInt(2, kontonr);
			try (ResultSet rs = stmt.executeQuery();) {
				Konto domain = new Konto();
				if (rs.next()) {
					domain.setRegnr(regnr);
					domain.setKontonr(kontonr);
					domain.setKontotype(rs.getInt("kontotype"));
					return domain;
				} else {
					throw new RuntimeException("Konto findes ikke");
				}
			} catch (Exception e) {
				throw new RuntimeException("Noget gik helt galt", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQLException caught", e);
		}
		
	}
	
	public void opdaterKonto(Konto domain) {
		try (DataAccess access = new DataAccess()) {
			try {
				opdaterKonto(access, domain);
				access.commit();
			} catch (Exception e) {
				access.rollback();
				throw e;
			}
		}
	}
	
	public void opdaterKonto(DataAccess access, Konto domain) {
		try (PreparedStatement stmt = access.getConnection().prepareStatement("Noget SQL")) {
			stmt.setInt(1, domain.getKontotype());
			stmt.setInt(2, domain.getRegnr());
			int antal = stmt.executeUpdate();
			if (antal != 1) {
				throw new RuntimeException("Konto findes ikke");
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQLException caught", e);
		}
		
	}

}
