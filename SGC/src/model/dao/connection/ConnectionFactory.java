package model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class ConnectionFactory {
	private static Map<String, Connection> poolMySQLConnection = new HashMap<String, Connection>();
	private static int countTentativesToConnect = 0;

	private ConnectionFactory() {}

	public static Connection getMySQLConnection(String url, String user, String password) throws SQLException {
		try {
			String key = url +"-"+ user;
			Connection con = poolMySQLConnection.get(key);

			if(con == null || con.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				poolMySQLConnection.put(key, DriverManager.getConnection("jdbc:mysql://" + url, user, password));
			}

			poolMySQLConnection.get(key).prepareStatement("SHOW TABLES").execute();

			countTentativesToConnect = 0;

			return poolMySQLConnection.get(key);

		} catch(ClassNotFoundException e) {
			throw new SQLException(e.getMessage());

		} catch(com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException e) {
			return ConnectionFactory.resetMySQLConnection(url, user, password);

		} catch(com.mysql.jdbc.CommunicationsException e) {
			return ConnectionFactory.resetMySQLConnection(url, user, password);

		}
	}

	private static Connection resetMySQLConnection(String url, String user, String password) throws SQLException {
		countTentativesToConnect ++;

		if(countTentativesToConnect > 5) {
			countTentativesToConnect = 0;
			System.out.println("Fail to reconnect! A broked pipe was have ocurred.");
			return null;

		} else {
			return ConnectionFactory.getMySQLConnection(url, user, password);

		}
	}
}