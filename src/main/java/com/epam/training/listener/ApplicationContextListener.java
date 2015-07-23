package com.epam.training.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.training.connection.ConnectionPool;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

/**
 * Class {@code ApplicationContextListener} is a Listener which sets a new
 * System property that stores the root path of the application and closes all
 * DB connections when the application is destroyed.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see javax.servlet.ServletContextListener
 */
public class ApplicationContextListener implements ServletContextListener {

	/**
	 * Constructs ApplicationContextListener object
	 */
	public ApplicationContextListener() {
	}

	/**
	 * This method is invoked when the application context is initialized. Here
	 * a new System property that stores the root path of the application is
	 * set.
	 * 
	 * @param event
	 *            javax.servlet.ServletContextEvent
	 * @see javax.servlet.ServletContextListener
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		System.setProperty("rootPath", context.getRealPath("/"));
	}

	/**
	 * This method is invoked when the application context is destroyed. Here
	 * all DB connections are closed and Drivers get unregistered.
	 * 
	 * @param event
	 *            javax.servlet.ServletContextEvent
	 * @see javax.servlet.ServletContextListener
	 */
	public void contextDestroyed(ServletContextEvent event) {
		Logger LOG = Logger.getLogger(ApplicationContextListener.class);
		ConnectionPool.getInstance().closeAllConnections();

		/* manually deregistering MySQL JDBC drivers to prevent Tomcat warnings */
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Enumeration<Driver> drivers = DriverManager.getDrivers();

		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			if (driver.getClass().getClassLoader() == cl) {
				try {
					LOG.info("Deregistering JDBC driver: " + driver);
					DriverManager.deregisterDriver(driver);
				} catch (SQLException ex) {
					LOG.error("Error deregistering JDBC driver " + driver, ex);
				}
			} else {
				LOG.trace("JDBC driver " + driver
						+ "doesn't belong to this ClassLoader");
			}
		}
		/* manually shutting down the CleanupThread to prevent Tomcat warnings */
		try {
			AbandonedConnectionCleanupThread.shutdown();
		} catch (InterruptedException ex) {
			LOG.warn("Problem with Thread Cleanup: " + ex.getMessage());
		}
	}
}
