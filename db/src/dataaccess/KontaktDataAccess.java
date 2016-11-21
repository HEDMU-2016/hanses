package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Kontakt;
import exceptions.KontaktAllreadyExists;
import exceptions.KontaktDoesNotExists;
import exceptions.PostnummerDoesNotExists;

public class KontaktDataAccess {

	private static final String SELECT_ONE = "SELECT navn, email, gade, postnummer FROM kontakt WHERE kontaktid = ?";
	private static final String SELECT_MANY = "SELECT kontaktid, navn, email, gade, postnummer FROM kontakt WHERE navn LIKE ?";
	private static final String INSERT_ONE = "INSERT INTO kontakt (navn, email, gade, postnummer) VALUES(?, ?, ?, ?)";
	private static final String UPDATE_ONE = "UPDATE kontakt SET navn = ?, email = ?, gade = ?, postnummer = ? where kontaktid = ?";
	private static final String DELETE_ONE = "DELETE FROM kontakt where kontaktid = ?";

	public Optional<Kontakt> readKontakt(DataAccess dataaccess, int kontaktid) throws PostnummerDoesNotExists {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);) {
			statement.setInt(1, kontaktid);
			try (ResultSet resultset = statement.executeQuery();) {
				PostNummerDataAccess postnummeraccess = new PostNummerDataAccess();
				TelefonDataAccess telefonaccess = new TelefonDataAccess();
				if (resultset.next()) {
					Kontakt kontakt = new Kontakt();
					kontakt.setKontaktid(kontaktid);
					kontakt.setNavn(resultset.getString("navn"));
					kontakt.setGade(resultset.getString("gade"));
					kontakt.setEmail(resultset.getString("email"));
					kontakt.setPostnummer(postnummeraccess.readPostnummer(dataaccess, resultset.getString("postnummer")).get());
					kontakt.setTelefoner(telefonaccess.listTelefoner(dataaccess, kontaktid));
					return Optional.of(kontakt);
				} else {
					return Optional.empty();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Kontakt> listKontakter(DataAccess dataaccess, String search) {
		PostNummerDataAccess postnummeraccess = new PostNummerDataAccess();
		TelefonDataAccess telefonaccess = new TelefonDataAccess();
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);) {
			statement.setString(1, "%" + search + "%");
			try (ResultSet resultset = statement.executeQuery();) {
				List<Kontakt> list = new ArrayList<Kontakt>();
				while (resultset.next()) {
					Kontakt kontakt = new Kontakt();
					kontakt.setKontaktid(resultset.getInt("kontaktid"));
					kontakt.setNavn(resultset.getString("navn"));
					kontakt.setGade(resultset.getString("gade"));
					kontakt.setEmail(resultset.getString("email"));
					kontakt.setPostnummer(postnummeraccess.readPostnummer(dataaccess, resultset.getString("postnummer")).get());
					kontakt.setTelefoner(telefonaccess.listTelefoner(dataaccess, kontakt.getKontaktid()));
					list.add(kontakt);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void createKontakt(DataAccess dataaccess, Kontakt kontakt) throws KontaktAllreadyExists {
		TelefonDataAccess telefonaccess = new TelefonDataAccess();
		int kontaktid = 0;
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(INSERT_ONE,
				PreparedStatement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, kontakt.getNavn());
			statement.setString(2, kontakt.getEmail());
			statement.setString(3, kontakt.getGade());
			statement.setString(4, kontakt.getPostnummer().getPostnr());
			statement.executeUpdate();
			try (ResultSet resultset = statement.getGeneratedKeys();) {
				if (resultset.next()) {
					kontaktid = resultset.getInt(1);
					telefonaccess.createTelefoner(dataaccess, kontaktid, kontakt.getTelefoner());
				}
			}
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new KontaktAllreadyExists();
			} else {
				throw new RuntimeException(e);
			}
		}
	}

	public void updateKontakt(DataAccess dataaccess, Kontakt kontakt) throws KontaktDoesNotExists {
		TelefonDataAccess telefonaccess = new TelefonDataAccess();
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(UPDATE_ONE);) {
			statement.setString(1, kontakt.getNavn());
			statement.setString(2, kontakt.getEmail());
			statement.setString(3, kontakt.getGade());
			statement.setString(4, kontakt.getPostnummer().getPostnr());
			statement.setInt(5, kontakt.getKontaktid());
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new KontaktDoesNotExists();
			}
			telefonaccess.deleteTelefoner(dataaccess, kontakt.getKontaktid());
			telefonaccess.createTelefoner(dataaccess, kontakt.getKontaktid(), kontakt.getTelefoner());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteKontakt(DataAccess dataaccess, int kontaktid) throws KontaktDoesNotExists {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);) {
			statement.setInt(1, kontaktid);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new KontaktDoesNotExists();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
