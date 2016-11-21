package dataaccess;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccess implements Closeable {
	private static final String CONNECTION_URL = "jdbc:hsqldb:hsql://localhost/mydb";
	private static final String DB_USER = "SA";
	private static final String DB_PASSWORD = "";
	
	private Connection connection = null;

	public DataAccess() {
		try {
			connection = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException("DataAccess not created", e);
		}
	}
	
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("Close failed", e);
			}
			this.connection = null;
		} else {
			throw new RuntimeException("Connection not available (null). Not closed");
		}
	}
	
	public void commit() {
		if (this.connection != null) {
			try {
				this.connection.commit();
			} catch (SQLException e) {
				throw new RuntimeException("Commit failed", e);
			}
		} else {
			throw new RuntimeException("Connection not available (null). Not committed");
		}
	}

	public void rollback() {
		if (this.connection != null) {
			try {
				this.connection.rollback();
			} catch (SQLException e) {
				throw new RuntimeException("Rollback failed", e);
			}
		} else {
			throw new RuntimeException("Connection not available (null). Not rolled back");
		}
	}

	Connection getConnection() {
		return connection;
	}

}
