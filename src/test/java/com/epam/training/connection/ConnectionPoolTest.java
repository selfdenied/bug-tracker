package com.epam.training.connection;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;
import org.apache.log4j.Logger;

/**
 * Class {@code ConnectionPoolTest} is used for testing the correct
 * initialization of ConnectionPool and correct operation of getConnection()
 * method.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.connection.ConnectionPool
 */
public class ConnectionPoolTest {
	private static final Logger LOG = Logger.getLogger(ConnectionPoolTest.class);

	/**
	 * Tests the correct initialization of ConnectionPool instance.
	 */
	@Test
	public void connectionPoolInitTest() {
		ConnectionPool pool = ConnectionPool.getInstance();
		LOG.info("The ConnectionPool has been initialized: " + (pool != null));
		Assert.assertNotNull(pool);
	}
	
	/**
	 * Tests the correct correct operation of getConnection() method.
	 */
	@Test
	public void getConnectionTest() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		LOG.info("Connection has been taken from pool: " + (connection != null));
		Assert.assertNotNull(connection);
	}
}
