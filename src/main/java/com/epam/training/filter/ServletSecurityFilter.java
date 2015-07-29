package com.epam.training.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.bean.Member;

/**
 * Class {@code ServletSecurityFilter} is a Filter that prohibits access to some
 * of the commands via controller servlet (with parameters inserted into GET
 * request) for Guests and Users (Admins have no limitations) and redirects such
 * requests to the index page.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see javax.servlet.Filter
 */
public class ServletSecurityFilter implements Filter {
	private String indexPath;

	/**
	 * Constructs ServletSecurityFilter object
	 */
	public ServletSecurityFilter() {
	}

	/**
	 * This method is invoked during Filter initialization. Here the index path
	 * parameter (value is stored as an init parameter) is set.
	 * 
	 * @param fConfig
	 *            javax.servlet.FilterConfig
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @see javax.servlet.Filter
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter("indexPath");
	}

	/**
	 * Here the access to some of the commands via controller servlet (with
	 * parameters inserted into GET request) for Guests and Users (Admins have
	 * no limitations) is prohibited. Such requests are redirected to the index
	 * (home) page.
	 * 
	 * 
	 * @param request
	 *            javax.servlet.ServletRequest
	 * @param response
	 *            javax.servlet.ServletResponse
	 * @param chain
	 *            javax.servlet.FilterChain
	 * @throws ServletException
	 *             When a Servlet exception of some sort has occurred
	 * @throws IOException
	 *             When an I/O exception of some sort has occurred
	 * @see javax.servlet.Filter
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		ClientType clientType = obtainClientType(httpRequest);
		String action = request.getParameter("action");

		if (badClientRequest(httpRequest, clientType, action)) {
			httpRequest.getSession().invalidate();
			httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * This method is invoked during Filter destruction.
	 * 
	 * @see javax.servlet.Filter
	 */
	public void destroy() {
	}

	/* method returns the type (role) of current Client */
	private ClientType obtainClientType(HttpServletRequest request) {
		ClientType clientType;
		Member member = (Member) request.getSession().getAttribute("member");

		if (member != null) {
			if (member.isAdmin()) {
				clientType = ClientType.ADMIN;
			} else {
				clientType = ClientType.USER;
			}
		} else {
			clientType = ClientType.GUEST;
		}
		return clientType;
	}

	/* method identifies unacceptable Clients requests to controller */
	private boolean badClientRequest(HttpServletRequest req, ClientType type,
			String action) throws ServletException, IOException {
		boolean badRequest = false;
		Set<String> userCommands = ClientCommandType.getUserCommands();
		Set<String> guestCommands = ClientCommandType.getGuestCommands();

		switch (type) {
		case GUEST:
			if (action != null && !guestCommands.contains(action)) {
				badRequest = true;
			}
			break;
		case USER:
			if (action != null && !userCommands.contains(action)) {
				badRequest = true;
			}
			break;
		case ADMIN:
			break;
		default:
			throw new EnumConstantNotPresentException(ClientType.class,
					type.name());
		}
		return badRequest;
	}
}
