package com.epam.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.command.ICommand;
import com.epam.training.command.factory.CommandFactory;

/**
 * Servlet {@code ControllerServlet} is a Servlet implementation class that
 * controls communication between Command interface and JSPs. It calls the
 * proper Command class and then redirects the request to the proper JSP.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs ControllerServlet object
	 * 
	 * @see javax.servlet.http.HttpServlet
	 */
	public ControllerServlet() {
		super();
	}

	/**
	 * Performs a set of actions when a HTTP GET method is used
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @param response
	 *            javax.servlet.http.HttpServletResponse
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @throws IOException
	 *             When an I/O exception of some sort has occurred
	 * @see javax.servlet.http.HttpServlet
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Performs a set of actions when a HTTP POST method is used
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @param response
	 *            javax.servlet.http.HttpServletResponse
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @throws IOException
	 *             When an I/O exception of some sort has occurred
	 * 
	 * @see javax.servlet.http.HttpServlet
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Processes the request
	 * 
	 * @param request
	 *            javax.servlet.http.HttpServletRequest
	 * @param response
	 *            javax.servlet.http.HttpServletResponse
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @throws IOException
	 *             When an I/O exception of some sort has occurred
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICommand command = CommandFactory.getInstance().getCommand(request);
		String url = command.execute(request);
		/* putting the reconstructed ControllerServlet URL into request */
		request.setAttribute("base", request.getRequestURL().toString());
		/* forwarding the request to a proper JSP */
		request.getRequestDispatcher(url).forward(request, response);
	}
}
