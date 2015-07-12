package com.epam.training.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

/**
 * Class {@code ConnectionPool} provides a thread-safe pool of connections to
 * MySQL database. The pool is constructed using Singleton design pattern
 * (allows to create only 1 instance of pool). Database properties are stored in
 * a separate file. The size of the pool is limited. It contains methods for
 * extracting and releasing DB connections.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see java.util.concurrent.ArrayBlockingQueue
 */
public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
	private static final String PROP_FILE_NAME = "mysqlDB";
	private static ResourceBundle rb;
	private static Properties prop;
	private int poolsize;
	private ArrayBlockingQueue<Connection> poolOfConnections;

	/* Constructor is made private to implement Singleton design pattern */
	/* initializes a thread-safe queue of connections */
	private ConnectionPool() {
		try {
			rb = ResourceBundle.getBundle(PROP_FILE_NAME);
			prop = new Properties();
			this.poolsize = Integer.parseInt(rb.getString("poolsize"));
			this.poolOfConnections = new ArrayBlockingQueue<>(poolsize);

			for (int i = 0; i < poolsize; i++) {
				poolOfConnections.add(openOneConnection());
			}
		} catch (MissingResourceException ex) {
			LOG.fatal("Error. Unable to find " + PROP_FILE_NAME + " file!");
			throw new RuntimeException("Missing " + PROP_FILE_NAME + " file",
					ex);
		}
	}

	/*
	 * Private class that contains static field where ConnectionPool object is
	 * initialized. Such schema prevents creation of 2 or more ConnectionPool
	 * objects in a multithread environment
	 * 
	 * (see Singleton design pattern)
	 */
	private static class ConnectionPoolHolder {
		private static final ConnectionPool pool = new ConnectionPool();
	}

	/**
	 * Returns the only instance of ConnectionPool
	 * 
	 * @return com.epam.training.connection.ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		return ConnectionPoolHolder.pool;
	}

	/**
	 * Takes a database connection from pool
	 * 
	 * @return java.sql.Connection
	 */
	public Connection getConnection() {
		Connection connection = null;

		try {
			/* takes a connection from pool */
			/* waits if needed for the element to become available */
			connection = poolOfConnections.take();
		} catch (InterruptedException exception) {
			LOG.error("Error. A waiting thread was interrupted!");
		}
		return connection;
	}

	/**
	 * Releases a database connection by returning it to the pool
	 * 
	 * @param connection
	 *            Connection that was previously taken
	 * @return {@code true} if Connection was successfully returned to the pool,
	 *         and {@code false} otherwise.
	 */
	public boolean releaseConnection(Connection connection) {
		boolean isReleased = false;

		if (connection != null) {
			try {
				/* puts the Connection into the pool */
				poolOfConnections.put(connection);
				isReleased = true;
			} catch (InterruptedException exception) {
				LOG.error("Error. A waiting thread was interrupted!");
			}
		}
		return isReleased;
	}

	/**
	 * Closes all initialized database connections
	 * 
	 * @return {@code true} if all DB Connections were closed and {@code false}
	 *         otherwise.
	 */
	public boolean closeAllConnections() {
		boolean areClosed = false;

		try {
			/* closing all connections by iterating over the pool */
			for (Connection connection : poolOfConnections) {
				connection.close();
			}
			areClosed = true;
		} catch (SQLException exception) {
			LOG.error("Error. Unable to close connection!");
		}
		return areClosed;
	}

	/* opens a connection with a database */
	private Connection openOneConnection() {
		Connection connection = null;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		try (InputStream is = cl.getResourceAsStream(PROP_FILE_NAME + ".properties")) {
			if (is != null) {
				prop.load(is); // loads properties using InputStream
			} else {
				LOG.fatal("Error. Unable to find " + PROP_FILE_NAME + " file!");
				throw new RuntimeException("Missing " + PROP_FILE_NAME
						+ " file");
			}
			Class.forName(rb.getString("driver"));
			/* gets connection using the url and properties loaded from file */
			connection = DriverManager.getConnection(rb.getString("url"), prop);
		} catch (IOException exception) {
			LOG.fatal("Error. Unable to read from " + PROP_FILE_NAME + " file!");
			throw new RuntimeException("Database connection failure", exception);
		} catch (SQLException exception) {
			LOG.fatal("Error. Unable to access the database!");
			throw new RuntimeException("Database connection failure", exception);
		} catch (ClassNotFoundException exception) {
			LOG.fatal("Error. Unable to find " + rb.getString("driver") + "!");
			throw new RuntimeException("Database connection failure", exception);
		}
		return connection;
	}
}
