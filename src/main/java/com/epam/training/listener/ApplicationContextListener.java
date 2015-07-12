package com.epam.training.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.training.connection.ConnectionPool;

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
	 * all DB connections are closed.
	 * 
	 * @param event
	 *            javax.servlet.ServletContextEvent
	 * @see javax.servlet.ServletContextListener
	 */
	public void contextDestroyed(ServletContextEvent event) {
		ConnectionPool.getInstance().closeAllConnections();
	}
}
