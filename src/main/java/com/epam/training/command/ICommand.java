package com.epam.training.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.training.exception.GeneralCommandException;

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

	/**
	 * Method is called by a a Servlet (Controller) in order to process the
	 * request and redirect it to a chosen JSP.
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @throws GeneralCommandException
	 *             If a Command exception of some sort has occurred
	 */
	String execute(HttpServletRequest request) throws GeneralCommandException;
}
