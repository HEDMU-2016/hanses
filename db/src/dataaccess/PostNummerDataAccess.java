package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.PostNummer;
import exceptions.PostnummerAllreadyExists;
import exceptions.PostnummerDoesNotExists;

public class PostNummerDataAccess {
	
	private static final String SELECT_ONE = "SELECT bynavn FROM postnummer where postnummer = ?";
	private static final String SELECT_MANY = "SELECT postnummer, bynavn FROM postnummer where UPPER(bynavn) LIKE ? or postnummer LIKE ?";
	private static final String INSERT_ONE = "INSERT INTO postnummer (postnummer, bynavn) VALUES(?, ?)";
	private static final String UPDATE_ONE = "UPDATE postnummer SET bynavn = ? where postnummer = ?";
	private static final String DELETE_ONE = "DELETE FROM postnummer where postnummer = ?";

	public Optional<PostNummer> readPostnummer(DataAccess dataaccess, String postnummer) {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(SELECT_ONE);) {
			statement.setString(1, postnummer);
			try (ResultSet resultset = statement.executeQuery();) {
				if (resultset.next()) {
					PostNummer postnr = new PostNummer();
					postnr.setPostnr(postnummer);
					postnr.setBy(resultset.getString("bynavn"));
					return Optional.of(postnr);
				} else {
					return Optional.empty();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<PostNummer> listPostnumre(DataAccess dataaccess, String search) {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(SELECT_MANY);) {
			statement.setString(1, "%" + search.toUpperCase() + "%");
			statement.setString(2, "%" + search + "%");
			try (ResultSet resultset = statement.executeQuery();) {
				List<PostNummer> list = new ArrayList<PostNummer>();
				while (resultset.next()) {
					PostNummer postnr = new PostNummer();
					postnr.setPostnr(resultset.getString("postnummer"));
					postnr.setBy(resultset.getString("bynavn"));
					list.add(postnr);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createPostnummer(DataAccess dataaccess, PostNummer postnummer) throws PostnummerAllreadyExists {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(INSERT_ONE);) {
			statement.setString(1, postnummer.getPostnr());
			statement.setString(2, postnummer.getBy());
			statement.executeUpdate();
		} catch (SQLException e) {
			if (e.getSQLState().equalsIgnoreCase("23505")) {
				throw new PostnummerAllreadyExists();
			} else {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void updatePostnummer(DataAccess dataaccess, PostNummer postnummer) throws PostnummerDoesNotExists {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(UPDATE_ONE);) {
			statement.setString(1, postnummer.getBy());
			statement.setString(2, postnummer.getPostnr());
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new PostnummerDoesNotExists();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletePostnummer(DataAccess dataaccess, String postnummer) throws PostnummerDoesNotExists {
		try (PreparedStatement statement = dataaccess.getConnection().prepareStatement(DELETE_ONE);) {
			statement.setString(1, postnummer);
			int antal = statement.executeUpdate();
			if (antal != 1) {
				throw new PostnummerDoesNotExists();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
