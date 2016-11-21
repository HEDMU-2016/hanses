package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefonDataAccess {
	private static final String SELECT_ALL = "SELECT telefonnummer FROM telefon WHERE kontaktid = ?";
	private static final String INSERT_ONE = "INSERT INTO telefon (kontaktid, telefonnummer) VALUES (?, ?)";
	private static final String DELETE_ALL = "DELETE FROM telefon WHERE kontaktid = ?";

	List<String> listTelefoner(DataAccess dataaccess, int kontaktid) {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(SELECT_ALL);) {
			statement.setInt(1, kontaktid);
			try (ResultSet resultset = statement.executeQuery();) {
				List<String> list = new ArrayList<String>();
				while (resultset.next()) {
					list.add(resultset.getString("telefonnummer"));
				}
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}

	void deleteTelefoner(DataAccess dataaccess, int kontaktid){
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(DELETE_ALL);) {
			statement.setInt(1, kontaktid);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	void createTelefoner(DataAccess dataaccess, int kontaktid, List<String> telefonnumre) {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);) {
			for(String telefonnummer: telefonnumre) {
				statement.setInt(1, kontaktid);
				statement.setString(2, telefonnummer);
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
