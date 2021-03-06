package mc.lightcraft.java.remote.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import mc.lightcraft.java.common.config.Configuration;

import org.bukkit.plugin.Plugin;

public class MySQL extends Database {

	private final String user;
	private final String database;
	private final String password;
	private final String port;
	private final String hostname;
	private Connection connection;

	/**
	 * Creates a MySQL instance initiated using specified plugin and retrieving
	 * connection information from supplied Configuration file
	 * 
	 * @param plugin
	 *            The plugin that the MySQL instance is for
	 * @param file
	 *            the configuration file containing the required MySQL log-in
	 *            information
	 */
	public MySQL(Plugin plugin, Configuration file) {
		super(plugin);
		if (!file.GetConfig().isSet("MySQL.user")) {
			file.GetConfig().set("MySQL.user", "user");
			file.setChanged();
		}
		if (!file.GetConfig().isSet("MySQL.password")) {
			file.GetConfig().set("MySQL.password", "password");
			file.setChanged();
		}
		if (!file.GetConfig().isSet("MySQL.host")) {
			file.GetConfig().set("MySQL.host", "localhost");
			file.setChanged();
		}
		if (!file.GetConfig().isSet("MySQL.port")) {
			file.GetConfig().set("MySQL.port", "3306");
			file.setChanged();
		}
		if (!file.GetConfig().isSet("MySQL.database")) {
			file.GetConfig().set("MySQL.database", "db");
			file.setChanged();
		}
		if (file.getChanged()) {
			file.SaveConfig();
		}
		this.user = file.GetConfig().getString("MySQL.user");
		this.password = file.GetConfig().getString("MySQL.password");
		this.hostname = file.GetConfig().getString("MySQL.host");
		this.port = file.GetConfig().getString("MySQL.port");
		this.database = file.GetConfig().getString("MySQL.database");
	}

	/**
	 * Creates a MySQL instance initiated using specified plugin and supplied
	 * connection information
	 * 
	 * @param plugin
	 *            Plugin requesting the server connection
	 * @param hostname
	 *            name of the server host
	 * @param port
	 *            port to be used for connecting to the MySQL server
	 * @param database
	 *            name of the database that you wish to access
	 * @param username
	 *            username for opening the connection
	 * @param password
	 *            password to complete database log-in
	 */
	public MySQL(Plugin plugin, String hostname, String port, String database,
			String username, String password) {
		super(plugin);
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;
		this.connection = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.lightcraft.java.remote.MySQL.Database#openConnection()
	 */
	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://"
					+ this.hostname + ":" + this.port + "/" + this.database,
					this.user, this.password);
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			this.plugin.getLogger().log(Level.SEVERE,
					"Can't connect to MySQL server");
			closeConnection();
		} catch (ClassNotFoundException e) {
			this.plugin.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
		}
		return this.connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.lightcraft.java.remote.MySQL.Database#checkConnection()
	 */
	public boolean checkConnection() {
		return this.connection != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.lightcraft.java.remote.MySQL.Database#getConnection()
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mc.lightcraft.java.remote.MySQL.Database#closeConnection()
	 */
	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				this.plugin.getLogger().log(Level.SEVERE,
						"Error closing the MySQL Connection!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retrieve a resultSet from the current MySWL server based on provided
	 * query
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet querySQL(String query) {
		Connection c = null;
		if (checkConnection()) {
			c = getConnection();
		} else {
			c = openConnection();
		}
		Statement s = null;
		try {
			s = c.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet ret = null;
		try {
			ret = s.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Update the current MySQL database with provided information
	 * 
	 * @param update
	 *            the information you wish to update
	 */
	public void updateSQL(String update) {
		Connection c = null;
		if (checkConnection()) {
			c = getConnection();
		} else {
			c = openConnection();
		}
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate(update);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
