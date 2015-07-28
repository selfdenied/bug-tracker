package com.epam.training.command;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface {@code ICommand} is an interface that will be implemented by all
 * Command classes that process data received from JSPs. It contains just one
 * method that will be invoked by a Servlet (Controller) in order to process the
 * request and redirect it to a chosen JSP.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public interface ICommand {
	/* needed to get JSP URLs from a properties file */
	ResourceBundle BUNDLE = ResourceBundle.getBundle("jspURLs");
	String ERROR = "error500";

	/**
	 * Method is called by a a Servlet (Controller) in order to process the
	 * request and redirect it to a chosen JSP.
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @return The URL of the JSP to which the request is forwarded.
	 */
	String execute(HttpServletRequest request);
}
